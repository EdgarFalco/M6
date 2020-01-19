import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;


public class Activitat4 {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, NullPointerException {
			
			// per a carregar en memòria un arxiu xml
			File file = new File("src/alumnes.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(file);

			//per obtenir el node arrel
			Element nodeArrel = doc.getDocumentElement();
			
			//Mostrem el node arrel
			System.out.println(nodeArrel.getNodeName());
			//Utilizem el metode per llegir la resta de nodes i atributs
			llegirNodesXml(nodeArrel);
	}
	
	//METODE
	public static void llegirNodesXml(Element element) {
		
		
		NodeList nlist = element.getChildNodes();

		for (int i = 0; i < nlist.getLength(); i++) {

			
			if (nlist.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element esElement = (Element) nlist.item(i);
				
				if (esElement.hasAttributes()) {
					NamedNodeMap nodeMap = nlist.item(i).getAttributes();

					for (int j = 0; j < nodeMap.getLength(); j++) {
						Node node = nodeMap.item(j);
						System.out.print(esElement.getNodeName() + ":\n	" + node.getNodeName() + ": " + node.getNodeValue());
						llegirNodesXml(esElement);
					}

				} else {
					System.out.print(esElement.getNodeName() + ": " );
					llegirNodesXml(esElement);	
				}

			} else {
				System.out.print(nlist.item(i).getTextContent());
			}
		}
	}
}
		 
	
	//Per obtenir els nodes fill d’un node useu el mètode getChildNodes()
	//Per obtenir els atributs d’un node, useu el mètode getAttributes()
	//Per obtenir el nom, el tipus i el valor d’un node, cerqueu els mètodes apropiats.
	

