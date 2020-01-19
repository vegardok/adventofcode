def gen1():
    state = 512 # 65
    while True:
        n = (state * 16807) % 2147483647
        yield bin(n)[-16:]
        state = n


def gen2():
    state = 191 # 8921
    while True:
        n = (state * 48271) % 2147483647
        yield bin(n)[-16:]
        state = n

def pair():
    g1 = gen1()
    g2 = gen2()
    while True:
        yield (next(g1), next(g2))

def matchingPairs(generator1, generator2, n):
    matches = 0
    g1 = generator1()
    g2 = generator2()
    for i in range(n):
        if next(g1) == next(g2):
            matches += 1

    return matches


print('Part 1')
print(len(matchingPairs(gen1, gen2, 40000000)))



def gen12():
    state = 512 # 65
    while True:
        n = (state * 16807) % 2147483647
        while n % 4 != 0:
            n = (state * 16807) % 2147483647
            state = n
        yield bin(n)[-16:]
        state = n


def gen22():
    state = 191 # 8921
    while True:
        n = (state * 48271) % 2147483647
        while n % 8 != 0:
            n = (state * 48271) % 2147483647
            state = n
        yield bin(n)[-16:]
        state = n


print('Part 2')
print(len(matchingPairs(gen12, gen22, 5000000)))

print('done')
