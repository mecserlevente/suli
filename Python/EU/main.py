import datetime
from datetime import date

class adatok:
    def __init__(self, sor):
        darabok = sor.split(';')
        self.orszag = darabok[0]
        ido = darabok[1].split('.')
        self.csatlakozas = datetime.date(year=int(ido[0]), month=int(ido[1]), day=int(ido[2]))


lista = list()

with open('EUcsatlakozas.txt', 'r')as file:
    for sor in file.readlines():
        lista.append(adatok(sor))

print(f"3. feladat: EU tagállamainak száma: {len(lista)} db")

db = 0
for adatok in lista:
    if adatok.csatlakozas.year == 2007:
        db += 1
print(f"4. feladat: 2007-ben {db} ország csatlakozott.")

for adatok in lista:
    if adatok.orszag == "Magyarország":
        print(f"5. feladat: Magyarország csatlakozásának dátuma: {adatok.csatlakozas.__str__().replace('-', '.')}")

majusi = False
for adatok in lista:
    if adatok.csatlakozas.month == 5:
        majusi = True
        break
if majusi:
    print("6. Feladat: Májusban volt csatlakozás!")
else:
    print("6. Feladat: Májusban nem volt csatlakozás!")

datum = date.today()
utolsoorszag = "asd"
for adatok in lista:
    if datum > adatok.csatlakozas:
        datum = adatok.csatlakozas
        utolsoorszag = adatok.orszag
print(f"7. feladat: Legutoljára csatlakozott ország: {utolsoorszag}")
stat = {}
for adatok in lista:
    ev = adatok.csatlakozas.year
    stat[ev] = stat.get(ev, 0) + 1

for ev, db in stat.items():
    print(f"{ev} - {db} ország")