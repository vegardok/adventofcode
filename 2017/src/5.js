const fs = require('fs')

let input = String(fs.readFileSync(__dirname + '/../inputs/5.txt'))
    .trim()
    .split('\n')
    .map(d => parseInt(d))

function range(start, stop) {
  var r = []
  for (let i = start; i < stop; i++) {
    r.push(i)
  }
  return r
}

let current = 0
let steps = 0
let instructions = input.slice()

while (current < instructions.length) {
  const tmp = instructions[current]
  instructions[current] += 1

  current += tmp
  steps += 1
}

console.log({ steps })

current = 0
steps = 0
instructions = input.slice()

let prevJump = 0
while (current < instructions.length) {
  const tmp = instructions[current]
  instructions[current] += (instructions[current] >= 3 ? -1 :1)

  current += tmp
  steps += 1
}

console.log({ steps })
