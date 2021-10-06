/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import modelo.UsuariosDAO;
import modelo.UsuariosVO;


/**
 *
 * @author Jhon
 */
public class ControladorIniciarSesion {
    
    public String mensaje1, mensaje2;
    UsuariosDAO usuario;
    
    public boolean iniciarSesion(UsuariosVO usuariovo) throws SQLException {

        usuario = new UsuariosDAO();
        boolean estadoLogin = false;

        if ((usuariovo.getNick().trim().equals("")) && (usuariovo.getPasswd().trim().equals(""))) {
            estadoLogin = false;
            mensaje1 = "El nombre de usuario es requerido";
            mensaje2 = "La contraseña es requerida";
        } else if (usuariovo.getNick().trim().equals("")) {
            estadoLogin = false;
            mensaje1 = "El nombre de usuario es requerido";
            mensaje2 = " ";
        } else if (usuariovo.getPasswd().trim().equals("")) {
            estadoLogin = false;
            mensaje1 = "La contraseña es requerida";
            mensaje2 = " ";
        } else if (usuario.inicarSesionUsuario(usuariovo) == true) {
            estadoLogin = true;
        } else if (usuario.inicarSesionUsuario(usuariovo) == false) {
            estadoLogin = false;
            mensaje1 = "Las credenciales no son validas o";
            mensaje2 = "la cuenta puede estar inactiva.";
        }
        return estadoLogin;
    }
    
}