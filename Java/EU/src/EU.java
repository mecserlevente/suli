import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class EU {

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, LocalDate> allamok = new HashMap<>();
        Scanner fileinput = new Scanner(new File("EUcsatlakozas.txt"));
        while (fileinput.hasNextLine()) {
            String data = fileinput.nextLine();
            String temp[] = data.split(";");
            String idotemp[] = temp[1].split("\\.");
            LocalDate idopont = LocalDate.of(Integer.parseInt(idotemp[0]), Integer.parseInt(idotemp[1]), Integer.parseInt(idotemp[2]));
            allamok.put(temp[0], idopont);
        }
        System.out.println("3. feladat: EU tagállamainak száma: " + allamok.size() + " db");

        //4.feladat funkcionális megoldás
        System.out.println("4. feladat: 2007-ben " + allamok.entrySet().stream().filter(k -> k.getValue().getYear() == 2007).count() + " ország csatlakozott.");
        //4.feladat ciklusos megoldás
        int db = 0;
        for (Map.Entry<String, LocalDate> e : allamok.entrySet()) {
            if (e.getValue().getYear() == 2007) {
                db++;
            }
        }
        System.out.println("4. feladat: 2007-ben " + db + " ország csatlakozott");


        //5.feladat funkcionális megoldás
        System.out.println("5. feladat: Magyarország csatlakozásának dátuma: " + (allamok.get("Magyarország")).toString().replace("-", "."));
        //5.feladat ciklusos megoldás
        for (Map.Entry<String, LocalDate> e : allamok.entrySet()) {
            if (e.getKey().equals("Magyarország")) {
                System.out.println("5. feladat: Magyarország csatlakozásának dátuma: " + e.getValue().toString().replace("-", "."));
            }
        }

        //6.feladat funkcionális megoldás
        System.out.println("6. feladat: Májusban " + ((allamok.entrySet().stream().anyMatch(k -> k.getValue().getMonth() == Month.MAY)) ? "" : " nem ") + "volt csatlakozás");
        //6.feladat ciklusos megoldás
        boolean vane = false;
        for (Map.Entry<String, LocalDate> e : allamok.entrySet()) {
            if (e.getValue().getMonth() == Month.MAY) {
                vane = true;
            } else {
                vane = false;
            }
        }
        System.out.println("6. feladat: Májusban " + (vane ? "" : " nem ") + "volt csatlakozás");

        allamok.entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(k -> System.out.println("7. feladat: Legutoljára csatlakozott ország: " + k.getKey()));

        System.out.println("8. feladat: Statisztika");
        HashMap<Integer, Integer> csatlakozasok = new HashMap<>();
        for (Map.Entry<String, LocalDate> e : allamok.entrySet()) {
            csatlakozasok.put(e.getValue().getYear(), csatlakozasok.getOrDefault(e.getValue().getYear(), 0) + 1);
        }
        for (Map.Entry<Integer, Integer> e : csatlakozasok.entrySet()) {
            System.out.println("        " + e.getKey() + " - " + e.getValue() + " ország");
        }

    }
}
