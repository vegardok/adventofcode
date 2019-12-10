(ns aoc-2019.5
  (:require [clojure.test :as test]
            [aoc-2019.computer :as c]
            [aoc-2019.inputs :as input]))

(let []
  (println "Day 5")
  (println "Part 1")
  (c/computer-loop input/day5-input (c/gen-in-fn ["1"])) ;; 1 => 15508323
  (c/computer-loop input/day5-input (c/gen-in-fn ["5"])) ;; 5 => 9006327
  nil)
