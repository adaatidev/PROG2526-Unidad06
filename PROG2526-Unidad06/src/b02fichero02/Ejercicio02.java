package b02fichero02;

import java.io.*;
import java.util.*;

public class Ejercicio02 {

	private static final String ARCHIVO = "firmas.txt";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<String> firmas = cargarFirmas();

		int opcion = 0;
		do {
			System.out.println("\n--- LIBRO DE FIRMAS ---");
			System.out.println("1. Mostrar libro de firmas.");
			System.out.println("2. Firmar (Nuevo nombre).");
			System.out.println("3. Salir.");
			System.out.print("Elige una opción: ");

			try {
				opcion = sc.nextInt();
				sc.nextLine();

				switch (opcion) {
				case 1 -> mostrarFirmas(firmas);
				case 2 -> insertarFirma(sc, firmas);
				case 3 -> System.out.println("¡Gracias por su visita!");
				default -> System.out.println("Opción no válida.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Introduce un número válido.");
				sc.nextLine();
			}
		} while (opcion != 3);

		sc.close();
	}

	private static Set<String> cargarFirmas() {
		Set<String> lista = new LinkedHashSet<>();
		File file = new File(ARCHIVO);

		if (file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String nombre;
				while ((nombre = br.readLine()) != null) {
					if (!nombre.isBlank()) {
						lista.add(nombre.trim());
					}
				}
			} catch (IOException e) {
				System.out.println("Error al cargar las firmas: " + e.getMessage());
			}
		}
		return lista;
	}

	private static void mostrarFirmas(Set<String> firmas) {
		if (firmas.isEmpty()) {
			System.out.println("El libro de firmas está vacío. ¡Sé el primero!");
		} else {
			System.out.println("--- PERSONAS QUE HAN PASADO POR AQUÍ ---");
			int i = 1;
			for (String f : firmas) {
				System.out.println(i + ". " + f);
				i++;
			}
		}
	}

	private static void insertarFirma(Scanner sc, Set<String> firmas) {
		System.out.print("Introduce tu nombre para firmar: ");
		String nuevoNombre = sc.nextLine().trim();

		if (nuevoNombre.isEmpty()) {
			System.out.println("El nombre no puede estar vacío.");
			return;
		}

		if (firmas.add(nuevoNombre)) {
			guardarNuevaFirmaEnArchivo(nuevoNombre);
			System.out.println("¡Gracias por firmar, " + nuevoNombre + "!");
		} else {
			System.out.println("Error: Este nombre ya consta en el libro de firmas.");
		}
	}

	private static void guardarNuevaFirmaEnArchivo(String nombre) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
			bw.write(nombre);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("No se pudo guardar la firma en el disco: " + e.getMessage());
		}
	}
}
