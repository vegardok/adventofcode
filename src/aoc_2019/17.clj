(ns aoc-2019.17
  (:require [aoc-2019.inputs :as inputs]
            [aoc-2019.computer :as c]
            [clojure.string :as string]
            ))



(defn find-intersection-sum [panel]
  (reduce
   +
  (remove
   nil?
   (flatten (let [panel
                  (str/split panel #"\n" )]
              (for [i (range 1 (dec (count panel)))]
                (for [j (range 1 (dec (count (first panel))))]
                  (if (and (= \# (get-in panel [(dec i) j]))
                           (= \# (get-in panel [(inc i) j]))
                           (= \# (get-in panel [i j]))
                           (= \# (get-in panel [ i (dec j)]))
                           (= \# (get-in panel [ i (inc j)])))
                    ;; { :i i :j j }
                    (* i j)
                    ))))))))

(defn find-stuff [panel]
  (first (remove
   nil?
   (flatten (let [panel
                  (str/split panel #"\n" )]
              (for [i (range (count panel))]
                (for [j (range (count (first panel)))]
                  (if (= \^ (get-in panel [i j]))
                    { :i i :j j }
                    ))))))))


(defn get-move [panel location direction]
  (

(defn get-path [panel]
  (let [start (find-stuff panel)]




(defn write [state]
  (fn [op]
    (swap!
     state
     (fn [prev-state]
       (update prev-state :panel #(str % (if (< op 127) (char op) op)))))))

(defn robot-control [state]
  (fn []
    (let [op (int (first (:program @state)))]
      (swap!
       state
       (fn [state] (update state :program #(string/join (rest %)))))
      op)))


(defn get-program [panel]
"A,B
L,8,R,10,L,8,R,8
L,12,R,8,R,8,L,8
L
n
")


(defn set-program [state]
  (swap!
   state
   (fn [state]
     (-> state
         (assoc :panel "")
         (assoc :program (get-program (:panel state)))))))


(defn print-results []
  (time
   (do
     (println "Day 17")
     (let [state (atom {:panel ""
                        :program ""
                        })
           write (write state)
           robot-control (robot-control state)
           ]
       (c/computer-loop inputs/day17-input read-line write)

       ;; not 7255
       (println "Part 1 " (find-intersection-sum (:panel @state)))

       (println "start " (find-stuff (:panel @state)))

       ;; (set-program state)

       ;; (c/computer-loop (assoc inputs/day17-input 0 2) robot-control write)

       ;; (println (string/replace (:panel @state) \. \ ))

       ))))
(print-results)
