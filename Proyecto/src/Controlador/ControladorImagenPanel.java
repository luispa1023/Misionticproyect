/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
/**
 *
 * @author Jhon
 */
public class ControladorImagenPanel extends javax.swing.JPanel {
        
    String imgPath;
    
    public ControladorImagenPanel(String pathImg, int ancho, int alto) {
        this.setSize(ancho, alto);
        imgPath = pathImg;
    }
    
    @Override
    public void paint(Graphics grafico) {
        Dimension tam = getSize();
        
        ImageIcon Img = new ImageIcon(getClass().getResource(imgPath)); 
        
        grafico.drawImage(Img.getImage(), 0, 0, tam.width, tam.height, null);

        setOpaque(false);
        super.paintComponent(grafico);
    }
    
}