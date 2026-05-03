package b02fichero01;

import java.io.*;

public class Ejercicio01 {

	public static void main(String[] args) {
		String nombreFichero = "carta.txt";

		int totalLineas = 0;
		int totalPalabras = 0;
		int totalCaracteres = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				totalLineas++;

				totalCaracteres += linea.length() + System.lineSeparator().length();

				if (!linea.trim().isEmpty()) {
					String[] palabras = linea.split(" ");
					totalPalabras += palabras.length;
				}
			}

			mostrarResultados(totalLineas, totalPalabras, totalCaracteres);

		} catch (FileNotFoundException e) {
			System.err.println("Error: El archivo '" + nombreFichero + "' no existe.");
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}
	}

	private static void mostrarResultados(int l, int p, int c) {
		System.out.println("======= ANÁLISIS DE CARTA.TXT =======");
		System.out.printf("Número de líneas:     %d%n", l);
		System.out.printf("Número de palabras:   %d%n", p);
		System.out.printf("Número de caracteres: %d%n", c);
		System.out.println("=====================================");
	}
}
