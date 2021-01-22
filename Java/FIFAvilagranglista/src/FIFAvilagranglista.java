import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.io.File;
import java.util.Map;

public class FIFAvilagranglista {
    public static class adatok {
        String csapat;
        int helyezes;
        int valtozas;
        int pontszam;

        public adatok(String csapat, int helyezes, int valtozas, int pontszam) {
            this.csapat = csapat;
            this.helyezes = helyezes;
            this.valtozas = valtozas;
            this.pontszam = pontszam;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<adatok> lista = new ArrayList<>();
        Scanner fileinput = new Scanner(new File("fifa.txt"));
        String elsosor = fileinput.nextLine();
        while (fileinput.hasNextLine()) {
            String data = fileinput.nextLine();
            String temp[] = data.split(";");
            lista.add(new adatok(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
        }
        fileinput.close();
        System.out.println("3. feladat: A világranglistán " + lista.size() + " csapat szerepel");
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("4. feladat: A csapatok átlgos pontszáma: " + (df.format(lista.stream().mapToInt(k -> k.pontszam).average().getAsDouble())) + " pont");
        int max = Integer.MIN_VALUE;
        int maxhely = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).valtozas > max) {
                max = lista.get(i).valtozas;
                maxhely = i;
            }
        }
        System.out.println("5. feladat: A legtöbbet javító csapat:");
        System.out.println("        Helyezés: " + lista.get(maxhely).helyezes);
        System.out.println("        Csapat: " + lista.get(maxhely).csapat);
        System.out.println("        Pontszám: " + lista.get(maxhely).pontszam);

        boolean vane = false;
        for (adatok e : lista) {
            if (e.csapat.equals("Magyarország")) {
                vane = true;
            } else {
                vane = false;
            }
        }
        System.out.println("6. feladat: A csapatok között " + (vane ? "van" : "nincs") + " Magyarország");
        Map<Integer, Integer> valtozasok = new LinkedHashMap<>();
        for (adatok e : lista) {
            valtozasok.put(e.valtozas, valtozasok.getOrDefault(e.valtozas, 0) + 1);
        }
        System.out.println("7. feladat: Statisztika");
        for (Map.Entry<Integer, Integer> e : valtozasok.entrySet()) {
            if (e.getValue() > 1) {
                System.out.println("        " + e.getKey() + " helet változott: " + e.getValue() + " csapat");
            }
        }
    }
}
