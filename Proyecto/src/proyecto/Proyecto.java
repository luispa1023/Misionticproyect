/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import Controlador.Control;
import Modelo.Consultas;
import Modelo.Producto;
import Vista.Panel;

/**
 *
 * @author jarre
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Producto mod = new Producto();
        Consultas cons = new Consultas();
        Panel panel = new Panel();
        
        Control ctrl = new Control(mod, cons,panel);
        ctrl.iniciar();
        panel.setVisible(true);
        
        
        
    }
    
}
