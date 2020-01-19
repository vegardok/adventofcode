from collections import deque
from functools import reduce
import numpy as np
from scipy.ndimage import measurements


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


def prep(s):
    padding = [17,31,73,47,23]
    return [ord(i) for i in s] + padding

def binHash(s):
    h = knotHash(prep(s))
    s = []
    for d in h:
        for bit in ('000' + bin(int('0x' + d, 16))[2:])[-4:]:
            s.append(1 if bit == '1' else 0)
    return np.array(s)

hashes = np.array([binHash('ugkiagan-' + str(i)) for i in range(0, 128)])

usedBits = hashes.sum()

print("Part1: ", usedBits)

lw, num = measurements.label(hashes)

print("Part 2", num)
