package b02fichero06;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Ejercicio06 {

	public static void main(String[] args) {
		String archivo = "deportistas.txt";

		String nombreMaxEdad = "", nombreMaxPeso = "", nombreMaxEstatura = "";
		int maxEdad = Integer.MIN_VALUE;
		double maxPeso = Double.MIN_VALUE;
		double maxEstatura = Double.MIN_VALUE;

		try (Scanner sc = new Scanner(new File(archivo))) {
			sc.useLocale(Locale.US);

			if (sc.hasNextLine()) {
				sc.nextLine();
			}

			while (sc.hasNextLine()) {
				String linea = sc.nextLine();

				String[] datos = linea.split("\\s{2,}");

				if (datos.length >= 4) {
					String nombre = datos[0].trim();
					int edad = Integer.parseInt(datos[1].trim());
					double peso = Double.parseDouble(datos[2].trim().replace(",", "."));
					double estatura = Double.parseDouble(datos[3].trim().replace(",", "."));

					if (edad > maxEdad) {
						maxEdad = edad;
						nombreMaxEdad = nombre;
					}

					if (peso > maxPeso) {
						maxPeso = peso;
						nombreMaxPeso = nombre;
					}

					if (estatura > maxEstatura) {
						maxEstatura = estatura;
						nombreMaxEstatura = nombre;
					}
				}
			}

			System.out.println("======= RESULTADOS DE LA ESTACIÓN =======");
			System.out.println("Deportista de mayor edad:     " + nombreMaxEdad + " (" + maxEdad + " años)");
			System.out.println("Deportista de mayor peso:     " + nombreMaxPeso + " (" + maxPeso + " kg)");
			System.out.println("Deportista de mayor estatura: " + nombreMaxEstatura + " (" + maxEstatura + " m)");

		} catch (FileNotFoundException e) {
			System.out.println("Error: No se encuentra el archivo 'deportistas.txt'");
		} catch (Exception e) {
			System.out.println("Error al procesar los datos: " + e.getMessage());
		}
	}
}
