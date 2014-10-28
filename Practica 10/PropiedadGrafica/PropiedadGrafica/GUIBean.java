//javac -cp beanutils.jar:commons-logging.jar:. GUIBean.java
//java -cp beanutils.jar:commons-logging.jar:. GUIBean java.awt.Button
// modificar las propiedades background, enabled, label, y visible 
//java -cp beanutils.jar:commons-logging.jar:. GUIBean java.awt.TextArea
//java -cp beanutils.jar:commons-logging.jar:. GUIBean PerroBean
import java.beans.*;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.*;

import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class GUIBean implements ActionListener, ChangeListener {
    private String[] propertys;
    private String[] names;
    private BeanProp bean;
    private JTextField[] prop;
    private JLabel[] nprop;
    private JColorChooser colors;
    private JButton bset, bcolor, bcolsel;
    private JPanel jp, jp1, jcolor;
    private Color color;
	private Color color1;
public GUIBean(String name) throws Exception {
    bean=new BeanProp(name);
    names=bean.getPropertyNames();
    nprop=new JLabel[names.length];
    prop=new JTextField[names.length];  
	//Se define el JColorChooser
    colors=new JColorChooser();
	colors.getSelectionModel().addChangeListener(this);
	
    System.out.println(bean.getComponent());  
    jp=new JPanel(); jp1=new JPanel();
    jp.setLayout(new GridLayout(names.length,2));
    for (int j=0;j<names.length;j++) {
      jp.add(nprop[j]=new JLabel(names[j]));
      System.out.println(names[j]+": "+PropertyUtils.getProperty(bean.getComponent(), names[j]));
      Object o1=PropertyUtils.getProperty(bean.getComponent(), names[j]);
      String s1;
      if(o1==null)  s1="";
      else
	 s1=o1.toString().trim();
      jp.add(prop[j]=new JTextField(s1));
    }
    bset=new JButton("Set");
    bset.addActionListener(this);
    jp1.add(bset);
	bcolor=new JButton("Color");
    bcolor.addActionListener(this);
    jp1.add(bcolor);
	bcolsel=new JButton("ForeGround Selected");
    bcolsel.addActionListener(this);
    jp1.add(bcolsel);
    JFrame f=new JFrame("Reflexion");
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    if( bean.getComponent() instanceof Component)
    	f.add("North", (Component)bean.getComponent());
    f.add("Center", jp); f.add("South", jp1);
	f.getContentPane().add(colors,BorderLayout.EAST);
	colors.setVisible(false);
    f.setSize(1000, 600); f.setVisible(true);  
}
public void actionPerformed(ActionEvent e){ 
       JButton sel=(JButton)e.getSource();
       if(sel==bset){        
           for(int i=0;i<names.length;i++){
				//Cambiar nombres de etiqueta o boton
				if(names[i].equals("text"))
						bean.setProperty(names[i], prop[i].getText());
				if(names[i].equals("label"))
						bean.setProperty(names[i], prop[i].getText());
				//Propiedades de Visibilidad, disponibilidad y concentrable (Valores bool)
				if(names[i].equals("visible"))
				{
					if(prop[i].getText().equals("false"))
						bean.setProperty(names[i],false);
					else if(prop[i].getText().equals("true"))
						bean.setProperty(names[i], true);
				}
				if(names[i].equals("enabled"))
				{
					if(prop[i].getText().equals("false"))
						bean.setProperty(names[i],false);
					else if(prop[i].getText().equals("true"))
						bean.setProperty(names[i], true);
				}
				if(names[i].equals("focusable"))
				{
					if(prop[i].getText().equals("false"))
						bean.setProperty(names[i],false);
					else if(prop[i].getText().equals("true"))
						bean.setProperty(names[i], true);
				}
				//Otros Valores....
					//JLable y JButton
				if(names[i].equals("name"))
						bean.setProperty(names[i], prop[i].getText());
					//Color
                if(names[i].equals("foreground"))
                        bean.setProperty(names[i], color);
				if(names[i].equals("background"))
                        bean.setProperty(names[i], color1);
				//Para PerroBean
				if(names[i].equals("edad"))
                        bean.setProperty(names[i],Integer.parseInt(prop[i].getText()) );
				if(names[i].equals("genero"))
                        bean.setProperty(names[i],prop[i].getText());
				if(names[i].equals("nombre"))
                        bean.setProperty(names[i],prop[i].getText());
				if(names[i].equals("raza"))
                        bean.setProperty(names[i],prop[i].getText());
               try{
System.out.println(names[i]+": "+PropertyUtils.getProperty(bean.getComponent(), names[i]));
	        } catch(Exception ex){ex.printStackTrace();} 
            }
        }
		if(sel==bcolor)
		{
			if (colors.isVisible())
				colors.setVisible(false);
			else colors.setVisible(true);
		}
		if(sel==bcolsel)
		{
			if(bcolsel.getText().equals("BackGround Selected"))
					bcolsel.setText("ForeGround Selected");
			else bcolsel.setText("BackGround Selected");
		}
 }
 public void stateChanged(ChangeEvent e){
		if(bcolsel.getText().equals("ForeGround Selected"))
					color=colors.getColor();
			else color1=colors.getColor();
		//bean.setProperty("foreground", colors.getColor());
    }
public static void main(String[] s) throws Exception {  new GUIBean(s[0]); }

}
