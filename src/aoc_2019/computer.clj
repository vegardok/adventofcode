(ns aoc-2019.computer
  (:require [clojure.test :as test]
            [clojure.string :as str]
            [aoc-2019.inputs :as inputs]))

(defn- input
  ([pointer program mode] (input pointer program mode 0))
  ([pointer program mode rel-offset]
   (case mode
     :pos-mode (nth program (nth program pointer 0) 0)
     :imm-mode (nth program pointer 0)
     )))

(defn- math-fn [func]
  (fn [a b]
    (fn [pointer program]
      (let [out (input (+ pointer 3) program :imm-mode)]
        {:program (assoc program out (func a b))
         :pointer (+ pointer 4) } ))))

(defn- read-input [in-fn]
  (fn [a b]
    (fn [pointer program]
      (let [out (input (+ pointer 1) program :imm-mode)]
        {:program (assoc program out (in-fn))
         :pointer (+ pointer 2) } ))))

(defn- print-out [out-fn]
  (fn [a b]
    (fn [pointer program]
      (out-fn a)
      { :program program :pointer (+ pointer 2) })))

(defn- exit [out-fn]
  (fn [a b]
    (fn [pointer program]
      (out-fn { :done pointer })
      {:program program :pointer nil })))

(defn- jump [jump-fn]
  (fn [a b]
    (fn [pointer program]
        {:program program
         :pointer (if (jump-fn a) b (+ pointer 3)) })))

(defn- compare-op [comp-fn]
  (fn [a b]
    (fn [pointer program]
      (let [out (input (+ pointer 3) program :imm-mode)]
        {:program (assoc program out (if (comp-fn a b) 1 0))
         :pointer (+ pointer 4) }))))

(defn- set-rel-offset [rel-offset]
  (fn [a b]
    (fn [pointer program]
      {:program program
       :pointer (+ pointer 2)
       :rel-offset (+ rel-offset a)
       })))

(defn parse-op [pointer program in-fn out-fn exit-out-fn rel-offset]
  (let [op (nth program pointer)
        ops
        {
         "01" (math-fn +)
         "02" (math-fn *)
         "03" (read-input in-fn)
         "04" (print-out out-fn)
         "05" (jump #(not= 0 %))
         "06" (jump #(= 0 %))
         "07" (compare-op #(< % %2))
         "08" (compare-op #(= % %2))
         "09" (set-rel-offset rel-offset)
         "99" (exit exit-out-fn)
         }
        str-op (format "%05d" op)
        op (clojure.string/join (drop 3 str-op))
        modes { \0 :pos-mode \1 :imm-mode \2 :rel-mode }
        mode-a (get modes (nth str-op 2))
        mode-b (get modes (nth str-op 1))
        ]
    (let [a (input (+ pointer 1) program mode-a rel-offset)
          b (input (+ pointer 2) program mode-b rel-offset)]
      ((get ops op) a b)
      )))

(defn computer-loop
  ([program]
   (computer-loop
    0
    program
    #(Integer/parseInt (read-line))
    println
    identity
    0))
  ([program in-fn out-fn exit-out-fn]
   (computer-loop 0 program in-fn out-fn exit-out-fn 0))
  ([program in-fn out-fn]
   (computer-loop 0 program in-fn out-fn identity 0))
  ([pointer program in-fn out-fn exit-out-fn rel-offset]
   (let [op (parse-op pointer program
                      in-fn out-fn exit-out-fn rel-offset)
         next-step (op pointer program)
         next-pointer (:pointer next-step )
         next-program (:program next-step )
         next-rel-offset (:rel-offset next-step rel-offset)]
     (if (not next-pointer)
       program
       (recur next-pointer next-program in-fn out-fn exit-out-fn next-rel-offset)))))

(test/deftest examples-part-1
  (test/is (= (computer-loop [1,0,0,0,99]) [2,0,0,0,99]))
  (test/is (= (computer-loop [2,3,0,3,99]) [2,3,0,6,99]))
  (test/is (= (computer-loop [2,4,4,5,99,0]) [2,4,4,5,99,9801]))
  (test/is (= (computer-loop [1,1,1,4,99,5,6,0,99]) [30,1,1,4,2,5,6,0,99]))
  (test/is (= (computer-loop [1002,4,3,4,33]) [1002,4,3,4,99])))
