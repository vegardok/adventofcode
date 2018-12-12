const { Range, Map } = require('immutable')

const fs = require('fs')

let input =String(fs.readFileSync(__dirname + '/../inputs/12.txt'))
    .trim()
    .split('\n')

let data = input[0].split(': ')[1].split('').map(c => c === '#' ? 1 : 0)

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

const GENS = 100000 // 20
const PADDING = 20
const EDGE = 3

let offset = -PADDING

const pots = new Uint8Array((PADDING * 2) + data.length)
pots.set(data, PADDING)


let arrays = {
  prev: pots.slice(),
  current: pots.slice()
}

function shiftRight(a) {
  const tmp = new Uint8Array(a.length + PADDING)
  tmp.set(a, PADDING)
  return tmp

}
function trimLeft(a) {
  const tmp = a.slice(a.indexOf(1) - PADDING, a.length)
  return tmp
}

function shiftLeft(a) {
  const tmp = new Uint8Array(a.length + PADDING)
  tmp.set(a)
  return tmp

}

function trimRight(a) {
  const tmp = new Uint8Array(a.length - PADDING)
  tmp.set(a.slice(0, a.length - PADDING))
  return tmp
}

for (let i = 0; i < GENS + 1; i++) {
  arrays.current = new Uint8Array(arrays.prev.length)
  for(let x = 0; x < arrays.prev.length; x++) {
    arrays.current[x] = getState(arrays.prev, x)
  }

  if (i % 10000 === 0) {
    console.log(i, new Date(), { offset, size: arrays.current.length }, Array.from(arrays.current).reduce((sum, c, i) => {
      return sum + (c === 1 ? (i + (offset)) : 0)
    }, 0))
    // console.log(Array.from(arrays.current).join(''))
  }

  if (arrays.current.indexOf(1) <= EDGE) {
    // console.log(1, arrays.current.indexOf(1) , EDGE)
    // console.log(Array.from(arrays.current).join(''))
    arrays.current = shiftRight(arrays.current)
    // arrays.prev = shiftRight(arrays.prev)
    offset = offset - PADDING
    // console.log (1, { offset })
    // console.log(Array.from(arrays.current).join(''))

    // console.log()
  }

  if (arrays.current.indexOf(1) > (PADDING * 2)) {
    // console.log({ foo: 2}, arrays.current.indexOf(1), (PADDING * 2))
    // console.log(Array.from(arrays.current).join(''))

    arrays.current = trimLeft(arrays.current)
    // arrays.prev = trimLeft(arrays.prev)
    offset = offset + PADDING
    // console.log (2, { offset })

    // console.log(Array.from(arrays.current).join(''))
    // console.log()
  }

  if ((arrays.current.length - arrays.current.lastIndexOf(1)) < EDGE) {
    // console.log(3, (arrays.current.length - arrays.current.lastIndexOf(1)), EDGE)
    // console.log(Array.from(arrays.current).join(''))
    arrays.current = shiftLeft(arrays.current)
    // console.log(3, { offset })
    // arrays.prev = shiftLeft(arrays.prev)
    // console.log(Array.from(arrays.current).join(''))
    // console.log()
  }

  if ((arrays.current.length - arrays.current.lastIndexOf(1)) > (2 * PADDING)) {
    // console.log(4, (arrays.current.length - arrays.current.lastIndexOf(1)), (2 * PADDING))
    // console.log(Array.from(arrays.current).join(''))
    arrays.current = trimRight(arrays.current)
    // console.log(4, { offset })
    // arrays.prev = trimRight(arrays.prev)
    // console.log(Array.from(arrays.current).join(''))
    // console.log()
  }


  // const tmpa = arrays.prev
  arrays.prev = arrays.current
  // arrays.prev = tmpa
}


console.log('done', { offset }) // 2767

// not 6662

var result = Array.from(arrays.current)
console.log(result.join(''))
console.log((result).reduce((sum, c, i) => {
  return sum + (c === 1 ? (i + (offset)) : 0)
}, 0))
