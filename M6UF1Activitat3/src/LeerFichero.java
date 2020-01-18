import java.io.*;

public class LeerFichero {

	private File fichero;

	public LeerFichero(File fichero) {
		this.fichero = fichero;
	}

	public void leer() {

		try {
			RandomAccessFile aleatoriFile = new RandomAccessFile(fichero, "r");

			int apuntador = 0;

			// Para almacenar lo que leemos
			int id;
			char arrayNombre[] = new char[50], arrayApellido[] = new char[50], arrayDNI[] = new char[50], aux;
			int edad;

			boolean salir = false;
			while (!salir) {
				aleatoriFile.seek(apuntador);
				// Almacenar id
				id = aleatoriFile.readInt();
				// Almacenar nombre
				for (int i = 0; i < arrayNombre.length; i++) {
					aux = aleatoriFile.readChar();
					arrayNombre[i] = aux;
				}
				String nombre = new String(arrayNombre);
				// Almacenar apellido
				for (int i = 0; i < arrayApellido.length; i++) {
					aux = aleatoriFile.readChar();
					arrayApellido[i] = aux;
				}
				String apellido = new String(arrayApellido);
				// Almacenar DNI
				for (int i = 0; i < arrayDNI.length; i++) {
					aux = aleatoriFile.readChar();
					arrayDNI[i] = aux;
				}
				String DNI = new String(arrayDNI);
				// Almacenar edad
				edad = aleatoriFile.readInt();
				// Mostramos lo que hemos almacenado para leer
				System.out.println("ID: " + id + "\nNombre: " + nombre + "\nApellido: " + apellido + 
						"\nDNI: " + DNI + "\nEdad: " + edad + "\n");
				// Movemos el apuntador a la siguiente persona
				apuntador += 308;
				// Si coincidimos con el final del fichero, salimos
				if (aleatoriFile.getFilePointer() == aleatoriFile.length()) {
					salir = true;
				}
			}
			// Cerrar el fichero
			aleatoriFile.close();
		} catch (Exception ex) {

		}
	}
}

