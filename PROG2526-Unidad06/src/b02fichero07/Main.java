package b02fichero07;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

	private static final String ARCHIVO = "clientes.txt";
	private static TreeSet<Cliente> clientes = new TreeSet<>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		cargarDatos();
		int opcion = 0;

		do {
			System.out.println("\n--- GESTIÓN DE CLIENTES ---");
			System.out.println("1. Alta cliente.");
			System.out.println("2. Baja cliente.");
			System.out.println("3. Listar clientes.");
			System.out.println("4. Salir.");
			System.out.print("Opción: ");

			try {
				opcion = Integer.parseInt(sc.nextLine());
				switch (opcion) {
				case 1 -> altaCliente();
				case 2 -> bajaCliente();
				case 3 -> listarClientes();
				case 4 -> guardarDatos();
				default -> System.out.println("Opción no válida.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Introduce un número válido.");
			}
		} while (opcion != 4);
	}

	private static void altaCliente() {
		System.out.print("DNI: ");
		String dni = sc.nextLine();
		System.out.print("Nombre completo: ");
		String nombre = sc.nextLine();
		System.out.print("Fecha nacimiento (AAAA-MM-DD): ");
		LocalDate fecha = LocalDate.parse(sc.nextLine());
		System.out.print("Saldo inicial: ");
		double saldo = Double.parseDouble(sc.nextLine());

		if (clientes.add(new Cliente(dni, nombre, fecha, saldo))) {
			System.out.println("Cliente registrado correctamente.");
		} else {
			System.out.println("Error: Ya existe un cliente con ese DNI.");
		}
	}

	private static void bajaCliente() {
		System.out.print("Introduce el DNI del cliente a eliminar: ");
		String dni = sc.nextLine();
		boolean eliminado = clientes.removeIf(c -> c.getDni().equalsIgnoreCase(dni));

		if (eliminado)
			System.out.println("Cliente eliminado.");
		else
			System.out.println("No se encontró el cliente.");
	}

	private static void listarClientes() {
		if (clientes.isEmpty()) {
			System.out.println("No hay clientes registrados.");
			return;
		}

		double max = Double.MIN_VALUE, min = Double.MAX_VALUE, suma = 0;

		System.out.println("--- LISTADO DE CLIENTES ---");
		for (Cliente c : clientes) {
			System.out.println(c);
			double s = c.getSaldo();
			suma += s;
			if (s > max)
				max = s;
			if (s < min)
				min = s;
		}

		System.out.println("---------------------------");
		System.out.printf("Saldo Máximo: %.2f€ | Saldo Mínimo: %.2f€ | Promedio: %.2f€\n", max, min,
				(suma / clientes.size()));
	}

	private static void cargarDatos() {
		File file = new File(ARCHIVO);
		if (!file.exists())
			return;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] d = linea.split(";");
				if (d.length == 4) {
					clientes.add(new Cliente(d[0], d[1], LocalDate.parse(d[2]), Double.parseDouble(d[3])));
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer archivo: " + e.getMessage());
		}
	}

	private static void guardarDatos() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
			for (Cliente c : clientes) {
				bw.write(c.toFileFormat());
				bw.newLine();
			}
			System.out.println("Datos guardados. Saliendo...");
		} catch (IOException e) {
			System.out.println("Error al guardar: " + e.getMessage());
		}
	}
}
