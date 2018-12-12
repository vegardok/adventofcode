const fs = require('fs')

let input = String(fs.readFileSync(__dirname + '/../inputs/7.txt'))
    .trim()
    .split('\n')
    .map(l => {
      l = l.split(' ')
      let [ name, w, a, ...children ] = l
      children = children.map(s => s.replace(',', ''))
      w = parseInt(w.replace('(', '').replace(')', ''))

      return { name, w, children }
    })

function range(start, stop) {
  var r = []
  for (let i = start; i < stop; i++) {
    r.push(i);
  }
  return r
}

input = input.reduce((a, n) => {
  a[n.name] = n // { children: n.children, w: n.w };
  return a
}, {})

Object
  .entries(input)
  .reduce((a, [name, node]) => {
    node.children.forEach(child => a[child].parent = name)
    a[name].children = node.children.map(child => a[child])
    return a
  }, input)


let currentNode = Object.keys(input)[0]

while (input[currentNode].parent) {
  currentNode = input[currentNode].parent
}

function realWeight(node) {
  return node.w + node.children.reduce((s, node) => s + realWeight(node), 0)
}

console.log(`Sollution 7.1:\t ${input[currentNode].name}`)

while(new Set(input[currentNode].children.map(c => realWeight(c))).size !== 1) {
  const weights = input[currentNode].children.reduce((a, c, i) => {

    const w = realWeight(c);
    a[w] = a[w] || 0
    a[w] += 1

    return a
  }, {})

  const nextW = parseInt(Object.entries(weights).sort((t1, t2) => t2[1] = t1[1])[0][0])

  const nextI = input[currentNode].children.map(c => realWeight(c)).indexOf(nextW)
  currentNode = input[currentNode].children[nextI].name
}


const unbalanced = input[input[currentNode].parent].children.map(c => [c.w, realWeight(c)])

console.log({ unbalanced })
