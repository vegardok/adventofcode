(ns aoc-2019.12
  (:require [aoc-2019.inputs :as inputs]
            [clojure.math.combinatorics :as combo]
            [clojure.set :as cset]
            [clojure.string :as string]
            [aoc-2019.inputs :as input]))

(defn make-moon [position]
  { :position position :velocity { :x 0 :y 0 :z 0 }})

(defn make-moons [input] (vec (map make-moon input)))

(defn gravity [moon1 moon2 dim]
  (let [{ { p1 dim } :position } moon1
        { { p2 dim } :position } moon2]
    (update-in moon1 [:velocity dim]
               #(+ % (if (= p1 p2) 0 (if (> p1 p2) -1 1))))))

(defn velocity [moon dim]
  (let [{{ v dim } :velocity} moon]
    (update-in moon [:position dim]
               #(+ % v))))

(defn moon-energy [moon]
  (* (reduce #(+ %1 (Math/abs %2)) 0 (vals (:position moon)))
     (reduce #(+ %1 (Math/abs %2)) 0 (vals (:velocity moon)))))

(defn energy [moons]
  (->> moons
       (map moon-energy)
       (reduce +)))

(defn step
  ([moons] (-> moons
               (step :x)
               (step :y)
               (step :z)))
  ([moons dim]
   (->>
    (combo/combinations (range (count moons)) 2)
    (reduce (fn [moons [i j] ]
              (-> moons
                  (update i #(gravity % (nth moons j) dim))
                  (update j #(gravity % (nth moons i) dim))))
            moons)
    (map #(velocity % dim))
    (vec))))

(defn step-n
  ([moons n ] (-> moons
                 (step-n n :x)
                 (step-n n :y)
                 (step-n n :z)))
  ([moons n dim]
   (loop [moons moons i 0]
     (if (= i n)
       moons
       (recur (step moons dim) (inc i))))))

(defn part1 []
  (-> inputs/day12-input
      make-moons
      (step-n 1000)
      energy))

(defn find-repeating-location
  ([moons dim] (find-repeating-location moons dim moons))
  ([moons dim find-me]
   (let [looking-for (->> find-me
                          (map :velocity)
                          (map dim)
                          (vec))]
     (loop [moons (step moons dim)
            i 1]
       (if (= (->> moons
                   (map :velocity)
                   (map dim)
                   (vec))
              looking-for)

         (* i 2)
         (recur (step moons dim) (inc i)))))))

(defn gcd [a b]
        (if (zero? b)
          a
          (recur b (mod a b))))

(defn lcm [a b]
  (/ (* a b) (gcd a b)))

(defn part2 []
  (reduce
   #(lcm % %2)
   (list
    (find-repeating-location (make-moons input/day12-input) :x)
    (find-repeating-location (make-moons input/day12-input) :y)
    (find-repeating-location (make-moons input/day12-input) :z))) )


(defn print-results []
  (time
   (do
     (println "Day 12")
     (println "Part 1" (part1))
     (println "Part 1" (part2)))))

;; (print-results)
