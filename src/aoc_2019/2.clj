(ns aoc-2019.2
  (:gen-class))


(use 'clojure.test)

(defn computer [op a b c program]
  (if (== op 99)
    program
    (let [ops {1 +
               2 * }]
      (assoc program c ((get ops op) (nth program a) (nth program b))))))

(defn computer-loop
  ([program] (computer-loop 0 program (nth program 1) (nth program 2)))
  ([program noun verb] (computer-loop 0 (assoc program 1 noun 2 verb)))
  ([count program]
   (let [op (nth program (* count 4)) ]
     (if (== op 99)
       program
       (let [a (nth program (+ 1 (* count 4)))
             b (nth program (+ 2 (* count 4)))
             c (nth program (+ 3 (* count 4)))
             program (computer op a b c program)]
         (recur (inc count) program))))))

(deftest examples-part-1
  (is (= (computer-loop input-test1) [2,0,0,0,99]))
  (is (= (computer-loop input-test2) [2,3,0,6,99]))
  (is (= (computer-loop input-test3) [2,4,4,5,99,9801]))
  (is (= (computer-loop input-test4) [30,1,1,4,2,5,6,0,99])))

(println "Day 2")
(let [noun 12 verb 2]
  (println "Part 1 ", (first (computer-loop input noun verb))))
;; 10566835


(let [nouns (range 0 99)
      verbs (range 0 99)
      key 19690720]
  (filter nouns
          (fn [noun])))

(defn find-key
  ([key] (find-key key 99 99 0 0))
  ([key max-noun max-verb current-noun current-verb]
   (if (= key (first (computer-loop input current-noun current-verb)))
     [current-noun current-verb]
     (if (= current-verb max-verb)
       (recur key max-noun max-verb (inc current-noun) 0)
       (recur key max-noun max-verb current-noun (inc current-verb))))))


(let [keys (find-key 19690720)]
  (println "Part 2 ", (+ (* 100 (nth keys 0)) (nth keys 1))))
;; 2347
