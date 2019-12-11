(ns aoc-2019.7
  (:require [clojure.test :as test]
            [clojure.core.async :refer
             [<! <!! >! >!! alt! alt!! alts! alts!! chan go onto-chan thread]]
            [clojure.math.combinatorics :as combo]
            [aoc-2019.computer :as comp]
            [aoc-2019.inputs :as inputs]))

(defn amp-circuit [a b c d e software]
  (let [a-in (chan 2)
        a-out (chan 1)
        b-out (chan 1)
        c-out (chan 1)
        d-out (chan 1)
        e-out (chan)
        error-output-channel (chan)]

    (>!! a-in a)
    (>!! a-in 0)
    (>!! a-out b)
    (>!! b-out c)
    (>!! c-out d)
    (>!! d-out e)

    (thread
      (try
        (comp/computer-loop software #(<!! a-in) #(>!! a-out %))
        (catch Exception e (>!! error-output-channel e))))
    (thread
      (try
        (comp/computer-loop software #(<!! a-out) #(>!! b-out %))
        (catch Exception e (>!! error-output-channel e))))
    (thread
      (try
        (comp/computer-loop software #(<!! b-out) #(>!! c-out %))
        (catch Exception e (>!! error-output-channel e))))
    (thread
      (try
        (comp/computer-loop software #(<!! c-out) #(>!! d-out %))
        (catch Exception e (>!! error-output-channel e))))
    (thread
      (try
        (comp/computer-loop software #(<!! d-out) #(>!! e-out %))
        (catch Exception e (>!! error-output-channel e))))

    (let [[v p] (alts!! [e-out error-output-channel])] v)))

(test/deftest amp-test-software-test
  (test/is (= 43210 (amp-circuit 4 3 2 1 0 inputs/day7-amp-test-software-1)))
  (test/is (= 54321 (amp-circuit 0 1 2 3 4 inputs/day7-amp-test-software-2)))
  (test/is (= 65210 (amp-circuit 1 0 4 3 2 inputs/day7-amp-test-software-3))))

(defn find-max-thrust [circuit software settings]
  (let [settings (combo/permutations settings)]
    (reduce max (map
     #(let [[a b c d e] %]
        (circuit a b c d e software)) settings))))

(test/deftest find-max-thrust-test
  (test/is
   (= 43210
      (find-max-thrust amp-circuit inputs/day7-amp-test-software-1 [0 1 2 3 4])))
  (test/is
   (= 54321
      (find-max-thrust amp-circuit inputs/day7-amp-test-software-2 [0 1 2 3 4])))
  (test/is
   (= 65210
      (find-max-thrust amp-circuit inputs/day7-amp-test-software-3 [0 1 2 3 4])))
  (test/is
   (= 22012
      (find-max-thrust amp-circuit inputs/day7-input [0 1 2 3 4]))))

(defn amp-feedback-circuit [a b c d e software]
  (let [a-out (chan 1)
        b-out (chan 1)
        c-out (chan 1)
        d-out (chan 1)
        e-out (chan 2)
        exit-out (chan)
        error-output-channel (chan)]

    (>!! e-out a)
    (>!! e-out 0)
    (>!! a-out b)
    (>!! b-out c)
    (>!! c-out d)
    (>!! d-out e)

    (thread
      (try
        (comp/computer-loop
         software #(<!! e-out) #(>!! a-out %) #(>!! exit-out %))
        (catch Exception e (>!! error-output-channel e))))
    (thread
      (try
        (comp/computer-loop
         software #(<!! a-out) #(>!! b-out %) #(>!! exit-out %))
        (catch Exception e (>!! error-output-channel e))))
    (thread
      (try
        (comp/computer-loop
         software #(<!! b-out) #(>!! c-out %) #(>!! exit-out %))
        (catch Exception e (>!! error-output-channel e))))
    (thread
      (try
        (comp/computer-loop
         software #(<!! c-out) #(>!! d-out %) #(>!! exit-out %))
        (catch Exception e (>!! error-output-channel e))))
    (thread
      (try
        (comp/computer-loop
         software #(<!! d-out) #(>!! e-out %) #(>!! exit-out %))
        (catch Exception e (>!! error-output-channel e))))

    (<!! exit-out)
    (<!! exit-out)
    (<!! exit-out)
    (<!! exit-out)
    (<!! exit-out)
    (<!! e-out)
    ))


(test/deftest feedback-test
  (test/is
   (= 139629729
      (amp-feedback-circuit 9 8 7 6 5 inputs/day7-amp-test-software-4)))
  (test/is
   (= 139629729
      (find-max-thrust amp-feedback-circuit inputs/day7-amp-test-software-4 [5 6 7 8 9])))
  (test/is
   (= 18216
      (amp-feedback-circuit 9 7 8 5 6 inputs/day7-amp-test-software-5)))
  (test/is
   (= 18216
      (find-max-thrust amp-feedback-circuit inputs/day7-amp-test-software-5 [5 6 7 8 9])))

  (test/is
   (= 4039164
      (find-max-thrust amp-feedback-circuit inputs/day7-input [5 6 7 8 9]))))


(println "Day 7")
(println "Part 1" (find-max-thrust amp-circuit inputs/day7-input [0 1 2 3 4]))
(println "Part 2" (find-max-thrust amp-feedback-circuit inputs/day7-input [5 6 7 8 9]))
