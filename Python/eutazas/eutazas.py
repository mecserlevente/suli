print('1. feladat')
adatok=[]
file=open("utasadat.txt")
 
adatok=[line.rstrip('\n') for line in file]
 
print("2. feladat")
print("A buszra "+str(len(adatok))+" utas akart felszállni.")
 
print("3. feladat")
 
elut=0
 
for i in range(0, len(adatok)):
    sordb=adatok[i].split(' ')
    dtdb=sordb[1].split('-')
 
    if (sordb[3]=='JGY' and sordb[4]=='0'):
        elut=elut+1
    if (sordb[3]=="FEB" or sordb[3]=="TAB" or sordb[3]=="NYB"):
        
        if dtdb[0]>sordb[4]:
            elut=elut+1
 
print("A buszra "+str(elut)+" utas nem szállhatott fel.")
 
print("4. feladat")
 
m_usz=[]
for i in range(0,30):
    m_usz.append(0)
 
for i in range(0, len(adatok)):
    sordb=adatok[i].split(' ')
    msz=int(sordb[0])
 
    m_usz[msz]=m_usz[msz]+1
 
 
print("A legtöbb utas ("+str(max(m_usz))+" fő) a "+str(m_usz.index(max(m_usz)))+". megállóban próbált felszállni.")
 
 
print("5. feladat")
kedv=0
ingy=0
 
for i in range(0,len(adatok)):
    sordb=adatok[i].split(' ')
    if (sordb[3]!="JGY"):
        dtdb=sordb[1].split('-')
        #dtdb[0]
        if (dtdb[0]<=sordb[4]): # érvényes
            if (sordb[3]=="TAB" or sordb[3]=="NYB"):
                kedv=kedv+1
            if (sordb[3]=="NYP" or sordb[3]=="RVS" or sordb[3]=="GYK"):
                ingy=ingy+1
 
print("Ingyenesen utazók száma: "+str(ingy)+" fő")
print("A kedvezményesen utazók száma: "+str(kedv)+" fő")
 
 
print("6. feladat")
 
def napokszama(e1, h1, n1, e2, h2, n2):
    h1 = (h1 + 9) % 12
    e1 = e1 - h1 / 10
    d1= 365*e1 + e1 / 4 - e1 / 100 + e1 / 400 + (h1*306 + 5) / 10 + n1 - 1
    h2 = (h2 + 9) % 12
    e2 = e2 - h2 / 10
    d2= 365*e2 + e2 / 4 - e2 / 100 + e2 / 400 + (h2*306 + 5) / 10 + n2 - 1
    ertek=d2-d1
    return ertek
 
 
 
print("7. feladat")
file2=open("figyelmeztetes.txt","w")
 
for i in range(0, len(adatok)):
    sordb=adatok[i].split(' ')
    if (sordb[3]!="JGY"):
        
        dtdb=sordb[1].split('-')
        ev1=int(sordb[1][0:4])
        ho1=int(sordb[1][4:6])
        nap1=int(sordb[1][6:8])
 
        ev2=int(sordb[4][0:4])
        ho2=int(sordb[4][4:6])
        nap2=int(sordb[4][6:8])
 
        if (napokszama(ev1,ho1,nap1,ev2,ho2,nap2)>=0 and napokszama(ev1,ho1,nap1,ev2,ho2,nap2)<=3):
           file2.write(sordb[2]+" "+sordb[4][0:4]+"-"+sordb[4][4:6]+"-"+sordb[4][6:8]+"\n")
            
file2.close()
