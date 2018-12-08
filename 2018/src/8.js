const fs = require('fs')
const {
  List,
  Map,
  Range,
  fromJS
} = require('immutable')

const input =String(fs.readFileSync(__dirname + '/../inputs/8.txt'))
      .trim()
      .split(' ')
      .map(c => parseInt(c))

let tree = Map()

function build(s, id=[]) {
  const childCount = s.splice(0, 1)[0]
  const dataCount = s.splice(0, 1)[0]
  let data = []

  if (childCount !== 0) {
    for (let i = 0; i < childCount; i++ ) {
      s = build(s, [...id, i])
    }
  }
  data = s.splice(0, dataCount)

  tree = tree.setIn([...id, 'value'], fromJS(data))
  return s
}


build(input)

const sumList = l => l.reduce((s, v) => s + v, 0)
function sum(tree) {
  const v = sumList(tree.get('value', List()))
  return v + Range(0, tree.size - 1).reduce((s, i) => {
    return s + sum(tree.get(i))
  }, 0)
}

const a1 = sum(tree)
console.log(`Solution 8.1: \t ${a1}`)

function altSum(tree) {
  // console.log(JSON.stringify(tree.toJS()))
  const defaultValue = Map({ value: List()})
  if (tree.size === 1) {
    return sumList(tree.get('value'))
  }
  return sumList(tree.get('value').map(
    i => altSum(tree.get(i-1, defaultValue))
  ).flatten())

}


const a2 = altSum(tree)
console.log(`Solution 8.2: \t ${a2}`)
