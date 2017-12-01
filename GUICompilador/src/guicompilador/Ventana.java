/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicompilador;

import guicompilador.Dibujo;
import javax.swing.JFrame;

/**
 *
 * @author GiannyEduardo
 */
public class Ventana extends JFrame{
    public Ventana(){
        setSize(550,670);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Dibujo());
        setVisible(true);
    
}
}
