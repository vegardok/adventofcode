(ns aoc-2019.15
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


(def print-map
  { nil " "
   :wall "#"
   :hall "."
   :oxygen "0"
   :north "↑"
   :south "↓"
   :west "←"
   :east "→"
   })

(def arne
  { 0 :north
   1 :south
   2 :west
   3 :east })

(defn print-game-state [state]
  (let [{ panel :panel
         { x :x y :y } :location
         exploring :exploring
         direction :direction
         queue :queue
         path :path-taken
         } state
        panel (assoc-in panel [x y] (get arne direction))
        from-x (apply min (keys panel))
        to-x (apply max (keys panel))
        from-y (apply min (flatten (map keys (vals panel))))
        to-y (apply max (flatten (map keys (vals panel))))]
    (println)
    (println "---")
    (println "location" { :x x :y y })
    (println "exploring" exploring)
    (println "queue" queue)
    (println "path" path)

    (println (string/join "\n"
                          (map
                           (fn [x]
                             (string/join
                              (map
                               (fn ([y] (get print-map (get-in panel [x y]))))
                               (range from-y (inc to-y)))))
                           (range from-x (inc to-x)))))))

(defn write-status [state]
  (let [state-map
        {0 :wall
         1 :hall
         2 :oxygen }]
    (fn [op]
      (swap!
       state
       (fn [state]
         (let [{location :location
                direction :direction} state
               { x :x y :y } (move direction location)]
           (assoc-in state [:panel x y] (get state-map op))))))))


(defn move [op location]
  (case op
    0 (update location :x dec) ;; north
    1 (update location :x inc) ;; south
    2 (update location :y dec) ;; west
    3 (update location :y inc)) ;; east
  )

(defn get-valid-neighbour-moves
  [{panel :panel
    location :location
    { x :x y :y } location
    }]
  (->>
   (range 4)
   (map (fn [op] [op (move op location)]))
   (filter (fn [[op { x :x y :y}]] (= :hall (get-in panel [x y] ))))
   (map first)
   ))

(defn get-unknown-neighbour-moves
  [{panel :panel
    location :location
    { x :x y :y } location
    }]
  (->>
   (range 4)
   (map (fn [op] [op (move op location)]))
   (filter (fn [[op { x :x y :y}]] (nil? (get-in panel [x y] ))))))

(defn get-explore-move
  [state]
  (let [turn-around-map {0 1
                         1 0
                         2 3
                         3 2}

        {exploring :exploring
         location :location
         status :status
         direction :direction
         } state
        moves (get-unknown-neighbour-moves state)
        go-back (not= location exploring)]
    (if go-back
      (get turn-around-map direction)
      (-> moves first first))))

(defn stop-exploring [state]
  (let [{ status :status
         exploring :exploring
         location :location
         } state
        move (get-explore-move state)
        ]
    (and (nil? move) (= location exploring))))

(defn update-explore-direction [state]
  (let [{ status :status
         exploring :exploring
         } state
        move (get-explore-move state)
        ]
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
            { x :x y :y } (move direction location)
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
            (let [{ exploring :exploring } state
                  stop (stop-exploring state)]
              (if (nil? exploring)
                state
                (->> state
                     update-location-after-walk
                     update-explore-direction
                     )
                ))))]
      (inc (:direction new-state)))))

(defn explore-program
  [program location]
  (let [state (atom {:location location
                     :status nil
                     :panel {}
                     :direction nil
                     :exploring location
                     :iter 0
                     })
        write-status (write-status state)
        get-movement (explore state)
        [first-program step] (c/computer-step-by-step program get-movement write-status)
        ]

    (let [[next-program state]
          (loop [next-program first-program
                 step step]
            (let [
                  [next-program step] (step)
                  state-o @state
                  stop (stop-exploring state-o)
                  ]
              (if stop
                [next-program state]
                (recur next-program step))))]
          (print-game-state @state)
          (println (get-valid-neighbour-moves @state))
          [next-program @state ]
          )))

(explore-program inputs/day15-input { :x 0 :y 0 })
