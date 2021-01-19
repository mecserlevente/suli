package cimek;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class cimek {

    public static void main(String[] args) throws IOException {    
        List<String> lista = Files.readAllLines(Paths.get("ip.txt"));
        
        System.out.println("2. feladat:");
        System.out.println("Az állományban " + lista.size() + " darab adatsor van.");
        ArrayList<String> rendezettlista = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            rendezettlista.add(lista.get(i));
        }
        Collections.sort(rendezettlista);
        System.out.println("3. feladat:");
        System.out.println("A legalacsonyabb tárolt IP-cím:\n" + rendezettlista.get(0));
        System.out.println("4. feladat:");
        System.out.println("Dokumentációs cím: "
                + lista.stream().filter(filterezo -> filterezo.startsWith("2001:0db8")).count() + " darab");
        System.out.println("Globális egyedi cím: "
                + lista.stream().filter(filterezo -> filterezo.startsWith("2001:0e")).count() + " darab");
        System.out.println("Helyi egyedi cím: "
                + lista.stream().filter(filterezo -> filterezo.startsWith("fc") || filterezo.startsWith("fd")).count()
                + " darab");

        TreeMap<Integer, String> tnyolcnullasok = new TreeMap<>();
        PrintWriter pr = new PrintWriter(new FileWriter("sok.txt"));

        for (int i = 0; i < lista.size(); i++) {
            if ((lista.get(i).length() - (lista.get(i).replace("0", "").length())) > 17) {

                tnyolcnullasok.put((i + 1), lista.get(i));
            }

        }

        for (Map.Entry<Integer, String> e : tnyolcnullasok.entrySet()) {
            pr.println(e.getKey() + " " + e.getValue());
        }
        pr.close();
        System.out.println("6. feladat:\nKérek egy sorszámot: ");
        Scanner be = new Scanner(System.in);
        int bekert = be.nextInt();
        System.out.println(lista.get(bekert - 1));
        System.out.println(lista.get(bekert - 1).replace("0000", "0"));

        System.out.println("7.feladat");
        if (lista.get(bekert - 1).contains(":0000:0000:0000:")) {
            System.out.println(lista.get(bekert - 1).replace(":0000:0000:0000:", "::"));
        } else {
            System.out.println("Nem rövidíthető tovább.");
        }

    }

}
