function range(start, stop) {
  const r = []
  for(let i = start; i < stop; i++) {
    r.push(i)
  }
  return r
}

const fs = require('fs')

const MAP = String(fs.readFileSync( __dirname + '/../inputs/15.txt')) //
    .trim()
    .split('\n')
    .map(l => l.split(''))

const PLAYERS = []

range(0, MAP.length).forEach(y => {
  return range(0, MAP[y].length).forEach(
    x => {
      const c = MAP[y][x]
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

var getNeighborCoords = (y, x) => (
  [[y-1, x],
   [y, x-1],
   [y, x+1],
   [y+1, x]]
)

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
      .filter(enemy => coords.find(([y, x]) => x === enemy.x
                                   && y === enemy.y)) || []
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

function horizonSize(a) {
  return a
    .map(l => l.reduce((sum, v) => sum + (v ? 1:0), 0))
    .reduce((sum, v) => sum + (v ? 1:0), 0)
}

function sortReadingOrder(a, b) {
  const y1 = parseInt(a.prev.split('.')[0])
  const x1 = parseInt(a.prev.split('.')[1])
  const y2 = parseInt(b.prev.split('.')[0])
  const x2 = parseInt(b.prev.split('.')[1])

  if (y1 === y2) {
    return x1 - x2
  }
  return y1 - y2
}


// Returns [[nextY, nextX], stopTheGame]
function getNextState(
  player,
  players,
  targets = [],
  horizon = new Array(MAP.length).fill(0).map(() => new Array(MAP[0].length).fill(false)),
  path = [],
  depth=1) {

  if (depth > 100) {
    throw new Error('too deep')
  }

  if (players.filter(p => p.type !== player.type && p.health > 0).length === 0) {
    return [[player.y, player.x], true]
  }

  const validTargets = targets.filter(([ty, tx]) => horizon[ty][tx])

  if (validTargets.length > 0) {
    const firstSteps = validTargets.map(target => {
      let currentTarget = s(target)

      const fastPath = [{prev: currentTarget }]
      while(currentTarget !== s(player)) {

        const currentTargetNextStep = path
              .filter(p => p.current === currentTarget)
              .sort(sortReadingOrder)[0]

        currentTarget = currentTargetNextStep.prev

        fastPath.push(currentTargetNextStep)
      }

      return fastPath[fastPath.length-2]
    })

    const nextStep = firstSteps.sort(sortReadingOrder)[0].prev

    const nextY = parseInt(nextStep.split('.')[0])
    const nextX = parseInt(nextStep.split('.')[1])

    return [[nextY, nextX], false]
  }


  let edge = []
  horizon.forEach((l, y) => l.forEach((visited, x) => {
    if (visited) {
      const newSteps = getNeighborCoords(y, x)
            .filter(([ny, nx]) => !horizon[ny][nx] && isFree(ny, nx, players))
      if (newSteps.length > 0) {
        edge = edge.concat(newSteps)

        const pathStuff = newSteps.map(current => ({
          prev: s([y, x]),
          current: s(current),
          depth
        }))
        path = path.concat(pathStuff)
      }

    }
  }))

  edge.forEach(([y, x]) => {
    horizon[y][x] = true
  })

  if (targets.length === 0) {
    targets = getEnemyTargetPositions(player, players)
  }
  if (horizonSize(horizon) === 0) {
    horizon[player.y][player.x] = true
    const nextSteps = getNeighborCoords(player.y, player.x)
          .filter(([ny, nx]) => isFree(ny, nx, players))
    path = nextSteps
      .map(([ny, nx]) => {
        horizon[ny][nx] = true
        return ({
          prev: s(player),
          current: s([ny, nx]),
          depth
        })
      })
  }


  if (depth > 1 && edge.length === 0) {
    return [[player.y, player.x], false]
  }

  return getNextState(player, players,targets, horizon, path, depth+1)
}


function tick(player, players) {
  var enemy = getEnemy(player, players)
  if (enemy) {
    enemy.health -= player.attack
    return player
  }

  const [nextStep, stop] = getNextState(player, players)

  player.y = nextStep[0]
  player.x = nextStep[1]

  enemy = getEnemy(player, players)
  if (enemy) {
    enemy.health -= player.attack
  }

  return { ...player, stop }
}

function round(players, throwOnElfDeath = false) {
  players = players.sort((p1, p2) => p1.y === p2.y ? p1.x - p2.x : p1.y - p2.y)

  for (let playerTurn = 0; playerTurn < players.length; playerTurn++) {
    if (players[playerTurn].health > 0) {
      const update = tick(players[playerTurn], players)

      if (update.stop) {
        if (throwOnElfDeath && players.find(p => p.type === 'E' && p.health <= 0)) {
          throw new Error('elf died')
        }
        return true
      }
      players[playerTurn] = update
    }
    if (throwOnElfDeath && players.find(p => p.type === 'E' && p.health <= 0)) {
      throw new Error('elf died')
    }
  }
  return false
}





function game1() {
  let i = 0
  let stop = false

  const players = PLAYERS.map(p => ({ ...p }))

  // console.log(printState(PLAYERS, false))
  while(!stop) {
    stop = round(players)
    if (!stop) {
      i++
    }
  }
  console.log('\nDONE\n', i)
  // console.log(printState(PLAYERS, false))

  const healthRemaining = players.filter(p => p.health > 0).reduce((s, p) => s + p.health, 0)

  console.log(`Sollution 1: ${i} * ${healthRemaining} = ${i * healthRemaining}`)
}


function game2() {
  let boost = 1

  const run = (b) => {

    const players = PLAYERS.map(p => ({
      ...p,
      attack: p.type === 'E' ? p.attack + b : p.attack
    }))

    let i = 0
    let stop = false


    try {
      while(!stop) {
        stop = round(players, true)
        if (!stop) {
          i++
        }
      }
    } catch (e) {
      console.log(`${e.message} at boost ${boost}`)
      return false
    }


    console.log('\nDONE\n', i)
    // console.log(printState(players, true))
    const healthRemaining = players.filter(p => p.health > 0).reduce((s, p) => s + p.health, 0)

    console.log(`Sollution 2: ${i} * ${healthRemaining} = ${i * healthRemaining}`)
    return true
  }

  while(!run(boost)) { boost++ }
}

game1()
game2()
