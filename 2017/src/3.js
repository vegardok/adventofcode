const fs = require('fs')
const { Map, Seq, List } = require('immutable')


const get = (board, x, y) => (board[x] || [])[y]
const sumN = (board, x, y) => {
  let sum = 0
  for (let i = -1; i <= 1; i++) {
    for (let j = -1; j <= 1; j++) {
      sum += get(board, x+ i, y + j) || 0
    }
  }
  return sum
}


function getBoard(UP_TO, sum=false) {
  const W = Math.ceil(Math.sqrt(UP_TO))
  const MID = [Math.floor(W / 2), Math.floor(W/2)]
  let x = MID[0];
  let y = MID[1] + 1
  const BOARD = new Array(W).fill().map(() => new Array(W).fill(0))
  BOARD[MID[0]][MID[1]] = 1
  let INDEX = {1: MID}

  for (let i = 2; i <= UP_TO; i++) {
    BOARD[x][y] = sum ? sumN(BOARD, x, y) : i
    INDEX[BOARD[x][y]] = [x, y]

    const left = Boolean(get(BOARD, x, y -1))
    const up = Boolean(get(BOARD, x -1, y))
    const down = Boolean(get(BOARD, x + 1,y))
    const right = Boolean(get(BOARD, x,y +1))

    if (left && !up) {
      x -=1
    }
    if (right && !down) {
      x += 1
    }
    if (down && !left) {
      y -= 1
    }
    if (up && !right) {
      y +=1


    }
  }
  return { BOARD, INDEX }
}


function countStep(n) {
  const {INDEX, BOARD} = getBoard(n, false)
  let steps = 0;
  let current = INDEX[n];
  let left
  let up
  let down
  let right

  while(BOARD[current[0]][current[1]] !== 1) {
    steps += 1;

    left = (BOARD[current[0]] || []) [current[1] -1 ] || Infinity
    up = (BOARD[current[0] -1 ] || []) [current[1]] || Infinity
    down = (BOARD[current[0] + 1 ] || [])[current[1]] || Infinity
    right = (BOARD[current[0]] || [])[current[1] +1 ] || Infinity

    current = INDEX[Math.min(left, up, down, right)]
  }
  return steps

}

const PUZZLE_INPUT = 368078

const steps = countStep(PUZZLE_INPUT)

console.log('done', steps)

const board2 = getBoard(PUZZLE_INPUT + 1, true)

const a2 = Object.keys(board2.INDEX).find(k => k > PUZZLE_INPUT)

console.log(a2)
