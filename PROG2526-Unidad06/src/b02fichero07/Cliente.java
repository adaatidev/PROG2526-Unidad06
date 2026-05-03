package b02fichero07;

import java.time.LocalDate;
import java.time.Period;

public class Cliente implements Comparable<Cliente> {
	private String dni;
	private String nombre;
	private LocalDate fechaNacimiento;
	private double saldo;

	public Cliente(String dni, String nombre, LocalDate fechaNacimiento, double saldo) {
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.saldo = saldo;
	}

	public int getEdad() {
		return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public double getSaldo() {
		return saldo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	@Override
	public int compareTo(Cliente otro) {
		return this.dni.compareTo(otro.dni);
	}

	@Override
	public String toString() {
		return String.format("DNI: %s | Nombre: %s | Saldo: %.2f€ | Edad: %d años", dni, nombre, saldo, getEdad());
	}

	public String toFileFormat() {
		return dni + ";" + nombre + ";" + fechaNacimiento + ";" + saldo;
	}
}