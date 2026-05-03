package b02fichero04;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio04 {

	public static void main(String[] args) {
		String archivoCodec = "codec.txt";
		String archivoEntrada = "mensaje.txt";
		String archivoSalida = "mensaje_cifrado.txt";

		Map<Character, Character> mapaCodec = cargarCodec(archivoCodec);

		if (mapaCodec.isEmpty()) {
			System.out.println("Error: No se pudo cargar el archivo de configuración codec.txt");
			return;
		}

		encriptarFichero(archivoEntrada, archivoSalida, mapaCodec);
	}

	private static Map<Character, Character> cargarCodec(String ruta) {
		Map<Character, Character> mapa = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String lineaAlfabeto = br.readLine();
			String lineaCifrado = br.readLine();

			if (lineaAlfabeto != null && lineaCifrado != null) {
				String origen = lineaAlfabeto.replace("Alfabeto:", "").replace(" ", "");
				String destino = lineaCifrado.replace("Cifrado:", "").replace(" ", "");

				for (int i = 0; i < origen.length(); i++) {
					mapa.put(origen.charAt(i), destino.charAt(i));
					mapa.put(Character.toUpperCase(origen.charAt(i)), Character.toUpperCase(destino.charAt(i)));
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el codec: " + e.getMessage());
		}
		return mapa;
	}

	private static void encriptarFichero(String entrada, String salida, Map<Character, Character> codec) {
		try (BufferedReader br = new BufferedReader(new FileReader(entrada));
				BufferedWriter bw = new BufferedWriter(new FileWriter(salida))) {

			int c;
			while ((c = br.read()) != -1) {
				char caracterOriginal = (char) c;

				char caracterCifrado = codec.getOrDefault(caracterOriginal, caracterOriginal);

				bw.write(caracterCifrado);
			}

			System.out.println("¡Archivo encriptado con éxito! Revisa: " + salida);

		} catch (FileNotFoundException e) {
			System.out.println("Error: No se encontró el archivo " + entrada);
		} catch (IOException e) {
			System.out.println("Error durante la encriptación: " + e.getMessage());
		}
	}
}
