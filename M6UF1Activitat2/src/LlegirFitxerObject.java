import java.io.*;

public class LlegirFitxerObject {
	public static void main(String[] args) throws IOException {
		
		String path = "./src/cotxes.txt";
		
		File fitxer = new File(path);
		FileInputStream filein = new FileInputStream(fitxer);
		DataInputStream dataInComarq = new DataInputStream(filein);
		
		int poblacio;
		String comarca;
		
		try {
			while(true) {
				comarca = dataInComarq.readUTF();
				poblacio = dataInComarq.readInt();
				System.out.println("Comarca:\t" +comarca);
				System.out.println(" població\t" +poblacio+"\n");
			}
		} catch (EOFException eo){}
		
		dataInComarq.close();

		}
		
}

