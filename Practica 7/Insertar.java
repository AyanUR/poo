import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class Insertar extends JFrame implements ActionListener{
	Connection con;
	Statement st;
	//String url="jdbc:odbc:Driver={Microsoft Access Driver(*.mdb)};"+ "DBQ=prueba.MDB";
	String url = "jdbc:mysql://localhost:3306/", dbName = "practica7";
	JButton conectar, insertar;
	JLabel nom,raz,eda,gen;
	TextField t1,t2,t3,t4;
	public Insertar(String t){
		super(t);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		conectar= new JButton("Conectar");
		conectar.addActionListener(this);
		insertar= new JButton("Insertar");
		insertar.addActionListener(this);
		p1.setLayout(new GridLayout(4,2));
		nom=new JLabel("Nombre");
		raz=new JLabel("Raza");
		eda=new JLabel("Edad");
		gen=new JLabel("Sexo");
		t1=new TextField("");
		t2=new TextField("");
		t3=new TextField("");
		t4=new TextField("");
		p1.add(nom);
		p1.add(t1);
		p1.add(raz);
		p1.add(t2);
		p1.add(eda);
		p1.add(t3);
		p1.add(gen);
		p1.add(t4);
		 p2.setLayout(new FlowLayout());
      	 p2.add(conectar);
      	 p2.add(insertar);
      	 Container c = getContentPane();
       	 c.setLayout(new BorderLayout());
       	 c.add(BorderLayout.NORTH, p1);
       	 c.add(BorderLayout.CENTER, p2);
        }
        public void actionPerformed(ActionEvent e){
        	JButton b=(JButton)e.getSource();
			if(b==conectar)
			{	
				conectar.setEnabled(false);
				insertar.setEnabled(true);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");// aqui cargamos el driver
					//con = DriverManager.getConnection();
					 con=DriverManager.getConnection(url+dbName,"root","root");
					System.out.println("Conexion exitosa");
				}catch(Exception ex){System.err.println(ex);System.exit(0);}
			}
			
        	else 
        	{	
				conectar.setEnabled(true);
				insertar.setEnabled(false);
				try{
					//st = con.createStatement();
					//st.executeUpdate("insert into PERROS(Nombre, Raza, Edad, Genero) values('"+t1.getText()"','"+t2.getText()"','"+Integer.parseInt(t3.getText())"','"+t4.getText()"')");
					PreparedStatement pstmt=con.prepareStatement("insert into perro(Nombre, Raza, Edad, Sexo) values(?,?,?,?)");
					pstmt.setString(1,t1.getText());
					pstmt.setString(2,t2.getText());
					pstmt.setString(3,t3.getText());
					pstmt.setString(4,t4.getText());
					pstmt.executeUpdate();
					con.close();
					JOptionPane.showMessageDialog(this,"Registro Guardado", "+kotas",JOptionPane.INFORMATION_MESSAGE);
					t1.setText(" ");
					t2.setText(" ");
					t3.setText(" ");
					t4.setText(" ");
				}catch(Exception ex){System.err.println(ex);}
			}
        	
        }
        public static void main (String[] args) {
        
        JFrame frame = new Insertar("Insertar");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setVisible(true);
    	}
} 
