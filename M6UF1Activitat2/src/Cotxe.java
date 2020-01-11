import java.io.Serializable;

public class Cotxe implements Serializable {

	//Atributs
	private String marca;
	private String model;
	private int any;
	private String matricula;
	
	//CONSTRUCTOR
	public Cotxe(String marca, String model, int any, String matricula) {
		this.marca = marca;
		this.model = model;
		this.any = any;
		this.matricula = matricula;
	}

	//GETTERS AND SETTERS
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getAny() {
		return any;
	}

	public void setAny(int any) {
		this.any = any;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
		
}
