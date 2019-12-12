(ns aoc-2019.3
  (:require [aoc-2019.inputs :as inputs]))
(use 'clojure.test
     'clojure.set)

(defn down [location] (update location 0 dec))
(defn up [location] (update location 0 inc))
(defn left [location] (update location 1 dec))
(defn right [location] (update location 1 inc))

(def direction-map
  {:U up :D down :L left :R right })

(deftest up-direction
  (is (= (up [0 0]) [1 0]))
  (is (= (up [-1 0]) [0 0])))

(deftest down-direction
  (is (= (down [0 0]) [-1 0]))
  (is (= (down [1 0]) [0 0])))

(deftest left-direction
  (is (= (left [0 0]) [0 -1]))
  (is (= (left [0 -1]) [0 -2])))

(deftest right-direction
  (is (= (right [0 0]) [0 1]))
  (is (= (right [0 -1]) [0 0])))


(defn step
  ([direction length current-location] (step direction length current-location []))
  ([direction length current-location steps]
   (if (= length 0)
     steps
     (let [next-location ((get direction-map direction) current-location)]
       (recur direction (dec length) next-location (conj steps next-location))))))

(defn walk
  ([directions] (walk directions [0 0] '()))
  ([directions current-location steps]
   (if (empty? directions)
     steps
     (let [next-op (first directions)
           new-steps (step (get next-op :direction) (get next-op :length) current-location)
           new-current-location (last new-steps)]
       (recur (rest directions) new-current-location (concat steps new-steps))))))


(defn intersection-wires [wire1 wire2]
  (let [wire1-positions (set (walk wire1))
        wire2-positions (set (walk wire2))]
    (intersection wire1-positions wire2-positions)))

(defn manhatten-distance
  ([pos] (manhatten-distance [0 0] pos))
  ([pos1 pos2] (+ (+ (Math/abs (get pos1 0)) (Math/abs (get pos2 0)))
                  (+ (Math/abs (get pos1 1)) (Math/abs (get pos2 1))))))

(deftest manhatten-distances
  (is (= (manhatten-distance [3 3]) 6))
  (is (= (manhatten-distance [-3 -3]) 6))
  (is (= (manhatten-distance [-1 -1] [3 3]) 8)))


(defn closest-intersection [wire1 wire2]
  (let [intersections (intersection-wires wire1 wire2)
        distances (map manhatten-distance intersections)]
    (reduce min distances)))


(deftest examples-part1
  (is (= 6 (closest-intersection (get inputs/day3-input-test0 :first) (get inputs/day3-input-test0 :second))))
  (is (= 159 (closest-intersection (get inputs/day3-input-test1 :first) (get inputs/day3-input-test1 :second))))
  (is (= 135 (closest-intersection (get inputs/day3-input-test2 :first) (get inputs/day3-input-test2 :second)))))

(defn closest-intersection-walk [data]
  (let [intersections (intersection-wires (get data :first) (get data :second))
        first-path (walk (get data :first))
        second-path (walk (get data :second))]
    (reduce min (map (fn [pos] (+ 2 (.indexOf first-path pos) (.indexOf second-path pos))) intersections))))

(deftest closest-intersection-walk-test
  (is (= 610 (closest-intersection-walk inputs/day3-input-test3)))
  (is (= 410 (closest-intersection-walk inputs/day3-input-test4))))


(defn day3-print-result []
  (time
   (do
     (println "Day 3")
     (println "Part 1" (closest-intersection (get inputs/day3-input :first) (get inputs/day3-input :second)))
     (println "Part 2", (closest-intersection-walk inputs/day3-input)))))
