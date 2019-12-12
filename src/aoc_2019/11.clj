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

(defn setup
  ([] (setup {}))
  ([panel-state]
   (let [panel-state (atom panel-state)
         robot-state (atom { :direction 0 :position [0 0]})
         input-flipper (atom 0)

         camera-fn (fn []
                     (let [{ [x y] :position } @robot-state
                           panel @panel-state]
                       (-> panel
                           (get x {})
                           (get y 0))))

         get-panel (fn []
                     (let [p @panel-state
                           lines-index (-> p keys sort)
                           lines (map (fn [line-n]
                                        (let [line (get p line-n)
                                              line-index (-> line keys sort)]
                                          (map (fn [i] (-> p (get line-n)(get i))) line-index))) lines-index)
                           ]
                       (string/join "\n" (map string/join lines))))

         paint (fn [color]
                 (let [{position :position} @robot-state
                       [x y] position]
                   (swap! panel-state
                          (fn [old-panel] (paint- old-panel x y color)))))

         turn-and-move (fn [input]
                         (let [dir-map {0 dec 1 inc}]
                           (swap! robot-state
                                  (fn [old-robot-state]
                                    (-> old-robot-state
                                        (update :direction #(mod ((get dir-map input) %) 4))
                                        (move))))))

         write (fn [op]
                 (let [flip @input-flipper]
                   (case flip
                     0 (paint op)
                     1 (turn-and-move op))
                   (swap! input-flipper #(mod (+ 1 %) 2))))
         ]
     {:read camera-fn :write write :get-panel get-panel}
     )))



(defn print-results []
  (time
   (do
     (println "Day 11")
     (let [{ write :write read :read get-panel :get-panel} (setup)]
       (c/computer-loop inputs/day11-input read write)
       (print "Part 1 " (count (filter #(or (= \0 %) (= \1 %)) (get-panel))))
       (println))

     (let [{ write :write read :read get-panel :get-panel} (setup {0 { 0 1}})]
       (c/computer-loop inputs/day11-input read write)
       (println "Part 2 ")
       (println (-> (get-panel)
                    (string/replace \0 \ )
                    (string/replace \1 \#)))

       ;;   ##   #### ###  #  # ####   ##  ##  ###
       ;;  #  #  #    #  # # #     #    # #  # #  #
       ;;  #     ###  #  # ##     #     # #    #  #
       ;;  #     #    ###  # #   #      # #    ###
       ;;  #  #  #    #    # #  #    #  # #  # # #
       ;;  ##    #### #    #  # ####  ##   ##  #  #

       (println)))))
(print-results)
