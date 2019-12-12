(ns aoc-2019.9
  (:require [clojure.test :as test]
            [aoc-2019.computer :as c]
            [aoc-2019.inputs :as inputs]))

(def test-program-1
  [109 1 204 -1 1001 100 1 100 1008 100 16 101 1006 101 0 99])
(def test-program-2
  [1102,34915192,34915192,7,4,7,99,0])
(def test-program-3
  [104,1125899906842624,99])

(def test-program
  [109, 10, 3, 10, 203, 1, 99])

;; (do (println) (c/computer-loop test-program-1) nil)
;; (do (println) (c/computer-loop test-program-2) nil)
;; (do (println) (c/computer-loop test-program-3) nil)

;; (c/computer-loop test-program)

;; (do (println) (c/computer-loop inputs/day9-input) nil)

(defn print-result []
  (time
   (do
  (println "Day 9")
  (print "Part 1 ")
  (c/computer-loop inputs/day9-input (fn [] 1) println)
  (print "Part 2 ")
  (c/computer-loop inputs/day9-input (fn [] 2) println)
  nil)))
