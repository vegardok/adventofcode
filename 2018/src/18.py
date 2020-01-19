import sys
sys.path.append("/Library/Frameworks/Python.framework/Versions/3.7/lib/python3.7/site-packages")


from collections import defaultdict
import numpy as np
from functools import reduce

inputS = open('../inputs/18.txt').read().strip()
# inputS = """
# .#.#...|#.
# .....#|##|
# .|..|...#.
# ..|#.....#
# #.#|||#|#|
# ...#.||...
# .|....|...
# ||...#|.#|
# |.||||..|.
# ...#.|..|.
# """


OPEN=0
TREE=1
YARD=2

M = {
    '.': 0 ,
    '|': 1 ,
    '#': 2,
}

board = np.array([[M[c] for c in l] for l in inputS.strip().split('\n')])

def count(a, n):
    unique, counts = np.unique(a, return_counts=True)
    d = dict(zip(unique, counts))
    return d[n] if n in d else 0


def nextTick(board):
    newBoard = np.copy(board)
    for y, l in enumerate(board):
        for x, ll in enumerate(l):
            neighbours = board[max(y-1, 0):min(y+2, len(board)),
                               max(x-1, 0):min(x+2, len(l))]

            if board[y, x] == OPEN and count(neighbours, TREE) >= 3:
                newBoard[y, x] = TREE
            elif board[y, x] == TREE and count(neighbours, YARD) >= 3:
                newBoard[y, x] = YARD
            elif board[y, x] == YARD:
                if count(neighbours, YARD) >= 2 and count(neighbours, TREE) >= 1:
                    newBoard[y, x] = YARD
                else:
                    newBoard[y, x] = OPEN


    return newBoard

def printV(board):
    palette = {
        0: '.',
        1: '|',
        2: '#'
    }
    s = ""
    for l in board:
        s += reduce(lambda accl, n: accl + palette[n], l , '')
        s += "\n"

    print(s)


print("")
printV(board)

for i in range(10):
    print(i)
    board = nextTick(board)
printV(board)


print("done")
printV(board)
trees = count(board, TREE)
yards = count(board, YARD)
print(trees, yards)
print('{} trees, {} yards, {} total'.format(trees, yards, yards * trees))


# not 176490 to high
res = []
for i in range(1002):
    print(i)
    board = nextTick(board)
    trees = count(board, TREE)
    yards = count(board, YARD)
    res.append(trees * yards)

print(res[-1])
