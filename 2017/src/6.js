const fs = require('fs')

let input = String(fs.readFileSync(__dirname + '/../inputs/6.txt'))
    .trim()
    .split('\t')
    .map(d => parseInt(d))

// input = [0, 2, 7, 0]

function range(start, stop) {
  var r = []
  for (let i = start; i < stop; i++) {
    r.push(i)
  }
  return r
}

let seenStates = {}

console.log(seenStates)

const k = state => state.join('.')
const seen = state => Boolean(seenStates[k(state)])

function redist(a) {
  a = a.slice()
  const m = Math.max(...a)
  const i = a.indexOf(m)
  a[i] = 0
  range(i+1, i + m + 1).forEach(i => a[i%a.length]++)
  return a
}

redist(input)

let count = 0;
while(!seen(input)) {
  count += 1
  seenStates[k(input)] = true
  input = redist(input)
}

console.log({ count })


count = 0;
seenStates = {}
while(!seen(input)) {
  count += 1
  seenStates[k(input)] = true
  input = redist(input)
}

console.log({ count })
