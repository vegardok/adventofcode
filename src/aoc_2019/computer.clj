(ns aoc-2019.computer
  (:require [clojure.test :as test]
            [clojure.string :as str]
            [aoc-2019.inputs :as inputs]))

(defn- input [pointer program mode]
  (if (= mode :pos-mode)
    (nth program (nth program pointer))
    (nth program pointer)))

(defn- math-fn [func]
  (fn [a-mode b-mode c-mode]
    (fn [pointer program]
      (let [a (input (+ pointer 1) program a-mode)
            b (input (+ pointer 2) program b-mode)
            out (input (+ pointer 3) program :imm-mode)]
        {:program (assoc program out (func a b))
         :pointer (+ pointer 4) } ))))

(defn gen-in-fn [inputs]
  (let [inputs (atom inputs)]
    (fn []
      (let [[val] (deref inputs)]
        (swap! inputs rest)
        val))))

(defn- read-input [in-fn]
  (fn [a-mode b-mode c-mode]
    (fn [pointer program]
      (let [out (input (+ pointer 1) program :imm-mode)]
        {:program (assoc program out (Integer/parseInt (in-fn)))
         :pointer (+ pointer 2) } ))))

(defn- print-out [a-mode b-mode c-mode]
  (fn [pointer program]
    (let [a (input (+ pointer 1) program a-mode)]
      (println a)
      { :program program :pointer (+ pointer 2) })))

(defn- exit [a-mode b-mode c-mode]
  (fn [pointer program]
    {:program program :pointer nil }))

(defn- jump [jump-fn]
  (fn [a-mode b-mode c-mode]
    (fn [pointer program]
      (let [a (input (+ pointer 1) program a-mode)
            b (input (+ pointer 2) program b-mode)]
        {:program program
         :pointer (if (jump-fn a) b (+ pointer 3)) }))))

(defn- compare-op [comp-fn]
  (fn [a-mode b-mode c-mode]
    (fn [pointer program]
      (let [a (input (+ pointer 1) program a-mode)
            b (input (+ pointer 2) program b-mode)
            out (input (+ pointer 3) program :imm-mode)]
        {:program (assoc program out (if (comp-fn a b) 1 0))
         :pointer (+ pointer 4) }))))

(defn parse-op [op in-fn]
  (let [ops
        {
         "01" (math-fn +)
         "02" (math-fn *)
         "03" (read-input in-fn)
         "04" print-out
         "05" (jump #(not= 0 %))
         "06" (jump #(= 0 %))
         "07" (compare-op #(< % %2))
         "08" (compare-op #(= % %2))
         "99" exit
         }
        str-op (format "%05d" op)
        op (clojure.string/join (drop 3 str-op))
        modes { \0 :pos-mode \1 :imm-mode }
        mode-a (get modes (nth str-op 2))
        mode-b (get modes (nth str-op 1))
        mode-c (get modes (nth str-op 0))
        ]
    ((get ops op) mode-a mode-b mode-c)))

(defn computer-loop
  ([program] (computer-loop 0 program read-line))
  ([program in-fn] (computer-loop 0 program in-fn))
  ([pointer program in-fn]
   (let [op (parse-op (nth program pointer) in-fn)
         next-step (op pointer program)
         next-pointer (get next-step :pointer)
         next-program (get next-step :program)]
     (if (not next-pointer)
       program
       (recur next-pointer next-program in-fn)))))

(test/deftest examples-part-1
  (test/is (= (computer-loop [1,0,0,0,99]) [2,0,0,0,99]))
  (test/is (= (computer-loop [2,3,0,3,99]) [2,3,0,6,99]))
  (test/is (= (computer-loop [2,4,4,5,99,0]) [2,4,4,5,99,9801]))
  (test/is (= (computer-loop [1,1,1,4,99,5,6,0,99]) [30,1,1,4,2,5,6,0,99]))
  (test/is (= (computer-loop [1002,4,3,4,33]) [1002,4,3,4,99])))
