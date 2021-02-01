from statistics import mean

class stadionok:
    def __init__(self, sor):
        darabok = sor.split(';')
        self.varos = darabok[0]
        self.nev1 = darabok[1]
        self.nev2 = darabok[2]
        self.ferohely = int(darabok[3])


lista = list()

with open("vb2018.txt", "r")as file:
    elsosor = file.readline()
    for sor in file.readlines():
        lista.append(stadionok(sor))

    print(f"3. feladat: Stadionok száma: {len(lista)}")

    min = 100000
    hely = 0
    print("4.feladat: A legkevesebb férőhely:")
    for i in range(len(lista)):
        if lista[i].ferohely < min:
            min = lista[i].ferohely
            hely = i
    print(f"Város {lista[hely].varos}\nStadion neve:{lista[hely].nev1} \nFérőhely:{lista[hely].ferohely}")

atlaghoz = list()
for stadionok in lista:
    atlaghoz.append(stadionok.ferohely)
atlag = mean(atlaghoz)
print(f"5. feladat: Átlagos férőhelyszám: {round(atlag, 1)}")

varosinput = input("7. feladat: Kérem a város nevét: ")
while len(varosinput) < 3:
    varosinput = input("7. feladat: Kérem a város nevét: ")

volte = False
for stadionok in lista:
    if varosinput.lower() == stadionok.varos.lower() or varosinput.upper() == stadionok.varos.upper() or varosinput == stadionok.varos:
        volte = True
        break
if volte:
    print("8. feladat: A megadott város VB helyszín.")
else:
    print("8. feladat: A megadott város nem VB helyszín.")

varosok = set()
for stadionok in lista:
    varosok.add(stadionok.varos)
print(f"9. feladat: {len(varosok)} különböző városban voltak mérkőzések.")
