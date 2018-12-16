from collections import deque
from functools import reduce


def knotHash(key):
    skip = 0
    rotateCount = 0
    index = deque([i for i in range(256)])
    for _ in range(64):
        for count in key:
            s = [index[i] for i in range(count)]
            s.reverse()

            for i, v in enumerate(s):
                index[i] = v

            rotateN = (count + skip) % len(index)
            rotateCount += rotateN
            index.rotate(-1 * rotateN)
            skip = skip + 1

    index.rotate(rotateCount)
    index = list(index)

    return ''.join([format(reduce((lambda accl, x: accl ^ x), index[i:i+16]), '02x') for i in range(0, len(index), 16)])

padding = [17,31,73,47,23]

key2 = [ord(i) for i in open('../inputs/10.txt').readline().strip()] + padding

print(knotHash(key2))
