(ns aoc-2019.1
  (:gen-class
   :import [input1]))

(defn fuel-requirement [n] (- (Math/floor (/ n 3)) 2))

;; Examples
(fuel-requirement 1969)
(fuel-requirement 100756) ;; 33583.0

;; Part 1

(println "Day 1")
(println
 "Part 1 :"
 (reduce + (map fuel-requirement input))) ;; 3369286

;; Part 2

(defn correct-fuel-requirement [n]
  (reduce +
          (loop [current-need [(fuel-requirement n)]]
            (if (<= (first current-need) 0)
              (rest current-need)
              (recur (cons (fuel-requirement (first current-need)) current-need))))))

(correct-fuel-requirement 100756) ;; 50346
(println
 "Part 2 :"
 (reduce + (map correct-fuel-requirement input))) ;; 5051054
