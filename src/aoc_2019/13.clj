(ns aoc-2019.13
  (:require [aoc-2019.inputs :as inputs]
            [aoc-2019.computer :as c]
            [clojure.set :as cset]
            [clojure.string :as string]))

(defn get-panel [{ p :panel }]
    (let [lines-index (-> p keys sort)
          lines (map (fn [line-n]
                       (let [line (get p line-n)
                             line-index (-> line keys sort)]
                         (map (fn [i] (-> p (get line-n)(get i))) line-index))) lines-index)
          ]
      (string/join "\n" (map string/join lines))))

(defn paint- [panel x y color]
  (let [row (assoc (get panel x {}) y color)]
    (assoc panel x row)))

(defn write- [state op]
  (let [{ x :x y :y panel :panel score :score} state]
    (if (and (= x -1) (= y 0))
      (assoc state :score op)
      (assoc state :panel (paint- panel x y op)))))

(defn write [state]
  (fn [op]
    (swap!
     state
     (fn [prev-state]
       (let [{count :input-count} prev-state
             new-state (case count
                         0 (assoc prev-state :x op)
                         1 (assoc prev-state :y op)
                         2 (write- prev-state op))]
         (update new-state :input-count #(mod (+ 1 %) 3)))))))

(defn print-game-state [state]
  (let [panel (-> (get-panel state)
                  (string/replace \0 \ )
                  (string/replace \1 \#)
                  (string/replace \2 \-)
                  (string/replace \3 \=)
                  (string/replace \4 \*)
                  )]
    (println panel)
    (println "Score " (:score state))
    panel))

(defn on-line [panel v]
  (->> panel
       (map (fn [[i line]] [i (-> line
                                  vals
                                  set
                                  (contains? v))]))
       (filter second)
       (map first)
       first))

(defn joystick [state]
  (fn []
    (let [{panel :panel } @state
          ball-row (on-line panel 4)
          paddle-row (on-line panel 3) ]
      (cond
        (< ball-row paddle-row) -1
        (> ball-row paddle-row) 1
        :else 0))))

(defn print-results []
  (time
   (let [start-state {:panel {} :input-count 0 :x nil :y nil :score nil }]
     (do
       (println "Day 13")
       (let [state (atom start-state)
             write (write state)]
         (println "Part 1")
         (c/computer-loop inputs/day13-input read-line write)
         (print "Part 1 " (count (filter #(= \2 %) (get-panel @state))))
         (print-game-state state)
         (println))


       (let [state (atom start-state)
             write (write state)
             read (joystick state)]
         (println "Part 2")
         (c/computer-loop (assoc inputs/day13-input 0 2) read write)
         (print-game-state @state)
         nil)
       (println)))))

(print-results)
