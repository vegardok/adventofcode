print('17')

from collections import deque

q = deque([0])

r=-355 - 1

for i in range(1, 2018):
    q.rotate(r)
    q.insert(0, i)

print("Part 1", q[q.index(2017)+1])


q = deque([0])

for i in range(1, 50000000):
    if i % 1000000 == 0:
        print('.', end='')
    q.rotate(r)
    q.insert(0, i)

print()
print("Part 2", q[q.index(0)+1])
