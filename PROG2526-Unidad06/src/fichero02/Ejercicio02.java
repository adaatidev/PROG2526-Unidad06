package fichero02;

import java.io.*;
import java.util.Scanner;

public class Ejercicio02 {

	public static void main(String[] args) {
		double suma = 0;
		int contador = 0;
		double media;

		try {
			Scanner sc = new Scanner(
					new FileReader("C:\\Users\\ada.atienza\\Documents\\Programación\\Unidad06\\Enteros.txt"));
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
