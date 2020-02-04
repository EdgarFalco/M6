

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
class Alumnes {
	
	private Alumne[] alumnes;
 
	
	public Alumne[] getAlumnes() {
		return alumnes;
	}
	public void setAlumnes(Alumne[] alumnes) {
		this.alumnes = alumnes;
	}
 
}
