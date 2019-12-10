(ns aoc-2019.7
  (:require [clojure.test :as test]
            [clojure.math.combinatorics :as combo]
            [aoc-2019.computer :as comp]
            [aoc-2019.inputs :as input]))

(def amp-test-software-1
  [3 15 3 16 1002 16 10 16 1 16 15 15 4 15 99 0 0])
(def amp-test-software-2
  [3 23 3 24 1002 24 10 24 1002 23 -1 23
   101 5 23 23 1 24 23 23 4 23 99 0 0])
(def amp-test-software-3
  [3 31 3 32 1002 32 10 32 1001 31 -2 31 1007 31 0 33
   1002 33 7 33 1 33 31 31 1 32 31 31 4 31 99 0 0 0])
(def amp-test-software-4
  [3 26 1001 26 -4 26 3 27 1002 27 2 27 1 27 26
   27 4 27 1001 28 -1 28 1005 28 6 99 0 0 5])
(def amp-test-software-5
  [3 52 1001 52 -5 52 3 53 1 52 56 54 1007 54 5 55 1005 55 26 1001 54
   -5 54 1105 1 12 1 53 54 53 1008 54 0 55 1001 55 1 55 2 53 55 53 4
   53 1001 56 -1 56 1005 56 6 99 0 0 0 0 10])


(defn gen-in-fn [inputs]
  (let [inputs (atom inputs)]
    (fn []
      (let [[val] (deref inputs)]
        (swap! inputs rest)
        val))))

(defn gen-out-fn []
  (let [a (atom nil)]
    (fn
      ([] (deref a))
      ([v] (swap! a (fn [&rest] v))))))

(defn gen-channel
  ([] (gen-channel []))
  ([initial-state]
   (let [state (atom initial-state)]
     (fn
       ([] (let [[out & tail] (deref state)]
             (swap! state rest)
             (str out)))
       ([val] (swap! state #(conj % val)))))))


(defn amp-circuit [a b c d e software]
  (let [a-channel (gen-channel (lazy-seq [a 0]))
        b-channel (gen-channel (lazy-seq [b (a-channel)]))
        c-channel (gen-channel (lazy-seq [c (b-channel)]))
        d-channel (gen-channel (lazy-seq [d (c-channel)]))
        e-channel (gen-channel (lazy-seq [e (d-channel)]))]
    (comp/computer-loop software a-channel a-channel)
    (comp/computer-loop software b-channel b-channel)
    (comp/computer-loop software c-channel c-channel)
    (comp/computer-loop software d-channel d-channel)
    (comp/computer-loop software e-channel e-channel)
    (Integer/parseInt (e-channel))))

(test/deftest amp-test-software-test
  (test/is (= 43210 (amp-circuit 4 3 2 1 0 amp-test-software-1)))
  (test/is (= 54321 (amp-circuit 0 1 2 3 4 amp-test-software-2)))
  (test/is (= 65210 (amp-circuit 1 0 4 3 2 amp-test-software-3))))


(defn find-max-thrust [software]
  (let [settings (combo/permutations [0 1 2 3 4])]
    (reduce max (map
     #(let [[a b c d e] %]
        (amp-circuit a b c d e software)) settings))))

(test/deftest find-max-thrust-test
  (test/is (= 43210 (find-max-thrust amp-test-software-1)))
  (test/is (= 54321 (find-max-thrust amp-test-software-2)))
  (test/is (= 65210 (find-max-thrust amp-test-software-3)))
  (test/is (= 22012 (find-max-thrust input/day7-input))))

;; (println "Day 7")
;; (println "Part 1" (find-max-thrust input/day7-input))
