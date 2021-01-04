1.
CREATE DATABASE snooker DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;

3.
SELECT CONCAT("3. feladat: A vil�granglist�n ",COUNT(*)," versenyz� szerepel") AS "3. feladat" 
FROM lista;

4.
SELECT CONCAT("4. feladat: A versenyz�k �tlagosan ",ROUND(AVG(`Nyeremeny`),2)," fontot kerestek") AS "4. feladat" 
FROM lista;

5.
SELECT CONCAT("Helyez�s :",Helyezes) as "helyez�s",CONCAT("N�v: ",Nev) as "n�v",CONCAT("Orsz�g: ",Orszag) as "orsz�g",CONCAT("Nyerem�ny �sszege: ",SUM(`Nyeremeny`*380)," Ft") as "nyerem�ny"
  FROM lista
  WHERE Orszag LIKE "%K�na"
  GROUP BY Nev
  ORDER BY Nyeremeny DESC
  limit 1;

6.
SELECT IF(Orszag LIKE "%Norv�gia%","6. feladat: A versenyz�k k�z�tt van norv�g versenyz�.","6. feladat: a versenyz�k k�z�tt nincs norv�g versenyz�.") as "6. feladat"
  FROM lista
  GROUP By Orszag
  HAVING IF(Orszag LIKE "%Norv�gia%",1,0);

7.
SELECT Orszag,CONCAT(COUNT(*)," f�") as "statisztika"
  FROM lista
  GROUP By Orszag
  HAVING COUNT(*)>4;