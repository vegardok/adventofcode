(ns aoc-2019.1
  (:require [aoc-2019.inputs :as in]))

(defn fuel-requirement [n] (- (Math/floor (/ n 3)) 2))

;; Examples
(fuel-requirement 1969)
(fuel-requirement 100756) ;; 33583.0

;; Part 2

(defn correct-fuel-requirement [n]
  (reduce
   +
   (loop [current-need [(fuel-requirement n)]]
     (if (<= (first current-need) 0)
       (rest current-need)
       (recur (cons (fuel-requirement (first current-need)) current-need))))))

(correct-fuel-requirement 100756) ;; 50346


(defn print-result []
  ;; Part 1
  (time
   (let [part1 (reduce + (map fuel-requirement in/input-day1))
         part2 (reduce + (map correct-fuel-requirement in/input-day1))]
     (println "Day 1")
     (println "Part 1 :" part1 ) ;; 33692
     (println "Part 2 :" part2 ) ;; 5051054
     )))

;; (day1-print-result)
