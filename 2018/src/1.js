const fs = require('fs')

const input = String(fs.readFileSync(__dirname + '/../inputs/1.txt')).trim()

const numbers = input.split('\n')
      .map(s => parseInt(s))
      .filter(n => typeof n === 'number')

const sum = numbers.reduce((accl, n) => accl += n, 0)

console.log(`Solution 1.1: \t ${sum}`)

// part 2
function* getNumbers () {
  let _numbers = [0]

  let n = 0
  while(true) {
    _numbers[n + 1] = _numbers[n] + numbers[n % numbers.length]
    yield _numbers[n + 1]
    n+=1
  }
}

const seen = {}
for (let n of getNumbers()) {
  if (seen[n]) {
    console.log(`Solution 1.2: \t ${n}`)
    break
  } else {
    seen[n] = true
  }
}
