from collections import defaultdict

stack = []
x = y = dist = 0
board = defaultdict(int)

d = {
    "N": (-1, 0),
    "E": (0, 1),
    "S": (1, 0),
    "W": (0, -1)
}

inputS = open("../inputs/20.txt").read().strip()

for c in inputS[1:-1]:
    if c == '(':
        stack.append((y, x, dist))
    elif c == ')':
        y, x, dist = stack.pop()
    elif c == '|':
        y, x, dist = stack[-1]
    else:
        x += d[c][1]
        y += d[c][0]
        if board[(x, y)] == 0:
            dist += 1
            board[(x, y)] = dist


print()
print("Part 1", max(board.values()))
print("Part 2", len([n for n in board.values() if n >= 1000]))
