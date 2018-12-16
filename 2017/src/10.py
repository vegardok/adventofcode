from collections import deque

key = [int(i) for i in open('../inputs/10.txt').readline().split(',')]
index = [i for i in range(256)]



# key = [3, 4, 1, 5]
# index = [i for i in range(5)]


current = 0
skip = 0

print("Starting")

for count in key:
    s = [index[(current + i) % len(index)] for i in range(count)]

    print("count", count)
    print("current", current)
    print("s", s)
    s.reverse()
    print("s", s)

    for i, v in enumerate(s):
        index[(i+current) % (len(index))] = v

    print('index', index)

    current = (current + count + skip) % len(index)
    skip = skip + 1
    print("skip", skip)


    print()

print(index[0:2], index[0] * index[1])
