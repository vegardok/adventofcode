const fs = require('fs')

const inputs = String(fs.readFileSync(__dirname + '/../inputs/3.txt')).trim().split('\n')

const claims = inputs.map(s => {
  const [id, rest] = s.split(' @ ')
  const [cord, size] = rest.split(': ')
  const [y, x] = cord.split(',').map(n => parseInt(n))
  const [h, w] = size.split('x').map(n => parseInt(n))
  return { id, x, y, h, w }
})

const [X, Y] = claims.reduce((accl, claim) => {
  const right = claim.x + claim.w
  const bottom = claim.y + claim.h
  return [
    accl[0] < right ? right : accl[0],
    accl[1] < bottom ? bottom : accl[1]
  ]
}, [0, 0])

const fabric = new Array(X * Y).fill(0)

function range (a, b) {
  return new Array(b - a).fill().map((v, i) => a + i)
}

claims.forEach(claim => {
  range(claim.x, claim.x + claim.w).forEach(x => {
    range(claim.y, claim.y + claim.h).forEach(y => {
      fabric[(x * X) + y] += 1
    })
  })
})

if (X * Y < 100) {
  console.log(fabric.map(l => l.join('.')).join('\n'))
}

const collisionCount = fabric.filter(i => i >= 2).length

console.log(`Solution 3.1: \t ${collisionCount}`)

// Part 2

const soloClaim = claims.find(claim => {
  let found = true
  range(claim.x, claim.x + claim.w).forEach(x => {
    range(claim.y, claim.y + claim.h).forEach(y => {
      found = found && fabric[(x * X) + y] === 1
    })
  })
  return found
})

console.log(`Solution 3.2: \t ${soloClaim.id}`)

console.log()
