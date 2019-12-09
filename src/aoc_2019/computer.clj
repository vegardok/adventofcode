(ns aoc-2019.computer
  (:require [clojure.test :as test]))

(defn computer [op a b c program]
  (if (== op 99)
    program
    (let [ops {1 +
               2 * }]
      (assoc program c ((get ops op) (nth program a) (nth program b))))))

(defn computer-loop
  ([program] (computer-loop 0 program))
  ([program noun verb] (computer-loop 0 (assoc program 1 noun 2 verb)))
  ([count program]
   (let [op (nth program (* count 4)) ]
     (if (== op 99)
       program
       (let [a (nth program (+ 1 (* count 4)))
             b (nth program (+ 2 (* count 4)))
             c (nth program (+ 3 (* count 4)))
             program (computer op a b c program)]
         (recur (inc count) program))))))

(def input-test1 [1,0,0,0,99]) ;;becomes 2,0,0,0,99 (1 + 1 = 2).
(def input-test2 [2,3,0,3,99]) ;;becomes 2,3,0,6,99 (3 * 2 = 6).
(def input-test3 [2,4,4,5,99,0]) ;;becomes 2,4,4,5,99,9801 (99 * 99 = 9801).
(def input-test4 [1,1,1,4,99,5,6,0,99]) ;;becomes 30,1,1,4,2,5,6,0,99.


(test/deftest examples-part-1
  (test/is (= (computer-loop input-test1) [2,0,0,0,99]))
  (test/is (= (computer-loop input-test2) [2,3,0,6,99]))
  (test/is (= (computer-loop input-test3) [2,4,4,5,99,9801]))
  (test/is (= (computer-loop input-test4) [30,1,1,4,2,5,6,0,99])))
