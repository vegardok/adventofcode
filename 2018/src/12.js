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


const GENS = 1000000
const PADDING = 5
const EDGE = 2

let offset = -PADDING

let pots = new Uint8Array([...(new Array(PADDING).fill(0)), ...data, ...(new Array(PADDING).fill(0))])


function sumPots (a, hmm=NaN) {
  return Array.from(a).reduce((sum, c, i) => {
    return sum + (c === 1 ? (i + hmm) : 0)
  }, 0)

}

let newPots
for (let i = 0; i < GENS; i++) {
  newPots = pots.slice()

  for (let x = 0; x < pots.length; x++) {
    newPots[x] = getState(pots, x)
  }

  if (i % 1000000 === 0) { console.log(i, sumPots(pots, offset)) }

  if (newPots.indexOf(1) <= EDGE) {
    const tmp = new Uint8Array(newPots.length + PADDING)
    tmp.set(newPots, PADDING)
    newPots = tmp
    offset -= PADDING
  } else if (newPots.indexOf(1) > (PADDING * 2)) {
    newPots = newPots.slice(PADDING)
    offset += PADDING
  }

  if ((newPots.length - newPots.lastIndexOf(1)) <= EDGE) {
    const tmp = new Uint8Array(newPots.length + PADDING)
    tmp.set(newPots)
    newPots = tmp
  } else if ((newPots.length - newPots.lastIndexOf(1) - 1) >= (2 * PADDING)) {
    newPots = newPots.slice(0, newPots.length - PADDING)
  }

  pots = newPots
}
var result = pots
console.log(sumPots(pots, offset))
console.log(result.slice(result.indexOf(1) -2, result.lastIndexOf(1) + 2).join(''))
