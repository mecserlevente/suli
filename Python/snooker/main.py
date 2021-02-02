from statistics import mean

class adatok:
    def __init__(self, sor):
        darabok = sor.split(';')
        self.helyezes = int(darabok[0])
        self.nev = darabok[1]
        self.orszag = darabok[2]
        self.nyeremeny = int(darabok[3])


lista = list()

with open('snooker.txt', 'r') as file:
    elsosor = file.readline()
    for sor in file.readlines():
        lista.append(adatok(sor))

print(f"3. feladat: A világranglistán {len(lista)} versenyző szerepel")
bevetel = list()
for adatok in lista:
    bevetel.append(adatok.nyeremeny)
print(f"4. feladat: A versenyzők átlagosan {round(mean(bevetel), 2)} fontot kerestek")
max = 0
maxhely=0

for i in range(len(lista)):
    if lista[i].orszag == "Kína":
        if lista[i].nyeremeny > int(max):
            max = lista[i].nyeremeny
            maxhely = i

print(f"5. feladat: A legjobban kereső kínai versenyző:\nHelyezés: {lista[maxhely].helyezes}\nNév: {lista[maxhely].nev}\nOrszág: Kína\nNyeremény összege: {380 * lista[maxhely].nyeremeny} Ft")

vanenorveg=False
for adatok in lista:
    if  adatok.orszag == "Norvégia":
        vanenorveg = True

if vanenorveg:
    print("6. feladat: A versenyzők között van norvég versenyző.")
else:
    print("6. feladat: A versenyzők között nincs norvég versenyző.")

print("7. feladat: Statisztika")
stat= {}
for adatok in lista:
    orszag=adatok.orszag
    stat[orszag]=stat.get(orszag,0)+1

for orszag,db in stat.items():
    if  db>4:
        print(f"{orszag} - {db} fő")
