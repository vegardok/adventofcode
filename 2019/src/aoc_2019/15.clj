(ns aoc-2019.15
  (:require [aoc-2019.inputs :as inputs]
            [aoc-2019.computer :as c]
            [clojure.string :as string]))


(def print-map
  { nil " "
   :wall "#"
   :hall "."
   :oxygen "0"
   :north "↑"
   :south "↓"
   :west "←"
   :east "→"
   :nodir "x"
   })


(defn print-game-state
  [{ panel :panel
    { x :x y :y } :location
    direction :direction
    path :path-taken
    }]
  (let [dir-map {0 :north
                 1 :south
                 2 :west
                 3 :east
                 nil :nodir}
        panel (assoc-in panel [x y] (get dir-map direction))
        from-x (apply min (keys panel))
        to-x (apply max (keys panel))
        from-y (apply min (flatten (map keys (vals panel))))
        to-y (apply max (flatten (map keys (vals panel))))]
    (println)
    ;; (println "---")
    (println "location" { :x x :y y })
    (println "direction" direction)
    (println "path" path)
    (println "path-length" (count path))
    (println (string/join "\n"
                          (map
                           (fn [x]
                             (string/join
                              (map
                               (fn ([y] (get print-map (get-in panel [x y]))))
                               (range from-y (inc to-y)))))
                           (range from-x (inc to-x)))))))

(defn move-op [op location]
  (case op
    0 (update location :x dec) ;; north
    1 (update location :x inc) ;; south
    2 (update location :y dec) ;; west
    3 (update location :y inc)) ;; east
  )

(defn get-valid-neighbour-moves
  [{panel :panel
    location :location
    explored :explored
    { x :x y :y } location
    }]
  (->>
   (range 4)
   (map (fn [op] [op (move-op op location)]))
   (filter (fn [[op { x :x y :y}]] (and (= :hall (get-in panel [x y] ))
                                        (not (get-in explored [x y])))))
   (map first)
   ))

(defn get-unknown-neighbour-moves
  [{panel :panel
    location :location
    { x :x y :y } location
    }]
  (->>
   (range 4)
   (map (fn [op] [op (move-op op location)]))
   (filter (fn [[op { x :x y :y}]] (nil? (get-in panel [x y] ))))))

(defn get-explore-move
  [state]
  (let [turn-around-map {0 1
                         1 0
                         2 3
                         3 2}

        {exploring :exploring
         location :location
         direction :direction
         } state
        moves (get-unknown-neighbour-moves state)
        go-back (and (not (nil? direction))
                     (not= location exploring))]
    (if go-back
      (get turn-around-map direction)
      (-> moves first first))))

(defn stop-exploring [state]
  (and (nil? (get-explore-move (assoc state :ignore true)))))

(defn update-explore-direction [state]
  (let [{ exploring :exploring
         location :location } state
        move (get-explore-move state)]
    (-> state
        (assoc :direction move)
        (update :exploring #(if (nil? move) nil %))
        )))

(defn update-location-after-walk [state]
  (let [{ direction :direction } state]
    (if (nil? direction)
      state
      (let [{location :location
             panel :panel} state
            { x :x y :y } (move-op direction location)
            status (get-in panel [x y])
            moved (= status :hall)
            ]
        (if moved
          (assoc state :location {:x x :y y})
          state)))))

(defn explore [state]
  (fn []
    (let
        [new-state
         (swap!
          state
          (fn [state]
            (let [{ exploring :exploring } state]
              (if (nil? exploring)
                state
                (->> state
                     update-explore-direction)
                ))))]
      (inc (:direction new-state)))))

(def state-map
  {0 :wall
   1 :hall
   2 :oxygen })

(defn write-status [state]
  (fn [op]
    (swap!
     state
     (fn [state]
       (let [{location :location
              direction :direction} state
             { x :x y :y } (move-op direction location)
             state (assoc-in state [:panel x y] (get state-map op))]
         (if (= (get state-map op) :oxygen)
           (print-game-state state))
           (if (not= (get state-map op) :wall)
             (assoc state :location {:x x :y y })
           state))))))

(defn explore-program
  [program state]
  (if (stop-exploring state)
    []
    (let [state (atom (assoc state :exploring (:location state)))
          write-status (write-status state)
          get-movement (explore state)
          step (c/computer-step-by-step program get-movement write-status)]

      (loop [step step]
        (let [stop (stop-exploring @state)
              [next-program step] (step)]
          (if stop
            (vec (map (fn [move] [next-program @state move])
                      (get-valid-neighbour-moves @state)))
            (recur step )))))))


(defn write-after-move [state]
  (fn [op]
    (swap!
     state
     (fn [state]
       ;;  #dbg
       (let [{location :location
              direction :direction } state
             { x :x y :y } location ]
         (-> state
             (assoc :direction nil)
             (assoc :exploring nil)
             (assoc :location (move-op direction location))
             (update :path-taken #(conj % direction))
             (assoc-in [:explored x y] true)
             ))))))

(defn move-program
  [program state]
  (let [starting-location (:location state)
        state (atom state)
        write-after-move (write-after-move state)
        get-movement (fn [] (inc (:direction @state)))
        step (c/computer-step-by-step program get-movement write-after-move)]
    (loop [step step]
      (let [[next-program step] (step)]
        (if (not= starting-location (:location @state))
          [next-program @state]
          (recur step))))))


(defn deep-merge [a & maps]
  (if (map? a)
    (apply merge-with deep-merge a maps)
    (apply merge-with deep-merge maps)))

(defn explore-and-move []
  (let [queue (explore-program
               inputs/day15-input
               {:location { :x 0 :y 0 }
                :panel {}
                :direction nil
                :iter 0
                })]

    (loop [queue queue
           full-state {}
           ]
      (if (empty? queue)
        full-state
        (let [[ [program state direction] & rest] queue
              [program-after-move state-after-move]
              (move-program program (assoc state :direction direction))
              new-moves (explore-program program-after-move state-after-move)
              ]
          ;; (print-game-state
          ;;  (apply deep-merge full-state (map second new-moves)))
          (recur (into (vec rest) new-moves)
                 (apply deep-merge full-state (map second new-moves))
                 ))))))



(defn get-moves [panel explored location]
  (->>
   (range 4)
   (map (fn [op] (move-op op location)))
   (filter (fn [{ x :x y :y }]
             (and
              (= :hall (get-in panel [x y] ))
              (not (get-in explored [x y]))
              )))))

(defn longest-path [panel location]
  (loop [explored (assoc-in {} [(:x location) (:y location)] true)
         queue (map (fn [l] [l [l]]) (get-moves panel explored location))
         longest-queue []
         ]

    (let [[first & rest ] queue
          [move q] first
          { x :x y :y } move
          explored (assoc-in explored [x y] true)
          new-moves (map (fn [l] [l (conj q l)]) (get-moves panel explored move))
          ]
      (if (or (nil? move )(empty? (conj rest new-moves)))
        longest-queue
        (recur explored
               (into (vec rest) new-moves)
               (if (< (count longest-queue) (count q)) q longest-queue)
               )))))


(defn print-result []
  (let [final-state (explore-and-move)]
    (println "Part 2")
    (println (count (longest-path (:panel final-state) { :x -16 :y -14 })))
    ))
(print-result)
