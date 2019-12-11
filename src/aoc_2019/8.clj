(ns aoc-2019.8
  (:require [clojure.test :as test]
            [clojure.string :as str]
            [clojure.core.async :refer
             [<! <!! >! >!! alt! alt!! alts! alts!! chan go onto-chan thread]]
            [clojure.math.combinatorics :as combo]
            [aoc-2019.computer :as comp]
            [aoc-2019.inputs :as inputs]))

(def width 25)
(def height 6)

(def image (map #(read-string (str %)) inputs/day8-input))
(def layers (/ (count image) (* width height)))

(count inputs/day8-input)

(defn slice [v from to]
  (apply vector (drop from (take to v))))

(defn layer
  ([image n] (layer image width height n))
  ([image width height n]
   (let [from (* n width height)
         to (+ from (* width height))]
     (slice image from to))))

(let [best-layer (apply min-key (fn [l]
                                  (count (filter #(= 0 %) (layer image l)))) (range layers))
      one-count (count (filter #(= 1 %) (layer image best-layer)))
      two-count (count (filter #(= 2 %) (layer image best-layer)))
      result (* one-count two-count)]
  (println "Day 8")
  (println "Part 1 " result)) ;; 1792

;; 0 black
;; 1 white
;; 2 transparent
(defn merge-pixel [a b]
  (if (= a 2) b a))

(defn merge-layer [layer1 layer2]
  (map merge-pixel layer1 layer2))

;; (merge-layer (layer image 0) (layer image 1))

(defn get-px [n]
  (cond (= 0 n) "■"
        (= 1 n) "□"
        (= 2 n) " " ))

(defn print-image [image width height]
  (str/join "\n" (for [h (range height)]
                   (apply str (map get-px (slice image (* width h) (+ (* width h) width)))))))

(let [img (reduce merge-layer (for [l (range layers)]
                                (layer image width height l)))]
  (println)
  (println "Part 2")
  (println (print-image img width height)))


;; □■■■■■■□□■□□□□■■□□■■□■■□■
;; □■■■■■■■□■□■■■■□■■□■□■■□■
;; □■■■■■■■□■□□□■■□■■■■□□□□■
;; □■■■■■■■□■□■■■■□■■■■□■■□■
;; □■■■■□■■□■□■■■■□■■□■□■■□■
;; □□□□■■□□■■□□□□■■□□■■□■■□■
