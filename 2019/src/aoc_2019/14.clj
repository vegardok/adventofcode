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
        inputs (into {} (->> (split from #", ")
                             (map parse-item)
                             (map (fn [t] [(first t) (second t)]))
                             ))]
    { output-chem { :makes makes
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
            b-reqs (cset/difference (reqs b) (reqs a))
            a-sum (reduce + (map #(chem-comperator % b) a-reqs))
            b-sum (reduce + (map #(chem-comperator % a) b-reqs))
            ]

        (if (< a-sum b-sum)
          -1
          (if (< b-sum a-sum)
            1
            0))
        ;; (if (> (count a-reqs) (count b-reqs))
        ;;   -1
        ;;   (if (< (count a-reqs) (count b-reqs))
        ;;     1
        ;;     0))
        )
      )))

(def chem-comperator (memoize chem-comperator))


(defn cost
  ([[chem n]]
   (cost chem n))
  ([chem n]
   (cost chem n {}))
  ([chem n overflow]
   (let [{requirements :requirements
          makes :makes } (get inputs chem
                              {:makes 1
                               :requirements { "ORE" 1 }})
         o (+
            (get overflow chem 0)
            (/ (mod n makes) makes))
         take (* (- makes (Math/floor o)) (Math/ceil (/ n makes)))

         needed (into {}
                      (map
                       (fn [[k v]]
                         {k (Math/round (* take (/ v makes)))})
                       requirements))
         arne (- o (Math/floor o))
         overflow (into {} (filter #(< 0 (second %)) (assoc overflow chem arne )))
         ]
     [(vec needed) overflow]
     )))


(cost "B" 14)
(cost "A" 3)
(cost "A" 3 {"A" 1/2 })


(test/deftest handle-overflow-output
  (test/is (= [[["ORE" 9.0]] {}] (cost "A" 2)))
  (test/is (= [[["ORE" 18.0]] {}] (cost "A" 4)))
  (test/is (= [[["ORE" 27.0]] {}] (cost "A" 6)))

  (test/is (= [[["ORE" 9.0]] {"A" {"ORE" 9/2}}] (cost "A" 1)))
  (test/is (= [[["ORE" 18.0]] {"A" {"ORE" 9/2}}] (cost "A" 3)))
  (test/is (= [[["ORE" 27.0]] {"A" {"ORE" 9/2}}] (cost "A" 5))))

(test/deftest handle-overflow-usage
  (test/is (= [[["ORE" 8.0]] {}] (cost "A" 2 { "A" { "ORE" 1 }})))
  (test/is (= [[["ORE" 0. ]] {}] (cost "A" 2 { "A" { "ORE" 9 }})))
  (test/is (= [[["ORE" 4.5 ]] {"A" { "ORE" 9/2}}] (cost "A" 1 { "A" { "ORE" 4.5 }}))) ;; hmm
  (test/is (= [[["ORE" 17.0]] {}] (cost "A" 4 { "A" { "ORE" 1 }})))
  (test/is (= [[["ORE" 26.0]] {}] (cost "A" 6 { "A" { "ORE" 1 }}))))

(defn merge-chems [list]
  (->> list
       (map (fn [[chem n]] { chem n }))
       (apply merge-with +)
       vec
       (sort (fn [[a] [b]] (chem-comperator a b)))
       ))

(defn ore-cost [chem n]
  (loop [costs [[chem n]]
         overflow {}]

    (println)
    (println overflow)
    (println costs)

    (if (= #{"ORE"} (->> costs (map first) set))
      (-> costs first second)
      (let [[[chem n] & rest] costs
            [expanded new-overflow] (cost chem n overflow)
            new-costs (into (vec rest) expanded )]
        (recur (merge-chems new-costs) new-overflow)))))

;; not 733336
;; not 721888
;; not 733108
;; not 733106
;; not 656045
(println)

(println (float (ore-cost "FUEL" 1)))
