package versenyzok;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Versenyzok {

    public static class adatok {

        String nev;
        LocalDate szulido;
        String nemzetiseg;
        int rajtszam;

        public adatok(String nev, LocalDate szulido, String nemzetiseg, int rajtszam) {
            this.nev = nev;
            this.szulido = szulido;
            this.nemzetiseg = nemzetiseg;
            this.rajtszam = rajtszam;
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<adatok> lista = new ArrayList<>();
        Scanner fileinput = new Scanner(new File("pilotak.csv"));
        String elsosor = fileinput.nextLine();
        while (fileinput.hasNextLine()) {
            String data = fileinput.nextLine();
            String temp[] = data.split(";", -1);
            String ido[] = temp[1].split("\\.");
            if (temp[3].isEmpty()) {
                lista.add(new adatok(temp[0], LocalDate.of(Integer.parseInt(ido[0]), Integer.parseInt(ido[1]),
                        Integer.parseInt(ido[2])), temp[2], 15000));  //15000 tuti nemlesz senkinek a rajtszáma, ideiglenes érték
            } else {
                lista.add(new adatok(temp[0], LocalDate.of(Integer.parseInt(ido[0]), Integer.parseInt(ido[1]),
                        Integer.parseInt(ido[2])), temp[2], Integer.parseInt(temp[3])));
            }

        }
        System.out.println("3. feladat: " + lista.size());
        System.out.println("4. feladat: " + lista.get(lista.size() - 1).nev);
        //ciklusos megoldás 
        for (adatok e : lista) {
            if (e.szulido.getYear() < 1901) {
                System.out.println(e.nev + " (" + e.szulido + ")");
            }
        }
        //STREAM-API megoldás
        lista.stream().filter(k -> k.szulido.getYear() < 1901).forEach(k -> System.out.println(k.nev + " (" + k.szulido + ")"));

        //ciklusos megoldás
        int min = Integer.MAX_VALUE;
        int minhely = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).rajtszam != 15000) {
                if (lista.get(i).rajtszam < min) {
                    min = lista.get(i).rajtszam;
                    minhely = i;
                }
            }
        }
        System.out.println("6. feladat: " + lista.get(minhely).nemzetiseg);
        //STREAM-API megoldás
        int min1 = lista.stream()
                .filter(k -> k.rajtszam != 15000)
                .mapToInt(k -> k.rajtszam)
                .min()
                .getAsInt();
        lista.stream()
                .filter(k -> k.rajtszam == min1)
                .findFirst()
                .ifPresent(k -> System.out.println("6. feladat: " + k.nemzetiseg));

        ArrayList<Integer> rajtszamokhoz = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).rajtszam != 15000) {
                rajtszamokhoz.add(lista.get(i).rajtszam);
            }
        }
        List<Integer> rajtsz = rajtszamokhoz.stream()
                .filter(k -> Collections.frequency(rajtszamokhoz, k) > 1)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("7. feladat: " + rajtsz);
    }

}
