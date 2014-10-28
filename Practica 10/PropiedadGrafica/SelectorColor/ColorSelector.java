import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

import java.beans.*;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.*;

public class ColorSelector extends JFrame implements ChangeListener {
    JColorChooser Colores; // Declaramos el JColorChooser con el nombre de Colores
    public ColorSelector(){
        setTitle("Color de fondo"); // T�tulo de la ventana.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Termina la ejecuci�n al cerrar.
                                                      // Si se usa como selector de color
                                                      // Entonces debe ser HIDE_ON_CLOSE
        Colores = new JColorChooser(); // Agrega el objeto JColorChooser.
        Colores.getSelectionModel().addChangeListener(this); // Agrega los eventos de cambios.
        Colores.setMaximumSize(new Dimension(100,100)); // Determina el tama�o.
        setLayout(new BorderLayout()); // Cuestiones de posici�n sobre el JFrame.
        add(Colores, BorderLayout.CENTER); // Agrega al JFrame
        pack();
    }
public void stateChanged(ChangeEvent e){
        // Aqui puedes aplicar el color que seleccionaste
        // Solo indica el objeto al cual le cambiar�s el color.
        // Toma nota que ese objeto debe ser public y static.
        // Y llamarlo como NombreDeClase.Objeto.setBackground(Colores.getColor());
        bean.setProperty("foreground", Color.BLUE;);
        // En este ejemplo, el t�tulo de la ventana cambia al escoger un color.
        //this.setTitle("Color de fondo: " + Colores.getColor());
    }
}