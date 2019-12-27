(ns aoc-2019.14
  (:require [aoc-2019.inputs :as inputs]
            [aoc-2019.computer :as c]
            [clojure.set :as cset]
            [clojure.test :as test]
            [clojure.string :as string :refer [split]]))

(defn parse-item [item]
  (let [[n chem] (string/split item #" " )]
    [chem (Integer/parseInt n)]))

(defn parse-line [line]
  (let [[from to] (split line #" => ")
        [makes output-chem] (split to #" " )
        makes (Integer/parseInt makes)
        inputs (->> (split from #", ")
                             (map parse-item)
                             (map (fn [t] [(first t) (second t)]))
                             )]
    { output-chem { :makes makes
                       :requirements inputs}}))

(defn parse [input]
  (into {} (map parse-line (split input #"\n"))))


(def inputs (parse inputs/day14-input))

(defn ore-cost
  ([chem n] (ore-cost chem n {}))
  ([chem n overflow]
   (if (= chem "ORE")
     [n overflow]
     (let [{makes :makes
            requirements :requirements } (get inputs chem)]
       (loop [overflow overflow
              sum 0
              [[chem-name chem-n] & rest-chems] requirements]
         (let [take (Math/ceil (/ 1 (/ makes n)))
               overflow-n (if (not= 0 (mod n makes)) (/ (- makes (mod n makes)) makes) 0)
               old-overflow (get overflow chem 0)
               use-overflow (<= 1 (+ overflow-n old-overflow))
               new-overflow (+ old-overflow overflow-n)
               new-overflow (if use-overflow (- new-overflow 1) new-overflow)
               take (- take
                       (if use-overflow 1 0))
               [cost overflow] (if (< 0 take)
                                 (ore-cost
                                  chem-name
                                  (int (* take chem-n))
                                  overflow)
                                 [0 overflow])]
          (if (empty? rest-chems)
            [(+ cost sum) (into {} (filter (fn [[k v]] (< 0 v))
                                           (assoc overflow chem new-overflow))) ]
            (recur overflow (+ sum cost) rest-chems))))))))

(println (first (ore-cost "FUEL" 1 {})))
