/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author John
 */
public class ClienteDAO {

    ConexionMySQL conexionmysql;

    public String registrarCliente(ClientesVO micliente) {
        String rptaRegistro = null;
        try {
            String sql = "INSERT INTO clientes(Nit, Cliente, Dir, Tel, Email, IdMcpio, IdVend, IdEstado) VALUE(?, ?, ?, ?, ?, ?, ?, ?)";

            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);

            ps.setString(1, micliente.getNit());
            ps.setString(2, micliente.getCliente());
            ps.setString(3, micliente.getDireccion());
            ps.setString(4, micliente.getTelefono());
            ps.setString(5, micliente.getEmail());
            ps.setInt(6, micliente.getIdMcpio());
            ps.setInt(7, micliente.getIdVend());
            ps.setInt(8, micliente.getIdEstado());
            int datosAlmacenados = ps.executeUpdate();

            //conexionmysql.cerrarConexionBaseDatosMySQL();

            if (datosAlmacenados > 0) {
                rptaRegistro = "Registro exitoso.";
            } else {
                rptaRegistro = null;
            }
        } catch (SQLException e) {
            rptaRegistro = e.getMessage();
        }
        return rptaRegistro;
    }

    public String buscarCliente(ClientesVO micliente) {
        String rptaRegistro = null;

        try {
            String sql = "SELECT Nit,Cliente,Dir,Tel,Email,departamentos.`NomDpto`, municipios.`NomMcpio`,vendedores.`NomVend`,estado.`NomEstado` FROM clientes,departamentos, municipios,vendedores,estado WHERE  clientes.`IdMcpio` = municipios.`IdMcpio` AND municipios.`IdDpto` = departamentos.`IdDpto` AND clientes.`IdVend` = vendedores.`IdVend` AND `clientes`.`IdEstado` = estado.`IdEstado`  AND clientes.`IdEstado` = 1 AND  Nit = ?";

            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
            ps.setString(1, micliente.getNit());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                micliente.setNit(rs.getString("Nit"));
                micliente.setCliente(rs.getString("Cliente"));
                micliente.setDireccion(rs.getString("Dir"));
                micliente.setTelefono(rs.getString("Tel"));
                micliente.setEmail(rs.getString("Email"));
                micliente.setNomDepto(rs.getString("NomDpto"));
                micliente.setNomMunicipio(rs.getString("NomMcpio"));
                micliente.setNomVendedor(rs.getString("NomVend"));
                micliente.setNomEstado(rs.getString("NomEstado"));
                rptaRegistro = "ok";
            }

           // conexionmysql.cerrarConexionBaseDatosMySQL();

        } catch (SQLException e) {
        }
        return rptaRegistro;
    }

    public String editarCliente(ClientesVO micliente) {
        String rptaRegistro = null;
        try {
            String sql = "UPDATE clientes SET  Cliente = ?, Dir = ?, Tel = ?, Email = ?, IdMcpio = ?, IdVend = ?, IdEstado = ? WHERE Nit = ?";

            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);

            ps.setString(1, micliente.getCliente());
            ps.setString(2, micliente.getDireccion());
            ps.setString(3, micliente.getTelefono());
            ps.setString(4, micliente.getEmail());
            ps.setInt(5, micliente.getIdMcpio());
            ps.setInt(6, micliente.getIdVend());
            ps.setInt(7, micliente.getIdEstado());
            ps.setString(8, micliente.getNit());
            int datosAlmacenados = ps.executeUpdate();

            //conexionmysql.cerrarConexionBaseDatosMySQL();

            if (datosAlmacenados > 0) {
                rptaRegistro = "Actualizacion Exitosa";
            } else {
                rptaRegistro = null;
            }

        } catch (SQLException e) {
        }

        return rptaRegistro;
    }
 public String buscarNit(String nit) {
         String rptaRegistro = null;
         System.out.println(nit);
       try {

            String sql = "SELECT Nit FROM clientes WHERE  Nit =" + nit;

            conexionmysql = new ConexionMySQL();
            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
           
                rptaRegistro = "ok";
            }

           // conexionmysql.cerrarConexionBaseDatosMySQL();

        } catch (SQLException e) {
        }

       return rptaRegistro;
    }
 public ArrayList<ClientesVO> buscarListaClientes(String nom) {
        ArrayList listarClientes = new ArrayList();
        try {
            String nombre = "%" + nom + "%";
            String mysql = "SELECT Nit,Cliente FROM clientes WHERE Cliente LIKE ?";

            conexionmysql = new ConexionMySQL();

            PreparedStatement ps = conexionmysql.getConnection().prepareStatement(mysql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClientesVO clientes = new ClientesVO();
                clientes.setNit(rs.getString("Nit"));
                clientes.setCliente(rs.getString("Cliente"));
                listarClientes.add(clientes);
            }

            rs.close();
            //conexionmysql.cerrarConexionBaseDatosMySQL();

        } catch (SQLException e) {
        }
        return listarClientes;
    }
 
}
