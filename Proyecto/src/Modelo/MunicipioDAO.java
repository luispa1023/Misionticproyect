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
 * @author Jhon
 */
public class MunicipioDAO {
    
     ConexionMySQL conexionmysql;
     MunicipioVO Mcpio;

    public DefaultComboBoxModel CargarMcpio(int iddpto) {
        conexionmysql = new ConexionMySQL();
        DefaultComboBoxModel municipios = new DefaultComboBoxModel();
        String mysql = "SELECT * FROM municipios WHERE IdDpto = " + iddpto;

        try {
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(mysql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mcpio = new MunicipioVO();
                Mcpio.setIdMcpio(rs.getInt("IdMcpio"));
                Mcpio.setIdDpto(rs.getInt("IdDpto"));
                Mcpio.setNomMcpio(rs.getString("NomMcpio"));
                municipios.addElement(Mcpio);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MunicipioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return municipios;
    }

}
