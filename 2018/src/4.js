const fs = require('fs')
const inputs = String(fs.readFileSync(__dirname + '/../inputs/4.txt')).trim().split('\n').sort()

const startIndexes = inputs.reduce(
  (accl, line, i) => line.indexOf('Guard') === -1 ? accl : accl.concat(i), []
)

function date(l) {
  return l.substr(1).split('] ')[0];
}

const shifts = startIndexes.map((start, i) => {
  const [startShift, rest] = inputs[start].substr(1).split('] ')
  const id = rest.split('Guard ')[1].split(' ')[0]
  const sleepCycles = [];

  const stop = startIndexes[i + 1] || startIndexes.length
  for (let i = start + 1; i < stop; i += 2) {
    let asleep = date(inputs[i]);
    let awake = date(inputs[i+1]);
    sleepCycles.push({
      asleep, awake
    })
  }

  return {
    id,
    startShift,
    sleepCycles
  };
})

const guards = shifts.reduce((accl, shift) => {
  const sleep = (accl[shift.id] || []).concat(shift.sleepCycles)
  accl[shift.id] = sleep;
  return accl;
}, {})

const sleepSum = Object.entries(guards).map(([id, sleepCycles]) => ({
  id,
  sleepSum: sleepCycles.reduce((accl, nap) => {
    const start = parseInt(nap.asleep.split(' ')[1].split(':')[1])
    const stop = parseInt(nap.awake.split(' ')[1].split(':')[1])
    return accl + (stop - start)
  }, 0)
}))

const sleepyGuard = sleepSum.sort((a,b) => b.sleepSum - a.sleepSum)[0]

const hardestMinute = guards[sleepyGuard.id].reduce((accl, nap) => {
  const start = parseInt(nap.asleep.split(' ')[1].split(':')[1])
  const stop = parseInt(nap.awake.split(' ')[1].split(':')[1])
  for (let i = start; i < stop; i += 1) {
    accl[i] += 1;
  }
  return accl;
}, new Array(60).fill(0)).map((c,i) => ([c,i])).sort((a,b) => b[0] - a[0])[0][1]

const answer = parseInt(sleepyGuard.id.substr(1)) * hardestMinute

console.log(`Solution 4.1: \t ${answer}`)

// --- Part Two ---

const sleepers = Object.entries(guards)
      .reduce((accl, [id, sleep]) => {
        sleep.forEach(({ asleep, awake }) => {
          const start = parseInt(asleep.split(' ')[1].split(':')[1])
          const stop = parseInt(awake.split(' ')[1].split(':')[1])
          for (let i = start; i < stop; i++) {
            accl[i][id] = (accl[i][id] || 0) + 1
          }
        })
        return accl;
      }, new Array(60).fill().map(() => ({ foo: 0})))
      .map((c, i) => [i, Object.entries(c).sort((a,b) => b[1] - a[1])[0]])
      .sort((a, b) => ((b[1] || [])[1] || 0) - ((a[1] || [])[1] || 0))[0]

const answer2 = sleepers[0] * parseInt(sleepers[1][0].substr(1))

console.log(`Solution 4.2: \t ${answer2}`)
console.log()
