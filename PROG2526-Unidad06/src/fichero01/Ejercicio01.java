package fichero01;

import java.io.*;
import java.util.Scanner;

public class Ejercicio01 {

	public static void main(String[] args) {
		double suma = 0;
		int contador = 0;
		double media;

		try {
			Scanner sc = new Scanner(new FileReader(
					"C:\\Users\\ada.atienza\\git\\PROG2526-Unidad06\\PROG2526-Unidad06\\src\\fichero01\\NumerosReales.txt"));
			while (sc.hasNext()) {
				if (sc.hasNextDouble()) {
					suma += sc.nextDouble();
					contador++;
				} else {
					sc.next();
				}
			}
			media = suma / contador;
			System.out.println("Suma: \t" + suma);
			System.out.println("Media: \t" + media);
			sc.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
