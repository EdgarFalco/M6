import java.io.File;
import java.io.IOException;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Activitat4 {
	
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, NullPointerException {
		
		// per a carregar en memòria un arxiu xml
		File file = new File("src/alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
			
		//per obtenir el node arrel
		Element nodeArrel = doc.getDocumentElement();
		String nodeArrelString = nodeArrel.getNodeName();
		
		//per obtenir els nodes fills
		Element nodeFills = doc.getDocumentElement();
		//Afegim els nodes fills al nodeLlista
		NodeList nList = nodeFills.getChildNodes();
		
		//Afegim atributs
		Element atributs = doc.getDocumentElement();
				
		
		for (int i = 0; i < nList.getLength(); i++) {
			  			  
			  if(nList.item(i).hasAttributes()){
				  NamedNodeMap nodeM = nList.item(i).getAttributes();
				  
				  for (int j = 0; j < nodeM.getLength(); j++) {
					 System.out.print(nodeM.item(j).getNodeName() + ":");
					 System.out.println(nodeM.item(j).getNodeValue());
					
				}
			  }
			  Node node = nList.item(i);
			  System.out.println(node.getTextContent());
		}
			
			    
			  
	}
		
		//System.out.println("Alumnes: " + nlist.getLength());
		
		
		
		
		
		/*Node root = nList.item(0);
        System.out.println("First node of root: " + root);
        Node nNode = nList.item(0);
        
        System.out.println("Number of attributes of root: " + nNode.getAttributes().getLength());*/
        
        
        
}
				
	/*public static void mostraInfoNode(Node inputNode) {
			 
		for (int i = 0; i < inputNode.getChildNodes().getLength(); ++i) {
			Node node = inputNode.getChildNodes().item(i);
		 
			if (node.getNodeType() == Node.ELEMENT_NODE) {
		 
				System.out.print("Node: " + node.getNodeName() + " ==> " );
					if(node.getChildNodes().getLength() == 1) {
						String nodeText = node.getTextContent().trim();
						System.out.println(nodeText);
		            } else {
		                System.out.println();
		            }
		 
		               //mostraInformacioNode(node);
		    }
		}
	}*/
		 
	
	//Per obtenir els nodes fill d’un node useu el mètode getChildNodes()
	//Per obtenir els atributs d’un node, useu el mètode getAttributes()
	//Per obtenir el nom, el tipus i el valor d’un node, cerqueu els mètodes apropiats.
	

