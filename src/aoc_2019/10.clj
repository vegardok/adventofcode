(ns aoc-2019.10
  (:require [clojure.string :as string ]
            [aoc-2019.inputs :as inputs]) )

(defn- angle [a b]
  (let [[ a-y a-x] a
        [ b-y b-x] b
        delta-x (- a-x b-x)
        delta-y (- a-y b-y)]
    (mod (+ (/ (* (Math/atan2 delta-x delta-y) 180) Math/PI) -90) 360)))


(defn- get-astroid-coords [input]
  (let [lines (string/split input #"\n")]
    (apply concat (map-indexed (fn [i line] (keep-indexed #(if (= %2 \#) [%1 i]) line)) lines))))

(defn in-line-of-sight
  ([from astroid-list] (in-line-of-sight from astroid-list #{}))
  ([from astroid-list found]
   (let [[astroid & remaining-astroid-list] astroid-list
         a (angle from astroid)
         found (if (= from astroid) found (conj found a))]
     (if (empty? remaining-astroid-list)
       found
       (recur from remaining-astroid-list found)))))

(defn part1 [dataset]
  (let [astroids (get-astroid-coords dataset )
        in-sight (map (fn [from] [from (count (in-line-of-sight from astroids)) ]) astroids)]
    (reduce #(if (>= (second %1) (second %2)) %1 %2) in-sight)))

(defn dist [a b]
  (let [[ax ay] a
        [bx by] b]
    (Math/sqrt (+ (Math/pow (- ax bx) 2) (Math/pow (- ay by) 2)))))

(defn astroid-by-angle
  ([from astroid-list] (astroid-by-angle from astroid-list {}))
  ([from astroid-list found]
   (let [[astroid & remaining-astroid-list] astroid-list
         a (angle from astroid)
         on-angle (sort-by #(dist from %) (conj (get found a []) astroid))
         found (if (= from astroid) found (assoc found a on-angle))]
     (if (empty? remaining-astroid-list)
       found
       (recur from remaining-astroid-list found)))))


(defn lazer- [astroids current-angle]
  (let [angles (sort (keys astroids))
        next-angle (nth angles (mod (inc (.indexOf angles current-angle)) (count angles)))
        new-astroids (into {} (remove #(empty? (second %)) (update astroids current-angle rest)))]
    [next-angle new-astroids]))

(defn lazer
  ([astroids n] (lazer astroids n (first (sort (keys astroids)))))
  ([astroids n current-angle]
   (if (= n 0)
     (first (get astroids current-angle))
     (let [ [new-angle new-astroids] (lazer- astroids current-angle)]
       (lazer new-astroids (dec n) new-angle )))))

(defn part2 [dataset]
  (let [astroids (get-astroid-coords dataset)
        in-sight (map (fn [from] [from (count (in-line-of-sight from astroids)) ]) astroids)
        station (first (reduce #(if (>= (second %1) (second %2)) %1 %2) in-sight))
        astroids (astroid-by-angle station (get-astroid-coords dataset))
        angles (sort (keys astroids))
        [x y] (lazer astroids 199)
        ]
    (+ (* 100 x) y)))


(defn print-result []
  (time
   (do
     (println "Day 10")
     (println "Part 1 " (part1 inputs/day10-input))
     (println "Part 2" (part2 inputs/day10-input))
     (println))))

;; (print-result)
