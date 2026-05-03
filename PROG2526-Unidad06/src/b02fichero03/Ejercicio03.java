package b02fichero03;

import java.io.*;
import java.util.Scanner;

public class Ejercicio03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nombreFichero = "documento.txt";

		File fichero = new File(nombreFichero);

		if (!fichero.exists()) {
			System.out.println("Error: El archivo '" + nombreFichero + "' no existe.");
			sc.close();
			return;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;
			int contadorLineas = 0;

			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
				contadorLineas++;

				if (contadorLineas == 24) {
					System.out.print("\n--- Pulse ENTER para ver más ---");
					sc.nextLine();
					contadorLineas = 0;
				}
			}
			System.out.println("--- Fin del documento ---");

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
}
