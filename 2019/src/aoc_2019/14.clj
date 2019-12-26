(ns aoc-2019.14
  (:require [aoc-2019.inputs :as inputs]
            [aoc-2019.computer :as c]
            [clojure.set :as cset]
            [clojure.string :as string :refer [split]]))



(defn parse-item [item]
  (let [[n chem] (string/split item #" " )]
    [chem (Integer/parseInt n)]))

(defn parse-line [line]
  (let [[from to] (split line #" => ")
        inputs (into {} (->> (split from #", ")
                             (map parse-item)))
        [makes output-chem] (split to #" " )]
    { output-chem { :makes (Integer/parseInt makes)
                       :requirements inputs}}))

(defn parse [input]
  (into {} (map parse-line (split input #"\n"))))

(def inputs (parse inputs/day14-input-test-1))


(defn is-dep-of [a b]
  (if (not (nil? (get-in inputs [a :requirements b])))
    true
    (reduce #(or %1 %2) false
            (map #(is-dep-of % b)
                 (keys (get-in inputs [a :requirements]))))))

(defn reqs [chem]
  (-> inputs
      (get-in [chem :requirements])
      keys
      set))

(defn chem-comperator [a b]
  (if (is-dep-of a b)
    -1
    (if (is-dep-of b a)
      1
      (let [a-reqs (cset/difference (reqs a) (reqs b))
            b-reqs (cset/difference (reqs b) (reqs a))]
        (if (> (count a-reqs) (count b-reqs))
          -1
          (if (< (count a-reqs) (count b-reqs))
            1
            0))))))

(def chem-comperator (memoize chem-comperator))

(defn cost
  ([[chem n]]
   (cost chem n))
  ([chem n]
   (let [{requirements :requirements
          makes :makes } (get inputs chem { :makes 1 :requirements { "ORE" 1 }})
         take-n (Math/ceil (/ n makes))]
     ;; {:chem chem :makes makes :reqs requirements}
     (map (fn [[chem n]] [chem (* n take-n)]) requirements))))


(defn cost
  ([[chem n]]
   (cost chem n {}))
  ([chem n]
   (cost chem n {}))
  ([chem n overflow]

   (let [{requirements :requirements
          makes :makes } (get inputs chem { :MAKES 1 :requirements { "ORE" 1 }})
         take-n (Math/ceil (/ n makes))
         foo (* (mod n makes) (/ 1 makes))
         costs (map (fn [[c n]]
                      [c (- (* n take-n) (get-in overflow [chem c] 0))]) requirements)
         ]
     [costs
      (assoc overflow chem
             (into
              {}
              (map
               (fn [[c n]]
                 [c (mod (- n (* n foo)) n)]) costs)))
      ]
     )))

(defn merge-chems [list]
  (->> list
       (map (fn [[chem n]] { chem n }))
       (apply merge-with +)
       vec
       (sort (fn [[a] [b]] (chem-comperator a b)))
       ))

(defn ore-cost [chem n]
  (loop [costs [[chem n]]]
    (println costs)
    (if (= #{"ORE"} (->> costs (map first) set))
      (-> costs first second)
      (let [[[chem n] & rest] costs
            new-costs (into (vec rest) (cost chem n))]
        ;; (println new-costs)
        (recur (merge-chems new-costs))))))

;; not 733336
;; not 721888
;; not 733108
(println)
(ore-cost "FUEL" 1)
