package b02fichero05;

import java.io.*;

public class Ejercicio05 {

	public static void main(String[] args) {
		String rutaFichero1 = "archivo1.txt";
		String rutaFichero2 = "archivo2.txt";

		compararFicheros(rutaFichero1, rutaFichero2);
	}

	public static void compararFicheros(String f1, String f2) {
		try (BufferedReader br1 = new BufferedReader(new FileReader(f1));
				BufferedReader br2 = new BufferedReader(new FileReader(f2))) {

			String linea1 = "", linea2 = "";
			int numLinea = 1;
			boolean sonIguales = true;

			while (true) {
				linea1 = br1.readLine();
				linea2 = br2.readLine();

				if (linea1 == null && linea2 == null) {
					break;
				}

				if (linea1 == null || linea2 == null || !linea1.equals(linea2)) {
					sonIguales = false;
					encontrarDiferencia(linea1, linea2, numLinea);
					break;
				}

				numLinea++;
			}

			if (sonIguales) {
				System.out.println("Los archivos son idénticos.");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: Uno de los archivos no existe.");
		} catch (IOException e) {
			System.out.println("Error al leer los archivos: " + e.getMessage());
		}
	}

	private static void encontrarDiferencia(String l1, String l2, int numLinea) {
		System.out.println("Los archivos son DISTINTOS.");

		if (l1 == null) {
			System.out.println("Diferencia en línea " + numLinea + ": El primer archivo terminó inesperadamente.");
			return;
		}
		if (l2 == null) {
			System.out.println("Diferencia en línea " + numLinea + ": El segundo archivo terminó inesperadamente.");
			return;
		}

		int longitudMinima = Math.min(l1.length(), l2.length());
		int i = 0;

		while (i < longitudMinima && l1.charAt(i) == l2.charAt(i)) {
			i++;
		}

		System.out.println("Primera diferencia encontrada en:");
		System.out.println("- Línea: " + numLinea);
		System.out.println("- Carácter: " + (i + 1));
		System.out.println("- Contenido archivo 1: '" + (i < l1.length() ? l1.charAt(i) : "[FIN DE LÍNEA]") + "'");
		System.out.println("- Contenido archivo 2: '" + (i < l2.length() ? l2.charAt(i) : "[FIN DE LÍNEA]") + "'");
	}
}
