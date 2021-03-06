1.
CREATE DATABASE kozossegi DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;

2.
SELECT nev 
  FROM tevekenyseg  
  WHERE iskolai=-1 
  ORDER BY nev ASC;

3.
SELECT SUM(maxletszam*hossz) as "óraszám" 
  FROM munka 
  WHERE maxletszam>0;

4.
SELECT diak.osztaly,diak.nev,munka.datum,munka.kezdes,munka.hossz,tevekenyseg.nev
FROM diak 
	LEFT JOIN jelentkezes ON jelentkezes.diakid = diak.id 
	LEFT JOIN munka ON jelentkezes.munkaid = munka.id 
	LEFT JOIN tevekenyseg ON munka.tevekenysegid = tevekenyseg.id
    WHERE diak.osztaly LIKE "%10%" AND jelentkezes.teljesitve=-1;

5.
SELECT diak.nev,COUNT(*) as "távolmaradás"
FROM diak 
	LEFT JOIN jelentkezes ON jelentkezes.diakid = diak.id
    WHERE jelentkezes.teljesitve=0 AND jelentkezes.elfogadva=-1
    GROUP BY diak.nev
    HAVING COUNT(*)>1;

6.
SELECT munka.datum,munka.kezdes,munka.hossz,tevekenyseg.nev
FROM munka 
	LEFT JOIN jelentkezes ON jelentkezes.munkaid = munka.id 
	LEFT JOIN tevekenyseg ON munka.tevekenysegid = tevekenyseg.id
    WHERE munka.id NOT IN(
    SELECT jelentkezes.munkaid FROM jelentkezes) 
       AND munka.datum BETWEEN "2016.10.29" AND "2016.11.06"
       ORDER BY munka.datum,munka.kezdes;

7.
SELECT diak.osztaly,COUNT(*)
FROM diak 
	LEFT JOIN jelentkezes ON jelentkezes.diakid = diak.id 
	LEFT JOIN munka ON jelentkezes.munkaid = munka.id
   WHERE diak.id IN
	(SELECT diakid FROM jelentkezes WHERE teljesitve=-1)
	GROUP BY diak.osztaly;
