/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.JFAdministrarUsuario;
import Vista.JFAdministrarVendedor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.UsuariosDAO;
import modelo.VendedorDAO;
import vista.JFAgregarCliente;
import vista.JFPrincipal;
import vista.JFVentanaSplash;


/**
 *
 * @author John
 */
public class ControladorPrincipal implements ActionListener {

    JFPrincipal vistaPrincipal = new JFPrincipal();
    JFAgregarCliente vistaAgregarCliente = new JFAgregarCliente();
    JFAdministrarUsuario vistaUsuario = new JFAdministrarUsuario();
    JFAdministrarVendedor vistaVendedor = new JFAdministrarVendedor();
    UsuariosDAO modeloUsuarios = new UsuariosDAO();
    VendedorDAO modeloVendedor = new VendedorDAO();
    

    public ControladorPrincipal(JFPrincipal vistaPrincipal) {

        
        this.vistaPrincipal = vistaPrincipal;
        this.vistaPrincipal.registraCliente.addActionListener(this);
        this.vistaPrincipal.Musuarios.addActionListener(this);
        this.vistaPrincipal.Mvendedores.addActionListener(this);
        
       

      
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaPrincipal.registraCliente) {

            if (estacerrado(vistaAgregarCliente) == true) {
                vistaPrincipal.jdpescritorio.add(vistaAgregarCliente);
                vistaAgregarCliente.setLocation(centradoXY(vistaAgregarCliente));
                ControlCliente controlC = new ControlCliente(vistaAgregarCliente);
                vistaAgregarCliente.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(vistaPrincipal.jdpescritorio, "La ventana de 'Registrar Cliente' ya esta abierta.");

            }
        
        }
        
        if (e.getSource() == vistaPrincipal.Musuarios) {

             LLenarTablaU(vistaUsuario.jtDatos);
            if (estacerrado(vistaUsuario) == true) {
                vistaPrincipal.jdpescritorio.add(vistaUsuario);
                vistaUsuario.setLocation(centradoXY(vistaUsuario));
                ControladorAdministrar controlAd = new ControladorAdministrar(vistaUsuario, modeloUsuarios, vistaVendedor, modeloVendedor);
                vistaUsuario.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(vistaPrincipal.jdpescritorio, "La ventana de 'Administrar Usuarios' ya esta abierta.");
            }
        }
        
        if (e.getSource() == vistaPrincipal.Mvendedores) {

            LLenarTablaV(vistaVendedor.jtDatos);
            if (estacerrado(vistaVendedor) == true) {
                vistaPrincipal.jdpescritorio.add(vistaVendedor);
                vistaVendedor.setLocation(centradoXY(vistaVendedor));
                ControladorAdministrar controlAd = new ControladorAdministrar(vistaUsuario, modeloUsuarios, vistaVendedor, modeloVendedor);
                vistaVendedor.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(vistaPrincipal.jdpescritorio, "La ventana de 'Administrar Vendedores' ya esta abierta.");
            }
        }
        
        }
    
    public void LLenarTablaU(JTable tablaD) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        modeloT.addColumn("CEDULA");
        modeloT.addColumn("NOMBRE");
        modeloT.addColumn("NICK");
        modeloT.addColumn("TIPO USUARIO");
        modeloT.addColumn("ESTADO");

        Object[] columna = new Object[5];

        int numRegistros = modeloUsuarios.listUsuarios().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloUsuarios.listUsuarios().get(i).getIdUser();
            columna[1] = modeloUsuarios.listUsuarios().get(i).getNombre();
            columna[2] = modeloUsuarios.listUsuarios().get(i).getNick();
            columna[3] = modeloUsuarios.listUsuarios().get(i).getTipUser();
            columna[4] = modeloUsuarios.listUsuarios().get(i).getEstado();
            modeloT.addRow(columna);
        }
    }
    
    public void LLenarTablaV(JTable tablaE) {
        DefaultTableModel modeloE = new DefaultTableModel();
        tablaE.setModel(modeloE);
        modeloE.addColumn("CODIGO");
        modeloE.addColumn("NOMBRE");
        modeloE.addColumn("ESTADO");

        Object[] columna = new Object[3];

        int numRegistros = modeloUsuarios.listVendedor().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloUsuarios.listVendedor().get(i).getCodigo();
            columna[1] = modeloUsuarios.listVendedor().get(i).getNonVend();
            columna[2] = modeloUsuarios.listVendedor().get(i).getEstado();
            modeloE.addRow(columna);
        }
    }


    public final Point centradoXY(JInternalFrame jif) {
        Point p = new Point(0, 0);
        //se obtiene dimension del contenedor
        Dimension pantalla = vistaPrincipal.jdpescritorio.getSize();
        //se obtiene dimension del JInternalFrame
        Dimension ventana = jif.getSize();
        //se calcula posición para el centrado
        p.x = (pantalla.width - ventana.width) / 2;
        p.y = (pantalla.height - ventana.height) / 2;
        return p;
    }

    public boolean estacerrado(Object obj) {
        JInternalFrame[] activos = vistaPrincipal.jdpescritorio.getAllFrames();
        int i = 0;
        boolean cerrado = true;
        while (i < activos.length && cerrado) {
            if (activos[i] == obj) {
                cerrado = false;
            }
            i++;
        }
        return cerrado;
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Thread(new JFVentanaSplash()).start();
        });

    }

}
