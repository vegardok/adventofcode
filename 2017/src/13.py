from collections import defaultdict
from functools import reduce

inputS = open('../inputs/13.txt').read().strip()

# inputS = """
# 0: 3
# 1: 2
# 4: 4
# 6: 4
# """

input = [[int(c) for c in s.split(': ')] for s in inputS.strip().split('\n')]


layers = max([l[0] for l in input])

walls = dict()
for l in input:
    walls[l[0]] = l[1]-1

def run(delay=0, s=True):
    hits = []
    for timetick in range(layers+1):
        for index, depth in walls.items():
            currentValue = abs(abs((depth - ((delay + timetick) % (depth*2)))) - depth)
            if currentValue == 0 and timetick == index:
                if not s:
                    return False
                hits.append((index, depth))

    if s:
        return sum([h[0] * (h[1]+1) for h in hits])
    else:
        return len(hits) == 0



print('Hits', run())

i = 0
while not run(i, False):
    if i % 100000 == 0:
        print(i)
    i += 1

print('Wait ', i )
