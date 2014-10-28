import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class convtem extends JApplet implements ActionListener
{
	Button bkac;
	Button bcak;
	TextField text;
	Container c;
	Label lab;
	
	public convtem()
	{
		init();start();
		Frame f=new Frame("Convertidor");
		f.add( "Center",this );
		f.setSize(400,200);
		f.setVisible(true);
	}

	public void init()
	{
		c=getContentPane();
		bkac=new Button("Kelvin a Celsius");
		bcak=new Button("Celsius a Kelvin");
		text=new TextField(10);
		lab=new Label("Temperatura",Label.CENTER);
		bcak.addActionListener(this);
		bkac.addActionListener(this);
		c.add( "West",bkac );
		c.add( "East",bcak );
		c.add( "South",text );
		c.add( "Center",lab );
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if( (bkac)==(e.getSource()) )
		{
			lab.setText(Integer.toString(Integer.parseInt(text.getText())-273)+" Celsius");
		}
		else
		{
			lab.setText(Integer.toString(Integer.parseInt(text.getText())+273)+" Kelvin");
		}
	}
	public static void main(String s[])
	{
		new convtem();
	}
}
