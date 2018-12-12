

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

function getEdge(grid, X, Y, size) {
  let nums = []
  range(Y, Y+size).map((y, yN) => {
    if (yN < (size-1)) {
      nums.push((grid[y] || [])[X+(size-1)] || 0)
    } else {
      nums = nums.concat((grid[y] || []).slice(X, X + size))
    }
  })
  return nums
}


const GRID_SERIAL_NUMBER = 7857

const FUEL_TANK = range(0, 301).map(y => range(0, 301).map(x => {

  const rackId = 10 + x
  let powerLevel = rackId * y
  powerLevel += GRID_SERIAL_NUMBER
  powerLevel *= rackId
  powerLevel = parseInt(powerLevel.toString().split('').reverse()[2] || '0')
  powerLevel -= 5
  return powerLevel
}))

const INDEX = range(0, 301).map(
  y => {
    console.log(y)
    return range(0, 301).map(
      x => {
        const sizes = range(2, 300 - Math.min(x, y)).reduce(
          (accl, size) => {
            return accl.concat(accl.slice(-1)[0] + getEdge(FUEL_TANK, x, y, size).reduce((s, i) => s + i, 0))
          }, [FUEL_TANK[y][x]])
        const m = Math.max(...sizes)
        const i = sizes.indexOf(m)
        return [m, i]
      })
  })


let largestCoord = [0, 0, 0]
range(0, 301).forEach(y => range(0, 301).forEach(x => {
  const sum = sumSlice(FUEL_TANK, x, y)

  if (sum > largestCoord[2]) {
    largestCoord = [x, y, sum]
  }
}))

console.log(largestCoord)


largestCoord = [0, 0, 0, 1]

range(0, 301).forEach(y => {
  range(0, 301).forEach(x => {
    const [sum, index] = INDEX[y][x]
    if (sum > largestCoord[2]) {
      largestCoord = [x, y, sum, index+1]
    }
  })
})

console.log(largestCoord)
