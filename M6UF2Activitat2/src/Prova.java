import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Prova {
		
	static Connection con = null;
	
	public static void main(String[] args) throws SQLException, InterruptedException {
		
		Statement stmt = null;
		Driver driver = null;
		
		String url = "jdbc:mysql://localhost:3306/instituto";
		String usuari = "usuari";
		String password = "usuari";
		
		comprovaConexio(url, usuari, password);
	
		insertaSentencies(url, usuari, password);
	}
	
	
	//Mira el correcte funcionament de la connexio a la base de dades
	private static void comprovaConexio(String url, String usuari,
			String password) {
		Connection con;
		Driver driver;
		System.out.println("provaDeConnexio");
		
		try{
			//Carreguem el controlador en memoria
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(ClassNotFoundException ex){
			System.out.println("No s'ha trobat el driver JDBC (" + ex.getMessage() + ")");
			//Si no tenim controlador no podem fer res més. Sortim.
			return;
			
		}
		
		try {
			//Obtenim una connexio des de DriverManager
			con = DriverManager.getConnection(url, usuari, password);
			System.out.println("Connexio realitzada usant DriverManager");
			con.close();
		} catch (SQLException ex){
			System.out.println("Error " + ex.getMessage());
		}
		
		try{
			//Obtenim el Driver del controlador des de DriverManager
			driver = DriverManager.getDriver(url);
			
			//configurem l'usuari i la contrasenya
			Properties properties = new Properties();
			properties.setProperty("user", usuari);
			properties.setProperty("password", password);
			
			//Obtenim una connexio des de la instancia de Driver
			con = driver.connect(url, properties);
			System.out.println("Connexio realitzada usant Driver");
			con.close();
			
		} catch (SQLException ex) {
			System.out.println("Error" + ex.getMessage());
		}
	}
	
	//Metode afegir alumne
	private static void insertaSentencies(String url, String usuari, String password) throws SQLException, InterruptedException {
				
		try {
			PreparedStatement stmt;
					
			con = DriverManager.getConnection(url, usuari, password);
			
			con.setAutoCommit(false);
			//Sentencia 1 inserta un alumne	
			stmt = con.prepareStatement("INSERT INTO ALUMNES (DNI, NOM, DIRECCIO, CP, DATA_NAIXAMENT) VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, "777777");
			stmt.setString(2, "Hola");
			stmt.setString(3, "Cd");
			stmt.setString(4, "43207");
			stmt.setString(5, "27-05-2018");
			stmt.executeUpdate();
			con.commit();
			
			System.out.println("S'ha inserit correctament i borrat");
			con.setAutoCommit(true);
			
		} catch (SQLException e){
			con.rollback();
			System.out.println("Se ha echo un rollback");
			System.out.println(e.getMessage());
		}
	}

}
