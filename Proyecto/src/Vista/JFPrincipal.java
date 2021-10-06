/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author John
 */
public class JFPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalView
     */
    public JFPrincipal() {
       
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
       

    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/misiontic_1.png"));
        return retValue;
    }
    
    public void cerra() {

        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void close(WindowEvent e) {
                    confirmar();
                }
            });
           } catch (Exception e) {
            
        }

    }
    
public void confirmar(){
    int resp = JOptionPane.showConfirmDialog(this, "Esta seguro de cerrar la aplicación", "Alerta!", JOptionPane.YES_NO_OPTION);
                if(resp!=1){
                   System.exit(0);
                }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpescritorio = new javax.swing.JDesktopPane();
        lblusuarios = new javax.swing.JLabel();
        lbltipo = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblimagen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Clientes = new javax.swing.JMenu();
        registraCliente = new javax.swing.JMenuItem();
        manejoCliente = new javax.swing.JMenuItem();
        productos = new javax.swing.JMenu();
        ingreProducto = new javax.swing.JMenuItem();
        maneProducto = new javax.swing.JMenuItem();
        facturacion = new javax.swing.JMenu();
        ingresarNC = new javax.swing.JMenuItem();
        consultarNC = new javax.swing.JMenuItem();
        usuarios = new javax.swing.JMenu();
        Musuarios = new javax.swing.JMenuItem();
        vendedores = new javax.swing.JMenu();
        Mvendedores = new javax.swing.JMenuItem();
        jmsalir1 = new javax.swing.JMenu();
        jmexit1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PROYECTO MisionTic");
        setIconImage(getIconImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jdpescritorio.setBackground(Color.WHITE);

        lblusuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/images.jpg"))); // NOI18N
        lblusuarios.setFocusable(false);

        lblimagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/misiontic_1.png"))); // NOI18N
        lblimagen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jdpescritorio.setLayer(lblusuarios, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpescritorio.setLayer(lbltipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpescritorio.setLayer(lblId, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpescritorio.setLayer(lblimagen, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpescritorioLayout = new javax.swing.GroupLayout(jdpescritorio);
        jdpescritorio.setLayout(jdpescritorioLayout);
        jdpescritorioLayout.setHorizontalGroup(
            jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdpescritorioLayout.createSequentialGroup()
                .addGap(483, 483, 483)
                .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpescritorioLayout.createSequentialGroup()
                        .addComponent(lblusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpescritorioLayout.createSequentialGroup()
                        .addComponent(lbltipo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpescritorioLayout.createSequentialGroup()
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpescritorioLayout.createSequentialGroup()
                        .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(106, 106, 106))))
        );
        jdpescritorioLayout.setVerticalGroup(
            jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdpescritorioLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblusuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbltipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addGap(85, 85, 85))
        );

        Clientes.setText("Clientes");

        registraCliente.setText("Registrar Cliente");
        Clientes.add(registraCliente);

        manejoCliente.setText("Manejo de Clientes");
        Clientes.add(manejoCliente);

        jMenuBar1.add(Clientes);

        productos.setText("Productos");

        ingreProducto.setText("Ingresar Producto");
        productos.add(ingreProducto);

        maneProducto.setText("Manejar Producto");
        productos.add(maneProducto);

        jMenuBar1.add(productos);

        facturacion.setText("Facturacion");

        ingresarNC.setText("Ingresar N.C");
        facturacion.add(ingresarNC);

        consultarNC.setText("Consultar N.C");
        facturacion.add(consultarNC);

        jMenuBar1.add(facturacion);

        usuarios.setText("Usuarios");

        Musuarios.setText("Manejar Usuarios");
        Musuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MusuariosActionPerformed(evt);
            }
        });
        usuarios.add(Musuarios);

        jMenuBar1.add(usuarios);

        vendedores.setText("Vendedores");

        Mvendedores.setText("Manejar Vendedores");
        Mvendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MvendedoresActionPerformed(evt);
            }
        });
        vendedores.add(Mvendedores);

        jMenuBar1.add(vendedores);

        jmsalir1.setText("Salir");

        jmexit1.setText("Salir del sistema");
        jmsalir1.add(jmexit1);

        jMenuBar1.add(jmsalir1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpescritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jdpescritorio)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int resp = JOptionPane.showConfirmDialog(this, "Esta seguro de cerrar la aplicación", "Alerta!", JOptionPane.YES_NO_OPTION);
                if(resp!=1){
                   System.exit(0);
                }
    }//GEN-LAST:event_formWindowClosing

    private void MusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MusuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MusuariosActionPerformed

    private void MvendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MvendedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MvendedoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu Clientes;
    public javax.swing.JMenuItem Musuarios;
    public javax.swing.JMenuItem Mvendedores;
    public javax.swing.JMenuItem consultarNC;
    private javax.swing.JMenu facturacion;
    public javax.swing.JMenuItem ingreProducto;
    public javax.swing.JMenuItem ingresarNC;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JDesktopPane jdpescritorio;
    public javax.swing.JMenuItem jmexit1;
    public javax.swing.JMenu jmsalir1;
    public javax.swing.JLabel lblId;
    private javax.swing.JLabel lblimagen;
    public javax.swing.JLabel lbltipo;
    public javax.swing.JLabel lblusuarios;
    public javax.swing.JMenuItem maneProducto;
    public javax.swing.JMenuItem manejoCliente;
    private javax.swing.JMenu productos;
    public javax.swing.JMenuItem registraCliente;
    private javax.swing.JMenu usuarios;
    private javax.swing.JMenu vendedores;
    // End of variables declaration//GEN-END:variables
}