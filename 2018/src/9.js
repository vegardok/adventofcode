function range(start, stop) {
  var r = []
  for (let i = start; i < stop; i++) {
    r.push(i)
  }
  return r
}

function getHighScore(PLAYERS, STOP) {
  let currentMarble = 0
  let currentPlayer = 0

  let score = range(0, PLAYERS).reduce((m, i) => { m[i] = 0; return m }, {})

  let current = { value: 0 }
  current.p = current
  current.n = current

  for (let i = currentMarble + 1; i < STOP + 1; i++) {
    if (i % 23 === 0) {
      score[currentPlayer] += i

      let target = current
      for(let i = 0; i < 7;i++) {
        target = target.p
      }
      score[currentPlayer] += target.value

      target.n.p = target.p
      target.p.n = target.n
      current = target.n
    } else {
      const target = current.n
      const node = {
        value: i,
        n: target.n,
        p: target,
      }
      target.n.p = node
      target.n = node
      current = node
    }
    currentPlayer = (currentPlayer + 1) % PLAYERS
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
