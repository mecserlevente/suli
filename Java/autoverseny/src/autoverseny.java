import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;

public class autoverseny {

    public static class adatok {
        String csapat;
        String versenyzo;
        int eletkor;
        String palya;
        LocalTime korido;
        int kor;

        public adatok(String csapat, String versenyzo, int eletkor, String palya, LocalTime korido, int kor) {
            this.csapat = csapat;
            this.versenyzo = versenyzo;
            this.eletkor = eletkor;
            this.palya = palya;
            this.korido = korido;
            this.kor = kor;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<adatok> lista = new ArrayList<>();

        Scanner fileinput = new Scanner(new File("autoverseny.csv"));
        String elsosor = fileinput.nextLine();
        while (fileinput.hasNextLine()) {
            String data = fileinput.nextLine();
            String tomb[] = data.split(";");
            String idotomb[] = tomb[4].split(":");
            lista.add(new adatok(tomb[0], tomb[1], Integer.parseInt(tomb[2]), tomb[3], LocalTime.of(Integer.parseInt(idotomb[0]), Integer.parseInt(idotomb[1]), Integer.parseInt(idotomb[2])), Integer.parseInt(tomb[5])));

        }
        fileinput.close();
        System.out.println("3. feladat: " + lista.size());

        for (adatok e : lista) {
            if (e.versenyzo.equals("Fürge Ferenc") && e.palya.equals("Gran Prix Circuit") && e.kor == 3) {
                System.out.println("4. feladat: " + e.korido.toSecondOfDay() + " másodperc");
            }
        }
        System.out.println("5. feladat:\nKérem egy versenyző nevét:");
        Scanner be = new Scanner(System.in);
        String bekertnev = be.nextLine();
        int min = Integer.MAX_VALUE;
        int minhely = 0;
        boolean vane = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).versenyzo.equals(bekertnev)) {
                if (lista.get(i).korido.toSecondOfDay() < min) {
                    min = lista.get(i).korido.toSecondOfDay();
                    minhely = i;
                    vane = true;
                }
            }
        }
        if (vane) {
            System.out.println("6. feladat: " + lista.get(minhely).palya + ", " + lista.get(minhely).korido);
        } else {
            System.out.println("6. feladat: Nincs ilyen versenyző az állományban!");
        }
    }

}
