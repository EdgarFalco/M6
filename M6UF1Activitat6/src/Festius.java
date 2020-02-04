
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Festius")
class Festius {
	
	private Row[]rows;
	
	@XmlElement(name="row")
	public Row[] getRows() {
		return rows;
	}

	public void setRows(Row[] rows) {
		this.rows = rows;
	}

}
