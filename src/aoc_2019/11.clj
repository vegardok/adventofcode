(ns aoc-2019.11
  (:require [clojure.test :as test]
            [clojure.string :as string]
            [aoc-2019.computer :as c]
            [aoc-2019.inputs :as inputs]))




(defn move [robot-state]
  (let [moves {0 (fn [[x y]] [(- x 1) y])
               1 (fn [[x y]] [x (+ y 1)])
               2 (fn [[x y]] [(+ x 1) y])
               3 (fn [[x y]] [x (- y 1)])}]
    (let [new-pos
          ((get moves (:direction robot-state))
           (:position robot-state))]
      (assoc robot-state :position new-pos))))


(defn camera-fn [state]
  (fn []
    (let [{panel :panel
           robot :robot } @state
          { [x y] :position } robot]
      (-> panel
          (get x {})
          (get y 0)))))

(defn get-panel [state]
    (let [{ p :panel } @state
          lines-index (-> p keys sort)
          lines (map (fn [line-n]
                       (let [line (get p line-n)
                             line-index (-> line keys sort)]
                         (map (fn [i] (-> p (get line-n)(get i))) line-index))) lines-index)
          ]
      (string/join "\n" (map string/join lines))))

(defn paint- [panel x y color]
  (let [row (assoc (get panel x {}) y color)]
    (assoc panel x row)))

(defn paint [state color]
  (let [ { { [x y] :position } :robot } state]

    (update state :panel (fn [old-panel] (paint- old-panel x y color)))))

(defn turn-and-move [state input]
  (let [dir-map {0 dec 1 inc}]

    (update state :robot
            (fn [old-robot-state]
              (-> old-robot-state
                  (update :direction #(mod ((get dir-map input) %) 4))
                  (move))))))

(defn write [state]
  (fn [op]
    (swap!
     state
     (fn [prev-state]
       (let [{flip :input-flip} prev-state
             new-state (case flip
                         0 (paint prev-state op)
                         1 (turn-and-move prev-state op))]
         (update new-state :input-flip #(mod (+ 1 %) 2)))))))

(defn print-results []
  (time
   (do
     (println "Day 11")
     (let [state (atom {:panel {}
                        :robot { :direction 0 :position [0 0]}
                        :input-flip 0
                        })
           write (write state)
           read (camera-fn state)]
       (c/computer-loop inputs/day11-input read write)
       (print "Part 1 " (count (filter #(or (= \0 %) (= \1 %)) (get-panel state))))
       (println))
     (let [state (atom {:panel {0 { 0 1}}
                        :robot { :direction 0 :position [0 0]}
                        :input-flip 0
                        })
           write (write state)
           read (camera-fn state)]
       (c/computer-loop inputs/day11-input read write)
       (println "Part 2 ")
       (println (-> (get-panel state)
                    (string/replace \0 \ )
                    (string/replace \1 \#)))
       (println))
     ;;   ##   #### ###  #  # ####   ##  ##  ###
     ;;  #  #  #    #  # # #     #    # #  # #  #
     ;;  #     ###  #  # ##     #     # #    #  #
     ;;  #     #    ###  # #   #      # #    ###
     ;;  #  #  #    #    # #  #    #  # #  # # #
     ;;  ##    #### #    #  # ####  ##   ##  #  #
     )))
;; (print-results)
