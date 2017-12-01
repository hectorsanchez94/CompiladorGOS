/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicompilador;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author GiannyEduardo
 */
public class Dibujo extends Canvas {
 
    public Dibujo(){
       setBackground(new Color(76, 153,0));
    }
    public void paint(Graphics g){
       
           ImageIcon Img = new ImageIcon(getClass().getResource("/iconos/Game_Boy2.png")); 
           ImageIcon Img2 = new ImageIcon(getClass().getResource("/iconos/Game_Boy3.png")); 
           g.drawImage(Img.getImage(), 0, 0, 550,50, null);
           g.setColor(Color.BLACK);
           g.drawRect(100,90,300,100);
           g.drawImage(Img2.getImage(), 0, 300, 550,350, null);
           
          
      }
}
