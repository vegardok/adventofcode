const fs = require('fs')

const input = String(fs.readFileSync(__dirname + '/../inputs/1.txt'))
      .trim()
      .split('')
      .map(c => parseInt(c))

const a1 = input.reduce((sum, value, index) => {
  if (value === input[(index + 1) % input.length]) {
    return sum + value
  }
  return sum
}, 0)

console.log(`Solution 1.1: \t ${a1}`)


const a2 = input.reduce((sum, value, index) => {
  if (value === input[(index + (input.length /2)) % input.length]) {
    return sum + value
  }
  return sum
}, 0)


console.log(`Solution 1.1: \t ${a2}`)
