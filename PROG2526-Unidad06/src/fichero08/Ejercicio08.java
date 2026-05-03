package fichero08;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Ejercicio08 {

	private static final String ARCHIVO = "temperaturas.txt";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		do {
			mostrarMenu();
			try {
				opcion = sc.nextInt();
				sc.nextLine();

				switch (opcion) {
				case 1 -> registrarTemperatura(sc);
				case 2 -> mostrarHistorial();
				case 3 -> System.out.println("Cerrando sistema...");
				default -> System.out.println("Opción no válida");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Por favor, introduce un número entero");
				sc.nextLine();
			}
		} while (opcion != 3);

		sc.close();
	}

	private static void mostrarMenu() {
		System.out.println("--- ESTACIÓN METEOROLÓGICA ---");
		System.out.println("1. Registrar nueva temperatura.");
		System.out.println("2. Mostrar historial de registros.");
		System.out.println("3. Salir.");
		System.out.print("Seleccione una opción: ");
	}

	// OPCIÓN 1: Registrar nueva temperatura (Añadir al final del archivo)
	private static void registrarTemperatura(Scanner sc) {
		System.out.print("Introduce la fecha (AAAA-MM-DD): ");
		String fecha = sc.nextLine();
		System.out.print("Temperatura máxima: ");
		int max = sc.nextInt();
		System.out.print("Temperatura mínima: ");
		int min = sc.nextInt();

		// El segundo parámetro 'true' en FileWriter activa el modo "append" (añadir al
		// final)
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
			bw.write(fecha + "," + max + "," + min);
			bw.newLine();
			System.out.println("Registro guardado con éxito.");
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	// OPCIÓN 2: Mostrar historial y calcular extremos globales
	private static void mostrarHistorial() {
		File file = new File(ARCHIVO);
		if (!file.exists()) {
			System.out.println("No hay registros todavía");
			return;
		}

		int maxGlobal = Integer.MIN_VALUE;
		int minGlobal = Integer.MAX_VALUE;

		System.out.println("--- HISTORIAL DE REGISTROS ---");
		System.out.println("Fecha\t\tMáx\tMín");
		System.out.println("------------------------------------");

		try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(",");
				if (datos.length == 3) {
					String fecha = datos[0];
					int tMax = Integer.parseInt(datos[1]);
					int tMin = Integer.parseInt(datos[2]);

					System.out.println(fecha + "\t" + tMax + "°C\t" + tMin + "°C");

					if (tMax > maxGlobal)
						maxGlobal = tMax;
					if (tMin < minGlobal)
						minGlobal = tMin;
				}
			}

			System.out.println("------------------------------------");
			System.out.println(
					"Récord de temperatura MÁXIMA: " + (maxGlobal == Integer.MIN_VALUE ? "N/A" : maxGlobal + "°C"));
			System.out.println(
					"Récord de temperatura MÍNIMA: " + (minGlobal == Integer.MAX_VALUE ? "N/A" : minGlobal + "°C"));

		} catch (IOException | NumberFormatException e) {
			System.out.println("Error al leer el historial: " + e.getMessage());
		}
	}

}
