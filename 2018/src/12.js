const fs = require('fs')
const { Map, Range } = require('immutable')

let input =String(fs.readFileSync(__dirname + '/../inputs/12.txt'))
    .trim()
    .split('\n')

const pots = input[0].split(': ')[1] // .split('') //.map(c => c === '#')

function trim(str, c, leading=true) {
  let i;
  if (leading) {
    i = 0;
    while(str.charAt(i) === c){
      i++
    }
    return [str.substr(i), i]
  } else {
    i = str.length-1
    while (str.charAt(i) === c) {
      i -= 1
    }
    return [str.substring(0, i+1), Math.abs(i - (str.length-1))]
  }
}

// function trimaa(, c, leading=true) {
//   let i;
//   if (leading) {
//     i = 0;
//     while(strt(i) === c){
//       i++
//     }
//     return [str.substr(i), i]
//   } else {
//     i = str.length-1
//     while (str.charAt(i) === c) {
//       i -= 1
//     }
//     return [str.substring(0, i+1), Math.abs(i - (str.length-1))]
//   }
// }


const GENS = 50000000000

const rules = input.slice(2).reduce((rules, rule) => {
  let [match, result] = rule.split(' => ')
  result = result === '#'
  return rules.set(match, result)
}, Map()).toJS()

const getState = (somePots, i) => {
  const k = Range(i-2, i+3).map(i => somePots[i] || '.').join('')
  // console.log(k, rules[k])
  return rules[k] ? '#' : '.';

}

let removedLeading;
let offset = 0

const finalPots = Range(1, GENS + 1).reduce((workingPots, i) => {
  if (i % 1000 === 0) {
    console.log(i)
  }
  let newPots = Range(-2, workingPots.length + 3).map(i => getState(workingPots, i)).join('');
  [newPots, removedLeading] = trim(newPots, '.');
  [newPots] = trim(newPots, '.', false)
  offset -= (2 - removedLeading)
  // console.log(i, 2 - removedLeading, offset, newPots)
  return newPots
}, pots)


console.log(finalPots, offset)
console.log(finalPots.split('').reduce((sum, c, i) => {
  return sum + (c === '#' ? (i + offset) : 0)

}, 0))
