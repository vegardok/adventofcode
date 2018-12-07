const fs = require('fs')
const { range, uniq } = require('lodash')

const inputs = String(fs.readFileSync(__dirname + '/../inputs/7.txt'))
      .trim()
      .split('\n')
      .map(l => l.split(' '))
      .map(a => ({ step: a[1], before: a[7]}))

var a = uniq(inputs.reduce((l, n) => [...l, n.step, n.before], [])).sort()

let graph = a.reduce((g, a) => {
  g[a] = {
    step: a,
    depBy: [],
  };
  return g
}, {})

graph = Object.values(inputs.reduce((graph, { step, before }) => {
  graph[before].depBy.push(step)
  return graph
}, graph))


const ready = node => node.depBy.length === 0
let s = ''

let g1 = [...graph]

while (g1.length > 0) {
  const next = g1.filter(n => ready(n)).map(n => n.step).sort()[0]
  g1 = g1.map(n => ({
    ...n,
    depBy: n.depBy.filter(d => d !== next)

  })).filter(n => n.step !== next)

  s += next
}

console.log(`Solution 7.1: \t ${s}`)

// part 2

const cost = a => 61 + (a.charCodeAt(0) - 65)

let g2 = graph.map(n => ({ ...n, remaining: cost(n.step) }))

let WORKERS = range(5).reduce((w, i) => { w[`worker_${i}`] = null; return w}, {})

const working = node => Object.values(WORKERS).indexOf(node.step) !== -1;
let i = 0
while (g2.length > 0) {
  const candidates = g2.filter(n => ready(n) && !working(n) ).map(n => n.step).sort()

  const freeWorkers = Object.entries(WORKERS)
        .filter((n) => !n[1]).map(w => w[0])
  const next = candidates.slice(0, freeWorkers.length)


  next.forEach((job, i) => {
    WORKERS[freeWorkers[i]] = job
    g2.filter(s => s.step === job)[0].worker = freeWorkers[i]
  })

  const done = []
  g2 = g2.map(node => ({
    ...node,
    remaining: node.worker ? node.remaining - 1 : node.remaining
  })).map(node => {
    if (node.remaining === 0) {
      done.push(node.step)
      WORKERS[node.worker] = null

      return {
        ...node,
        worker: null
      }
    } else {
      return node
    }
  })
  if (done.length > 0) {
    g2 = g2.map(n => ({
      ...n,
      depBy: n.depBy.filter( d => done.indexOf(d))
    })).filter(node => done.indexOf(node.step) !== 0)

  }
  i += 1
}
console.log(`Solution 7.2: \t ${i}`)
