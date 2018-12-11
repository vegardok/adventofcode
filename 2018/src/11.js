

function range(start, stop) {
  var r = []
  for (let i = start; i < stop; i++) {
    r.push(i)
  }
  return r
}

function slice(grid, X, Y, size=3) {
  return grid.slice(Y, Y+ size )
    .reduce((accl, l) => accl.concat(l.slice(X, X+size)), [])
}

function sumSlice(grid, X, Y, size=3) {
  return slice(grid, X, Y, size).reduce((s, i) => s+ i, 0)
}

const GRID_SERIAL_NUMBER = 7857 // TODO

const PRINT_X = 33
const PRINT_Y = 45

const FUEL_TANK = range(0, 301).map(y => range(0, 301).map(x => {

  const rackId = 10 + x
  let powerLevel = rackId * y
  powerLevel += GRID_SERIAL_NUMBER
  powerLevel *= rackId
  powerLevel = parseInt(powerLevel.toString().split('').reverse()[2] || '0')
  powerLevel -= 5
  return powerLevel
}))


// console.log(FUEL_TANK[PRINT_Y][PRINT_X])

console.log(slice(FUEL_TANK, PRINT_X, PRINT_Y, 3).join(' '))
console.log()
console.log(sumSlice(FUEL_TANK, PRINT_X, PRINT_Y))


let largestCoord = [0, 0, 0]
range(0, 301).forEach(y => range(0, 301).forEach(x => {
  const sum = sumSlice(FUEL_TANK, x, y)

  if (sum > largestCoord[2]) {
    largestCoord = [x, y, sum]
  }
}))

console.log(largestCoord)


largestCoord = [0, 0, 0, 1]
range(1, 300).forEach(size => {
  console.log({ size })
  range(0, 301 - size).forEach(y => {
    range(0, 301 - size).forEach(x => {
      const sum = sumSlice(FUEL_TANK, x, y, size)
      if (sum > largestCoord[2]) {
        largestCoord = [x, y, sum, size]
      }
    })
  })
})

console.log(largestCoord)
