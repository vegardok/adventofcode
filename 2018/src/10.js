const fs = require('fs')

let stars =String(fs.readFileSync(__dirname + '/../inputs/10.txt'))
    .trim()
    .split('\n')
    .map(l => {
      const [pos, vel] = l
            .replace('position=<', '')
            .replace('> velocity=<', '.')
            .replace('>', '').split('.')
      const [x, y] = pos.split(',').map(p => parseInt(p))
      const [vx, vy] = vel.split(',').map(p => parseInt(p))
      return {pos: {x, y}, vel: {x: vx, y: vy}}
    })

function range(start, stop) {
  var r = []
  for (let i = start; i < stop; i++) {
    r.push(i)
  }
  return r
}

function move(stars) {
  return stars.map(({ pos, vel }) => ({
    pos: {
      x: pos.x + vel.x,
      y: pos.y + vel.y
    },
    vel: vel
  }))
}


function draw(stars) {
  const X_begin = Math.min(...stars.map(s => s.pos.x))
  const X_end = Math.max(...stars.map(s => s.pos.x))+1

  const Y_begin = Math.min(...stars.map(s => s.pos.y))
  const Y_end = Math.max(...stars.map(s => s.pos.y))+1

  return range(Y_begin, Y_end).map(y => range(X_begin, X_end).map(x => {
    if (stars.find(({ pos }) => pos.x === x && pos.y === y)) {
      return '#'
    }
    else {
      return ' '
    }
  }).join('')).join('\n')
}

function size(stars) {
  const Y_end = Math.max(...stars.map(s => s.pos.x))+1
  const X_end = Math.max(...stars.map(s => s.pos.y))+1
  const Y_begin = Math.min(...stars.map(s => s.pos.x))
  const X_begin = Math.min(...stars.map(s => s.pos.y))
  return (X_end - X_begin) * (Y_end - Y_begin)
}

let shrinking = true
let steps = 0

while (shrinking) {
  steps += 1
  const newStars = move(stars)
  shrinking = size(newStars) < size(stars)
  if (shrinking) {
    stars = newStars
  }
}

console.log(draw(stars))
console.log(steps)
