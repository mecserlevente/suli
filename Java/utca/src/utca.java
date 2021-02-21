import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class utca {
    public static class adatok {
        int parose;
        int szelesseg;
        char szin;
        int hazszam;

        public adatok(int parose, int szelesseg, char szin, int hazszam) {
            this.parose = parose;
            this.szelesseg = szelesseg;
            this.szin = szin;
            this.hazszam = hazszam;
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<adatok> lista = new ArrayList<adatok>();
        beolvas(lista);

        System.out.println("2. feladat\nAz eladott telkek száma: " + lista.size());
        System.out.println("3. feladat\nA " + (lista.get(lista.size() - 1).parose == 0 ? "páros" : "páratlan") + " oldalon adták el az utolsó telket.");
        System.out.println("Az utolsó telek házszáma: " + lista.get(lista.size() - 1).hazszam);
        System.out.println("4. feladat");
        for (int i = 0; i < lista.size() - 1; i++) {
            if (lista.get(i).parose == 1 && lista.get(i + 1).parose == 1) {
                if (lista.get(i).szin != '#' || lista.get(i).szin != ':') {
                    if (lista.get(i).szin == lista.get(i + 1).szin) {
                        System.out.println("A szomszédossal egyezik a kerítés színe: " + lista.get(i).hazszam);
                        break;
                    }
                }
            }
        }

        System.out.print("5. feladat\nAdjon meg egy házszámot! ");
        String behazszam = new Scanner(System.in).nextLine();
        lista.stream()
                .filter(k -> k.hazszam == Integer.parseInt(behazszam))
                .findFirst()
                .ifPresent(k -> System.out.println("A kerítés színe / állapota: " + k.szin));
        String szin = "";
        List<Character> szinek = IntStream.rangeClosed('A', 'Z').mapToObj(k -> (char) k).collect(Collectors.toList());
        for (int i = 1; i < lista.size() - 1; i++) {
            if (lista.get(i).hazszam == Integer.parseInt(behazszam)) {
                if (lista.get(i - 1).szin == lista.get(i).szin) {
                    szinek.remove(lista.get(i).szin);
                }
                if (lista.get(i + 1).szin == lista.get(i).szin) {
                    szinek.remove(lista.get(i).szin);
                }
            }
        }
        int random = (int) (Math.random() * szinek.size());
        System.out.print("Egy lehetséges festési szín: " + szinek.get(random));

        PrintWriter pr = new PrintWriter(new FileWriter("utcakep.txt"));
        for (adatok e : lista) {
            if (e.parose == 1) {
                for (int i = 0; i < e.szelesseg; i++) {
                    pr.print(e.szin);
                }
            }
        }
            //TODO a házszámot hozzáírni
        pr.close();
    }

    public static void beolvas(ArrayList<adatok> lista) throws FileNotFoundException {
        Scanner fileinput = new Scanner(new File("kerites.txt"));
        int paroshaz = 2;
        int paratlanhaz = 1;

        while (fileinput.hasNextLine()) {
            String data = fileinput.nextLine();
            String tomb[] = data.split(" ");

            if (Integer.parseInt(tomb[0]) == 0) { //páros
                lista.add(new adatok(Integer.parseInt(tomb[0]), Integer.parseInt(tomb[1]), tomb[2].charAt(0), paroshaz));
                paroshaz += 2;
            } else {  //páratlan
                lista.add(new adatok(Integer.parseInt(tomb[0]), Integer.parseInt(tomb[1]), tomb[2].charAt(0), paratlanhaz));
                paratlanhaz += 2;
            }
        }
        fileinput.close();
    }

}
