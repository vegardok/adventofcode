import collections


inputS = open('../inputs/12.txt').read()

def parse(l):
    parts = l.split(' <-> ')
    id = int(parts[0])
    n = [int(c) for c in parts[1].split(', ')]
    return { "id": id, "n": n }

input = [parse(l) for l in inputS.strip().split('\n')]

nodes = defaultdict(set)

for i in input:
    nodes[i['id']].update(i['n'])


def visit(id, seen=set()):
    seen.add(id)
    for i in nodes[id]:
        if i not in seen:
            seen.update( visit(i, seen))
    return seen


print("Part 1", len(visit(0)))


visitedNodes = nodes.copy()

current=0
groups=0
while visitedNodes:
    groups += 1
    connected = visit(current)
    for c in connected:
        if c in visitedNodes:
            visitedNodes.pop(c)
    if visitedNodes:
        current = list(visitedNodes.keys())[0]

print("Part 2", groups)
