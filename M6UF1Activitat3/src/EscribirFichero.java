import java.io.*;

public class EscribirFichero {

	private Persona[] arrayPerson;
	private File fichero;

	public EscribirFichero(File file,Persona[] personas) throws IOException{
		this.fichero= file;;
		this.arrayPerson = personas;
	}

	public void guardar() throws IOException{

		try {
			RandomAccessFile aleatoriFile = new RandomAccessFile(fichero, "rw");
			StringBuffer buffer = null;

			for (int i=0; i<arrayPerson.length; i++) {
				aleatoriFile.writeInt(i+1);//1 enter ocupa 4 bytes
				//50 car�cters a 2bytes/car�cter 100 bytes
				buffer = new StringBuffer (arrayPerson[i].getNombre());
				buffer.setLength(50);
				aleatoriFile.writeChars(buffer.toString());
				//50 car�cters a 2bytes/car�cter 100 bytes
				buffer = new StringBuffer (arrayPerson[i].getApellido());
				buffer.setLength(50);
				aleatoriFile.writeChars(buffer.toString());
				//25 car�cters a 2bytes/car�cter 50 bytes

				buffer = new StringBuffer (arrayPerson[i].getDNI());
				buffer.setLength(50);
				aleatoriFile.writeChars(buffer.toString());

				//1 float ocupa 4 bytes
				aleatoriFile.writeInt(arrayPerson[i].getEdad());
				//Total 222 bytes
			}
			aleatoriFile.close();
		} catch (Exception ex) {

		}
	}
}
