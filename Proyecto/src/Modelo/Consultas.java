/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jarre
 */
public class Consultas extends ConexionMySQL {
    
     private Producto mod;
    
    public boolean registrar (Producto pro){
        
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "INSERT INTO productos (nombre, cantidad, categoria, precio) VALUES (?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setInt(2, pro.getCantidad());
            ps.setString(3, pro.getCategoria());
            ps.setDouble(4, pro.getPrecio());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
            finally{
            try {
                con.close();
            } catch (SQLException e) {
            System.err.println(e);
            }
        }
    }


public boolean actualizar (Producto pro){
        
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = "UPDATE productos SET nombre = ?, cantidad = ?, categoria = ?, precio = ? WHERE id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setInt(2, pro.getCantidad());
            ps.setString(3, pro.getCategoria());
            ps.setDouble(4, pro.getPrecio());
            ps.setInt(5, pro.getId());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
            finally{
            try {
                con.close();
            } catch (SQLException e) {
            System.err.println(e);
            }
        }
    }

public boolean eliminar (Producto pro){
        
        PreparedStatement ps = null;
        Connection con = getConnection();
        
        String sql = " DELETE FROM productos WHERE id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
            
                       
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
            finally{
            try {
                con.close();
            } catch (SQLException e) {
            System.err.println(e);
            }
        }
    }

public ArrayList Consultar(){
        
        Connection con = getConnection(); 
        ArrayList miLista = new ArrayList();
        PreparedStatement ps = null;
       
       
        
        String sql = "SELECT * FROM productos";
        
        try {
            
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            while(rs.next()){
                mod = new Producto();
                mod.setId(Integer.parseInt(rs.getString("id")));
                mod.setNombre(rs.getString("nombre"));
                mod.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                mod.setCategoria((String)rs.getString("categoria"));
                mod.setPrecio(Double.parseDouble(rs.getString("precio")));
                miLista.add(mod);
            }
            rs.close();
            con.close();
            }             
                         
        catch (SQLException e) {
            System.err.println(e);
            
        }
           
    return miLista;
}
}
