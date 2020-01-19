const fs = require('fs')

const program = String(fs.readFileSync(__dirname + '/../inputs/18.txt'))
      .trim()
      .split('\n')
      .map(l => {
        let [op, a, b] = l.split(' ')
        let t = parseInt(a)
        if (!isNaN(t)) {
          a = t
        }
        t = parseInt(b)
        if (!isNaN(t)) {
          b = t
        }
        return [op, a, b]
      })

let SOUND;
let i = 0;


const regs = {}

program.forEach(l => {
  if (!parseInt(l[1])) {
    regs[l[1]] = 0
  }
})


function get(x) {
  return typeof x == 'number' ? x : regs[x]
}

const OPS = {
  // snd X plays a SOUND with a frequency equal to the value of X.
  snd: (x) => SOUND = regs[x],
  // set X Y sets register X to the value of Y.
  set: (x, y) => regs[x] = get(y),
  // add X Y increases register X by the value of Y.
  add: (x, y) => regs[x] += get(y),
  // mul X Y sets register X to the result of multiplying the value contained in register X by the
  // value of Y.
  mul: (x, y) => regs[x] *= get(y),
  // mod X Y sets register X to the remainder of dividing the value contained in register X by the
  // value of Y (that is, it sets X to the result of X modulo Y).
  mod: (x, y) => regs[x] = regs[x] % get(y),
  // rcv X recovers the frequency of the last SOUND played, but only when the value of X is not
  // zero. (If it is zero, the command does nothing.)
  rcv: x => x && SOUND,

  // jgz X Y jumps with an offset of the value of Y, but only if the value of X is greater than
  // zero. (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous
  // instruction, and so on.)
  jgz: (x, y) => {
    if (get(x) > 0) {
      i += get(y)
    }
  }
}


let count = 0
for (; i < program.length; i++) {
  let [op, a, b] = program[i]
  b = b && parseInt(b) || b
  // console.log(program[i], regs)
  OPS[op](a, b)

  console.log(i, SOUND, program[i], regs)

  if (op === 'rcv' && SOUND > 0) {
    console.log('done')
    console.log(SOUND)
    process.exit(0)
  }
}
