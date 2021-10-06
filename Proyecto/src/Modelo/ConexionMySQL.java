/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author John
 */
public class ConexionMySQL {
    

    private final String baseDatos = "mintic";
    private final String url = "jdbc:mysql://localhost:3306/" + baseDatos;
    private final String nombreUsuario = "root";
    private final String contrasena = "";
    private Connection con = null;

    public Connection getConnection(){

              try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url, this.nombreUsuario, this.contrasena);
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
       return con;
    }
}