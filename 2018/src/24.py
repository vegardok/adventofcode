inputS = open('../inputs/24.txt').read().strip()

immunity = inputS.split('Immune System:')[1].split('\n\n')[0].strip().split('\n')
infection = inputS.split('Infection:')[1].strip().split('\n')

class Bunch:
    def __init__(self, **kwds):
        self.__dict__.update(kwds)

    def __str__(self):
        state = ["%s=%r" % (attribute, value)
                 for (attribute, value)
                 in self.__dict__.items()]
        return '{' + ', '.join(state) + '}'

    def __repr__(self):
        return self.__str__()


def getStuff(l, k='weak to '):
    if k in l:
        s = ';' if ';' in l.split(k)[1] in l else ')'
        return set(l.split(k)[1].split(s)[0].split(', '))
    else:
        return {}

def parse(l):
    return Bunch(
        units=int(l.split(' ')[0]),
        hp=int(l.split(' ')[4]),
        weak=getStuff(l, 'weak to '),
        immune=getStuff(l, 'immune to '),
        damage=int(l.split('attack that does ')[1].split(' ')[0]),
        damageType=l.split(' damage at initiative ')[0].split(' ')[-1],
        initiative=int(l.split(' damage at initiative ')[1])
    )

def sort(groups):
    return sorted(groups, key=lambda g: g.units * g.damage, reverse=True)

def fight(attackingGroups, defendingGroups):
    targets = defendingGroups[:]
    attack = dict()
    for index, group in enumerate(attackingGroups):
        weakestEnemy = [d for d in defendingGroups
                        if group.attackType not in d.immune][0]

    #attack phase

immunity = sort([parse(l) for l in immunity])
infection = sort([parse(l) for l in infection])

for foo in immunity + infection:
    print(foo)
