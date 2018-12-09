const { Range } = require('immutable');
console.log('Advent of code 2018')
console.log()

Range(1, 10).forEach(i => {
  const start = Date.now()
  require(`./src/${i}`)
  console.log(`Challenge ${i}, (${Date.now() - start}ms)\n`)
})
