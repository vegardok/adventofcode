inputS = """
pos=<0,0,0>, r=4
pos=<1,0,0>, r=1
pos=<4,0,0>, r=3
pos=<0,2,0>, r=1
pos=<0,5,0>, r=3
pos=<0,0,3>, r=1
pos=<1,1,1>, r=1
pos=<1,1,2>, r=1
pos=<1,3,1>, r=1
""".strip()

inputS = open('../inputs/23.txt').read().strip()

# inputS = """
# pos=<10,12,12>, r=2
# pos=<12,14,12>, r=2
# pos=<16,12,12>, r=4
# pos=<14,14,14>, r=6
# pos=<50,50,50>, r=200
# pos=<10,10,10>, r=5
# """.strip()


def parseLine(s):
    pos = s.split('=<')[1].split('>,')[0].split(',')
    r = s.split('>, r=')[1]
    return (int(pos[0]),
            int(pos[1]),
            int(pos[2]),
            int(r))

bots = sorted([parseLine(s) for s in inputS.split('\n')], key=lambda bot: -bot[3])
# bots = [parseLine(s) for s in inputS.split('\n')]


def dist(t1, t2):
    x1, y1, z1, r1 = t1
    x2, y2, z2, r2 = t2
    return abs(x1 - x2) + abs(y1 - y2) + abs(z1 - z2)

def inRange(bot, bots):
    return len([b for b in bots if dist(bot, b) <= bot[3]])


print('Part1: In range', inRange(bots[0], bots))


# https://old.reddit.com/r/adventofcode/comments/a8s17l/2018_day_23_solutions/ecdbux2/
from z3 import If, Int, Optimize

def zabs(x):
    return If(x >= 0, x, -x)

(x, y, z) = (Int('x'), Int('y'), Int('z'))

in_ranges = [Int('in_range_' + str(i)) for i in range(len(bots))]

range_count = Int('sum')

o = Optimize()

for i in range(len(bots)):
    (bx, by, bz, br) = bots[i]
    o.add(in_ranges[i] == If(
        zabs(x - bx) + zabs(y - by) + zabs(z - bz) <= br, 1, 0))

o.add(range_count == sum(in_ranges))

dist_from_zero = Int('dist')

o.add(dist_from_zero == zabs(x) + zabs(y) + zabs(z))

h1 = o.maximize(range_count)
h2 = o.minimize(dist_from_zero)

print(o.check())
print(o.lower(h1))
print(o.upper(h1))

print(o.lower(h2))
print(o.upper(h2))
