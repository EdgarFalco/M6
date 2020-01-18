
import java.io.*;
import java.util.*;

public class ConsultarFichero {


	private File fichero;
	private int seleccion;

	//CONSTRUCTOR
	public ConsultarFichero(File fichero, int seleccion) {
		this.fichero = fichero;
		this.seleccion = seleccion;
	}

	public void consulta() throws IOException{

		//Crea flujo (stream) del archivo de acceso aleatorio de solo lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fichero, "r");

		int apuntador;
		int id; 
		int edad;

		char nombre[] = new char[50]; 
		char apellido[] = new char[50];
		char DNI[] = new char[50];
		char aux;

		//Controlamos el tamaño de bytes de la persona en el fichero
		apuntador = (seleccion - 1) * 308;

		if (apuntador >= aleatoriFile.length()) {
			System.out.println("ERROR: ID incorrecto, no existe esta persona");
		} else {
			//Apunta al inicio de la persona al fichero
			aleatoriFile.seek(apuntador);
			//Lee id
			id = aleatoriFile.readInt();

			//Lee nombre
			for(int i = 0; i<nombre.length; i++) {
				aux = aleatoriFile.readChar();
				nombre[i] = aux;
			}
			String nombres = new String(nombre);

			//Lee Apellido
			for(int i = 0; i<apellido.length; i++) {
				aux = aleatoriFile.readChar();
				apellido[i] = aux;
			}
			String apellidos = new String(apellido);

			//Lee DNI
			for(int i = 0; i<DNI.length; i++) {
				aux = aleatoriFile.readChar();
				DNI[i] = aux;
			}
			String DNIs = new String(DNI);
			
			//Lee edad
			edad = aleatoriFile.readInt();

			//Salida de los datos de cada Persona
			System.out.println("ID: " + id + "\nNombre: " + nombres + "\nApellido: " + apellidos + "\nDNI: " + DNIs + "\nedad: " + edad);
		}
		//Cierra Fichero
		aleatoriFile.close();
	}
}

