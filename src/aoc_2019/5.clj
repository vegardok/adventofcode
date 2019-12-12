(ns aoc-2019.5
  (:require [clojure.test :as test]
            [aoc-2019.computer :as c]
            [aoc-2019.inputs :as input]))

(defn print-result []
  (let []
    (println "Day 5")
    ;; 1 => 15508323
    (print "Part 1 ")
    (c/computer-loop input/day5-input (fn [] 1) println)
    ;; 5 => 9006327
    (print "Part 2 ")
    (c/computer-loop input/day5-input (fn [] 5) println)))
