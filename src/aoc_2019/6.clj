(ns aoc-2019.6
  (:require [clojure.test :as test]
            [clojure.string :as string]
            [clojure.set :as cs]
            [aoc-2019.inputs :as input]))

(defn- gen-graph
  ([inputs] (gen-graph inputs {}))
  ([inputs graph]
   (let [[next & rest] inputs
         [from to] (map keyword (string/split next #"\)"))
         existing-pointers (get graph from #{})
         new-graph (assoc graph from (conj existing-pointers to))]
     (if (empty? rest) new-graph (recur rest new-graph)))))

(defn- graph-size
  ([graph] (graph-size graph :COM 0))
  ([graph node depth]
   (let [child-nodes (get graph node)]
     (+ depth (reduce + (map #(graph-size graph % (inc depth)) child-nodes))))))

(test/deftest count-orbits-test
  (test/is (= 42 (graph-size (gen-graph (shuffle input/day6-input-test-1)))))
  (test/is (= 145250 (graph-size (gen-graph (shuffle input/day6-input))))))



(defn search-graph
  ([graph target-node] (search-graph graph :COM target-node #{}))
  ([graph current-node target-node current-path]
   (let [child-nodes (current-node graph )
         path (conj current-path current-node)]
     (if (contains? child-nodes target-node)
       path

       (flatten (map #(search-graph graph % target-node path) child-nodes))))))


(defn orbital-transfers [graph from to]
  (let [[from-path] (search-graph graph from)
        [to-path] (search-graph graph to)
        common-path (cs/intersection from-path to-path)
        uniq-from-path (cs/difference from-path common-path)
        uniq-to-path (cs/difference to-path common-path)
        path (cs/union uniq-from-path uniq-to-path)]
    (count path)))

(let [graph (gen-graph input/day6-input)]
  (println "Day 6")
  (println "Part 1 " (graph-size graph))
  (println "Part 2 " (orbital-transfers graph :YOU :SAN)))
