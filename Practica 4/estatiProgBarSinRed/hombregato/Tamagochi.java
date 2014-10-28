import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Tamagochi extends Frame implements Runnable {
private Canvas3D canvas3D;
private Appearance ap;
private Texture feliz, enfermo;
private static Texture texture;

private JPanel jp;

private JButton bcomer;
private JButton bdormir;
private JButton bjugar;

private JLabel jl;
private JLabel jl1;
private JLabel jl2;

private JProgressBar progressBar = new JProgressBar();
private JProgressBar progressBar1 = new JProgressBar();
private JProgressBar progressBar2 = new JProgressBar();
private EventHandler eh; 

Body b;
int val,val1,val2;
Thread hilo;


public Tamagochi(){
   	super("Tamagochi 3D");
   	val=0;
   	setResizable(false); setSize(900, 600);
   	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	canvas3D = new Canvas3D(config);
   	eh = new EventHandler(); 
 
   	bcomer=new JButton("Comer");
	bcomer.addActionListener(eh);
   	jl=new JLabel("Hambre");
   	progressBar.setValue(0);
	
	bdormir=new JButton("Dormir");
   	bdormir.addActionListener(eh);
   	jl1=new JLabel("Sueño");
   	progressBar1.setValue(0);

	bjugar=new JButton("Jugar");
   	bjugar.addActionListener(eh);
   	jl2=new JLabel("Aburrimiento");
   	progressBar2.setValue(0);

   	jp=new JPanel();

   	jp.add(jl); jp.add(progressBar);
   	jp.add(bcomer);

	jp.add(jl1); jp.add(progressBar1);
   	jp.add(bdormir);

	jp.add(jl2); jp.add(progressBar2);
   	jp.add(bjugar);

   	add("North", jp);
 
	add("Center",canvas3D);
   	addWindowListener(eh);
   	setup3DGraphics();
   	setVisible(true);
	
	hilo=new Thread(this);
	
	hilo.start();
}
public void run(){
	while(1!=0){
		val=val+1;
		val1=val1+1;
		val2=val2+1;
		progressBar.setValue(val+6); //Comer
		progressBar1.setValue(val1+2); //Dormir
		progressBar2.setValue(val2+3); //Jugar
		try{
			Thread.sleep(500);
		}catch(Exception e){
			return;
		}
		if((val>=100)||(val1>=100)||(val2>=100)){
			b.changeTextureCab(texture, "caraenfermo.jpg");
			System.out.println("Tu Tamagochi ha muerto :3");}
		else{
			b.changeTextureCab(texture, "carafeliz.jpg");}
	}
}
void setup3DGraphics(){
   	DirectionalLight light1;
   	SimpleUniverse universe = new SimpleUniverse(canvas3D);
   	universe.getViewingPlatform().setNominalViewingTransform();
   	b=new Body(-0.4f,0.0f,0.0f,"",true, this, "Avatar1");
   	b.changeTextureCab(texture, "carafeliz.jpg");
   	BranchGroup group= b.mybody();
   	Color3f light1Color = new Color3f(1.0f, 1.0f, 0.0f);
   	BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
   	Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   	light1 = new DirectionalLight(light1Color, light1Direction);
   	light1.setInfluencingBounds(bounds);
   	group.addChild(light1);
   	universe.addBranchGraph(group);
}
public static void main(String[] args) { new Tamagochi(); }
class EventHandler extends WindowAdapter implements ActionListener {  
  	public void actionPerformed(ActionEvent e) {  
     	JButton btn=(JButton)e.getSource();
	if(btn==bcomer){ 
		val=val-7;
        System.out.println("Tamagochi come 2#### "+val); 
		progressBar.setValue(val);}
	if(btn==bdormir){ 
		val1=val1-18;
        System.out.println("Tamagochi duerme 2#### "+val1);
		progressBar1.setValue(val1);}
	if(btn==bjugar){ 
		val2=val2-10;
        System.out.println("Tamagochi juega 2#### "+val2);
		progressBar2.setValue(val2);}
    }
  }
public void windowClosing(WindowEvent e){ System.exit(0); }
}

