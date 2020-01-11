import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CotxeTester {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		
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
			//OPCIO1
			if(opcio==1) { //OPCIO 1 Escriu arxiu
				
				Cotxe cotxe;
				
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

								
				dataOutCotxe.close();
				
			//OPCIO 2	
			} else if (opcio == 2) { //OPCIO 2 Llegeix arxiu
				
				System.out.println("INTRODUEIX L'OPCIO 1 o 2: ");
				System.out.println("OPCIO 1 ----- MOSTRA TOTS ELS COTXES");
				System.out.println("OPCIO 2 ----- SELECCIONA EL CAMP PER MOSTRAR ELS COTXES");
				int opcio2 = sc.nextInt();
				
				FileInputStream filein = new FileInputStream(fitxer);
				ObjectInputStream dataInCotxe = new ObjectInputStream(filein);
				
				//OPCIO 2.1
				if(opcio2 == 1){ //OPCIO 2.1 Llegeix tot l'arxiu i el mostra
					
					Cotxe cotxe;
				
					try {	
						while(true) {
							cotxe = (Cotxe) dataInCotxe.readObject();
							System.out.println("Marca: " + cotxe.getMarca() + " Model: " + cotxe.getModel() + " Any: " + cotxe.getAny() + " Matricula: " + cotxe.getMatricula());;
						}
						
					} catch(EOFException eo) {
						
					}
				dataInCotxe.close();
				
				//OPCIO 2.1		
				} else if (opcio2 == 2){ //OPCIO 2.2 Llegir fitxer per camp
					System.out.println("Introdueix un camp per buscar");
											
				} else {
					System.out.println("No ha introduit una opcio correcta");
				}
				
			//OPCIO 3	
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
