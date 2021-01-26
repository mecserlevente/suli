import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class vasmegye {
    public static class adatok {
        int nem;
        int ev;
        int honap;
        int nap;
        int megkulonbozteto;
        int[] fullid;

        public adatok(int nem, int ev, int honap, int nap, int megkulonbozteto, int[] fullid) {
            this.nem = nem;
            this.ev = ev;
            this.honap = honap;
            this.nap = nap;
            this.megkulonbozteto = megkulonbozteto;
            this.fullid = fullid;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<adatok> lista = new ArrayList<>();
        Scanner fileinput = new Scanner(new File("vas.txt"));
        String ev = "";
        while (fileinput.hasNextLine()) {
            String data = fileinput.nextLine();
            String temp[] = data.split("-");
            char[] fullidhez = data.replace("-", "").toCharArray();
            int idtmep[] = new int[11];
            for (int i = 0; i < 11; i++) {
                idtmep[i] = Integer.parseInt(String.valueOf(fullidhez[i]));
            }
            if (Integer.parseInt(temp[0]) < 3) {
                ev = "19" + temp[1].substring(0, 2);
            } else {
                ev = "20" + temp[1].substring(0, 2);
            }
            lista.add(new adatok(Integer.parseInt(temp[0]), //nem
                    Integer.parseInt(ev), //ev
                    Integer.parseInt(temp[1].substring(2, 4)),  //honap
                    Integer.parseInt(temp[1].substring(4, 6)),  //nap
                    Integer.parseInt(temp[2]),  //megkulonbozteto
                    idtmep  //fullid
            ));
        }
        fileinput.close();
        System.out.println("2. feladat: " + lista.size() + " adat beolvasva");
        System.out.println("4.feladat: Ellenőrzés");

        for (int i = 0; i < lista.size(); i++) {
            if (CdvEll(lista.get(i).fullid) == false) {
                String tempev = "" + lista.get(i).fullid[1] + lista.get(i).fullid[2] + lista.get(i).fullid[3] + lista.get(i).fullid[4]
                        + lista.get(i).fullid[5] + lista.get(i).fullid[6]; // a kiiratás formázásához
                System.out.println("        Hibás a " + lista.get(i).fullid[0] + "-" + tempev + "-" + lista.get(i).megkulonbozteto + " személyi azonosító!");
                lista.remove(i);
            }
        }

        System.out.println("5. feladat: Vas megyében a vizsgált évek alatt " + lista.size() + " csecsemő született.");
        System.out.println("6. feladat: Fiúk száma: " + (lista.stream().filter(e -> e.nem == 1 || e.nem == 3).count()));
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (adatok e : lista) {
            min = Integer.min(min, e.ev);
            max = Integer.max(max, e.ev);
        }
        System.out.println("7. feladat: Vizsgált időszak: " + min + " - " + max);
        System.out.println("8. feladat: Szökőnapon " + ((lista.stream().filter(e -> e.honap == 2 && e.nap == 24).count()) > 0 ? "" : "nem ") + "született baba!");
        TreeMap<Integer, Integer> szulstatisztika = new TreeMap<>();
        for (adatok e : lista) {
            szulstatisztika.put(e.ev, szulstatisztika.getOrDefault(e.ev, 0) + 1);
        }
        System.out.println("9. feladat: Statisztika");
        for (Map.Entry<Integer, Integer> e : szulstatisztika.entrySet()) {
            System.out.println("        " + e.getKey() + " - " + e.getValue() + " fő");
        }

    }

    public static boolean CdvEll(int[] id) {
        int k = id[10];
        int sum = ((id[0] * 10) + (id[1] * 9) + (id[2] * 8) + (id[3] * 7) + (id[4] * 6) + (id[5] * 5) + (id[6] * 4) + (id[7] * 3) + (id[8] * 2) + (id[9] * 1));
        boolean result = true;
        if (sum % 11 == k) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

}
