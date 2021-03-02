import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Fuvar {
	public static class adatok {
		int id;
		LocalDateTime indulas;
		int idotartam;
		double tavolsag;
		double vitelidij;
		double borravalo;
		String fizetesimod;
		
		public adatok(int id, LocalDateTime indulas, int idotartam, double tavolsag, double vitelidij, double borravalo,
				String fizetesimod) {
			this.id = id;
			this.indulas = indulas;
			this.idotartam = idotartam;
			this.tavolsag = tavolsag;
			this.vitelidij = vitelidij;
			this.borravalo = borravalo;
			this.fizetesimod = fizetesimod;
		}

	}

	public static ArrayList<adatok> lista = new ArrayList<adatok>();

	public static void main(String[] args) throws IOException {
		Scanner fileinput = new Scanner(new File("fuvar.csv"));
		String elsosor = fileinput.nextLine();
		while (fileinput.hasNextLine()) {
			String data = fileinput.nextLine();
			String temp[] = data.split(";");
			lista.add(new adatok(Integer.parseInt(temp[0]),LocalDateTime.parse(temp[1].replace(' ', 'T')), Integer.parseInt(temp[2]), Double.parseDouble(temp[3].replace(',', '.')),
					Double.parseDouble(temp[4].replace(',', '.')), Double.parseDouble(temp[5].replace(',', '.')), temp[6]));

		}
		fileinput.close();
		
		System.out.println("3. feladat: "+lista.size()+" fuvar");
		Long utazasdb=lista.stream()
								  .filter(k -> k.id==6185)
								  .distinct()
								  .count();
		
		double bevetelfizetes=lista.stream()
								 .filter(k -> k.id==6185)
								 .mapToDouble(k -> k.vitelidij).sum();
		
		double bevetelborravalo=lista.stream()
				 .filter(k -> k.id==6185)
				 .mapToDouble(k -> k.borravalo).sum();
		
		System.out.println("4. feladat: "+utazasdb+" fuvar alatt: "+(bevetelborravalo+bevetelfizetes)+"$");
		
		System.out.println("5. feladat: ");
		lista.stream()
					.collect(Collectors.groupingBy(k -> k.fizetesimod, Collectors.counting()))
					.entrySet()
					.stream()
					.forEach(k -> System.out.println(k.getKey()+": "+k.getValue()+" fuvar"));
		
		double osszesmerfold=lista.stream()
								.mapToDouble(k ->k.tavolsag)
								.sum();
		DecimalFormat df=new DecimalFormat("0.00");
		System.out.println("6. feladat: "+df.format(osszesmerfold*1.6)+"km");
		
		int leghosszabb=Integer.MIN_VALUE;
		int leghosszabbhely=0;
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).idotartam>leghosszabb) {
				leghosszabb=lista.get(i).idotartam;
				leghosszabbhely=i;
			}
		}
		
		System.out.println("7. feladat: Leghosszabb fuvar:");
		System.out.println("Fuvar hossza: "+lista.get(leghosszabbhely).idotartam+" másodperc");
		System.out.println("Taxi azonosító: "+lista.get(leghosszabbhely).id);
		System.out.println("Megtett távolság: "+lista.get(leghosszabbhely).tavolsag+" km");
		System.out.println("Viteli díj: "+lista.get(leghosszabbhely).vitelidij+"$");
		
		PrintWriter pr=new PrintWriter(new FileWriter("hibak.txt"));
		pr.print(elsosor+"\n");
		
		lista.sort(Comparator.comparing(k->k.indulas));
		
		for (adatok e:lista) {
			if(e.idotartam>0 && e.vitelidij>0 && e.tavolsag==0) {
				pr.println(e.id+";"+e.indulas+";"+e.idotartam+";"+e.tavolsag+";"+e.vitelidij+";"+e.borravalo+";"+e.fizetesimod);
			}
		}
		pr.close();
		System.out.println("8. feladat: hibak.txt");
	}

}
