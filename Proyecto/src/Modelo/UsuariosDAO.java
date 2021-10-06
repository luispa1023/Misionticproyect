/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Modelo.ConexionMySQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Jhon
 */
public class UsuariosDAO {
    
    ConexionMySQL conexionmysql;
    EstadoVO estado;
    UsuariosVO usuarios;
    VendedorVO vendedor;
    
    public boolean inicarSesionUsuario(UsuariosVO usuariovo) throws SQLException {
        boolean estadoInicioSesion = false;
        String sql = "SELECT * FROM usuarios WHERE Nick = ? AND Passwd = ? AND IdEstado = ?";
        conexionmysql = new ConexionMySQL();
        PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
        ps.setString(1, usuariovo.getNick());
        ps.setString(2, usuariovo.getPasswd());
        ps.setInt(3, usuariovo.getIdEstado());
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            if(usuariovo.getNick().equals(rs.getString("Nick")) && usuariovo.getPasswd().equals(rs.getString("Passwd"))) {
                usuariovo.setIdUser(Integer.parseInt(rs.getString("IdUser")));
                usuariovo.setNick(rs.getString("Nick"));
                usuariovo.setNombre(rs.getString("NomUser"));
                usuariovo.setTipUser(rs.getString("TipUser"));
                usuariovo.setEstado(rs.getString("IdEstado"));
                estadoInicioSesion = true;
            }
        }
        
        return estadoInicioSesion;
    }
    
    
     public String registrarUsuario(UsuariosVO miusuario) {
        String rptaRegistro = null;
        try {
            String sql = "INSERT INTO usuarios(IdUser,NomUser, Nick, Passwd, TipUser, IdEstado) VALUE(?, ? ,?, ?, ?, ?)";

            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
            ps.setInt(1, miusuario.getIdUser());
            ps.setString(2, miusuario.getNombre());
            ps.setString(3, miusuario.getNick());
            ps.setString(4, miusuario.getPasswd());
            ps.setString(5, miusuario.getTipUser());
            ps.setInt(6, miusuario.getIdEstado());
            int datosAlmacenados = ps.executeUpdate();

            //conexionmysql.cerrarConexionBaseDatosMySQL();

            if (datosAlmacenados > 0) {
                rptaRegistro = "Registro exitoso.";
            } else {
                rptaRegistro = null;
            }
        } catch (SQLException e) {
        }
        return rptaRegistro;
    }
     
     public String actualizarUsuario(UsuariosVO miusuario) {
        String rptaRegistro = null;
        try {
            String sql = "UPDATE usuarios SET  Nick = ?,NomUser = ?, Passwd = ?,  TipUser = ?, IdEstado = ? WHERE IdUser = ?";

            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);

            ps.setString(1, miusuario.getNick());
            ps.setString(2, miusuario.getNombre());
            ps.setString(3, miusuario.getPasswd());
            ps.setString(4, miusuario.getTipUser());
            ps.setInt(5, miusuario.getIdEstado());
            ps.setInt(6, miusuario.getIdUser());
            int datosAlmacenados = ps.executeUpdate();

            //conexionmysql.cerrarConexionBaseDatosMySQL();

            if (datosAlmacenados > 0) {
                rptaRegistro = "Edicion realizada con exito";
            } else {
                rptaRegistro = null;
            }

        } catch (SQLException e) {
        }

        return rptaRegistro;
    }

     
     public ArrayList<UsuariosVO> listUsuarios(){
        ArrayList listaUsuario = new ArrayList();
         try {
             String sql = "SELECT IdUser, Nick, Passwd, NomUser, TipUser,estado.`NomEstado`FROM usuarios, estado WHERE usuarios.`IdEstado` = estado.`IdEstado`";
            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usuarios = new UsuariosVO();
                usuarios.setIdUser(rs.getInt(1));
                usuarios.setNick(rs.getString(2));
                usuarios.setPasswd(rs.getString(3));
                usuarios.setNombre(rs.getString(4));
                usuarios.setTipUser(rs.getString(5));
                usuarios.setEstado(rs.getString(6));
                listaUsuario.add(usuarios);
            }
             // conexionmysql.cerrarConexionBaseDatosMySQL();
        } catch (SQLException e) {
             Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaUsuario;
    }
     
      public ArrayList<VendedorVO> listVendedor(){
        ArrayList listaVendedor = new ArrayList();
         try {
             String sql = "SELECT IdVend, NomVend, estado.`NomEstado`FROM vendedores, estado WHERE vendedores.`IdEstado` = estado.`IdEstado`";
            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vendedor = new VendedorVO();
                vendedor.setCodigo(rs.getInt(1));
                vendedor.setNonVend(rs.getString(2));
                vendedor.setEstado(rs.getString(3));
                listaVendedor.add(vendedor);
            }
              
        } catch (SQLException e) {
             Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaVendedor;
    }
     
     
    
    
    public DefaultComboBoxModel CargarEstado() {  
       
        conexionmysql = new ConexionMySQL();
        DefaultComboBoxModel estados = new DefaultComboBoxModel();
        String mysql = "SELECT *  FROM estado  WHERE IdEstado = '1' OR IdEstado = '2' ";

        try {
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(mysql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                estado = new EstadoVO();
                estado.setIdEstado(rs.getInt("IdEstado"));
                estado.setEstado(rs.getString("NomEstado"));
                estados.addElement(estado);
              
            }
             // conexionmysql.cerrarConexionBaseDatosMySQL();
        } catch (SQLException ex) {
           Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }

        return estados;
    }
}
