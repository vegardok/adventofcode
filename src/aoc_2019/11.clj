(ns aoc-2019.11
  (:require [clojure.test :as test]
            [clojure.string :as string]
            [aoc-2019.computer :as c]
            [aoc-2019.inputs :as inputs]))


(defn paint- [panel x y color]
  (let [row (assoc (get panel x {}) y color)]
    (assoc panel x row)))

(defn move [robot-state]
  (let [moves {0 (fn [[x y]] [(- x 1) y])
               1 (fn [[x y]] [x (+ y 1)])
               2 (fn [[x y]] [(+ x 1) y])
               3 (fn [[x y]] [x (- y 1)])}]
    (let [new-pos
          ((get moves (:direction robot-state))
           (:position robot-state))]
      (assoc robot-state :position new-pos))))


(defn camera-fn [robot-state panel-state]
  (fn []
    (let [{ [x y] :position } @robot-state
          panel @panel-state]
      (-> panel
          (get x {})
          (get y 0)))))

(defn get-panel [panel-state]
    (let [p @panel-state
          lines-index (-> p keys sort)
          lines (map (fn [line-n]
                       (let [line (get p line-n)
                             line-index (-> line keys sort)]
                         (map (fn [i] (-> p (get line-n)(get i))) line-index))) lines-index)
          ]
      (string/join "\n" (map string/join lines))))

(defn paint [robot-state panel-state color]
  (let [{position :position} @robot-state
        [x y] position]
    (swap! panel-state
           (fn [old-panel] (paint- old-panel x y color)))))

(defn turn-and-move [robot-state input]
    (let [dir-map {0 dec 1 inc}]
      (swap! robot-state
             (fn [old-robot-state]
               (-> old-robot-state
                   (update :direction #(mod ((get dir-map input) %) 4))
                   (move))))))

(defn write [input-flipper robot-state panel-state ]
  (fn [op]
    (let [flip @input-flipper]
      (case flip
        0 (paint robot-state panel-state op)
        1 (turn-and-move robot-state op))
      (swap! input-flipper #(mod (+ 1 %) 2)))))



(defn print-results []
  (time
   (do
     (println "Day 11")
     (let [panel-state (atom {})
           robot-state (atom { :direction 0 :position [0 0]})
           input-flipper (atom 0)
           write (write input-flipper robot-state panel-state)
           read (camera-fn robot-state panel-state)]
       (c/computer-loop inputs/day11-input read write)
       (print "Part 1 " (count (filter #(or (= \0 %) (= \1 %)) (get-panel panel-state))))
       (println))

     (let [panel-state (atom {0 {0 1}})
           robot-state (atom { :direction 0 :position [0 0]})
           input-flipper (atom 0)
           write (write input-flipper robot-state panel-state)
           read (camera-fn robot-state panel-state)]
       (c/computer-loop inputs/day11-input read write)
       (println "Part 2 ")
       (println (-> (get-panel panel-state)
                    (string/replace \0 \ )
                    (string/replace \1 \#)))
       (println))

     ;;   ;;   ##   #### ###  #  # ####   ##  ##  ###
     ;;   ;;  #  #  #    #  # # #     #    # #  # #  #
     ;;   ;;  #     ###  #  # ##     #     # #    #  #
     ;;   ;;  #     #    ###  # #   #      # #    ###
     ;;   ;;  #  #  #    #    # #  #    #  # #  # # #
     ;;   ;;  ##    #### #    #  # ####  ##   ##  #  #
     )))
(print-results)
