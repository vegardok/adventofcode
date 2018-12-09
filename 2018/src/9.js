const { Range } = require('immutable')

function getHighScore(PLAYERS, STOP) {
  let currentMarble = 0
  let currentPlayer = 0

  let score = Range(0, PLAYERS).reduce((m, i) => { m[i] = 0; return m }, {})
  let game = new Uint32Array(STOP)
  let boardSize = 1

  for (let i = currentMarble + 1; i < STOP + 1; i++) {
    if (i % 10000 === 0) {
      console.log(i, i / STOP * 100)
    }


    if (i % 23 === 0) {
      currentMarble = (currentMarble - 7) % boardSize
      if (currentMarble < 0) {
        currentMarble = boardSize + currentMarble
      }
      score[currentPlayer] = score[currentPlayer] + i + game[currentMarble]

      // todo
      game.copyWithin(currentMarble, currentMarble + 1, boardSize)
      boardSize -= 1
    } else {
      let next = (currentMarble + 2) % (boardSize)
      // console.log({ i, boardSize, next, })
      if (next === 0) {
        next = boardSize
      }

      game.copyWithin(next + 1, next, boardSize)

      game[next] = i
      currentMarble = next
      currentPlayer = (currentPlayer + 1) % PLAYERS
      boardSize += 1
    }
    // console.log(boardSize, game.slice(0, boardSize ).join(', '))
    // console.log()
  }

  const winner = Object.values(score).sort((a, b) => b - a)
  return winner[0]
}


// 459 players; last marble is worth 71320 points

// 10 players; last marble is worth 1618 points: high score is 8317
// 13 players; last marble is worth 7999 points: high score is 146373
// 17 players; last marble is worth 1104 points: high score is 2764
// 21 players; last marble is worth 6111 points: high score is 54718
// 30 players; last marble is worth 5807 points: high score is 37305


const a1 = getHighScore(459, 71320)
console.log(`Solution 9.1: \t ${a1}`)


const a2 = getHighScore(459, 71320 * 100)
console.log(`Solution 9.1: \t ${a2}`)
