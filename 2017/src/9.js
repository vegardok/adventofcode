// Beware, here be some ugly code

const fs = require('fs')

let input = String(fs.readFileSync(__dirname + '/../inputs/9.txt'))
    .trim()

input = input.split('')

let skip = input[0] === '!'
let garbage = input[0] === '<'

let garbChars = 0

input = input.reduce((a, s) => {
  // console.log({ s, garbage, skip, a })
  let localGarbage = garbage
  let localSkip = skip



  garbage = garbage ? (s !== '>' || skip) : s === '<'
  skip = !localSkip && s === '!'

  if (localSkip || localGarbage || garbage) {
    if ((!localSkip && !skip) && (garbage && localGarbage )) {
      // console.log({ s, i })
      garbChars += 1
    }
    return a
  }

  return a + s

}, "")

let score = 0
let level = 0
input.split('').map(c => {
  if (c === '{') {
    level += 1
  } else if (c === '}') {
    score += level
    level -= 1
  }
})


console.log({ score, garbChars })
