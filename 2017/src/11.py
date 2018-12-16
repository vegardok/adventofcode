input = [s for s in open('../inputs/11.txt').readline().strip().split(',')]

def getNeighbours(q, r):
    oddQ = q % 2 == 1
    return {
        'n': ( q, r-1,),
        's': ( q, r+1),
        'nw': ( q-1, r if oddQ else r-1),
        'sw': ( q-1, r + 1 if oddQ else r),
        'ne': ( q+1, r if oddQ else r-1),
        'se': ( q+1, r+1 if oddQ else r),
    }


def oddq_to_cube(q, r):
    x = q
    z = r - (q - (q&1)) / 2
    y = -x-z
    return [x, y, z]

def cube_distance(a, b):
    a = oddq_to_cube(a[0], a[1])
    b = oddq_to_cube(b[0], b[1])
    return (abs(a[0] - b[0]) + abs(a[1] - b[1]) + abs(a[2] - b[2])) / 2


start= [0, 0]
currentPos = start[:]
maxDistance = 0

for step in input:
    currentPos = getNeighbours(currentPos[0], currentPos[1])[step]
    d = cube_distance(start, currentPos)
    maxDistance = d if d > maxDistance else maxDistance
finalDist = cube_distance(start, currentPos)

print("Walked from " + str(start) + \
      " to " + str(currentPos) + " distance " + \
      str(finalDist) + ", max dist: " + str(maxDistance))
