import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CotxeTester {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		String marca;
		String model;
		int any;
		String matricula;
			
		boolean sortir = true;
				
		String path = "cotxes.txt";
		File fitxer = new File(path);
		
		Scanner sc = new Scanner(System.in);
						
		while (sortir) {
			//MENU D'OPCIONS
			System.out.println("INTRODUIR O MOSTRAR COTXES");
			System.out.println("OPCIO 1 --- Introduir cotxe");
			System.out.println("OPCIO 2 --- Mostrar cotxes");
			System.out.println("OPCIO 3 --- Sortir");
			
			System.out.println("Introdueix 1, 2 o 3:");
			int opcio = sc.nextInt();
			
			//OPCIO1
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
												
				Cotxe cotxe = new Cotxe(marca, model, any, matricula);
				
				dataOutCotxe.writeObject(cotxe);

				dataOutCotxe.close();				
				
				
			//OPCIO 2	
			} else if (opcio == 2) { //OPCIO 2 Llegeix arxiu
				
				System.out.println("INTRODUEIX L'OPCIO 1 o 2: ");
				System.out.println("OPCIO 1 ----- MOSTRA TOTS ELS COTXES");
				System.out.println("OPCIO 2 ----- SELECCIONA EL CAMP PER MOSTRAR ELS COTXES");
				int opcio2 = sc.nextInt();
				
				fitxer = new File(path);
				
				FileInputStream filein = new FileInputStream(fitxer);
				ObjectInputStream dataInCotxe = new ObjectInputStream(filein);
				
				//OPCIO 2.1
				if(opcio2 == 1){ //OPCIO 2.1 Llegeix tot l'arxiu i el mostra
					Cotxe cotxe;				
					try {	
						while(true) {
							cotxe = (Cotxe) dataInCotxe.readObject();
							System.out.println("Marca: " + cotxe.getMarca() + " Model: " + cotxe.getModel() + " Any: " + cotxe.getAny() + " Matricula: " + cotxe.getMatricula());
						}
						
					} catch(EOFException eo) {
						
					}
				dataInCotxe.close();
				
				//OPCIO 2.1	LLEGIR PER CAMPS
				} else if (opcio2 == 2){ 
					System.out.println("Introdueix un camp per buscar");
					System.out.println("1- Marca: ");
					System.out.println("2- Model: ");
					System.out.println("3- Any: ");
					System.out.println("4- Matricula: ");
					System.out.println("Introdueix 1, 2, 3 o 4");
					int camp = sc.nextInt();
					
					sc.nextLine();
					
					int enterAny = 0;
					String lletres = "";
					
					if(camp == 3 ) {
						System.out.println("Escriu un camp: ");
						enterAny = sc.nextInt(); 
						
					} else if(camp == 1 || camp ==2 || camp ==4) {
						System.out.println("Escriu un camp:");
						lletres = sc.nextLine();
					}
										
					try {
						while(true) {
							Cotxe cotxe = (Cotxe) dataInCotxe.readObject();
														
							//CAMP MARCA
							if(camp == 1) {
								
								if(cotxe.getMarca().equalsIgnoreCase(lletres)) {
									System.out.println("Marca: " + cotxe.getMarca() + " Model: " + cotxe.getModel() + " Any: " + cotxe.getAny() + " Matricula: " + cotxe.getMatricula());
								}
															
							//CAMP MODEL
							} else if (camp == 2) {
								
								if(cotxe.getModel().equalsIgnoreCase(lletres)) {
									System.out.println("Marca: " + cotxe.getMarca() + " Model: " + cotxe.getModel() + " Any: " + cotxe.getAny() + " Matricula: " + cotxe.getMatricula());
								}
								
							//CAMP ANY
							} else if (camp == 3) {
								
								if(cotxe.getAny() == enterAny) {
									System.out.println("Marca: " + cotxe.getMarca() + " Model: " + cotxe.getModel() + " Any: " + cotxe.getAny() + " Matricula: " + cotxe.getMatricula());
								}	
							//CAMP MATRICULA
							} else if (camp == 4) {
								
								if(cotxe.getMatricula().equalsIgnoreCase(lletres)) {
									System.out.println("Marca: " + cotxe.getMarca() + " Model: " + cotxe.getModel() + " Any: " + cotxe.getAny() + " Matricula: " + cotxe.getMatricula());
								}	
							//ALTRES	
							} else {
								System.out.println("No ha introduit un numero correcte");
								System.out.println();
							}
							
						}
							
					} catch (EOFException eo) {
						
					}
					
				} else {
					System.out.println("No ha introduit una opcio correcta");
					System.out.println();
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
