package b01fichero06;

import java.io.*;
import java.util.*;

public class Ejercicio06 {

	public static void main(String[] args) {
		List<Integer> listaNumeros = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new FileReader("C:\\Users\\ada.atienza\\git\\PROG2526-Unidad06\\PROG2526-Unidad06\\src\\fichero06\\Numeros"));
			while (sc.hasNextInt()) {
				listaNumeros.add(sc.nextInt());
			}
			sc.close();
		} catch (IOException e) {
			e.getMessage();
		}

		Collections.sort(listaNumeros);

		try (BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\ada.atienza\\git\\PROG2526-Unidad06\\PROG2526-Unidad06\\src\\fichero06\\NumerosOrdenados"))) {
			for (Integer n : listaNumeros) {
				out.write(String.valueOf(n));
				out.newLine();
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
