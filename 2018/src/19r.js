const GOAL = 10551315

let sum = 0
for (let d = 1; d <= GOAL; d++) {
  for (let f = 1; (f * d) <= GOAL; f++) {
    if (d * f == GOAL) {
      sum += d
      f = GOAL
    }
  }
}

console.log('DONE, goal', sum)
