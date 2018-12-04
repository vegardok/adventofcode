const fs = require('fs')

const inputs = String(fs.readFileSync(__dirname + '/../inputs/2.txt')).trim().split('\n')

function count(id) {
  const c = id.split('').reduce((a, s) => {
    a[s] =(a[s] || 0) + 1;
    return a
  }, {});

  return new Set(Object.values(c).filter(v => v > 1));
}

const counts = inputs
      .map(s => count(s))
      .reduce((a, s) => {
        for (let n of s.values()) {
          a[n] = (a[n] || 0) + 1
        }
        return a
      }, {})

const checksum = Object.values(counts).reduce((a, n) => a * n, 1);

console.log(`Solution 2.1: \t ${checksum}`)

// part 2

function distance(a, b) {
  a = a.split('')
  b = b.split('')
  return a.reduce((accl, s, i) => accl + (s === b[i] ? 0 : 1), 0)
}

inputs.find(id1 => {
  return inputs.find(id2 => {
    if (distance(id1, id2) === 1) {
      id1= id1.split('')
      id2= id2.split('')
      const common = id1.filter((s, i) => s === id2[i])

      console.log(`Solution 2.1: \t ${common.join('')}`)
      return true;
    }
  })
})

console.log()
