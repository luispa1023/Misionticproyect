/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jhon
 */
public class UsuariosVO {

     private int idUser;
     private int idEstado;
     private String nombre;
     private String nick;
     private String passwd;   
     private String tipUser;
     private String estado;
   

    public UsuariosVO() {
        idUser = 0;
        idEstado = 0;
        nick = "";
        passwd = "";
        nombre = "";
        tipUser = "";
        estado = "";
       
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getTipUser() {
        return tipUser;
    }

    public void setTipUser(String tipUser) {
        this.tipUser = tipUser;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

}
