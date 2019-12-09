(ns aoc-2019.2
  (:require [clojure.test :as test]
            [aoc-2019.computer :as c]
            [aoc-2019.inputs :as in]))

(let [nouns (range 0 99)
      verbs (range 0 99)
      key 19690720]
  (filter nouns
          (fn [noun])))

(defn find-key
  ([key] (find-key key 99 99 0 0))
  ([key max-noun max-verb current-noun current-verb]
   (if (= key (first (c/computer-loop in/input-day2 current-noun current-verb)))
     [current-noun current-verb]
     (if (= current-verb max-verb)
       (recur key max-noun max-verb (inc current-noun) 0)
       (recur key max-noun max-verb current-noun (inc current-verb))))))

(time
 (let [noun 12
       verb 2
       keys (find-key 19690720)]
   (println "Day 2")
   (println "Part 1 ", (first (c/computer-loop in/input-day2 noun verb))) ;; 10566835
   (println "Part 2 ", (+ (* 100 (nth keys 0)) (nth keys 1))) ;; 2347
   ))
