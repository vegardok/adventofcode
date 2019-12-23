(ns aoc-2019.16
  (:require [aoc-2019.inputs :as inputs]
            [clojure.set :as cset]
            [clojure.string :as string]
            [clojure.test :as test]))


(def input-numbers (map #(Integer/parseInt (str %)) inputs/day16-input))

(defn rotate
  [n coll]
  (let [c (count coll)]
    (take c (drop (mod n c) (cycle coll)))))


(defn last-digit-of [n]
  (Integer/parseInt (str (last (str n)))))

(defn base-pattern [n]
  (let [base (rotate 1 (flatten (map #(repeat n %) '(0, 1, 0, -1))))
        n (Math/ceil (/ 650 (count base)))]
    (vec (flatten (repeat n base)))))


(defn phase [numbers]
  (let [numbers (for [n (range (count numbers))] [n (nth numbers n)])]
    (for [cycle (range 1 (inc (count numbers)))]
      (let [p (base-pattern cycle)]
        (->> numbers
             (map (fn [[i n]] (* n (nth p i))))
             (reduce +)
             last-digit-of)))))


(println
 (string/join
  (take 8
        (let [start (map #(Integer/parseInt (str %)) inputs/day16-input)]
          (reduce (fn [accl i] (print i " ") (phase accl)) start (range 100))))))
