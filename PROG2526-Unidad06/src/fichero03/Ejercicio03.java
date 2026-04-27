package fichero03;

import java.io.*;
import java.util.Scanner;

public class Ejercicio03 {

	public static void main(String[] args) {
		String nombre;
		int edad;
		double estatura;
		double sumaEdad = 0;
		double sumaEstatura = 0;
		double mediaEdad = 0;
		double mediaEstatura = 0;
		int contador = 0;

		try {
			Scanner sc = new Scanner(
					new FileReader("C:\\Users\\ada.atienza\\Documents\\Programación\\Unidad06\\Alumnos.txt"));
			while (sc.hasNext()) {
				nombre = sc.next();
				edad = Integer.parseInt(sc.next());
				estatura = Double.parseDouble(sc.next());
				System.out.println("Alumno: \t" + nombre);
				sumaEdad += edad;
				sumaEstatura += estatura;
				contador++;
			}
			mediaEdad = sumaEdad / contador;
			mediaEstatura = sumaEstatura / contador;
			System.out.println("Media Edad: \t \t" + mediaEdad);
			System.out.println("Media Estatura: \t" + mediaEstatura);
			sc.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
