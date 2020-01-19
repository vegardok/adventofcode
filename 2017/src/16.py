from collections import deque

moves = open('../inputs/16.txt').read().strip().split(',')
programs = deque('abcdefghijklmnop')

def swap(a, b, queue):
    index1 = a if isinstance(a, int) else queue.index(a)
    index2 = b if isinstance(b, int) else queue.index(b)

    val1 = queue[index1]
    val2 = queue[index2]

    queue.remove(val1)
    queue.remove(val2)

    if index1 < index2:
        queue.insert(index1, val2)
        queue.insert(index2, val1)
    else:
        queue.insert(index2, val1)
        queue.insert(index1, val2)

def move():
    for direction in moves:
        move = direction[0]
        if move == 's':
            spin = int(direction[1:])
            programs.rotate(spin)
        elif move == 'x':
            a, b = [int(i) for i in direction[1:].split('/')]
            swap(a, b, programs)
        elif move == 'p':
            a, b = direction[1:].split('/')
            swap(a, b, programs)

move()

print('Part 1', ''.join(programs))

endStates = [''.join(programs)]

for i in range(50):
    move()
    endStates.append(''.join(programs))

# not hjdikflncgpbomae
print('Part 2', endStates[(1000000000-1) % len(set(endStates))])
