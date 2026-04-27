package fichero06;

import java.io.*;
import java.util.*;

public class Ejercicio06 {

	public static void main(String[] args) {
		List<Integer> listaNumeros = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new FileReader("Numeros.txt"));
			while (sc.hasNextInt()) {
				listaNumeros.add(sc.nextInt());
			}
			sc.close();
		} catch (IOException e) {
			e.getMessage();
		}

		Collections.sort(listaNumeros);

		try (BufferedWriter out = new BufferedWriter(new FileWriter("NumerosOrdenados.txt"))) {
			for (Integer n : listaNumeros) {
				out.write(String.valueOf(n));
				out.newLine();
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
