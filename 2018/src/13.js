const { Range } = require('immutable')

const fs = require('fs')

const input =String(fs.readFileSync(__dirname + '/../inputs/13.txt'))
      .split('\n')

const width = Math.max(...input.map(l => l.length))

const MAP = input.map(l => Range(0, width).map(i => l[i] || ' ').toJS())

const DIRECTIONS = {
  '<': 'LEFT',
  '>': 'RIGHT',
  'v': 'DOWN',
  '^': 'UP',
  'LEFT': '<',
  'RIGHT': '>',
  'DOWN': 'v',
  'UP': '^',

}

const MOVE = {
  LEFT: (x, y) => [x, y-1, 'LEFT'],
  RIGHT: (x, y) => [x, y+1, 'RIGHT'],
  DOWN: (x, y) => [x+1, y, 'DOWN'],
  UP: (x, y) => [x-1, y, 'UP']
}

const POSSIBLE_MOVES = {
  LEFT: (x, y) => [ MOVE.DOWN(x, y), MOVE.LEFT(x, y), MOVE.UP(x, y)],
  RIGHT: (x, y) => [ MOVE.UP(x, y), MOVE.RIGHT(x, y), MOVE.DOWN(x, y)],
  DOWN: (x, y) => [ MOVE.RIGHT(x, y), MOVE.DOWN(x, y), MOVE.LEFT(x, y)],
  UP: (x, y) => [ MOVE.LEFT(x, y), MOVE.UP(x, y), MOVE.RIGHT(x, y)]
}

const TURNS = {
  RIGHT: {
    '/': 'UP',
    '\\': 'DOWN'
  },
  LEFT: {
    '/': 'DOWN',
    '\\': 'UP'
  },
  DOWN: {
    '/': 'LEFT',
    '\\': 'RIGHT'
  },
  UP: {
    '/': 'RIGHT',
    '\\': 'LEFT'
  },
}

let trainStates1 = []
let trainStates2 = []
Range(0, MAP.length).forEach(x => Range(0, width).forEach(y => {
  const d = DIRECTIONS[MAP[x][y]]
  if (d) {
    MAP[x][y] = ['LEFT', 'RIGHT'].indexOf(d) === -1 ? '|' : '-'
    trainStates1.push({ x, y, d, turns: 0, loaded: trainStates1.length })
    trainStates2.push({ x, y, d, turns: 0, loaded: trainStates2.length })
  }
}))

const crash = (trains) => trains.filter(( train, index) => {
  return Boolean(trains.find((e, i) => i !== index && train.x === e.x && train.y === e.y))
})

const newPos = ({turns, x, y, d, loaded }) => {
  const [nextX, nextY] = MOVE[d](x, y)
  const nextTrack = MAP[nextX][ nextY]
  const atIntersection = MAP[nextX][nextY] === '+'
  const atTurn = '/' === nextTrack || '\\' === nextTrack

  let newDirection = d

  if (atTurn) {
    newDirection = TURNS[d][MAP[nextX][nextY]]
  } else if (atIntersection) {
    const moves = POSSIBLE_MOVES[d](nextX, nextY)
    newDirection = moves[turns][2]
  }

  return {
    x: nextX,
    y: nextY,
    turns: atIntersection ? ((turns + 1) % 3) : turns,
    d: newDirection,
    loaded
  }
}

const print = (map, trains = []) => {
  return map.map((l, x) => {
    return l.map((p, y) => {
      const { d } = trains.find(t => t.x === x && t.y === y) || {}
      return DIRECTIONS[d] || p
    }).join('')
  }).join('\n')
}


let foundCrash = []
let i = 0

while (foundCrash.length === 0) {
  trainStates1 = trainStates1.map(newPos)

  foundCrash = crash(trainStates1)
  i++
}
console.log(`First train crash at ${i} (${foundCrash[0].y},${foundCrash[0].x})`)
console.log()

i=1
while (trainStates2.length > 1) {
  trainStates2.sort((a,b) => a.y === b.y ? a.x - b.x : a.y - b.y)

  let newState = trainStates2.map(newPos)

  const midTickCrash = newState.filter((newTrain, i1) => {
    return trainStates2.find((oldTrain, i2) => {
      return newTrain.x === oldTrain.x && newTrain.y === oldTrain.y && i1 < i2
    })
  })

  const removeFromOldState = trainStates2.filter((oldTrain, i1) => {
    return newState.find((newTrain, i2) => {
      return newTrain.x === oldTrain.x && newTrain.y === oldTrain.y && i1 >= i2
    })
  })

  if (midTickCrash.length) {
    const train1 = newState.indexOf(midTickCrash[0])
    const train2 = trainStates2.indexOf(removeFromOldState[0])
    newState.splice(train2, 1)
    newState.splice(train1, 1)
  }

  foundCrash = crash(newState)
  if (foundCrash.length > 0) {
    newState = newState.filter(t1 => !foundCrash.find(t2 => t1.x === t2.x && t1.y === t2.y))
  }
  trainStates2 = newState

  i++
}

console.log(`Last train found after ${i} at (${trainStates2[0].y},${trainStates2[0].x})`)
