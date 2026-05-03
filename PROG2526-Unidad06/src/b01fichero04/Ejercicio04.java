package b01fichero04;

import java.io.*;
import java.util.Scanner;

public class Ejercicio04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String cadena;

		try (BufferedWriter in = new BufferedWriter(new FileWriter("cadenasTexto.txt", true))) {
			do {
				System.out.print("Introduzca una cadena de texto: ");
				cadena = sc.nextLine();
				in.write(cadena);
				in.newLine();
			} while (!cadena.equals("fin"));
			in.close();
		} catch (IOException e) {
			System.out.println("Error al escribir.");
		}
		sc.close();
	}

}
