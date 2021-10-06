/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author John
 */
public class DepartamentoDAO {
    
    ConexionMySQL conexionmysql;
    DepartamentosVO Dpto;

    public DefaultComboBoxModel CargarDptos() {
        conexionmysql = new ConexionMySQL();
        DefaultComboBoxModel departamentos = new DefaultComboBoxModel();
        String mysql = "SELECT * FROM departamentos ORDER BY NomDpto ";

        try {
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(mysql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dpto = new DepartamentosVO();
                Dpto.setIddpto(rs.getInt("IdDpto"));
                Dpto.setNomdpto(rs.getString("NomDpto"));
                departamentos.addElement(Dpto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return departamentos;
    }

}
