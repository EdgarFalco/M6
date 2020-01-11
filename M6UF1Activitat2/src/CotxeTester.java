import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CotxeTester {
	public static void main(String[] args) throws IOException {
		
		Cotxe cotxe;
		boolean sortir = true;
		
		String marca;
		String model;
		int any;
		String matricula;
		
		String path = "./src/cotxes.txt";
		
		Scanner sc = new Scanner(System.in);
		
		File fitxer = new File(path);
				
		while (sortir) {
			System.out.println("INTRODUIR O MOSTRAR COTXES");
			System.out.println("OPCIO 1 --- Introduir cotxe");
			System.out.println("OPCIO 2 --- Mostrar cotxes");
			System.out.println("OPCIO 3 --- Sortir");
			
			System.out.println("Introdueix 1, 2 o 3:");
			int opcio = sc.nextInt();
			
			if(opcio==1) { //OPCIO 1 Escriu arxiu
								
				FileOutputStream fileout = new FileOutputStream(fitxer, true);
				ObjectOutputStream dataOutCotxe = new ObjectOutputStream(fileout);
				
				System.out.println("Introdueix la marca d'un cotxe: ");
				sc.nextLine();
				marca = sc.nextLine();
								
				System.out.println("Introdueix el model: ");
				model = sc.nextLine();
				
				System.out.println("Introdueix l'any: ");
				any = sc.nextInt();
				
				System.out.println("Introdueix la matricula: ");
				sc.nextLine();
				matricula = sc.nextLine();
												
				cotxe = new Cotxe(marca, model, any, matricula);
				
				ArrayList llistaCotxe = new ArrayList(); 
				
				
				
				//List list = new ArrayList();
						
				/*for(int i=0; i<llistaCotxe.length; i++){
					dataOutCotxe.writeUTF(llistaMarca[i]);
					dataOutCotxe.writeUTF(llistaModel[i]);
					dataOutCotxe.writeInt(llistaAny[i]);
					dataOutCotxe.writeUTF(llistaMatricula[i]);
				}*/
				
				for (int i=0; i < llistaCotxe.size(); i++) {
					
					dataOutCotxe.writeObject(cotxe);
				}
								
				dataOutCotxe.close();
				
			} else if (opcio == 2) { //OPCIO 2 Llegeix arxiu
				
				System.out.println("INTRODUEIX L'OPCIO 1 o 2: ");
				System.out.println("OPCIO 1 ----- MOSTRA TOTS ELS COTXES");
				System.out.println("OPCIO 2 ----- SELECCIONA EL CAMP PER MOSTRAR ELS COTXES");
				int opcio2 = sc.nextInt();
				
				if(opcio2 == 1){ //OPCIO 2.1 Llegeix tot l'arxiu i el mostra
				
					FileInputStream filein = new FileInputStream(fitxer);
					DataInputStream dataInCotxe = new DataInputStream(filein);
					
					try {
						while(true) {
							marca = dataInCotxe.readUTF();
							model = dataInCotxe.readUTF();
							any = dataInCotxe.readInt();
							matricula = dataInCotxe.readUTF();
							
							System.out.println("Marca    :" + marca);
							System.out.println("Model    :" + model);
							System.out.println("Any:     :" + any);
							System.out.println("Matricula:" + matricula);
							System.out.println();
						}
					} catch (EOFException eo){}
					
					dataInCotxe.close();
					
				} else if (opcio2 == 2){ //OPCIO 2.2 Ll
					System.out.println("Introdueix un camp per buscar");
					
					
					
				} else {
					System.out.println("No ha introduit una opcio correcta");
				}
				
			} else if (opcio == 3) { //OPCIO 3 Surt del programa
				sortir = false;
				System.out.println("Programa tancat");
				
			} else {
				System.out.println("No ha introduit un numero correcte");
				System.out.println();
			}
		}
	}
}
