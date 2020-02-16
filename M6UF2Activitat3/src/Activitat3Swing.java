import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import net.miginfocom.swing.MigLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Activitat3Swing extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuari = new JTextField();
	private JTextField tfPass = new JTextField();
	private static JLabel lblSortida;
	private ButtonGroup buttonGroup;
		
	String url = "jdbc:mysql://localhost:3306/instituto";
	String usuari = "usuari";
	String password = "usuari";
	static Connection con = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Activitat3Swing frame = new Activitat3Swing();
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
	public Activitat3Swing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[][][][][][]"));
		
		JLabel lblUsuari = new JLabel("Usuari:");
		contentPane.add(lblUsuari, "cell 0 0,alignx left");
		
		tfUsuari = new JTextField();
		contentPane.add(tfUsuari, "cell 1 0,growx");
		tfUsuari.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword, "cell 0 2,alignx left");
		
		tfPass = new JTextField();
		contentPane.add(tfPass, "cell 1 2,growx");
		tfPass.setColumns(10);
		
		JRadioButton rdbtnStatment = new JRadioButton("Statment", true);
		contentPane.add(rdbtnStatment, "cell 0 4");
		
		JRadioButton rdbtnPreparedstatment = new JRadioButton("PreparedStatment");
		contentPane.add(rdbtnPreparedstatment, "cell 0 5");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnStatment);
		buttonGroup.add(rdbtnPreparedstatment);
						
		//Event Click entrar
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comprovaConexio(url, usuari, password);
				
				if(tfUsuari.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Falten dades");
				
				} else {
					if(rdbtnStatment.isSelected()){
						try {
							//JOptionPane.showMessageDialog(null, "Statment");
							mostraStatment(url, usuari, password);
						} catch (SQLException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							//JOptionPane.showMessageDialog(null, "PreparedStatment");
							mostraPreparedStatment(url, usuari, password);
						} catch (SQLException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		contentPane.add(btnEntrar, "cell 1 4,alignx right");
	}
	
	//-----------METODOS------------
	
	//Consulta usuari amb preparedStatment
	private void mostraPreparedStatment(String url, String usuari, String password) throws SQLException, InterruptedException {
		
		try {
			
			PreparedStatement stmt;
				
			con = DriverManager.getConnection(url, usuari, password);
			
			String consulta = "SELECT * FROM usuaris WHERE usuari = ? AND contraseña = ?";
									
			con.setAutoCommit(false);
						
			stmt = con.prepareStatement(consulta);
			stmt.setString(1, tfUsuari.getText());
			stmt.setString(2, tfPass.getText());
								
			con.commit();
			con.setAutoCommit(true);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Correcte");
			} else {
				JOptionPane.showMessageDialog(null, "Incorrecto");
			}
				
		} catch (SQLException e){
			con.rollback();
			
		}
	}
	
	//Consulta Usuari amb Statment
	private void mostraStatment(String url, String usuari, String password) throws SQLException, InterruptedException {

		Statement stmt;
		Connection con;
						
		con = DriverManager.getConnection(url, usuari, password);
		stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM usuaris WHERE usuari ='" + tfUsuari.getText() + "' AND contraseña = '" + tfPass.getText() + "'");
				
		if(rs.next()){
			JOptionPane.showMessageDialog(null, "Correcte");
		} else {
			JOptionPane.showMessageDialog(null, "Incorrecte");
		}
		
		con.close();
	}
	
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
}
