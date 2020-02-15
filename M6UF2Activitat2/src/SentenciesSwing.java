import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class SentenciesSwing extends JFrame {

	String url = "jdbc:mysql://localhost:3306/instituto";
	String usuari = "usuari";
	String password = "usuari";
	static Connection con = null;
	
	static JLabel lblsortida;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SentenciesSwing frame = new SentenciesSwing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SentenciesSwing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 107);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		lblsortida = new JLabel("");
		panel.add(lblsortida);

		JButton btnSentencies = new JButton("Executa Sentencies");

		//Event Click
		btnSentencies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprovaConexio(url, usuari, password);
				try {
					insertaSentencies(url, usuari, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		panel.add(btnSentencies);
	}
	
//METODES
	
	//METODE COMPROVA CONNEXIO
	
	//Mira el correcte funcionament de la connexio a la base de dades
	private static void comprovaConexio(String url, String usuari,String password) {
		
		Statement stmt = null;
		Driver driver = null;
		Connection con;
		
		try{
			//Carreguem el controlador en memoria
			Class.forName("com.mysql.cj.jdbc.Driver");

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
	
	
	//METODE INSERTAR SENTENCIES
	private static void insertaSentencies(String url, String usuari, String password) throws SQLException, InterruptedException {
		
		try {
			PreparedStatement stmt;
					
			con = DriverManager.getConnection(url, usuari, password);
						
			con.setAutoCommit(false);
			//Sentencia 1 inserta un alumne	
			stmt = con.prepareStatement("INSERT INTO ALUMNES (DNI, NOM, DIRECCIO, CP, DATA_NAIXAMENT) VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, "3991871R");
			stmt.setString(2, "Eudal");
			stmt.setString(3, "Fol");
			stmt.setString(4, "43207");
			stmt.setString(5, "27-03-2018");
			stmt.executeUpdate();
						
			//Sentencia 2 inserta un alumne	
			stmt = con.prepareStatement("INSERT INTO ALUMNES (DNI, NOM, DIRECCIO, CP, DATA_NAIXAMENT) VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, "3918771Z");
			stmt.setString(2, "Pepe");
			stmt.setString(3, "Or");
			stmt.setString(4, "43207");
			stmt.setString(5, "29-02-2019");
			stmt.executeUpdate();
						
			lblsortida.setText("Espera 10 segons");
			Thread.sleep(10000);
						
			//Sentencia 3 Elimina un alumne
			stmt = con.prepareStatement("DELETE FROM ALUMNES WHERE DNI = ?");
			stmt.setString(1, "3991871R");
			stmt.executeUpdate();
			con.commit();
			
			lblsortida.setText("Sentencies SQL realitzades correctament");
			con.setAutoCommit(true);
			
		} catch (SQLException e){
			con.rollback();
			lblsortida.setText("Incorrecte s'ha fet un rollback");
			//System.out.println(e.getMessage());
		}
	}
}
