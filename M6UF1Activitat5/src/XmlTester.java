import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlTester {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		boolean segueix = true;
		
		while(segueix){
			//metode mostra menu
			int opcio = menu();
			
			if(opcio == 1){
				//Afegeix nodes al node arrel
				afegir();
				
			} else if (opcio == 2){
				
			} else if (opcio == 3){
				
			} else if (opcio == 4){
				
			}else if (opcio == 5){
				//Surt del programa1
				segueix = false;
			}
		}
	}
	
	//METODES
	
	//Mostra el menu i agafa una opcio de l'usuari
	private static int menu() {
		System.out.println("MENU");
		System.out.println("1. Afegir:");
		System.out.println("2. Eliminar:");
		System.out.println("3. Modificar:");
		System.out.println("4. Llegir");
		System.out.println("5. Sortir\n");
		
		
		System.out.println("Introdueix l'opcio 1...5 : ");
		Scanner sc = new Scanner(System.in);
		int opcio = sc.nextInt();
		return opcio;
	}
	
	//Afegeix nodes a l'arrel
	private static void afegir() throws ParserConfigurationException,
			SAXException, IOException, TransformerFactoryConfigurationError {
		// per a carregar en memòria un arxiu xml
		File file = new File("src/alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		Scanner sc = new Scanner(System.in);
		Element nodeArrel = doc.getDocumentElement();
		
		try {
			  // Definim el node que contindra els elements
			  Element alumne = doc.createElement("alumne");
			  nodeArrel.appendChild(alumne);
			  			  
			  // atriubt pel node alumne
			  Attr attr = doc.createAttribute("id");
			  
			  System.out.println("Introdueix el valor de l'atribut id:");
			  String teclatUsuari = sc.nextLine();
			  //Afegim el valor l'atribut
			  attr.setValue(teclatUsuari);
			  alumne.setAttributeNode(attr);
			  			  			  
			  //1 fill d'alumne
			  Element nodeNom = doc.createElement("nom");
			  System.out.println("Introdueix el valor del primer fill alumne:");
			  teclatUsuari = sc.nextLine();
			  nodeNom.appendChild(doc.createTextNode(teclatUsuari));
			  alumne.appendChild(nodeNom);
			  
			  //2 fill d'alumne
			  Element nodeCognom1 = doc.createElement("cognom1");
			  
			  System.out.println("Introdueix el valor del segon fill d'alumne: ");
			  teclatUsuari = sc.nextLine();
			  nodeCognom1.appendChild(doc.createTextNode(teclatUsuari));
			  alumne.appendChild(nodeCognom1);
			  			  
			  //3 fill d'alumne
			  Element nodeCognom2 = doc.createElement("cognom2");
			  
			  System.out.println("Introdueix el valor del tercer fill d'alumne: ");
			  teclatUsuari = sc.nextLine();
			  nodeCognom2.appendChild(doc.createTextNode(teclatUsuari));
			  alumne.appendChild(nodeCognom2);
			  
			  //4 fill d'alumne
			  Element nodeNota = doc.createElement("notaFinal");
			  
			  System.out.println("Introdueix el valor del quart fill d'alumne: ");
			  teclatUsuari = sc.nextLine();
			  nodeNota.appendChild(doc.createTextNode(teclatUsuari));
			  alumne.appendChild(nodeNota);
			  
			  // Finalitza la creacio del ariux XML i guarda
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  StreamResult result = new StreamResult(new File("src/alumnes.xml"));
			  transformer.transform(source, result);
			  
		} catch(Exception e) {
			   e.printStackTrace();
		}
	}
}
