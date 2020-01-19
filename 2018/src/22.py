import numpy as np
import heapq
import math

DEPTH = 11109
TARGET_X = 9
TARGET_Y = 731

# leddit
# DEPTH = 9171
# TARGET_X = 7
# TARGET_Y

# DEPTH = 510
# TARGET_X = 10
# TARGET_Y = 10


K1 = 20183

CACHE = np.ndarray(((TARGET_Y * 10) + 1, (TARGET_X * 20) + 1), int)
CACHE.fill(0)


def erosionLevel(y, x):
    if CACHE[y, x] > 0:
        return CACHE[y, x]
    elif x == 0 and y == 0:
        CACHE[y, x] = (DEPTH % K1)
        return CACHE[y, x]
    elif x == TARGET_X and y == TARGET_Y:
        CACHE[y, x] = erosionLevel(0, 0)
        return CACHE[y, x]
    elif y == 0:
        CACHE[y, x] = (((x * 16807) + DEPTH) % K1)
        return CACHE[y, x]
    elif x == 0:
        CACHE[y, x] = (((y * 48271) + DEPTH) % K1)
        return CACHE[y, x]
    else:
        a = CACHE[y, x-1] if CACHE[y, x-1] > 0 else erosionLevel(y, x-1)
        if CACHE[y, x-1] == 0:
            CACHE[y, x-1] = a
        b = CACHE[y-1, x] if CACHE[y-1, x] > 0 else erosionLevel(y-1, x)
        if CACHE[y-1, x] == 0:
            CACHE[y-1, x] = b
        CACHE[y, x] = ((a * b) + DEPTH) % K1
        return CACHE[y, x]

MAPPING = {
    0: '.', # ROCK
    1: '=', # WET
    2: '|' # NARROW
}


# def printMap(MAP):
#     for l in MAP:
#         print(''.join([MAPPING[i] for i in l]))


MAP = np.array([[(erosionLevel(y, x) % 3)
                 for x in range(TARGET_X+1)]
                for y in range(TARGET_Y+1)])

# Part 1
print("Part 1", sum(MAP.flatten()))

MAP = np.array([[(erosionLevel(y, x) % 3)
                 for x in range(TARGET_X + 25)]
                for y in range(TARGET_Y + 10)])



# * In rocky regions, you can use the climbing gear or the torch.
#   You cannot use neither (you'll likely slip and fall).
# * In wet regions, you can use the climbing gear or neither tool.
#   You cannot use the torch (if it gets wet, you won't have a light source).
# * In narrow regions, you can use the torch or neither tool. You
#   cannot use the climbing gear (it's too bulky to fit).

TOOLS = {
    0: 'NONE',
    1: 'TORCH',
    2: 'GEAR',
    'NONE': 0,
    'TORCH': 1,
    'GEAR': 2
}

ROOM_TYPES_GEAR_MAP = {
    0: [-1, TOOLS['TORCH'], TOOLS['GEAR']], # ROCK
    1: [TOOLS['NONE'], -1, TOOLS['GEAR']], # WET
    2: [TOOLS['NONE'], TOOLS['TORCH'], -1 ] # NARROW
}

TOOL_MAP = np.array([[ROOM_TYPES_GEAR_MAP[(erosionLevel(y, x) % 3)]
                      for x in range(MAP.shape[1]+1)]
                     for y in range(MAP.shape[0]+1)])


def cost(start, stop):
    if start[:2] == stop[:2]:
        if stop[2] in TOOL_MAP[stop[:2]]:
            return 7
        else:
            return np.Infinity
    elif TOOL_MAP[start] == TOOL_MAP[stop]:
        return 1
    else:
        return np.Infinity


def neighbours(p):
    newPoints = [(p[0], p[1], (p[2] + 1) % 3),
                 (p[0], p[1], (p[2] + 2) % 3)] # tool change
    if p[0] > 0:
        newPoints.append((p[0]-1, p[1], p[2]))
    if p[0] < (TOOL_MAP.shape[0]-1):
        newPoints.append((p[0]+1, p[1], p[2]))
    if p[1] > 0:
        newPoints.append((p[0], p[1]-1, p[2]))
    if p[1] < (TOOL_MAP.shape[1] - 1):
        newPoints.append((p[0], p[1]+1, p[2]))
    return [i for i in newPoints if cost(p, i) < np.Infinity]



def search(start, stop):
    openSet = [(0, start)]
    heapq.heapify(openSet)

    distances = np.ndarray((TOOL_MAP.shape[0], TOOL_MAP.shape[1], 3), float)
    distances.fill(np.Infinity)
    distances[start] = 0

    prev = {}
    visited = {start}

    print("Starting part 2")
    while openSet:
        bestScore, bestPoint = heapq.heappop(openSet)
        if bestPoint[0] % 100 == 0 and bestPoint[1] == 0:
            print('best', bestPoint, bestScore, len(openSet))
        n = neighbours(bestPoint)
        for p in n:
            if (bestPoint + p) not in visited:
                alt = distances[bestPoint] + cost(bestPoint, p)
                if alt < distances[p]:
                    distances[p] = alt
                    prev[p] = bestPoint
                    heapq.heappush(openSet, (alt, p))
                visited.add(bestPoint + p)
            # if stop in visited:
            #     print("DONE, never mind")
            #     return distances, prev, openSet

    return distances, prev, openSet


def getPath(p, prev, printPath=False):
    palette = {
        0: '-',
        1: '*',
        2: '#'
    }
    path = []
    foo = p
    while foo in prev:
        path.append(foo)
        foo = prev[foo]


    path = reversed(path)

    if printPath:
        for p in path:
            print((' ' * p[1]) + palette[p[2]])

    return list(path)

start = (0, 0, 1)
stop = (TARGET_Y,TARGET_X, 1)
done, prev, queue = search(start, stop)

print("cost", done[stop])
# NOT 1018.0, to high
