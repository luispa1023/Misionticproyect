/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Modelo.ConexionMySQL;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author John
 */
public class Splash extends Thread{
    
    private static JProgressBar barraProgreso;
    private static JLabel eti;
    ConexionMySQL conexionmysql;
        
    public Splash(JProgressBar barra, JLabel etimensaje) {
        
        Splash.barraProgreso = barra;
        Splash.eti = etimensaje;
       
    }
    
    private void CargarBarra() {
        conexionmysql = new ConexionMySQL();
        
        for(int i = 0; i < 101; i++) {
            try {
                
                if (barraProgreso.getValue() > 0 && barraProgreso.getValue() < 3) {
                    String mensaje;
                    if(conexionmysql.getConnection() != null) {
                        mensaje = "Exitosa";
                    } else {
                        mensaje = "Falló";
                    }
                    
                    eti.setText("Verificando Conexión: " + mensaje);
                    Thread.sleep(950);
                }
                
                if (barraProgreso.getValue() > 40 && barraProgreso.getValue() < 70) {
                    eti.setText("Cargando Imagenes");
                    Thread.sleep(200);
                }
                        
                if (barraProgreso.getValue() > 69 && barraProgreso.getValue() < 101) {
                    eti.setText("Cargando Ventanas");
                    Thread.sleep(200);
                }

                barraProgreso.setValue(i);
            } catch (InterruptedException e) {
                System.out.println("Error clase\n" + e.getMessage() + "\n" + e);
            }
        }
    }
    
    @Override
    public void run() {
        CargarBarra();
    }

}