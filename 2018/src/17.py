import sys
sys.path.append("/Library/Frameworks/Python.framework/Versions/3.7/lib/python3.7/site-packages")


from collections import defaultdict
import numpy as np
from functools import reduce

print("")

inputS = open('../inputs/17.txt').read().strip()

# inputS="""
# x=495, y=2..7
# y=7, x=495..501
# x=501, y=3..7
# x=498, y=2..4
# x=506, y=1..2
# x=498, y=10..15
# x=504, y=10..15
# y=13, x=501..502
# y=15, x=498..504
# y=9..13, x=494
# y=9..13, x=508
# """

# inputS = """
# x=495, y=2..7
# y=7, x=495..501
# x=501, y=3..7
# x=498, y=2..4
# x=506, y=1..2
# x=498, y=10..13
# x=504, y=10..13
# y=13, x=498..504
# """

def r(v):
    if '..' in v:
        return range(int(v.split('..')[0]), int(v.split('..')[1])+1)
    else:
        return range(int(v), int(v)+1)

def parseL(l):
    coord = dict()
    for c in l.split(', '):
        l = c.split('=')[0]
        v = r(c.split('=')[1])

        coord[l] = v
    return coord


lines = [parseL(l) for l in inputS.strip().split('\n')]

height = max([max(l['y']) for l in lines]) + 1
width = max([max(l['x']) for l in lines]) + 2
begin = min([max(l['x']) for l in lines]) - 2

AIR=0
WALL=1
SPRING=2
WATER=3
DRY_WATER=4

spring=(0, 500)

world = np.ndarray(shape=(height, width), dtype=int)
world.fill(AIR)

for l in lines:
    for y in l['y']:
        for x in l['x']:
            world[y][x] = WALL

world[spring[0]][spring[1]] = 2

def getWalls(world, i, x):
    walls = np.where(world[i]==WALL)[0]
    leftWall = max(walls[walls < x]) if len(walls[walls < x]) > 0 else 0
    rightWall = min(walls[walls > x]) if len(walls[walls > x]) else len(world[0]) - 1

    return [leftWall, rightWall]

def mark(spring):

    x = spring[1]
    height = 0
    i = spring[0] + 1
    if world[i, x] != AIR:
        return

    downWall = np.where(world[spring[0]+1:, x] == WALL)[0]

    if len(downWall) == 0 or downWall[0] == 0:
        world[spring[0]+1:, x] = DRY_WATER
        return
    else:
        i = spring[0] + downWall[0]

    # print(spring, 'start at', i)

    # water drop
    drop = world[spring[0]+1:i+1, x]
    drop[np.where(drop == AIR)] = DRY_WATER
    # world[spring[0]+1:i+1, x] = DRY_WATER

    if i == (len(world) - 1):
        return


    walls = np.where(world[i]==WALL)[0]

    initialLeftWall, initialRightWall = getWalls(world, i, x)

    leftWall = initialLeftWall
    rightWall = initialRightWall

    while leftWall >= initialLeftWall and rightWall <= initialRightWall:

        if len(np.where(world[i+1, leftWall:rightWall] == AIR)[0]) > 0:
            for foo in np.where(world[i+1, leftWall:rightWall] == AIR)[0]:
                mark((i, leftWall + foo))

        world[i, leftWall+1:rightWall] = WATER
        i -= 1

        newLeftWall, newRightWall = getWalls(world, i, x)

        leftWall = newLeftWall
        rightWall = newRightWall


    prevLeftWall, prevRightWall = getWalls(world, i+1, x)
    goLeft = leftWall < prevLeftWall
    goRight = rightWall > prevRightWall

    # print('x', leftWall, prevLeftWall)
    if goLeft and goRight:
        lip = world[i, prevLeftWall:prevRightWall + 1]
        lip[np.where(lip == AIR)] = DRY_WATER
        # world[i, prevLeftWall:prevRightWall + 1] = DRY_WATER
    elif goRight:
        lip = world[i, leftWall+1:prevRightWall + 1]
        lip[np.where(lip == AIR)] = DRY_WATER
        # world[i, leftWall+1:prevRightWall + 1] = DRY_WATER
    elif goLeft:
        lip = world[i, prevLeftWall:rightWall: + 1]
        lip[np.where(lip == AIR)] = DRY_WATER
        # world[i, prevLeftWall:rightWall: + 1] = DRY_WATER
    else:

        return
        raise ValueError('wtf', 'i', i, 'x', x, leftWall, prevLeftWall, rightWall, prevRightWall)

    if goRight:
        mark((i-1, prevRightWall + 1))
    if goLeft:
        mark((i-1, prevLeftWall - 1))

def printW(world):
    palette = {
        0: ' ',
        1: '#',
        2: '+',
        3: '~',
        4: '|'
    }
    s = ""
    for l in world[:, begin:width]:
        s += reduce(lambda accl, n: accl + palette[n], l , '')
        s += "\n"
    return s

mark(spring)


world = world[min([min(l['y']) for l in lines]):max([max(l['y']) for l in lines])+1]

water_count = np.count_nonzero(world == WATER)
dry_count = np.count_nonzero(world == DRY_WATER)

print(printW(world))
print("still water ~", water_count)
print("dry water", dry_count)
print("total", water_count + dry_count)
