class adatok:
    def __init__(self, sor):
        darabok = sor.split(';')
        self.kategoria = darabok[0]
        self.tulelo = int(darabok[1])
        self.eltuntek = int(darabok[2])

lista = list()

with open('titanic.txt', 'r') as file:
    for sor in file.readlines():
        lista.append(adatok(sor))

print(f"2. feladat: {len(lista)} db")

sum = 0
for adatok in lista:
    sum += adatok.tulelo + adatok.eltuntek
print(f"3. feladat: {sum} fő")

bekert = input("4. feladat: Kulcsszó: ")
vane = False
for adatok in lista:
    if bekert in adatok.kategoria:
        vane = True
if vane:
    print("       Van találat!")
else:
    print("       Nincs találat!")

print("5. feladat:")
for adatok in lista:
    if bekert in adatok.kategoria:
        print(f"        {adatok.kategoria} {adatok.tulelo + adatok.eltuntek} fő")

print("6. feladat:")
for adatok in lista:
    if  (adatok.eltuntek/adatok.tulelo)*100 >60:
        print(f"        {adatok.kategoria}")

max=0
maxhely=0
for i in range(len(lista)):
    if  lista[i].tulelo>max:
        max=lista[i].tulelo
        maxhely=i
print(f"7. feladat: {lista[maxhely].kategoria}")