import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class autoverseny_funct {
    public static void main(String[] args) throws IOException {
        List<String> lista = Files.lines(Paths.get("autoverseny.csv"))
                .skip(1)
                .collect(Collectors.toList());
        System.out.println("3. feladat: " + lista.size());

        lista.stream().filter(k -> Arrays.stream(k.split(";")).toArray()[1].equals("Fürge Ferenc"))
                .filter(k -> Arrays.stream(k.split(";")).toArray()[3].equals("Gran Prix Circuit"))
                .filter(k -> Arrays.stream(k.split(";")).toArray()[5].equals("3"))
                .findFirst()
                .ifPresent(k -> System.out.println("4. feladat " +
                        (LocalTime.of(Integer.parseInt(Arrays.stream(k.split(";")).toArray()[4].toString().split(":")[0]),
                                Integer.parseInt(Arrays.stream(k.split(";")).toArray()[4].toString().split(":")[1]),
                                Integer.parseInt(Arrays.stream(k.split(";")).toArray()[4].toString().split(":")[2])
                        ).toSecondOfDay()) + " másodperc\n5.feladat:\nKérem egy versenyző nevét:"
                ));

        String bekertnev = new Scanner(System.in).nextLine();
        lista.stream().filter(k -> Arrays.stream(k.split(";")).toArray()[1].equals(bekertnev))
                .min(Comparator.comparing(e -> LocalTime.of(Integer.parseInt(Arrays.stream(e.split(";")).toArray()[4].toString().split(":")[0]),
                        Integer.parseInt(Arrays.stream(e.split(";")).toArray()[4].toString().split(":")[1]),
                        Integer.parseInt(Arrays.stream(e.split(";")).toArray()[4].toString().split(":")[2])
                ))).ifPresentOrElse(k -> System.out.println("6. feladat: " + (Arrays.stream(k.split(";")).toArray()[3]) +
                ", " + (Arrays.stream(k.split(";")).toArray()[4])), () -> System.out.println("6. feladat: Nincs ilyen versenyző az állományban!"));
    }
}
