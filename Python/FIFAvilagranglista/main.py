from statistics import mean

class adatok:
    def __init__(self, sor):
        darabok = sor.split(';')
        self.csapat = darabok[0]
        self.helyezes = int(darabok[1])
        self.valtozas = int(darabok[2])
        self.pontszam = int(darabok[3])


lista = list()

with open("fifa.txt", "r") as file:
    elso = file.readline()
    for sor in file.readlines():
        lista.append(adatok(sor))

print(f"3. feladat: A világranglistán {len(lista)} csapat szerepel")
pontszam = list()
for adatok in lista:
    pontszam.append(adatok.pontszam)
atlag = mean(pontszam)
print(f"4. feladat: A csapatok átlagos pontszáma: {atlag} pont")
max=0
maxhely=0
for i in range(len(lista)):
    if  lista[i].valtozas>max:
        max=lista[i].valtozas
        maxhely=i
print("5. feladat: A legtöbbet javító csapat:")
print(f"Helyezés: {lista[maxhely].helyezes}\nCsapat: {lista[maxhely].csapat}\nPontszám: {lista[maxhely].pontszam}")

vanehun=False
for adatok in lista:
    if  adatok.csapat=="Magyarország":
        vanehun=True

if  vanehun:
    print("6. feladat: A csapatok között van Magyarország")
else:
    print("6. feladat: A csapatok között nincs Magyarország")

stat={}
for adatok in lista:
    valtozas=adatok.valtozas
    stat[valtozas]=stat.get(valtozas,0)+1

for valtozas,db in stat.items():
    if  db>1:
        print(f"{valtozas} helyet változott: {db} csapat")
