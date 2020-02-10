import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ConBdTester {
		
	public static void main(String[] args) throws SQLException {
		
		Statement stmt = null;
		Connection con = null;
		Driver driver = null;
		
		String url = "jdbc:mysql://localhost:3306/instituto";
		String usuari = "root";
		String password = "";
						
		boolean segueix = true;
		
		System.out.println("MENU: ");
				
		while(segueix){
			//metode mostra menu
			int opcio = menu();
			
			if(opcio == 1){
				//Inserta un alumne a la base de dades
				insertaAlumne(url, usuari, password);
			} else if (opcio == 2){
				//Borra un alumne de la base de dades
				borraAlumne(url, usuari, password);
			} else if (opcio == 3){
				//Mostra la informacio del alumne
				mostraAlumne(url, usuari, password);
				
			} else if (opcio == 4){
				insertaPoblacio(url, usuari, password);
				
			} else if (opcio == 5){
				borraPoblacio(url, usuari, password);
				
			} else if (opcio == 6){
				//tanca el programa
				System.out.println("Programa tancat");
				segueix = false;
			}
		}
		
		comprovaConexio(url, usuari, password);
				
		mostraAlumne(url, usuari, password);

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
	private static void insertaAlumne(String url, String usuari, String password) throws SQLException {
		Statement stmt;
		Connection con;
		Scanner sc = new Scanner(System.in);
		String dni;
		String nom;
		String direccio;
		String CP;
		String data_naixament;
		
		System.out.println("Introdueix el dni: ");
		dni = sc.nextLine();
		
		System.out.println("Introdueix el nom del alumne: ");
		nom = sc.nextLine();
		
		System.out.println("Introdueix la direccio: ");
		direccio = sc.nextLine();
		
		System.out.println("Introdueix el CP: ");
		CP = sc.nextLine();
		
		System.out.println("Introdueix la data de naixament: ");
		data_naixament = sc.nextLine();
		
		con = DriverManager.getConnection(url, usuari, password);
		stmt = con.createStatement();
		stmt.execute("INSERT INTO Alumnes (DNI, NOM, DIRECCIO, CP, DATA_NAIXAMENT) VALUES ('" + dni + "', '" + nom + "', '" + direccio + "', " + CP + ", '" + data_naixament + "' )");
		
		System.out.println("S'ha inserit correctament");
		con.close();
	}
	
	//Metode eliminar alumne
	private static void borraAlumne(String url, String usuari, String password) throws SQLException {
		
		Statement stmt;
		Connection con;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introdueix el dni del alumne que vols borrar: ");
		String dni = sc.nextLine();
		
		con = DriverManager.getConnection(url, usuari, password);
		stmt = con.createStatement();
		stmt.execute("DELETE FROM ALUMNES WHERE DNI='" + dni + "'");
		
		System.out.println("Borrat");
		con.close();
	}
	
	//Metode mostrar alumne
	private static void mostraAlumne(String url, String usuari, String password) throws SQLException {
		Statement stmt;
		Connection con;
		con = DriverManager.getConnection(url, usuari, password);
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ALUMNES");
		
		System.out.println("Informacio Alumnes: ");
		
		while(rs.next())
        {
            System.out.println(rs.getString(1));    //First Column
            System.out.println(rs.getString(2));    //Second Column
            System.out.println(rs.getString(3));    //Third Column
            System.out.println(rs.getString(4));    //Fourth Column
            System.out.println(rs.getString(5));    //Fourth Column
            System.out.println();
        }
				
		con.close();
	}
	
	//Mostra el menu i agafa una opcio de l'usuari
	private static int menu() {
		System.out.println("\nMENU");
		System.out.println("1. Insertar Alumne");
		System.out.println("2. Eliminar Alumne");
		System.out.println("3. Mostrar Alumne");
		System.out.println("4. Crear Poblacions");
		System.out.println("5. Esborrar Poblacio");
		System.out.println("6. Sortir\n");
						
		System.out.println("Introdueix l'opcio 1...6 : ");
		Scanner sc = new Scanner(System.in);
		int opcio = sc.nextInt();
		return opcio;
	}
	
	//Metode insertar poblacio
	private static void insertaPoblacio(String url, String usuari, String password) throws SQLException {
		Statement stmt;
		Connection con;
		Scanner sc = new Scanner(System.in);
		String CP;
		String nom;
			
		System.out.println("Introdueix el CP: ");
		CP = sc.nextLine();
			
		System.out.println("Introdueix el nom de la poblacio: ");
		nom = sc.nextLine();
				
		con = DriverManager.getConnection(url, usuari, password);
		stmt = con.createStatement();
		stmt.execute("INSERT INTO POBLACIO (NOM, CP) VALUES ('" + nom + "', '" + CP +"')");
		
		System.out.println("S'ha inserit correctament");
		con.close();
	}
	
	//Metode eliminar poblacio
	private static void borraPoblacio(String url, String usuari, String password) throws SQLException {
			
		Statement stmt;
		Connection con;
		Scanner sc = new Scanner(System.in);
			
		System.out.println("Introdueix el CP de la poblacio que vols borrar: ");
		String CP = sc.nextLine();
			
		con = DriverManager.getConnection(url, usuari, password);
		stmt = con.createStatement();
		stmt.execute("DELETE FROM POBLACIO WHERE CP='" + CP + "'");
			
		System.out.println("Borrat");
		con.close();
	}

}
