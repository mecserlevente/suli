
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Lift {
    public static class adatok {
        LocalDate idopont;
        int kartyaszam;
        int induloszint;
        int celszint;

        public adatok(LocalDate idopont, int kartyaszam, int induloszint, int celszint) {
            this.idopont = idopont;
            this.kartyaszam = kartyaszam;
            this.induloszint = induloszint;
            this.celszint = celszint;
        }
    }

    public static final DateTimeFormatter idoformazo = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
    public static Scanner be = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<adatok> lista = new ArrayList<>();
        Scanner fileinput = new Scanner(new File("lift.txt"));
        while (fileinput.hasNextLine()) {
            String data = fileinput.nextLine();
            String temp[] = data.split(" ");
            lista.add(new adatok(LocalDate.parse(temp[0], idoformazo), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));

        }
        fileinput.close();
        System.out.println("3. feladat: Összes lifthasználat: " + lista.size());

        System.out.println("4. feladat: Időszak: " + idokiiras(lista.get(0).idopont) + " - " + idokiiras(lista.get(lista.size() - 1).idopont));
        int maxszint = lista.stream().mapToInt(k -> k.celszint).max().getAsInt();
        System.out.println("5. feladat: Célszint max: " + maxszint);

        System.out.println("6. feladat:");
        System.out.print("Kártya száma: ");
        String input1 = be.nextLine();
        System.out.print("Célszint száma: ");
        String input2 = be.nextLine();
        int bekertkartya = 0;
        int bekertcel = 0;
        try {
            bekertkartya = Integer.parseInt(input1);
            bekertcel = Integer.parseInt(input2);

        } catch (NumberFormatException e) {
            bekertkartya = 5;
            bekertcel = 5;
        }
        boolean utazott = false;
        for (adatok e : lista) {
            if (e.kartyaszam == bekertkartya && e.celszint == bekertcel) {
                utazott = true;
                break;
            }
        }
        System.out.println("7. feladat: A(z) " + bekertkartya + ". kártyával " + (utazott ? "" : "nem ") + "utaztak a(z) " + bekertcel + ". emeletre!");

        Map<LocalDate, Integer> stat = new TreeMap<>(); //treemap mivel időrendben kell megjeleníteni
        for (adatok e : lista) {
            stat.put(e.idopont, stat.getOrDefault(e.idopont, 0) + 1);
        }
        for (Map.Entry<LocalDate, Integer> e : stat.entrySet()) {
            System.out.println(idokiiras(e.getKey()) + " - " + e.getValue() + "x");
        }

    }

    public static String idokiiras(LocalDate ido) {
        return String.valueOf(ido).replace("-", ".");
    }
}
