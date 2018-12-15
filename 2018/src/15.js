var { Range } = require('immutable')

var fs = require('fs')

var MAP =String(fs.readFileSync( __dirname + '/../inputs/15.txt')) //
    .trim()
    .split('\n')
    .map(l => l.split(''))

var PLAYERS = []

Range(0, MAP.length).forEach(y => {
    return Range(0, MAP[y].length).forEach(
      x => {
        var c = MAP[y][x]
        if (c === 'G' || c === 'E') {
          PLAYERS.push({ type: c, health: 200, attack: 3, x, y })
          MAP[y][x] = '.'
        }

      })
  })

function printState(players=[], incHealth=true) {
  var outMap = MAP.map((l, y) => l.map((c, x) => {
    var player = players.find(p => p.x === x && p.y === y)
    if (player && player.health > 0) {
      return player.type
    }
    return c
  }).join('')).join('\n')

  if (incHealth) {
    outMap += '\n' +players.filter(p => p.health > 0)
      .map(p => `${p.type}(${p.health}): ${p.y}.${p.x}`).join('\n') + '\n'
  }
  return outMap
}

var getNeighborCoords = (y, x) => ([[y-1, x],
                                  [y, x-1],
                                  [y, x+1],
                                  [y+1, x]])

function sortEnemies(a, b) {
  if (a.health === b.health) {
    if (a.y === b.y) {
      return a.x - b.x
    } else {
      return a.y - b.y
    }
  }
  return a.health - b.health
}
function getEnemy(player, players) {
  var coords = getNeighborCoords(player.y, player.x)

  var enemies = players
      .filter(otherPlayer => otherPlayer.type !== player.type)
      .filter(otherPlayer => otherPlayer.health > 0)
      .filter(enemy => coords.find(([y, x]) => x === enemy.x && y === enemy.y)) || []

  // return enemies.sort((a, b) => {
  //   return coords.findIndex(c => c.x === a.x && c.y === a.t) -
  //     coords.findIndex(c => c.x === b.x && c.y === b.t)
  // })[0]
  return enemies.sort(sortEnemies)[0]
}

function isFree(y, x, players) {
  return MAP[y][x] === '.' && !players.filter(p => p.health > 0).find(
    ({x: px, y: py }) => py === y && x == px)
}

function getEnemyTargetPositions(player, players){
  const { type } = player

  return players
    .filter(e => e.type !== type && e.health > 0)
    .map(({x: px, y: py}) => getNeighborCoords(py, px))
    .reduce((coords, currentCoords) => coords.concat(currentCoords), [])
    .filter(([ty, tx]) => isFree(ty, tx, players))
}

function s(thing) {
  if (Array.isArray(thing)) {
    return `${thing[0]}.${thing[1]}`
  }
 return `${thing.y}.${thing.x}`

}

// TODO: save path
function findReachableTargets(
  player,
  players,
  targets = [],
  horizon = [],
  path = [],
  depth=1) {

  if (depth > 100) {
    throw new Error('too deep')
  }


  const target = targets
        .filter(([ ty, tx]) => horizon
                .find(([hy, hx]) => hy === ty && hx === tx))
        .sort(([y1, x1], [y2, x2]) => y1 == y2 ? x1 - x2 : y1 - y2)[0]

  if (target) {
    let fastestPaths = path.filter(p => p.current === s(target))

    for (let i = depth - 2; i > 0; i--) {
      const toBeAdded = path
            .filter(p => p.depth == i )
            .filter(p => fastestPaths.find(fp => fp.depth === (i + 1)
                                           && fp.prev === p.current))
      fastestPaths = fastestPaths.concat(toBeAdded)
    }

    const nextStepS = fastestPaths
          .filter(p => p.depth === 1)
          .sort((a,b) => a.current.localeCompare(b.current))[0].current

    const nextStep = [
      parseInt(nextStepS.split('.')[0]),
      parseInt(nextStepS.split('.')[1])
    ]

    return [target, nextStep, depth]
  }



  const edge = horizon.filter(([hy, hx]) => {
    const nextSteps = getNeighborCoords(hy, hx)
          .filter(([y, x]) => isFree(y, x, players))

    const newSteps = nextSteps.filter(
      ([ny, nx]) => !horizon.find(([hhy, hhx]) => ny === hhy && nx === hhx))

    // console.log({ nextSteps, newSteps })
    // process.exit(0)
    return newSteps.length > 0
  })



  if (player.type === 'G' && player.y === 9 && player.x === 7) {
    console.log('hello', depth, edge.length, horizon.length, path.length)
  }

  const additionalHorizon = edge
        .map((prev) => {
          const [hy, hx] = prev
          const nextSteps = getNeighborCoords(hy, hx)
                .filter(([nhy, nhx]) =>
                        !horizon.find(([hhy, hhx]) => hhy === nhy && hhx === nhx)
                        && isFree(nhy, nhx, players))

          const pathStuff = nextSteps.map(current => ({
            prev: s(prev),
            current: s(current),
            depth
          }))
          path = path.concat(pathStuff)
          return nextSteps
        })
        .reduce((coords, currentCoords) => coords.concat(currentCoords), [])

  if (player.type === 'G' && player.y === 9 && player.x === 7) {
    console.log(additionalHorizon.length)
  }


  if (targets.length === 0) {
    targets = getEnemyTargetPositions(player, players)
  }
  if (horizon.length === 0) {
    horizon = getNeighborCoords(player.y, player.x)
      .filter(([y, x]) => isFree(y, x, players))

    path = path.concat(horizon.map(current => ({
      prev: s(player),
      current: s(current),
      depth
    })))
  }

  const newHorizon = horizon.concat(additionalHorizon)
  if (depth > 1 && newHorizon.length === horizon.length) {
    return [[], [player.y, player.x], depth]
  }

  return findReachableTargets(player, players,targets, newHorizon, path, depth+1)
}


function tick(player, players) {
  var enemy = getEnemy(player, players)
  if (enemy) {
    enemy.health -= player.attack
    return player
  }
  // console.log(player)
  const [target, nextStep, steps] = findReachableTargets(player, players)
  // console.log({target, nextStep, steps})

  player.y = nextStep[0]
  player.x = nextStep[1]

  enemy = getEnemy(player, players)
  if (enemy) {
    enemy.health -= player.attack
  }
  return player
}

function round(players) {
  players = players.sort((p1, p2) => p1.y === p2.y ? p1.x - p2.x : p1.y - p2.y)

  Range(0, players.length).forEach(playerTurn => {

    if (players[playerTurn].health > 0) {
      players[playerTurn] = tick(players[playerTurn], players)
    }
  })
}

console.log(printState(PLAYERS))

let i = 0
let bothRemaining = PLAYERS.find(p => p.type === 'G' && p.health > 0)
    && PLAYERS.find(p => p.type === 'E' && p.health > 0)

while(bothRemaining && i < 60) {

  console.log(i, '---')

  round(PLAYERS)
  console.log(printState(PLAYERS))
  bothRemaining = PLAYERS.find(p => p.type === 'G' && p.health > 0) && PLAYERS.find(p => p.type === 'E' && p.health > 0)

  if (bothRemaining) {
    i++
  }

}



console.log('done', i)
console.log(printState(PLAYERS, false))


const healthRemaining = PLAYERS.filter(p => p.health > 0).reduce((s, p) => s + p.health, 0)

console.log(`Sollution 1: ${i} * ${healthRemaining} = ${i * healthRemaining}`)
