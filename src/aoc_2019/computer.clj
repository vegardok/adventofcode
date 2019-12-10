(ns aoc-2019.computer
  (:require [clojure.test :as test]
            [clojure.string :as str]))

(defn- pos-mode [n program] (nth program (nth program n)))
(defn- imm-mode [n program] (nth program n))

(defn parse-op [op]
  (let [str-op (format "%05d" op)
        op (clojure.string/join (drop 3 str-op))
        modes {\0 pos-mode
               \1 imm-mode }
        mode-a (get modes (nth str-op 2))
        mode-b (get modes (nth str-op 1))
        mode-c (get modes (nth str-op 0))
        io-op (or (= op "03") (= op "04"))
        ops {"01" +
             "02" *
             "03" (fn [a b] (Integer/parseInt (read-line)))
             "04" (fn [a b] (println a) a)}]
    { :fn (get ops op)
     :op op
     :mode-a mode-a
     :mode-b mode-b
     :mode-c mode-c
     :seek (if io-op 2 4)
     :output (if io-op #(+ 1 %) #(+ 3 %))
     }))


(defn computer [pointer program]
  (let [op (parse-op (nth program pointer))
        op-fn (get op :fn)
        a ((get op :mode-a) (+ 1 pointer) program)
        b (try
            ((get op :mode-b) (+ 2 pointer) program)
            (catch Exception e nil))
        output-adress (get program ((get op :output) pointer))
        result (op-fn a b)]
    (assoc program output-adress result)))

(defn computer-loop
  ([program] (computer-loop 0 program))
  ([program noun verb] (computer-loop 0 (assoc program 1 noun 2 verb)))
  ([pointer program]
   (let [parsed-op (parse-op (nth program pointer))
         op (get parsed-op :op)
         new-pointer (+ pointer (get parsed-op :seek))]
     (if (= op "99")
       program
       (let [program (computer pointer program)]
         (recur new-pointer program))))))

(def input-test1 [1,0,0,0,99])
(def input-test2 [2,3,0,3,99])
(def input-test3 [2,4,4,5,99,0])
(def input-test4 [1,1,1,4,99,5,6,0,99])
(def input-test5 [3,0,4,0,99])
(def input-test6 [1002,4,3,4,33])


(test/deftest examples-part-1
  (test/is (= (computer-loop input-test1) [2,0,0,0,99]))
  (test/is (= (computer-loop input-test2) [2,3,0,6,99]))
  (test/is (= (computer-loop input-test3) [2,4,4,5,99,9801]))
  (test/is (= (computer-loop input-test4) [30,1,1,4,2,5,6,0,99]))
  (test/is (= (computer-loop input-test6) [1002,4,3,4,99])))
