const fs = require('fs')
const { Map, Range } = require('immutable')

let input =String(fs.readFileSync(__dirname + '/../inputs/11.txt'))
    .trim()
    .split('\n')

let pots = input[0].split(': ')[1] // .split('') //.map(c => c === '#')

function countSub(s, c) {
  let abort = false
  return s.split('').reduce((sum, char) => {
    abort = abort || c !== char
    return sum + (abort ? 0 : 1)
  }, 0)
}

function pad(s) {
  const leftEmpty = countSub(s, '.')
  const padN = 3 - leftEmpty
  if (leftEmpty > 3) {
    return [padN, s.substr(leftEmpty -3)]
  } else {
    return [padN, Range(0, padN).map(() => '.').join('') + `${s}...`]
  }

  // const padN = 2 - s.substring(0, 2).split('')
  //       .reduce((sum, c) => sum + (c === '.' ? 1 : 0), 0)
  // const padN = s.substring(0, 3) === '...' ? 0 : (s.substring(0, 2) === '..' ? 1 : (s.substring(0, 1) === '.' ? 2 : 3))
  // return [padN, Range(0, padN).map(() => '.').join('') + `${s}...`]
  // return `..${s}..`a
  // const leftPad = 2 - s.indexOf('#')
  // const rightPad = 2 - s.split('').reverse().indexOf('#')

  // return Range(0, leftPad).map(() => '.').join('') + s + Range(0, rightPad).map(() => '.').join('')
}

const GENS = 20

const rules = input.slice(2).reduce((rules, rule) => {
  let [match, result] = rule.split(' => ')
  result = result === '#'
  return rules.set(match, result)
}, Map())

// function getState(pots, i) {
//   let left = i < 2
// }

const get = (c, n) => Range(0, n).map(() => c).join('')

// console.log(JSON.stringify(rules.toJS(), null, 2))
let offset = 0
let padN
const done = Range(1, GENS+1).map(gen => {
  // console.log(pots);
  [padN, pots] = pad(pots)
  offset -= padN

  // console.log(gen, pots)

  pots = Range(0, pots.length).map(i => {
    let neighbours = pots.substring(i - 2, i + 3) // TODO
    // if (i < 2) {
    //   neighbours = get('.', i - 2) + neighbours
    // }
    // if (i > pots.length -2) {
    //   neighbours = neighbours + get('.', pots.length -i)
    // }

    // console.log(i, neighbours, rules.get(neighbours, 'not found') || 'found')

    let alive = rules.get(neighbours, false) // .split(''))

    return alive ? '#' : '.'
  }).join('')

  console.log(gen, padN, pots)
  // console.log()
  return pots.split('').reduce((sum, c, i) => sum + (c === '#' ? 1 : 0), 0)
})
console.log(done)
console.log(offset)

console.log(pots)
console.log(pots.split('').reduce((sum, c, i) => sum + (c === '#' ? i-offset : 0), 0))
