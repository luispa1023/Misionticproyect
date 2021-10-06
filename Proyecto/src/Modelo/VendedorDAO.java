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
import vista.JFPrincipal;

/**
 *
 * @author Jhon
 */
public class VendedorDAO {

    JFPrincipal principal = new JFPrincipal();
    ConexionMySQL conexionmysql;
    VendedorVO vendedorvo;

    public String registrarVendedor(VendedorVO vendedorvo) {
        String rptaRegistro = null;
        try {
            String sql = "INSERT INTO vendedores(IdVend, NomVend, IdEstado) VALUE(?, ?, ?)";

            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
            ps.setInt(1, vendedorvo.getCodigo());
            ps.setString(2, vendedorvo.getNonVend());
            ps.setInt(3, vendedorvo.getIdEstado());
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

    public String actualizarVendedor(VendedorVO vendedorvo) {
       
        String rptaRegistro = null;
        try {
            String sql = "UPDATE vendedores SET NomVend = ?, IdEstado = ? WHERE IdVend = ?";

            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
            ps.setString(1, vendedorvo.getNonVend());
            ps.setInt(2, vendedorvo.getIdEstado());
            ps.setInt(3, vendedorvo.getCodigo());
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

    public ArrayList<VendedorVO> listVendedores() {
        ArrayList listaVendedor = new ArrayList();
        try {
            String sql = "SELECT IdVend, NomVend, estado.`NomEstado`FROM vendedores, estado WHERE vendedores.`IdEstado` = estado.`IdEstado`";
            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vendedorvo = new VendedorVO();
                vendedorvo.setCodigo(rs.getInt(1));
                vendedorvo.setNonVend(rs.getString(2));
                vendedorvo.setEstado(rs.getString(3));
                listaVendedor.add(vendedorvo);
            }
              //conexionmysql.cerrarConexionBaseDatosMySQL();
        } catch (SQLException e) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaVendedor;
    }

    public DefaultComboBoxModel CargarVend() {
        conexionmysql = new ConexionMySQL();
        DefaultComboBoxModel vendedores = new DefaultComboBoxModel();
        String mysql = "SELECT * FROM vendedores WHERE IdEstado = 1 ORDER BY NomVend ";

        try {
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(mysql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vendedorvo = new VendedorVO();
                vendedorvo.setCodigo(rs.getInt("IdVend"));
                vendedorvo.setNonVend(rs.getString("NomVend"));
                vendedores.addElement(vendedorvo);
            }
              //conexionmysql.cerrarConexionBaseDatosMySQL();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vendedores;
    }

}
