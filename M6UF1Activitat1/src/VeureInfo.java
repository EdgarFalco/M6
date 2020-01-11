import java.io.*;
import java.sql.Time;
import java.util.Date;

import org.omg.CORBA.PUBLIC_MEMBER;

public class VeureInfo {
	public static void main(String [] args) {
		
		//Controlem els dies (cada dia te 86400000 milisegons)
		int dias = 3;
		long modificat = dias * 86400000; 
		
		Date date = new Date();
		
		//Establim un argument que guardara la ruta del directory o fitxer
		String path = args[0];
		
		//Guardem la ruta d'un Arxiu que li passarem com a parametre
		File f = new File(path);
		
		String[] arxius = f.list();
		
		
				
		//SI es un directori
		if (f.isDirectory()){
			System.out.println("Fitxers al directori actual: ");
			if(f.exists()){
				for (int i = 0; i < arxius.length; i++){
					System.out.println(arxius[i]);
				}
			//Mira si es un directory ocult
			System.out.println("Es un directory ocult: " + f.isHidden());
			//Mostra modificacio del directory
			if((date.getTime()-f.lastModified())< modificat){
				System.out.println("S'ha modificat fa menys de " + dias + " dies");
			}else {
				System.out.println("No s'ha modificat fa més de " + dias + " dies");
			}
			
		
		//SI es un fitxer
			
		} else if (f.isFile()) {
			System.out.println("Tipus de fitxer: ");
			//Si existeix el fitxer
			if(f.exists()){
				//Mostra el nom i extensio del fitxer
				System.out.println("Nom del fitxer : "+f.getName());
				//Mostra la ruta desde el punt on indiquem fins al fitxer
				System.out.println("Ruta: "+f.getPath());
				//Mostra la ruta completa desde l'arrel fins el fitxer
				System.out.println("Ruta absoluta  : " + f.getAbsolutePath());
				//Mostra si es pot llegir el fitxer
				System.out.println("Es pot llegir: " + f.canRead());
				//Mostra si es pot escriure el fitxer
				System.out.println("Es pot esciure  : " + f.canWrite());
				//Mostra la grandaria en bytes.
				System.out.println("Grandaria      : " + f.length());
				//Mostra si es un directori o no
				System.out.println("Es un directori: " + f.isDirectory());
				//Mostra si es un fitxer o no
				System.out.println("Es un fitxer   : " + f.isFile());
				//Mira si hi ha objectes ocults
				System.out.println("Es un fitxer ocult: " + f.isHidden());
				//Mostra la modificacio de l'arxiu
				if((date.getTime()-f.lastModified())< modificat){
					System.out.println("S'ha modificat fa menys de " + dias + " dies");
				} else{
					System.out.println("No s'ha modificat fa més de " + dias + " dies");
				}
			}
		}
		}
	}
}
