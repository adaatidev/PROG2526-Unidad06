package fichero07;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

public class Ejercicio07 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeMap<String, Integer> listaContactos = new TreeMap<>();
		final int MAXIMOCONTACTOS = 20;
		int contadorContactos = 0;

		int opcion = 0;

		do {
			try {
				try {
					BufferedWriter in = new BufferedWriter(new FileWriter(
							"C:\\Users\\ada.atienza\\git\\PROG2526-Unidad06\\PROG2526-Unidad06\\src\\fichero07\\Contactos"));
					opcion = sc.nextInt();
					sc.nextLine();

					switch (opcion) {
					case 1 -> agregarContacto(sc, listaContactos, contadorContactos, MAXIMOCONTACTOS);
					case 2 -> buscarContacto(sc, listaContactos);
					case 3 -> mostrarContactos(listaContactos, contadorContactos);
					case 4 -> {
						for (String key : listaContactos.keySet()) {
							in.write("Nombre: " + key + " | Teléfono: " + listaContactos.get(key));
						}

						System.out.println("Cerrando la agenda...");
					}
					default -> {
						System.out.println("Opción no válida, intente de nuevo");
					}
					}
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (InputMismatchException e) {
				System.out.println("ERROR: Entrada no válida. Por favor, use números.");
				sc.nextLine();
			}

		} while (opcion != 4);

		System.out.println("Ha salido la agenda con éxito, nos vemos o/");

		sc.close();
	}

	public static void imprimirMenu() {
		System.out.println("=========AGENDA=========");
		System.out.println("1. Nuevo contacto.");
		System.out.println("2. Buscar por nombre.");
		System.out.println("3. Mostrar todos.");
		System.out.println("4. Salir.");
		System.out.println("> ");
	}

	public static void agregarContacto(Scanner sc, TreeMap<String, Integer> listaContactos, int contadorContactos,
			final int MAXIMOCONTACTOS) {
		if (contadorContactos < MAXIMOCONTACTOS) {
			System.out.println("Introduzca el nombre del contacto: ");
			String nombreAgregado = sc.nextLine();

			if (!nombreRepetido(nombreAgregado, listaContactos)) {
				contadorContactos++;
				System.out.println("Introduzca el teléfono de " + nombreAgregado + ": ");
				int telefonoAgregado = sc.nextInt();
				listaContactos.put(nombreAgregado, telefonoAgregado);
				System.out.println("Se ha agregado el contacto correctamente");
			} else {
				System.out.println("El nombre está repetido");
			}
		} else {
			System.out.println("Máximo de contactos alcanzado, no se pueden agregar más contactos a esta agenda");
		}

	}

	public static void buscarContacto(Scanner sc, TreeMap<String, Integer> listaContactos) {
		System.out.println("Introduzca el nombre del contacto que desea buscar: ");
		String nombreBuscado = sc.nextLine();

		if (listaContactos.containsKey(nombreBuscado)) {
			System.out.println(
					"Contacto encontrado | Teléfono de " + nombreBuscado + ": " + listaContactos.get(nombreBuscado));
		} else {
			System.out.println("No se ha encontrado el contacto " + nombreBuscado);
		}
	}

	public static void mostrarContactos(TreeMap<String, Integer> listaContactos, int contadorContactos) {
		if (listaContactos.isEmpty()) {
			System.out.println("La lista de contactos está vacía");
		} else {
			System.out.println(listaContactos);
		}
		System.out.println("Fin de contactos");
	}

	public static boolean nombreRepetido(String nombre, TreeMap<String, Integer> listaContactos) {
		return listaContactos.containsKey(nombre);
	}

}
