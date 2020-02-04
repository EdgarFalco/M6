
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = {"id", "nom", "any", "data", "localitzacio"})
class Row {
        
	private String id;
	private String nom;
	private String any;
	private String data;
	private String localitzacio;
        
    @XmlAttribute(name="_id")        
    public String getId() {
      	return this.id;
    }        
            
	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAny() {
		return any;
	}

	public void setAny(String any) {
		this.any = any;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLocalitzacio() {
		return localitzacio;
	}

	public void setLocalitzacio(String localitzacio) {
		this.localitzacio = localitzacio;
	}
}