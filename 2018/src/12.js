const { Range, Map } = require('immutable')

const fs = require('fs')

const input =String(fs.readFileSync(__dirname + '/../inputs/12.txt'))
    .trim()
    .split('\n')

const data = input[0].split(': ')[1].split('').map(c => c === '#' ? 1 : 0)

const rules = input.slice(2).reduce((rules, rule) => {
  let [match, result] = rule.split(' => ')
  match = match.split('').map(c => c === '#' ? '1' : '0').join('')
  result = result === '#'
  rules[match] = result
  return rules
}, {})


let rules2 = Map()

Range(0, 2).forEach(
  i1 => Range(0, 2).forEach(
    i2 => Range(0, 2).forEach(
      i3 => Range(0, 2).forEach(
        i4 => Range(0, 2).forEach(
          i5 => {
            rules2 = rules2.setIn([i1, i2, i3, i4, i5], rules[[i1, i2, i3, i4, i5].join('') ] ? 1 : 0)
          }
        )
      )
    )
  )
)

rules2 = rules2.toJS()


const getState = (all, i) => {
  return rules2[all[i-2] || 0][all[i-1] || 0][all[i] || 0][all[i+1] || 0][all[i+2] || 0]
}

function sumPots (a, offset=NaN) {
  return a.reduce((sum, c, i) => {
    return sum + (c === 1 ? (i + offset) : 0)
  }, 0)

}

const PADDING = 5
const EDGE = 2

function spreadFlowers(pots, gens) {
  let newPots

  let offset = -PADDING
  for (let i = 0; i < gens; i++) {
    newPots = pots.slice()

    for (let x = 0; x < pots.length; x++) {
      newPots[x] = getState(pots, x)
    }

    if (newPots.indexOf(1) <= EDGE) {
      newPots = [...new Array(PADDING).fill(0), ...newPots]
      offset -= PADDING
    } else if (newPots.indexOf(1) > (PADDING * 2)) {
      newPots = newPots.slice(PADDING)
      offset += PADDING
    }

    if ((newPots.length - newPots.lastIndexOf(1)) <= EDGE) {
      newPots = [...newPots, ...new Array(PADDING).fill(0)]
    } else if ((newPots.length - newPots.lastIndexOf(1) - 1) >= (2 * PADDING)) {
      newPots = newPots.slice(0, newPots.length - PADDING)
    }

    pots = newPots
  }
  return [ pots, offset ]
}

let initialPots = [...(new Array(PADDING).fill(0)), ...data, ...(new Array(PADDING).fill(0))]

const [result1, offset] = spreadFlowers(initialPots.slice(), 20)

console.log(`12 part 1: ${sumPots(result1, offset)}`, offset)

const ROUNDS = 1000

const [pots2, offset2] = spreadFlowers(initialPots.slice(), ROUNDS)
const result2 = sumPots(pots2, offset2)
const [pots3, offset3] = spreadFlowers(initialPots.slice(), ROUNDS * 2)
const result3 = sumPots(pots3, offset3)

const atGenX = gens => result2 % (result3 - result2) + (((result3 - result2) / ROUNDS) * gens)

console.log(console.log(`12 part 2: ${atGenX(50000000000)}`))
