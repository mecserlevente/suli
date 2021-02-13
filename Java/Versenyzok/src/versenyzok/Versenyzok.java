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
        String rajtszam;

        public adatok(String nev, LocalDate szulido, String nemzetiseg, String rajtszam) {
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
            String temp[] = data.split(";");
            String ido[] = temp[1].split("\\.");

            String rajtszam = data.substring(data.length() - 2, data.length()).replace(";", "").replaceAll("[A-Za-z]", "15000"); //nemjutott jobb megoldás eszembe:-(

            lista.add(new adatok(temp[0], LocalDate.of(Integer.parseInt(ido[0]), Integer.parseInt(ido[1]),
                    Integer.parseInt(ido[2])), temp[2], rajtszam.replace(" ", "15000")));

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
            if (!lista.get(i).rajtszam.equals("-") || (!lista.get(i).rajtszam.equals(" "))) {
                if (Integer.parseInt(lista.get(i).rajtszam) < min) {
                    min = Integer.parseInt(lista.get(i).rajtszam);
                    minhely = i;
                }
            }
        }
        System.out.println("6. feladat: " + lista.get(minhely).nemzetiseg);
        //STREAM-API megoldás
        int min1 = lista.stream()
                .filter(k -> !k.rajtszam.equals("-") || !k.rajtszam.equals(" "))
                .mapToInt(k -> Integer.parseInt(k.rajtszam))
                .min()
                .getAsInt();
        lista.stream()
                .filter(k -> k.rajtszam.equals(Integer.toString(min1)))
                .findFirst()
                .ifPresent(k -> System.out.println("6. feladat: " + k.nemzetiseg));

        ArrayList<Integer> rajtszamokhoz = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).rajtszam.equals("-") || (!lista.get(i).rajtszam.equals(" "))) {
                rajtszamokhoz.add(Integer.parseInt(lista.get(i).rajtszam));
            }
        }
        List<Integer> rajtsz = rajtszamokhoz.stream()
                .filter(k -> Collections.frequency(rajtszamokhoz, k) > 1)
                .distinct()
                .collect(Collectors.toList());
        rajtsz.remove(0);
        System.out.println("7. feladat: " + rajtsz);
    }

}
