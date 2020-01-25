import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
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


public class XmlCatalunyaHorarisTester {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		
		// per a carregar en memòria un arxiu xml
		File file = new File("src/horarisCatalunya.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		
		Element nodeArrel = doc.getDocumentElement();
		
		Scanner sc = new Scanner(System.in);
						
		boolean segueix = true;
		
		while(segueix){
			//metode mostra menu
			int opcio = menu();
			
			if(opcio == 1){
				//Afegeix nodes al node arrel
				afegir(sc, doc);
			} else if (opcio == 2){
				//Elimina node row
				eliminar(sc, doc);
			} else if (opcio == 3){
				//Modifica node row
				modificar(doc, sc);
			} else if (opcio == 4){
				//llegeix row
				llegirNodesXml(nodeArrel);
			}else if (opcio == 5){
				//Surt del programa
				System.out.println("Programa tancat");
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
	private static void afegir(Scanner sc, Document doc) throws IOException, ParserConfigurationException, SAXException, TransformerFactoryConfigurationError {
					
		Element nodeArrel = doc.getDocumentElement();
		
		try {
			  // Definim el node que contindra els elements
			  Element alumne = doc.createElement("row");
			  nodeArrel.appendChild(alumne);
			  			  
			  // atribut id
			  Attr attr = doc.createAttribute("_id");
			  
			  System.out.println("Introdueix el valor de l'atribut _id:");
			  String teclatUsuari = sc.nextLine();
			  //Afegim el valor l'atribut
			  attr.setValue(teclatUsuari);
			  alumne.setAttributeNode(attr);
			  			  			  
			  //1 Nom del festiu
			  Element nodeNom = doc.createElement("nom_del_festiu");
			  System.out.println("Introdueix el valor del primer fill row:");
			  teclatUsuari = sc.nextLine();
			  nodeNom.appendChild(doc.createTextNode(teclatUsuari));
			  alumne.appendChild(nodeNom);
			  
			  //Any
			  Element nodeCognom1 = doc.createElement("any_calendari");
			  
			  System.out.println("Introdueix el valor del segon fill row: ");
			  teclatUsuari = sc.nextLine();
			  nodeCognom1.appendChild(doc.createTextNode(teclatUsuari));
			  alumne.appendChild(nodeCognom1);
			  			  
			  //Data
			  Element nodeCognom2 = doc.createElement("data");
			  
			  System.out.println("Introdueix el valor del tercer fill row ");
			  teclatUsuari = sc.nextLine();
			  nodeCognom2.appendChild(doc.createTextNode(teclatUsuari));
			  alumne.appendChild(nodeCognom2);
			  
			  //Localitzacio
			  Element nodeNota = doc.createElement("localitzaci");
			  
			  System.out.println("Introdueix el valor del quart fill row ");
			  teclatUsuari = sc.nextLine();
			  nodeNota.appendChild(doc.createTextNode(teclatUsuari));
			  alumne.appendChild(nodeNota);
			  
			//Finalitza la creacio del arxiu XML i guarda l'arxiu
			  guardar(sc, doc);
			  
		} catch(Exception e) {
			   e.printStackTrace();
		}
	}
	
	//Finalitza la creacio del arxiu XML i guarda l'arxiu (L'usuari introdueix si o no)
	private static void guardar(Scanner sc, Document doc )throws TransformerFactoryConfigurationError, TransformerException, TransformerConfigurationException {
		
		System.out.println(" --> Vols guardar: SI/NO ");
		
		if(sc.next().equalsIgnoreCase("si")){
			  
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  StreamResult result = new StreamResult(new File("src/horarisCatalunya.xml"));
			  transformer.transform(source, result);
		}
	}
	
	//Elimina nodes de l'arrel
	private static void eliminar(Scanner sc, Document doc) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		Element element = buscarIdentificador(sc, doc);
		//Comprova que el id no sigui null
		if(element != null){
			//mostra el node
			llegirNodesXml(element);
			//Elimina
			element.getParentNode().removeChild(element);
			guardar(sc, doc);
		}
	}
	
	//Busca identificador per poder agafa el id
	private static Element buscarIdentificador(Scanner sc, Document doc){
		
		System.out.println("Introdueix el identificador: ");
		int id = sc.nextInt();
		
		Element element = null;
		
		Node nodeArrel = doc.getFirstChild();
		
		//Agafa el nodeList del node Arrel
		NodeList list = nodeArrel.getChildNodes();
		//Recorre el nodeList
		for (int i = 0; i < list.getLength(); i++) {
		    Node node = list.item(i);
		    
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		        Element element2 = (Element) node;
		        //Mira el atribut i agafa el valor
		        if(element2.getAttribute("_id").equalsIgnoreCase(Integer.toString(id))){
		        	element = element2;
		        }
		    }
		}
		return element;
	}
	
	//Llegeix el xml
	public static void llegirNodesXml(Element element) {
		
		//Guardem els fills
		NodeList nlist = element.getChildNodes();
		//Recorrem els nodes fills		
		for (int i = 0; i < nlist.getLength(); i++) {

			// Si es un element (mirem si es un element)
			if (nlist.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element esElement = (Element) nlist.item(i);
				// Si te el node conte algun atribut agafara l'atribut i el contingut
				if (esElement.hasAttributes()) {
					NamedNodeMap nodeMap = nlist.item(i).getAttributes();
					
					// Mostrem el node
					for (int j = 0; j < nodeMap.getLength(); j++) {
						Node node = nodeMap.item(j);
						System.out.print(esElement.getNodeName() + ":\n	" + node.getNodeName() + ": " + node.getNodeValue());
						llegirNodesXml(esElement);
					}
					//Si no es un atribut imprimim el node
				} else {
					//Imprimim el nodes
					System.out.print(esElement.getNodeName() + ": " );
					llegirNodesXml(esElement);	
				}

			} else {
				// Imprimim les tabulacions i els intros i tambe el contingut dels nodes, en cas de que no sigui un element
				System.out.print(nlist.item(i).getTextContent());
			}
		}
	}
	
	//Modifica els valors
	private static void modificar(Document doc, Scanner sc) throws TransformerFactoryConfigurationError, TransformerException, TransformerConfigurationException {
		
		Element element = buscarIdentificador(sc, doc);
		
		if(element != null){
			//id
			System.out.println("Introdueix el nou id: ");
			String id = sc.nextLine();
			//Accedim al contingut del atribut i cambiem el valor
			element.getAttributes().getNamedItem("id").setTextContent(id);
			
			//nom del festiu
			System.out.println("Introdueix el nou nom del festiu: ");
			String nombre = sc.nextLine();
			//Accedim al contingut del node i cambiem el valor
			element.getChildNodes().item(0).getChildNodes().item(0).setTextContent(nombre);
			
			//Any
			System.out.println("Introdueix nou any: ");
			String nota = sc.nextLine();
			//Accedim al contingut del node i cambiem el valor
			element.getChildNodes().item(3).getChildNodes().item(0).setTextContent(nota);
			
			//Data
			System.out.println("Introdueix la nova data: ");
			String cognom1 = sc.nextLine();
			//Accedim al contingut del node i cambiem el valor
			element.getChildNodes().item(1).getChildNodes().item(0).setTextContent(cognom1);
			
			//Localitzaci
			System.out.println("Introdueix la nova localitzacio: ");
			String cognom2 = sc.nextLine();
			//Accedim al contingut del node i cambiem el valor
			element.getChildNodes().item(2).getChildNodes().item(0).setTextContent(cognom2);
			
			

			guardar(sc, doc);
		}
	}
}
