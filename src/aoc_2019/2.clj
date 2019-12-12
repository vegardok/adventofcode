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
  ([key] (find-key key 99 99 0 0)) ;; (assoc program 1 noun 2 verb)
  ([key max-noun max-verb current-noun current-verb]
   (if (= key (first (c/computer-loop (assoc in/input-day2 1 current-noun 2 current-verb))))
     [current-noun current-verb]
     (if (= current-verb max-verb)
       (recur key max-noun max-verb (inc current-noun) 0)
       (recur key max-noun max-verb current-noun (inc current-verb))))))

(defn day2-print-result []
  (time
   (let [noun 12
         verb 2
         keys (find-key 19690720)]
     (println "Day 2")
     ;; 10566835
     (println "Part 1 ", (first (c/computer-loop (assoc in/input-day2 1 noun 2 verb))))
     (println "Part 2 ", (+ (* 100 (nth keys 0)) (nth keys 1))) ;; 2347
     )))
