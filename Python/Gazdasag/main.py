lista = list()
with open('terulet.txt', 'r') as file:
    for sor in file.readlines():
        lista.append(sor)

hossz = len(lista)
szelesseg = len(lista[0].rstrip())
print(f"2. feladat: {hossz * 100}m x {szelesseg * 100}m, területe {hossz * szelesseg}ha")

ldb = 0
for i in range(len(lista)):
    if "L" in lista[i]:
        temphossz = len(lista[i]) - len(lista[i].replace('L', ''))
        ldb += temphossz
print(f"3. feladat: {round((ldb / (hossz * szelesseg) * 100), 2)}%")

hely = 0
for i in range(len(lista)):
    if "L" in lista[i]:
        hely = i
        break
print(f"4. feladat: {hely*100}m")

tav = 0
for i in range(len(lista) - 1):
    if lista[i] == lista[i + 1]:
        tav += 1
print(f"5. feladat: {tav * 100}m")
print(f"6. feladat: {tav}db")
print(f"7. feladat: {tav * 2}ha")
