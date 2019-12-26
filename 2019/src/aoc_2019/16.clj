(ns aoc-2019.16
  (:require [aoc-2019.inputs :as inputs]
            [clojure.string :as string]))

(defn parse [s] (map #(Integer/parseInt (str %)) s))

(defn rotate
  [n coll]
  (let [c (count coll)]
    (take c (drop (mod n c) (cycle coll)))))


(defn last-digit-of [n]
  (Integer/parseInt (str (last (str n)))))

(defn base-pattern [n]
  (cycle (rotate 1 (flatten (map #(repeat n %) '(0, 1, 0, -1))))))

(defn base-pattern [n]
  (rotate 1 (flatten (map #(repeat n %) '(0, 1, 0, -1)))))

(def base-pattern (memoize base-pattern))


(defn get-actual-numbers
  "Remove half of the numbers and negate half of the remaining"
  [numbers depth repeated]
  (let [keep (base-pattern depth)]
    (keep-indexed #(if (not= 0 (nth keep (mod %1 (count keep)) ))
                     (* %2 (nth keep (mod %1 (count keep)))))
                  numbers)))

(last-digit-of (reduce + (get-actual-numbers (parse "12345678") 2)))


(defn phase [numbers]
  (for [cycle (range 1 (inc (count numbers)))]
    (->> numbers
         (map * (base-pattern cycle))
         (reduce +)
         last-digit-of)))





(for [n (range 1 4)]
  (phase (parse (string/join (repeat n "12345678")))))

(defn full-phase [numbers repeated]
  (for [cycle (range 1 (inc (* repeated (count numbers))))]
    (let [sollutions (for [i (range 1 (inc (* 4 cycle)))]
                       (->> (flatten (repeat i numbers))
                            (get-actual-numbers cycle)
                            (reduce +)
                            last-digit-of))]
      (nth sollutions (mod (dec cycle) (count sollutions))))))
(full-phase (parse (string/join (shuffle (range 1 9)))) 3)



;; (time
;;  (println (string/join (take 8 (full-phase (parse inputs/day16-input) 1)))))



(defn part1 []
  (time
   (println
    (string/join
     (take 8
           (let [start (parse inputs/day16-input)]
             (reduce (fn [accl i] (if (= 0 (mod i 10))(print i " " ))(phase accl)) start (range 100))))))))



;; (part1)
