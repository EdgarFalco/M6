import java.io.*;
import java.util.Scanner;

public class EscriuFitxerObject {
	public static void main(String[] args) throws IOException {
		
		Cotxe cotxe;
		int numeroCotxe = 1;
		boolean sortir = true;
		
		String marca;
		String model;
		int any;
		String matricula;
		
		String path = "./src/cotxes.txt";
		
		Scanner sc = new Scanner(System.in);
		
		File fitxer = new File(path);
		FileOutputStream fileout = new FileOutputStream(fitxer);
		DataOutputStream dataOuComarq = new DataOutputStream(fileout);
		
		while (sortir) {
			System.out.println("INTRODUIR O MOSTRAR COTXES");
			System.out.println("OPCIO 1 --- Introduir cotxe");
			System.out.println("OPCIO 2 --- Mostrar cotxes");
			System.out.println("OPCIO 3 --- Sortir");
			
			System.out.println("Introdueix 1, 2 o 3:");
			int opcio = sc.nextInt();
			
			if(opcio==1) { //OPCIO 1 Escriu arxiu
				
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
				
				//Cotxe cotxe[] = {cotxe}; 
				//String comarq[] = {"Baix Camp", "Segarra", "Bages", "Priorat", "Terra Alta", "Montsià"};
				//int poblacio[] = {190249, 22713, 184403, 9550, 12119, 69613};
						
				//for(int i=0; i<comarq.length; i++){
					//dataOuComarq.writeUTF(comarq[i]);
					//dataOuComarq.writeInt(poblacio[i]);
				
				//}
				//dataOuComarq.close();
			} else if (opcio == 2) { //OPCIO 2 Llegeix arxiu
				
				
				
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
