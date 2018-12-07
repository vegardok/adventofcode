const fs = require('fs')
const {
  List,
  Map,
  OrderedMap,
  Range,
  Set,
  fromJS
} = require('immutable')


const input = String(fs.readFileSync(__dirname + '/../inputs/7.txt'))
      .trim()
      .split('\n')

const graph = fromJS(input)
      .reduce((accl, i) => {
        const step = i.split(' ')[1]
        const dep = i.split(' ')[7]
        return accl
          .update(dep, Set(), s => s.add(step))
          .set(step, accl.get(step) || Set())
      }, Map())

function getDepOrder(graph, done = List()) {
  const next = graph
        .filter((deps, key) => !done.includes(key))
        .filter(deps => deps.filterNot(d => done.includes(d)).isEmpty())
        .keySeq().sort().first()
  if (next) {
    return List(next).concat(getDepOrder(graph, done.concat(next)))
  }
  return ''
}

const a1 = getDepOrder(graph).join('')

console.log(`Solution 7.1: \t ${a1}`)

// part 2


const WORKER_COUNT = 5
const cost = a => 60 + (a.charCodeAt(0) - 64)

const graph2 = graph.map((deps, key) => Map({
  deps,
  remaining: cost(key),
  working: false
}))


function countIterations(graph, done = List(), workers = WORKER_COUNT, steps = 0) {
  const remaining = graph.reduce((s, v) => s + v.get('remaining'), 0)
  if (remaining === 0 || steps >= 1500) {
    return steps
  }

  const startWorking = graph
        .filter((deps, key) => !done.includes(key))
        .filter(node => node.get('deps').filterNot(
          d => done.includes(d)).isEmpty())
        .filter(node => !node.get('working'))
        .keySeq()
        .take(workers)

  const doneNow = graph.filter(node => node.get('remaining') === 1 && node.get('working')).keySeq()

  const newGraph = startWorking
        .reduce((a, v) => {
          return a.setIn([v, 'working'], true)
        }, graph)
        .map(node => node
             .update('remaining',
                     r => node.get('working') ? r - 1 : r)
             .update('working',
                     working => working && node.get('remaining') !== 1)
            )
  return countIterations(
    newGraph,
    done.concat(doneNow),
    workers - startWorking.size + doneNow.size,
    steps + 1
  )
}

const a2 = countIterations(graph2)

console.log(`Solution 7.2: \t ${a2}`)
