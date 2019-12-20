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

(defn paint- [panel x y color]
  (let [row (assoc (get panel x {}) y color)]
    (assoc panel x row)))

(def state-map
  {0 :wall
   1 :hall
   2 :oxygen })

(defn write-status [state]
  (fn [op]
    (swap!
     state
     (fn [prev-state]
       (assoc prev-state :status (get state-map op))))))


(defn move [op location]
  (case op
    0 (update location :x dec) ;; north
    1 (update location :x inc) ;; south
    2 (update location :y dec) ;; west
    3 (update location :y inc)) ;; east
  )

(def move-map
  { 0 3
   1 2
   2 0
   3 1 })

(def foo
  { 0 :north
   1 :south
   2 :west
   3 :east })

(def bar
  { :wall 0
   :hall 1 })

(defn get-unknown-neighbour-moves
  [{panel :panel
    location :location
    { x :x y :y } location
    }]
  (->>
   (range 4)
   (map (fn [op] [op (move op location)]))
   (filter (fn [[op { x :x y :y}]] (nil? (get-in panel [x y] ))))))

(defn get-valid-neighbour-moves
  [{panel :panel
    location :location
    { x :x y :y } location
    }]
  (->>
   (range 4)
   (map (fn [op] [op (move op location)]))
   (filter (fn [[op { x :x y :y}]] (= :hall (get-in panel [x y] ))))))


(defn update-location-after-walk [state]
  (let [{location :location
         direction :direction
         status :status
         } state
        moved (= status :hall)
        ]
    (if (nil? status)
      state
      (let [ { x :x y :y } (move direction location)]
        (-> state
            (assoc-in [:panel x y] status)
            (assoc :location
                   (if moved
                     (move direction location)
                     location)))))))


(defn get-path-move [state]
  (if false ;; TODO move to front
    state
    #dbg
    (get-in state [:queue 0])))


(def turn-around-map
  {0 1
   1 0
   2 3
   3 2 })

(defn get-explore-move
  [state]
  (let [{exploring :exploring
         location :location
         status :status
         direction :direction
         } state
        moves (get-unknown-neighbour-moves state)
        go-back (not= location exploring)]
    (if go-back
      (get turn-around-map direction)
      (-> moves first first))))

(defn move-back-to-start [queue]
  (->> queue
      reverse
      (map #(get turn-around-map %))
      vec))

(move-back-to-start [0 0 2 2])


(defn update-direction [state]
  (let [{exploring :exploring
         queue :queue
         location :location} state
        is-exploring (not (nil? exploring))
        ]
    (-> state
        (assoc :direction
               (if is-exploring
                 (get-explore-move state)
                 (get-path-move state)))
        (update :visited
                #(if is-exploring
                   %
                   (let [{ x :x y :y } (move (first queue) location)]
                     (assoc-in % [x y] true))))
        (update :exploring
                #(if is-exploring
                   %
                  (move (first queue) location)))
        (update :path-taken
                #(if is-exploring
                   %
                   (conj % (first queue))))
        (update :queue
                #(if is-exploring
                   %
                   (vec (rest %))))
        (update :iter inc)
        )))


(defn update-explore-state [state]
  (let [explore-move (get-explore-move state)]
    (if (nil? explore-move)
      (assoc state :exploring nil)
      state)))

(defn update-queue [state]
  (let [{exploring :exploring
         queue :queue
         panel :panel
         visited :visited
         } state
        moves (get-valid-neighbour-moves state)
        unexplored-moves (map
                          first
                          (filter
                           (fn [[_ {x :x y :y }]]
                             (not
                              (get-in visited [x y]))) moves))]
    (if (nil? exploring)
      (let [new-queue (apply conj queue unexplored-moves)]
        #dbg
        (-> state
            (assoc :queue new-queue)
            ;; (update :queue #(apply conj % moves) )
            ))
      state)))

(defn update-exploring [state]
  (let [{exploring :exploring
         direction :direction
         location :location
         queue :queue
         } state
        explore-move (move direction location)
        ]
    ;; TODO: wait until droid is in correct pos, walking to "front"
    (if (nil? exploring)
      (assoc state :exploring explore-move)
      state)))


(defn get-movement [state]
  (fn []
    (let
        [new-state
         (swap!
          state
          (fn [state]
            (let [{ status :status } state]
              #dbg ^{:break/when (not (empty? (:queue state)))}
              (->> state
                   update-location-after-walk
                   update-explore-state
                   update-queue
                   update-direction
                   ))))]
      (print-game-state new-state)
      #dbg ^{:break/when (nil? (:direction new-state))}
      (inc (:direction new-state)))))

(let [state (atom {:location {:x 0 :y 0}
                   :status nil
                   :panel {}
                   :visited {0 {0 true}}
                   :direction nil
                   :queue []
                   :path-taken []
                   :exploring {:x 0 :y 0}

                   :iter 0
                   })
      write-status (write-status state)
      get-movement (get-movement state)]
  ;; (c/computer-loop inputs/day15-input get-movement write-status)
  )
