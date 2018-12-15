var { Range, fromJS } = require('immutable')

var fs = require('fs')

var MAP =String(fs.readFileSync( __dirname + '/../inputs/15debug2.txt'))
    .trim()
    .split('\n')
    .map(l => l.split(''))

var PLAYERS = []

Range(0, MAP.length).forEach(y => {
    return Range(0, MAP[y].length).forEach(
      x => {
        var c = MAP[y][x]
        if (c === 'G' || c === 'E') {
          PLAYERS.push({ type: c, health: 300, attack: 3, x, y })
          MAP[y][x] = '.'
        }

      })
  })

function printState(players=[]) {
  var outMap = MAP.map((l, y) => l.map((c, x) => {
    var player = players.find(p => p.x === x && p.y === y)
    if (player) {
      return player.type
    }
    return c
  }).join('')).join('\n')
  return outMap
}

var getNeighborCoords = (y, x) => ([[y-1, x],
                                  [y, x-1],
                                  [y, x+1],
                                  [y+1, x]])

function getEnemy(player, players) {
  var coords = getNeighborCoords(player.y, player.x)
  var enemies = players.filter(enemy => enemy.type !== player.type &&
                                coords.find(c => c.x === enemy.x && c.y === enemy.y)) || []

  return enemies.sort((a, b) => {
    return coords.findIndex(c => c.x === a.x && c.y === a.t) -
      coords.findIndex(c => c.x === b.x && c.y === b.t)
  })[0]
}

function isFree(y, x, players) {
  return MAP[y][x] === '.' && !players.find(
    ({x: px, y: py }) => py === y && x == px)
}

function getEnemyTargetPositions({ type }, players){
  return players
    .filter(e => e.type !== type)
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

  if (depth > 5) {
    throw new Error('too deep')
  }


  const target = targets
        .filter(([ ty, tx]) => horizon
                .find(([hy, hx]) => hy === ty && hx === tx))
        .sort(([y1, x1], [y2, x2]) => y1 == y2 ? x1 - x2 : y1 - y2)[0]

  if (target) {

    let fastestPaths = path.filter(p => p.current === s(target)) // array of arrays?

    for (let i = depth - 2; i > 0; i--) {
      const toBeAdded = path
            .filter(p => p.depth == i )
            .filter(p => fastestPaths.find(fp => fp.depth === (i + 1)
                                           && fp.prev === p.current))
      fastestPaths = fastestPaths.concat(toBeAdded)
    }

    const nextStep = fastestPaths
          .filter(p => p.depth === 1)
          .sort((a,b) => a.current.localeCompare(b.current))[0]

    console.log({ nextStep })

    return [target, nextStep, depth]
  }


  const additionalHorizon = horizon
        .map((prev) => {
          const [hy, hx] = prev
          const nextSteps = getNeighborCoords(hy, hx)
            .filter(([nhy, nhx]) =>
                    isFree(nhy, nhx, players) &&
                    !horizon.find(([hhy, hhx]) => hhy === nhy && hhx === nhx))

          const pathStuff = nextSteps.map(current => ({
            prev: s(prev),
            current: s(current),
            depth
          }))
          path = path.concat(pathStuff)
          return nextSteps
        })
        .reduce((coords, currentCoords) => coords.concat(currentCoords), [])


  if (targets.length === 0) {
    targets = getEnemyTargetPositions(player, players)
  }
  if (horizon.length === 0) {
    horizon = getNeighborCoords(player.y, player.x).filter(([y, x]) => isFree(y, x, players))

    path = path.concat(horizon.map(current => ({
      prev: s(player),
      current: s(current),
      depth
    })))
  }

  const newHorizon = horizon.concat(additionalHorizon)

  return findReachableTargets(player, players,targets, newHorizon, path, depth+1)
}


function tick(player, players) {
  var enemy = getEnemy(player, players)
    if (enemy) {
    enemy.health -= player.attack
    return
  }
  const [target, path, steps] = findReachableTargets(player, players)


  // console.log({ target, path, steps })
}

function round(players) {
  players = players.sort((p1, p2) => p1.y === p2.y ? p1.x - p2.x : p1.y - p2.y)

  tick(players[0], players)
}


console.log(printState(PLAYERS))

round(PLAYERS) && "DONE"
