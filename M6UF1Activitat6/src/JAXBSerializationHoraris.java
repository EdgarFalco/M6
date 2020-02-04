


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 





import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
 
public class JAXBSerializationHoraris {
	
	private static final String HORARIS_XML_FILE = "horarisCatalunya.xml";
 
public static void main(String[] args) throws JAXBException, IOException {
		
		JAXBContext context = JAXBContext.newInstance(Festius.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Festius rows = ompleRows();
		
		//Mostrem el document XML generat por la sortida estandard
		marshaller.marshal(rows, System.out);
		
		FileOutputStream fos = new FileOutputStream(HORARIS_XML_FILE);
		//guardem l'objecte serializat en un document XML
		marshaller.marshal(rows, fos);
		fos.close();
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//Deserialitzem a partir de un document XML
		Festius rowAux = (Festius) unmarshaller.unmarshal(new File(HORARIS_XML_FILE));
		System.out.println("********* Alumnes carregat desde fitxer XML***************");
		//Mostrem l'objeto Java obtingut
		marshaller.marshal(rowAux, System.out);
		
	}
	
	private static Festius ompleRows(){
				
		String[] nomsFestius = {"Any nou", "major", "Festa", "Pascua", "Misericordia"};
        String[] anysFestius = {"2020", "2020", "2020", "2020", "2020" };
        String[] dataFestius = {"10-10-2020", "11-10-2020", "10-12-2019", "10-02-2019", "22-02-2019"};
        String[] localitzacioFestius = {"Reus", "Tarragona", "Barcelona", "Tarragona", "Reus"};
		
        Row[] ArrayRow = new Row[5];
		
		for(int i=0; i<5; i++){
			ArrayRow[i] = new Row();
	            ArrayRow[i].setNom(nomsFestius[i]);
	            ArrayRow[i].setAny(anysFestius[i]);
	            ArrayRow[i].setData(dataFestius[i]);
	            ArrayRow[i].setLocalitzacio(localitzacioFestius[i]);
            ArrayRow[i].setId("_" + i);	
        }
		
		Festius row = new Festius();
		row.setRows(ArrayRow);	
		return row;
	}
}