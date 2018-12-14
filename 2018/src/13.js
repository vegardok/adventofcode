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
  LEFT: (x, y) => [MOVE.UP(x, y), MOVE.LEFT(x, y), MOVE.DOWN(x, y)],
  RIGHT: (x, y) => [MOVE.DOWN(x, y), MOVE.RIGHT(x, y), MOVE.UP(x, y)],
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


let trainStates = []

Range(0, MAP.length).forEach(x => Range(0, width).forEach(y => {
  const d = DIRECTIONS[MAP[x][y]]
  if (d) {
    MAP[x][y] = ['LEFT', 'RIGHT'].indexOf(d) === -1 ? '|' : '-'
    trainStates.push({ x, y, d, turns: 0 })
  }
}))

const crash = (trains) => trains.find(( train, index) => {
  return Boolean(trains.find((e, i) => i !== index && train.x === e.x && train.y === e.y))
})

const mod = (a, b) => {
  var i = a % b
  if (i < 0) {
    return b +i
  }
  return i
}

// let rotate = (a, r=0) => new Array(a.length).fill(0).map((v,i) => a[mod(i - r, a.length)])

const newPos = ({turns, x, y, d }) => {
  const [nextX, nextY] = MOVE[d](x, y)


  const atIntersection = MAP[nextX][nextY] === '+'
  const atTurn = (['/' , '\\']).indexOf(MAP[nextX][ nextY]) !== -1

  let newDirection = d
  const moves = POSSIBLE_MOVES[d](nextX, nextY)
  if (atTurn) {
    newDirection = TURNS[d][MAP[nextX][nextY]]
  } else if (atIntersection) {
    newDirection = moves[turns % 3][2]
  }

  return {
    x: nextX,
    y: nextY,
    turns: atIntersection ? turns + 1 : turns,
    d: newDirection
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

console.log(`Simulating ${trainStates.length} trains`)
console.log(print(MAP, trainStates))
let foundCrash = false
let i = 0

while (!foundCrash) {

  if (i % 10000 === 0) { console.log(i) }
  trainStates = trainStates.map(newPos)
  foundCrash = crash(trainStates)
  i++
}

console.log({ foundCrash })
