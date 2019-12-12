(ns aoc-2019.4)
(use 'clojure.test)

(def input (range 138307 654504))

(defn has-two-consectutive-numbers? [d]
  (some #(not (= 1 %)) (map count (partition-by identity (str d)))))

(deftest has-two-consectutive-numbers?-test
  (is (has-two-consectutive-numbers? 11))
  (is (has-two-consectutive-numbers? 1223))
  (is (has-two-consectutive-numbers? 1233))
  (is (has-two-consectutive-numbers? 1123))
  (is (has-two-consectutive-numbers? 11122))
  (is (not (has-two-consectutive-numbers? 1)))
  (is (not (has-two-consectutive-numbers? 12)))
  (is (not (has-two-consectutive-numbers? 2121))))

(defn increasing-digits? [n]
  (let [digits (seq (str n))]
    (= digits (sort digits))))

(deftest increasing-digits?-test
  (is (increasing-digits? 123))
  (is (not (increasing-digits? 121))))

(defn has-strict-two-consectutive-numbers? [d]
  (some #(= 2 %) (map count (partition-by identity (str d)))))

(deftest has-strict-two-consectutive-numbers?-test
  (is (has-strict-two-consectutive-numbers? 11))
  (is (has-strict-two-consectutive-numbers? 1223))
  (is (has-strict-two-consectutive-numbers? 1233))
  (is (has-strict-two-consectutive-numbers? 1123))
  (is (not (has-strict-two-consectutive-numbers? 1)))
  (is (not (has-strict-two-consectutive-numbers? 12)))
  (is (not (has-strict-two-consectutive-numbers? 1243)))
  (is (not (has-strict-two-consectutive-numbers? 1323)))
  (is (not (has-strict-two-consectutive-numbers? 1222))))

(defn day4-print-result []
  (time
   (let [inc-digits (filter increasing-digits? input)
         result-part-1 (filter has-two-consectutive-numbers? inc-digits)
         result-part-2 (filter has-strict-two-consectutive-numbers? inc-digits)]
     (println "Day 4")
     (println "Part 1" (count result-part-1))
     (println "Part 1" (count result-part-2))
     (println)))
