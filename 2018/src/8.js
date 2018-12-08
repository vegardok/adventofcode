const fs = require('fs')
const {
  List,
  Map,
  OrderedMap,
  Range,
  Set,
  fromJS
} = require('immutable')



const alt = '2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2'
// 0 3 10 11 12 1 1 0 1 99 2
// const alt = '0 3 1 2 10'
const input =String(fs.readFileSync(__dirname + '/../inputs/8.txt'))
      .trim()
      .split(' ')
      .map(c => parseInt(c))


let tree = Map()

function build(s, id=[]) {
  if (s.length === 0) {
    console.log('ping')
    return List()
  }
  const childCount = s.splice(0, 1)[0]
  const dataCount = s.splice(0, 1)[0]
  let data = []

  if (childCount !== 0) {
    for (let i = 0; i < childCount; i++ ) {
      s = build(s, [...id, i])

    }
  }
  data = s.splice(0, dataCount)
  // console.log({ id, data })
  tree = tree.setIn([...id, 'value'], fromJS(data))
  // console.log(tree)
  // valueNodes.push(data)
  return s
}


build(input)
const sumReducer = l => l.reduce((s, v) => s + v, 0)
function sum(tree) {
  const v = sumReducer(tree.get('value', List()))
  return v + Range(0, tree.size -1).reduce((s, i) => {
      return s + sum(tree.get(i))
    }, 0)
}

// console.log(JSON.stringify(tree.toJS(), null, 2)) //

const a1 = sum(tree)

console.log('sum', a1)


function sum2(tree) {
  // console.log(JSON.stringify(tree.toJS()))
  const defaultValue = Map({ value: List()})
  if (tree.size === 1) {
    return sumReducer(tree.get('value'))
  }
  return sumReducer(tree.get('value').map(
    i => sum2(tree.get(i-1, defaultValue))
  ).flatten())

}


const a2 = sum2(tree)

console.log(a2)
