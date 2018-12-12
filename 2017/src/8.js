const fs = require('fs')

let input = String(fs.readFileSync(__dirname + '/../inputs/8.txt'))
    .trim()
    .split('\n')
    .map(l => {
      const [r, op, v, i, cond_reg, cond_op, cond_v] = l.split(' ')
      return { r, op, v: parseInt(v), cond_reg, cond_op, cond_v: parseInt(cond_v) }
    })

const registers = {}

const operations = {
  '>': (r, v) => (registers[r] || 0) > v,
  '>=': (r, v) => (registers[r] || 0) >= v,
  '<': (r, v) => (registers[r] || 0) < v,
  '<=': (r, v) => (registers[r] || 0) <= v,
  '==': (r, v) => (registers[r] || 0) === v,
  '!=': (r, v) => (registers[r] || 0) !== v,
  inc: (r, v) => registers[r] = (registers[r] || 0) + v,
  dec: (r, v) => registers[r] = (registers[r] || 0) - v
}

const calc = ({ r, op ,v ,cond_reg ,cond_op, cond_v }) => {
  if (operations[cond_op](cond_reg, cond_v)) {
    operations[op](r, v)
  }
}


let highestValue = 0
 input.forEach(op => {
  calc(op)
  highestValue = Math.max(highestValue, Object.entries(registers).sort((a,b) => b[1] - a[1])[0][1])
})

const currentLargestReg = Object.entries(registers).sort((a,b) => b[1] - a[1])[0]

console.log({ currentLargestReg, highestValue })
