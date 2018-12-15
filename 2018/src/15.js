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

const getNeighborCoords = (y, x) => (
  [[y-1, x],
   [y, x-1],
   [y, x+1],
   [y+1, x]]
)

function compareEnemies(a, b) {
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
      .filter(otherPlayer => otherPlayer.type !== player.type
              && otherPlayer.health > 0
              && coords.find(([y, x]) => x === otherPlayer.x
                             && y === otherPlayer.y)) || []
  return enemies.sort(compareEnemies)[0]
}

function sortReadingOrder(a, b) {
  const y1 = a.prev.y
  const x1 = a.prev.x
  const y2 = b.prev.y
  const x2 = b.prev.x

  if (y1 === y2) {
    return x1 - x2
  }
  return y1 - y2
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

function horizonSize(a) {
  return a
    .map(l => l.reduce((sum, v) => sum + (v ? 1:0), 0))
    .reduce((sum, v) => sum + (v ? 1:0), 0)
}

const horizonToString = a => a.map(l => l.map(v => v ? '#' : ' ').join('')).join('\n')

function findReachableTargets(
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

  const validTargets = targets
        .filter(([ty, tx]) => horizon[ty][tx])

  if (validTargets.length > 0) {
    const stuff = validTargets.map(validTarget => {
      let currentTarget = { y: validTarget[0], x: validTarget[1] }

      const fastPath = [{prev: currentTarget }]
      while (currentTarget.y !== player.y || currentTarget.x !== player.x) {

        const currentTargetNextStep = path
              .filter(p => p.current.y === currentTarget.y
                      && p.current.x === currentTarget.x)
              .sort(sortReadingOrder)[0]

        currentTarget = currentTargetNextStep.prev

        fastPath.push(currentTargetNextStep)
      }

      return fastPath[fastPath.length-2]
    })

    const { x: nextX, y: nextY } = stuff.sort(sortReadingOrder)[0].prev

    return [[nextY, nextX], false]
  }


  const expandedEdge = horizon.reduce(
    ( accl1, l, y) => accl1.concat(l.reduce(
      (accl2, v, x) => !v ? accl2 : [...accl2, { y, x }], [] )), [])

    .reduce(( accl, { y: edgeY, x: edgeX }) => {

      const newSteps = getNeighborCoords(edgeY, edgeX)
            .filter(([ny, nx]) => !horizon[ny][nx] && isFree(ny, nx, players))

      newSteps.forEach(([ny, nx]) => {
        horizon[ny][nx] = true
      })

      const pathStuff = newSteps.map(current => ({
        prev: { y: edgeY, x: edgeX },
        current: { y: current[0], x: current[1] },
        depth
      }))

      path = path.concat(pathStuff)
      return newSteps.length > 0 ? [...accl, newSteps] : accl
    }, [])


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
          prev: { y: player.y, x: player.x},
          current: {y: ny, x: nx },
          depth
        })
      })
  }


  if (depth > 1 && expandedEdge.length === 0) {
    return [[player.y, player.x], false]
  }

  return findReachableTargets(player, players,targets, horizon, path, depth+1)
}


function tick(player, players) {
  var enemy = getEnemy(player, players)
  if (enemy) {
    enemy.health -= player.attack
    return player
  }

  const [nextStep, stop] = findReachableTargets(player, players)

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

    const healthRemaining = players.filter(p => p.health > 0).reduce((s, p) => s + p.health, 0)

    console.log(`Sollution 2: ${i} * ${healthRemaining} = ${i * healthRemaining}`)
    return true
  }

  while(!run(boost)) { boost++ }
}

game1()
game2()
