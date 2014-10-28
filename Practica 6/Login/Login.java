import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
public class Login extends Panel implements ActionListener, Runnable, Serializable {	
Socket cliente;  
private ObjectInputStream oisNet;
private ObjectOutputStream oosNet;
Thread thread;
int puerto;
Button conecta, conecta1;
TextField tf;

public Login(){
        Panel p1, p2;
	puerto=5000;
	p1=new Panel();
        p2=new Panel();
	p1.setLayout(new GridLayout(3,3));
	p2.setLayout(new BorderLayout());
	
    conecta=new Button("Conecta");
	conecta.addActionListener(this);
	
    conecta1=new Button("Envia");
	conecta1.addActionListener(this); 
        
		tf=new TextField(15);    
        p2.add(conecta,BorderLayout.NORTH);
        p2.add(conecta1,BorderLayout.CENTER);
		p2.add(tf,BorderLayout.SOUTH);
        add(p2);
} 
public static void main(String args[]){
	Frame f=new Frame("Login");
	f.add("Center", new Login());
        f.setSize(250, 250); f.setVisible(true);	
}      
public void run(){
	int j;
	Object c=null;
   	for(;;)
   	{
    		j=0;
			try {
				c=oisNet.readObject();	
    		} catch ( IOException e) {
				System.out.println("IO ex"+e);
         		j=1;
            } catch (ClassNotFoundException ex) {
				System.out.println("Class no found"+ex);
				j=1;
		} 
    		if (j==0) {
                if(c!=null){
					UserSession u=(UserSession)c;
				if(u.getPrograma().equals("Login")){
                    System.out.println("Cli read " + u.getUser());
					System.out.println("Cli read " + u.getLoginTime());
                }
                }
                else System.out.println("c nullo ");
		}
	}
}
public void actionPerformed(ActionEvent e) {
	Button btn=(Button)e.getSource();
        //Object c=null;
        if(btn==conecta){
           	conectar();
		} else { 
                if(!e.getActionCommand().equals("Conecta"))
                	try {
                                
                            UserSession c=new UserSession();
							c.setUser(tf.getText());
                    		c.setLoginTime(new Date());
                            c.setPrograma(new String("Login"));
							oosNet.writeObject(c);
     			} catch (IOException ex) { ex.printStackTrace(); }          
	}
}
void conectar(){
	int i=0;
   	while(i==0)
   	{
    	i=1;
    	System.out.println("Login Esperando por el servidor . . .");
    	try {
			cliente=new Socket("localhost", puerto);
    		} catch ( IOException ioe) {
			System.out.println("Fallo creacion Socket"+ioe);
        i=0;
   		}
   	}
   	System.out.println("Login Connectado al servidor.");
   	try {
		oisNet = getOISNet(cliente.getInputStream());
        oosNet = getOOSNet(cliente.getOutputStream()); 
   	} catch ( IOException ioe) {
        System.out.println("ERROR(2)"+ioe);
   	}
        System.out.println("flujos obtenidos.");
        thread = new Thread (this);
		thread.start ();
}
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os);
}
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);
}
}
