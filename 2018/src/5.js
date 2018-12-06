// For example:

// In aA, a and A react, leaving nothing behind.
// In abBA, bB destroys itself, leaving aA. As above, this then destroys itself, leaving nothing.
// In abAB, no two adjacent units are of the same type, and so nothing happens.
// In aabAAB, even though aa and AA are of the same type, their polarities match, and so nothing happens.
// Now, consider a larger example, dabAcCaCBAcCcaDA:

// dabAcCaCBAcCcaDA  The first 'cC' is removed.
// dabAaCBAcCcaDA    This creates 'Aa', which is removed.
// dabCBAcCcaDA      Either 'cC' or 'Cc' are removed (the result is the same).
// dabCBAcaDA        No further actions can be taken.
// After all possible reactions, the resulting polymer contains 10 units.


const fs = require('fs')
const inputs = String(fs.readFileSync(__dirname + '/../inputs/5.txt'))
      .trim()
      .split('')
      .map(c => c.charCodeAt(0))

const POLAR_DISTANCE = 'a'.charCodeAt(0) - 'A'.charCodeAt(0)

let part1 = inputs.slice()

function minLength(part1) {
  let working = true;
  while (working) {
    working = false
    for (let i = 0; i < part1.length; i++) {
      if (Math.abs(part1[i] - part1[i+1]) === POLAR_DISTANCE) {
        part1.splice(i, 2)
        i -= 1
        working = true
      }
    }
    part1 = part1.filter(c => c > 0)
  }
  return part1.length
}


console.log(`Solution 5.1: \t ${minLength(part1)}`) // 10978

// Part 5

function range(start, stop) {
  const l = []
  for (let i = start; i < stop; i++) {
    l.push(i)
  }
  return l;
}

const a2 = range('A'.charCodeAt(0), 'Z'.charCodeAt(0))
      .reduce((accl, i) => {
        let part2 = inputs.filter(c => c !== i && c !== i + 32);
        return Math.min(accl, minLength(part2));
      }, inputs.length)


console.log(`Solution 5.2: \t ${a2}`) // 4840
console.log()
