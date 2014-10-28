import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class camionres extends Applet implements ActionListener
{
	Button mostrar;
	Button asientos[];
	Label lab;
	
	public camionres()
	{
		asientos=new Button[44];
		setLayout(new GridLayout(11,4));
		for(int i=0;i<asientos.length;i++)
		{
			add(asientos[i]=new Button("D"));
			asientos[i].addActionListener(this);
		}
		mostrar=new Button(" Mostrar ");
		mostrar.addActionListener(this);
		lab=new Label(" ");
		add(mostrar);add("North",lab);
		Frame f=new Frame("Reservador de Asientos");
		f.add( "Center",this );
		f.setSize(600,600);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Button b=(Button)e.getSource();
		if(b.getLabel()==" Mostrar ")
		{
			int k=0;
			lab.setText(" ");
			for(int i=0;i<asientos.length;i++)
			{
				if(asientos[i].getLabel()=="R")
				{
					lab.setText( lab.getText()+Integer.toString(i+1)+"," );
				}
			}	
		}
		else if (b.getLabel()=="R")
		{
			b.setLabel("D");
		}
		else
		{
			b.setLabel("R");
		}
	}
	public static void main(String s[])
	{
		camionres c=new camionres();
		c.init();
		c.start();
	}
	
}
