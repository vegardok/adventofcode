const fs = require('fs')
const inputs = String(fs.readFileSync(__dirname + '/../inputs/4.txt'))
      .trim()
      .split('\n')
      .sort()

const startIndexes = inputs.reduce(
  (accl, line, i) => line.indexOf('Guard') === -1 ? accl : accl.concat(i), []
)

const getMinutes = s => parseInt(s.split(' ')[1].split(':')[1])
const date = l => l.substr(1).split('] ')[0]
const sum = (l, fn = i => i) => l.reduce((a, i) => a + fn(i), 0)

const shifts = startIndexes.map((start, i) => {
  const [startShift, rest] = inputs[start].substr(1).split('] ')
  const id = parseInt(rest.split('Guard ')[1].split(' ')[0].substr(1))
  const sleepCycles = [];

  const stop = startIndexes[i + 1] || startIndexes.length
  for (let i = start + 1; i < stop; i += 2) {
    let asleep = getMinutes(date(inputs[i]))
    let awake = getMinutes(date(inputs[i+1]))
    sleepCycles.push({
      asleep,
      awake,
      sleepTime: awake - asleep
    })
  }

  return {
    id,
    startShift,
    sleepCycles
  };
})

const guards = shifts.reduce((accl, shift) => ({
  ...accl,
  [shift.id]: (accl[shift.id] || []).concat(shift.sleepCycles)
}), {})

const sleepSum = Object.entries(guards)
      .map(([id, sleepCycles]) => ({
        id,
        sleepSum: sum(sleepCycles, o => o.sleepTime)
      }))

const sleepyGuard = sleepSum.sort((a,b) => b.sleepSum - a.sleepSum)[0]

const hardestMinute = guards[sleepyGuard.id]
      .reduce((accl, { asleep, awake }) => {
        for (let i = asleep; i < awake; i += 1) {
          accl[i] += 1;
        }
        return accl;
      }, new Array(60).fill(0))
      .map((c,i) => ([c,i]))
      .sort((a,b) => b[0] - a[0])[0][1]

console.log(`Solution 4.1: \t ${sleepyGuard.id * hardestMinute}`)

// --- Part Two ---

const sleepers = Object.entries(guards)
      .reduce((accl, [id, sleep]) => {
        sleep.forEach(({ asleep, awake }) => {
          for (let i = asleep; i < awake; i++) {
            accl[i][id] = (accl[i][id] || 0) + 1
          }
        })
        return accl;
      }, new Array(60).fill().map(() => ({ foo: 0 })))
      .map((c, i) => [i, Object.entries(c).sort((a,b) => b[1] - a[1])[0]])
      .sort((a, b) => b[1][1] - a[1][1])[0]

console.log(`Solution 4.2: \t ${sleepers[0] * sleepers[1][0]}`)
console.log()
