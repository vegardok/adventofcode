const fs = require('fs')
const {
  List,
  Map,
  Range,
  fromJS
} = require('immutable')

const alt = '2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2'

const input = alt // String(fs.readFileSync(__dirname + '/../inputs/8.txt'))
      .trim()
      .split(' ')
      .map(c => parseInt(c))

function build(s, id=0, tree=Map()) {
  console.log(s)
  if (s.length === 0) {
    return [s, tree]
  }
  const childCount = s.splice(0, 1)[0]
  const dataCount = s.splice(0, 1)[0]



  if (childCount !== 0) {
    for (let i = 0; i < childCount; i++ ) {
      const foo = build(s, i, s)
      s = foo[0]
      // tree = foo[1]
      console.log(i)
      tree = foo[1].set(i, tree)
    }
  }
  const value = fromJS(s.splice(0, dataCount))
  console.log({ tree })
  return [s, tree.set('value', value)]
}


const [s, tree] = build(input)

console.log(JSON.stringify(tree.toJS(), null, 2))

process.exit(0)

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
console.log(a1 === 46781)


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
console.log(a2 === 21405)
