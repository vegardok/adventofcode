const fs = require('fs')
const input = String(fs.readFileSync( __dirname + '/../inputs/21.txt'))
      .trim()
      .split('\n')

const instructions = input.map(l => {
  const [ op, a, b, c ] = l.split(' ');
  return [ op, a && parseInt(a), b && parseInt(b),c && parseInt(c)]
})

const OPS = {
  addr: (a, b, c, regs) => { regs[c] = regs[a] + regs[b] },
  addi: (a, b, c, regs) => { regs[c] = regs[a] + b },
  mulr: (a, b, c, regs) => { regs[c] = regs[a] * regs[b] },
  muli: (a, b, c, regs) => { regs[c] = regs[a] * b },
  banr: (a, b, c, regs) => { regs[c] = regs[a] & regs[b] },
  bani: (a, b, c, regs) => { regs[c] = regs[a] & b },
  borr: (a, b, c, regs) => { regs[c] = regs[a] | regs[b] },
  bori: (a, b, c, regs) => { regs[c] = regs[a] | b },
  setr: (a, b, c, regs) => { regs[c] = regs[a] },
  seti: (a, b, c, regs) => { regs[c] = a },
  gtir: (a, b, c, regs) => { regs[c] = a > regs[b] ? 1 : 0 },
  gtri: (a, b, c, regs) => { regs[c] = regs[a] > b ? 1 : 0 },
  gtrr: (a, b, c, regs) => { regs[c] = regs[a] > regs[b] ? 1 : 0 },
  eqir: (a, b, c, regs) => { regs[c] = a === regs[b] ? 1 : 0 },
  eqri: (a, b, c, regs) => { regs[c] = regs[a] === b ? 1 : 0 },
  eqrr: (a, b, c, regs) => { regs[c] = regs[a] === regs[b] ? 1 : 0 },
}

const [ ir, irN ] = instructions[0]

let program = instructions.slice(1)
let regs = new Array(5).fill(0)

let count = 0

// exit cond: r2 < 255 && r4 == r0
regs = [0, 0, 0, 0, 0, 0]

const MAX_ITER = 1
count = 0
for (let i = regs[irN]; i < program.length && count < MAX_ITER; i++) {

  regs[irN] = i

  const op = program[i]
  const before = regs.slice()
  OPS[op[0]](op[1], op[2], op[3], regs)


  if (true || i == 16 && regs[2] < 255) {
    // console.log(regs[4])
    console.log(`|${i} |${op[0]} ${op[1]} ${op[2]} ${op[3]} |${regs.join('|')} | ${count} |`)
  }


  i = regs[irN]

  count++
}




// 10551315 to low
console.log(`Final reg 0 part 2: ${regs[0]}, after ${count} iters` )

console.log('Part 2')
// 119548 to low
