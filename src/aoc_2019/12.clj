(ns aoc-2019.12
  (:require [aoc-2019.inputs :as inputs]
            [clojure.math.combinatorics :as combo]
            [clojure.set :as cset]
            [clojure.string :as string]))

(defn gen-empty-velocity [positions]
  (vec (for [_ (range (count positions))] { :x 0 :y 0 :z 0})))

(defn calc-velocity [pos1 pos2 vel dim]
  (+ (dim vel) (if (= (dim pos1 ) (dim pos2 )) 0 (if (< (dim pos1 ) (dim pos2 )) 1 -1))))

(defn calc-position [pos vel dim]
  (+ (dim pos) (dim vel)))

(def pairs (filter #(not=(first %) (second %))
                      (combo/selections (range 4) 2)))

(defn update-velocity
  ([pos vel] (update-velocity pos vel pairs))
  ([pos vel pairs]
   (if (empty? pairs)
     vel
     (let [[[from to] & remaining-pairs] pairs
           x (calc-velocity (nth pos from) (nth pos to) (nth vel from) :x)
           y (calc-velocity (nth pos from) (nth pos to) (nth vel from) :y)
           z (calc-velocity (nth pos from) (nth pos to) (nth vel from) :z)

           new-velocity {:x x :y y :z z }
           new-velcities (assoc vel from new-velocity)]
       (recur pos new-velcities remaining-pairs)))))


(defn step
  ([steps positions] (step steps positions (gen-empty-velocity positions)))
  ([steps positions velocities]
   (let [new-velocities (update-velocity positions velocities)
         new-positions (map-indexed (fn [index pos]
                                      {:x (calc-position pos (nth new-velocities index) :x)
                                       :y (calc-position pos (nth new-velocities index) :y)
                                       :z (calc-position pos (nth new-velocities index) :z)
                                       }
                                      ) positions)]
     (if (= steps 1)
       {:positions new-positions :velocities new-velocities }
       (recur (dec steps) new-positions new-velocities)
       ))))

(defn find-dup-step
  ([positions] (find-dup-step positions (gen-empty-velocity positions) 0 #{}))
  ([positions velocities steps seen-states]
   (println (first positions) ;; (first velocities)
            )
   (let [new-velocities (update-velocity positions velocities)
         new-positions (map-indexed (fn [index pos]
                                      (calc-position pos (nth new-velocities index) :x)
                                      ) positions)
         state [new-positions new-velocities]]
     (if (= (mod steps 100000) 0) (print "."))
     (if (= (mod steps 1000000) 0) (println))
     (if (contains? seen-states state)
       steps
       (recur new-positions new-velocities (inc steps) (conj seen-states state))
       ))))


;; (defn find-dup-step
;;   ([positions] (find-dup-step 0 positions (gen-empty-velocity positions) #{}))
;;   ([steps positions velocities seen-pos]
;;    (let [new-velocities (update-velocity positions velocities)
;;          new-positions (map-indexed (fn [index pos]
;;                                       {:x (calc-position pos (nth new-velocities index) :x)
;;                                        :y (calc-position pos (nth new-velocities index) :y)
;;                                        :z (calc-position pos (nth new-velocities index) :z)
;;                                        }) positions)
;;          pairs (map (fn [%1 %2] (string/join \, [ (vals %1) (vals %2)])) new-positions new-velocities]

;;      (if (not (empty? (cset/intersection seen-pos

;;      (if (and (reduce #(or %1 %2) (for [pos new-positions] (contains? seen-pos pos)))
;;               (reduce #(or %1 %2) (for [vel new-velocities] (contains? seen-vel vel))))
;;        steps
;;        (recur
;;         (inc steps)
;;         new-positions
;;         new-velocities
;;         (cset/union seen-pos (set new-positions))
;;         (cset/union seen-vel (set new-positions)))))))

(defn energy [positions velocities]
  (reduce +
          (map * (for [p positions] (reduce + (map #(Math/abs %) (vals p))))
               (for [c velocities] (reduce + (map #(Math/abs %) (vals c)))))))

(defn print-result []
  (time
   (let [{ velocities :velocities positions :positions } (step 1000 inputs/day12-input)]
     (println "Day 12")
     (println "Part 1 " (energy positions velocities))
     (println "Part 2 " (find-dup-step inputs/day12-test-input))
     )))

;; (print-result)
