const fs = require('fs')

const input = String(fs.readFileSync(__dirname + '/../inputs/2.txt'))
      .trim()
      .split('\n')
      .map(l => l.split('\t').map(c => parseInt(c)))

const min = l => l.reduce((m, v) => Math.min(m, v))
const max = l => l.reduce((m, v) => Math.max(m, v))
const sum = l => l.reduce((m, v) => m + v)

const a1 = sum(input.map( l=> max(l) - min(l)))


console.log(`Solution 2.1: \t ${a1}`)

const a2 = sum(input.map(l => {
  const divs = l.filter(
    n => l.filter(
      x => x !==n && Math.max(n, x) % Math.min(n, x) === 0
    ).length !== 0).sort((a,b) => a - b)

  return divs[1] / divs[0]
}))

console.log(`Solution 2.1: \t ${a2}`)
