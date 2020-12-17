/* 1. feladat:  */
CREATE DATABASE katica DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
/* 3. feladat:  */
ALTER TABLE `kategoria` ADD PRIMARY KEY( `id`); 
/* 4. feladat:  */
ALTER TABLE `forgalom` FOREIGN KEY(`kategoriaID`) REFERENCES `kategoria`(`id`);
/* 6. feladat:  */
INSERT INTO `kategoria` (`id`, `kategoriaNev`) VALUES ('', 'Aj�nd�kt�rgyak');
/* 7. feladat:  */
UPDATE `forgalom` SET `termek`="Gyros t�l" WHERE `termek`="Gyrost�l";
/* 8. feladat:  */
SELECT `termek`,`vevo` FROM `forgalom` WHERE `kiadva`=0 ORDER BY `vevo`;
/* 9. feladat:  */
SELECT kategoriaNev,SUM(nettoar*mennyiseg) AS "Nett� bev�tel",SUM(nettoar*mennyiseg*0.27) AS "Forgalmi ad�"
FROM kategoria INNER JOIN forgalom ON kategoria.id=forgalom.kategoriaId
GROUP BY kategoriaNev

+feladat
DELETE FROM `kategoria` WHERE `kategoriaNev`="Informatikai eszk�z"
