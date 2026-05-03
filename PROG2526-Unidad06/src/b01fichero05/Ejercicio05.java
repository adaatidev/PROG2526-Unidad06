package b01fichero05;

import java.util.Scanner;
import java.io.*;

public class Ejercicio05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String nombre;
		int edad;

		try (BufferedWriter in = new BufferedWriter(new FileWriter(
				"C:\\Users\\ada.atienza\\git\\PROG2526-Unidad06\\PROG2526-Unidad06\\src\\fichero05\\datos", true))) {
			System.out.print("Introduzca su nombre: ");
			nombre = sc.nextLine();

			System.out.print("Introduzca su edad: ");
			edad = sc.nextInt();

			in.write(nombre + " " + edad);
			in.newLine();
			in.close();
		} catch (IOException e) {
			System.out.println("Error al escribir.");
		}

		sc.close();
	}

}
