const fs = require('fs')
const chalk = require('chalk')
const { range, last, flatten, max } = require('lodash')

const inputs = String(fs.readFileSync(__dirname + '/../inputs/6.txt'))
      .trim()
      .split('\n')
      .map((l, i) => ({
        i,
        x: parseInt(l.split(', ')[0]), // + 100,
        y: parseInt(l.split(', ')[1]) // + 100
      }))

function distance(a, b) {
  return Math.abs(a.x - b.x) + Math.abs(a.y - b.y)
}

const colors = [
  'bgGreen',
  'bgYellow',
  'bgBlue',
  'bgMagenta',
  'bgCyan',
  'bgWhite',
  'bgRedBright',
  'bgGreenBright',
  'bgYellowBright',
  'bgBlueBright',
  'bgMagentaBright',
  'bgCyanBright',
  'bgWhiteBright',
]

const format = i => {
  if (i === -1) {
    return chalk.red(' ')
  }
  return chalk[colors[i % colors.length]](' ')
}

const X = max(inputs.map(p => p.x)) + 1
const Y = max(inputs.map(p => p.y)) + 1

const board = range(0, X).map(
  x => range(0, Y).map(
    y => {
      const [best, secondBest] = inputs.sort((a, b) => distance(a, {x, y}) - distance(b, { x, y }))
      if (distance(best, { x, y}) !== distance(secondBest, {x, y})) {
        return best.i
      } else {
        return -1
      }
    }))

const infiniteArea = new Set([
  ...board[0],
  ...board.map(l => last(l)),
  ...board.map(l => l[0]),
  ...last(board)
])

const count = flatten(board)
      .filter(id => !infiniteArea.has(id))
      .reduce((accl, id) => {
        accl[id] = (accl[id] || 0) + 1
        return accl
      }, {})

const biggest = Object.entries(count).sort((a,b) => b[1] - a[1])[0] //) , t => -t[1])[0]

console.log(`Solution 6.1: \t ${biggest[1]} (${biggest[0]})`) // 4589 (46)
// console.log(board.map(l => l.map(format).join('')).join('\n'))



const distances = flatten(range(0,X).map(
  x => range(0, Y).map(
    y => inputs.reduce((sum, p) => sum + distance(p, { x, y }), 0))))

// console.log(distances.slice(0, 100))
console.log(`Solution 6.1: \t ${distances.filter(d => d < 10000).length}`) // 4025
