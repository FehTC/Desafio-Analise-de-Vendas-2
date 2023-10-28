package Application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import Entitie.Sale;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.next();

		Set<String> set = new HashSet<>();
		List<Sale> list = new ArrayList<>();
		List<String> newList = new ArrayList<>();
		List<Sale> result = new ArrayList<>();
		String[] fields = null;

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();

			while (line != null) {

				fields = line.split(",");
				String seller = fields[2];
				Double total = Double.parseDouble(fields[4]);

				set.add(seller);
				list.add(new Sale(seller, total));
				line = br.readLine();
			}

			for (String s : set) {
				newList.add(s);
			}

			for (String s : newList) {
				
					Double sumEachSeller = list.stream().filter(x -> x.getSeller().equals(s))
							.map(x -> x.getTotal()).reduce(0.0, (x, y) -> x + y);

					result.add(new Sale(s, sumEachSeller));
				
			}
			System.out.println("\nTotal de vendas por vendedor:");
			for (Sale s : result) {
				System.out.println(s);
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		//c:\\temp\in.csv
		
		sc.close();
	}
}
