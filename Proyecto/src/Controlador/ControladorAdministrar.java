/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.UsuariosDAO;
import modelo.VendedorDAO;
import modelo.EstadoVO;
import modelo.UsuariosVO;
import modelo.VendedorVO;
import Vista.JFAdministrarUsuario;
import Vista.JFAdministrarVendedor;
import vista.JFPrincipal;

/**
 *
 * @author John
 */
public final class ControladorAdministrar implements ActionListener, KeyListener {

    JFPrincipal principal = new JFPrincipal();
    JFAdministrarUsuario vistaUsuario = new JFAdministrarUsuario();
    JFAdministrarVendedor vistaVendedor = new JFAdministrarVendedor();
    VendedorVO vendedorvo = new VendedorVO();
    VendedorDAO modeloVendedor = new VendedorDAO();
    UsuariosDAO modeloUsuarios = new UsuariosDAO();
    UsuariosVO usuariosvo = new UsuariosVO();

    public ControladorAdministrar(JFAdministrarUsuario vistaUsuario, UsuariosDAO modeloUsuarios, JFAdministrarVendedor vistaVendedor, VendedorDAO modeloVendedor) {

        
        this.vistaUsuario = vistaUsuario;
        this.modeloUsuarios = modeloUsuarios;
        this.vistaVendedor = vistaVendedor;
        this.modeloVendedor = modeloVendedor;
        this.vistaUsuario.btagregar.addActionListener(this);
        this.vistaUsuario.btnModificar.addActionListener(this);
        this.vistaUsuario.btnsalir.addActionListener(this);
        this.vistaVendedor.btagregar.addActionListener(this);
        this.vistaVendedor.btnModificar.addActionListener(this);
        this.vistaVendedor.btnsalir.addActionListener(this);
        this.vistaUsuario.txtid.addKeyListener(this);
        this.vistaUsuario.txtNombre.addKeyListener(this);
        this.vistaUsuario.txtNick.addKeyListener(this);
        this.vistaUsuario.txtPasswd.addKeyListener(this);
        this.vistaVendedor.txtid.addKeyListener(this);
        this.vistaVendedor.txtNombre.addKeyListener(this);
        configuracion();
        
    }
    
    private void configuracion(){
        int n = 0;
        listarEstado(n);
        //LLenarTabla(vistaUsuario.jtDatos);
        //LLenarTabla1(vistaVendedor.jtDatos);
        limpiar();
        bloquear();
        limpiarVend();
        bloquearVend();
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaUsuario.btagregar) {
            vistaUsuario.btagregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/save.png")));
            desbloquear();
            if (!vistaUsuario.btagregar.isSelected()) {
                 boolean res = vaciosU();            
            if(res != true){        
                //String passwd = cifrar(vistaUsuario.txtPasswd.getText());
                char[] passw = vistaUsuario.txtPasswd.getPassword();
                String passw1 = new String(passw);               
                EstadoVO estadovo = (EstadoVO) vistaUsuario.cmbEstado.getSelectedItem();
                usuariosvo.setIdUser(Integer.parseInt(vistaUsuario.txtid.getText()));
                usuariosvo.setNick(vistaUsuario.txtNick.getText());
                usuariosvo.setPasswd(passw1);
                usuariosvo.setNombre(vistaUsuario.txtNombre.getText());
                usuariosvo.setTipUser(vistaUsuario.cmbTipo.getSelectedItem().toString());
                usuariosvo.setIdEstado(estadovo.getIdEstado());
                String respuesta = modeloUsuarios.registrarUsuario(usuariosvo);
                if (respuesta != null) {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, respuesta);
                    limpiar();
                    LLenarTabla(vistaUsuario.jtDatos);
                    vistaUsuario.btagregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/add_user1.png")));
                    bloquear();
                } else {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, "Registro fallido");
                }
            }else{
                 JOptionPane.showMessageDialog(principal.jdpescritorio, "No pueden haber campos vacíos");
                  vistaUsuario.btagregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/add_user1.png")));
            }
        }
        }
    
        if (e.getSource() == vistaUsuario.btnModificar) {
            int filaEditar = vistaUsuario.jtDatos.getSelectedRow();
            int numfilas = vistaUsuario.jtDatos.getSelectedRowCount();

            if (filaEditar >= 0 && numfilas == 1) {
                vistaUsuario.btnModificar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/save.png")));
                vistaUsuario.txtid.setText(String.valueOf(vistaUsuario.jtDatos.getValueAt(filaEditar, 0)));
                vistaUsuario.txtNombre.setText(String.valueOf(vistaUsuario.jtDatos.getValueAt(filaEditar, 1)));
                vistaUsuario.txtid.setEnabled(false);
                vistaUsuario.txtNombre.setEnabled(false);
                desbloquear1();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un registro a editar");
            }

            if (!vistaUsuario.btnModificar.isSelected()) {
                  boolean res = vaciosU();            
            if(res != true){        
                //String passwd = cifrar(vistaUsuario.txtPasswd.getText());
                char[] passw = vistaUsuario.txtPasswd.getPassword();
                String passw1 = new String(passw);
                EstadoVO estadovo = (EstadoVO) vistaUsuario.cmbEstado.getSelectedItem();
                usuariosvo.setIdUser(Integer.parseInt(vistaUsuario.txtid.getText()));
                usuariosvo.setNick(vistaUsuario.txtNick.getText());
                usuariosvo.setPasswd(passw1);
                usuariosvo.setNombre(vistaUsuario.txtNombre.getText());
                usuariosvo.setTipUser(vistaUsuario.cmbTipo.getSelectedItem().toString());
                usuariosvo.setIdEstado(estadovo.getIdEstado());
                String respuesta = modeloUsuarios.actualizarUsuario(usuariosvo);

                if (respuesta != null) {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, respuesta);
                    limpiar();
                    LLenarTabla(vistaUsuario.jtDatos);
                    vistaUsuario.btnModificar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/edit.png")));
                    bloquear();
                } else if (respuesta == null) {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, "Registro fallido");
                    vistaUsuario.btnModificar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/edit.png")));
                }
            }else if(res == true){
                 JOptionPane.showMessageDialog(principal.jdpescritorio, "No pueden haber campos vacíos");
            }

        }
        }
        if (e.getSource() == vistaUsuario.btnsalir) {
            limpiar();
            vistaUsuario.dispose();
        }

        if (e.getSource() == vistaVendedor.btagregar) {
            
         vistaVendedor.btagregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/save.png")));
            desbloquearVend();
            if (!vistaVendedor.btagregar.isSelected()) {
                 boolean res = vaciosV();            
            if(res != true){        
               
                EstadoVO estadovo = (EstadoVO) vistaVendedor.cmbEstado1.getSelectedItem();
                vendedorvo.setCodigo(Integer.parseInt(vistaVendedor.txtid.getText()));
                vendedorvo.setNonVend(vistaVendedor.txtNombre.getText());
                vendedorvo.setIdEstado(estadovo.getIdEstado());
                String respuesta = modeloVendedor.registrarVendedor(vendedorvo);
                if (respuesta != null) {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, respuesta);
                    limpiarVend();
                    LLenarTabla1(vistaVendedor.jtDatos);
                    vistaVendedor.btagregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/add_user1.png")));
                    bloquear();
                } else {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, "Registro fallido");
                }
            }else{
                 JOptionPane.showMessageDialog(principal.jdpescritorio, "No pueden haber campos vacíos");
                  vistaVendedor.btagregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/add_user1.png")));
            }
        }   
        }    
         if (e.getSource() == vistaVendedor.btnModificar) {
            int filaEditar = vistaVendedor.jtDatos.getSelectedRow();
            int numfilas = vistaVendedor.jtDatos.getSelectedRowCount();

            if (filaEditar >= 0 && numfilas == 1) {
                vistaVendedor.btnModificar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/save.png")));
                vistaVendedor.txtid.setText(String.valueOf(vistaVendedor.jtDatos.getValueAt(filaEditar, 0)));
               
                     desbloquearVend1();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un registro a editar");
            }

            if (!vistaVendedor.btnModificar.isSelected()) {
                  boolean res = vaciosV();            
            if(res != true){        
                EstadoVO estadovo = (EstadoVO) vistaVendedor.cmbEstado1.getSelectedItem();
                vendedorvo.setCodigo(Integer.parseInt(vistaVendedor.txtid.getText()));
                vendedorvo.setNonVend(vistaVendedor.txtNombre.getText());
                vendedorvo.setIdEstado(estadovo.getIdEstado());
                                
                String respuesta = modeloVendedor.actualizarVendedor(vendedorvo);

                if (respuesta != null) {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, respuesta);
                    limpiarVend();
                    LLenarTabla1(vistaVendedor.jtDatos);
                    vistaVendedor.btnModificar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/edit.png")));
                    bloquearVend();
                } else if (respuesta == null) {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, "Registro fallido");
                    vistaVendedor.btnModificar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/edit.png")));
                }
            }else if(res == true){
                 JOptionPane.showMessageDialog(principal.jdpescritorio, "No pueden haber campos vacíos");
            }

        }
          
         }    
         if (e.getSource() == vistaVendedor.btnsalir) {
            limpiarVend();
            vistaVendedor.dispose();
        }
    }
    
   

    private void listarEstado(int n) {
        @SuppressWarnings("UnusedAssignment")
        DefaultComboBoxModel estado = new DefaultComboBoxModel();
        estado = modeloUsuarios.CargarEstado();
        if (n == 0) {
            vistaUsuario.cmbEstado.setModel(estado);
            vistaVendedor.cmbEstado1.setModel(estado);
          }
    }
    
     private boolean vaciosU() {
        boolean r = vistaUsuario.txtNick.getText().isEmpty();
        r |= vistaUsuario.txtNombre.getText().isEmpty();
        // r |= vistaUsuario.txtPasswd.getText().isEmpty();
        r |= vistaUsuario.txtPasswd.getPassword().equals("");
          r |= vistaUsuario.txtid.getText().isEmpty();

        return r;
    }
      private boolean vaciosV() {
        boolean r = vistaVendedor.txtNombre.getText().isEmpty();
        r |= vistaVendedor.txtid.getText().isEmpty();
       
        return r;
    }
    private void limpiar() {
        vistaUsuario.txtid.setText("");
        vistaUsuario.txtNombre.setText("");
        vistaUsuario.txtPasswd.setText("");
        vistaUsuario.txtNick.setText("");
        vistaUsuario.lblniterror.setText("");
        vistaUsuario.cmbEstado.setSelectedIndex(0);
        vistaUsuario.cmbTipo.setSelectedIndex(0);
        vistaUsuario.btagregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/add_user1.png")));
    }

    private void limpiarVend() {
        vistaVendedor.txtid.setText("");
        vistaVendedor.txtNombre.setText("");
        vistaVendedor.cmbEstado1.setSelectedIndex(0);
        vistaVendedor.btagregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/add_user1.png")));
        vistaVendedor.lblerrornit.setText("");
        vistaVendedor.txtid.requestFocus();
    }
    
    private void bloquearVend() {
        vistaVendedor.txtid.setEnabled(false);
        vistaVendedor.txtNombre.setEnabled(false);
        vistaVendedor.cmbEstado1.setEnabled(false);
    }
    
      private void desbloquearVend() {
        vistaVendedor.txtid.setEnabled(true);
        vistaVendedor.txtNombre.setEnabled(true);
        vistaVendedor.cmbEstado1.setEnabled(true);
        vistaVendedor.txtid.requestFocus();
       
      }
       private void desbloquearVend1() {
        vistaVendedor.txtid.setEnabled(false);
        vistaVendedor.txtNombre.setEnabled(true);
        vistaVendedor.cmbEstado1.setEnabled(true);
        vistaVendedor.txtNombre.requestFocus();
       
      }
    private void bloquear() {
        vistaUsuario.txtid.setEnabled(false);
        vistaUsuario.txtNombre.setEnabled(false);
        vistaUsuario.txtPasswd.setEnabled(false);
        vistaUsuario.txtNick.setEnabled(false);
        vistaUsuario.cmbEstado.setEnabled(false);
        vistaUsuario.cmbTipo.setEnabled(false);
    }
    
    

    private void desbloquear() {
        vistaUsuario.txtid.setEnabled(true);
        vistaUsuario.txtNombre.setEnabled(true);
        vistaUsuario.txtPasswd.setEnabled(true);
        vistaUsuario.txtNick.setEnabled(true);
        vistaUsuario.cmbEstado.setEnabled(true);
        vistaUsuario.cmbTipo.setEnabled(true);
        vistaUsuario.txtid.requestFocus();
    }

    private void desbloquear1() {
        vistaUsuario.txtPasswd.setEnabled(true);
        vistaUsuario.txtNick.setEnabled(true);
        vistaUsuario.cmbEstado.setEnabled(true);
        vistaUsuario.cmbTipo.setEnabled(true);
        vistaUsuario.txtNick.requestFocus();

    }
    
   
    public void LLenarTabla(JTable tablaD) {
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

    public void LLenarTabla1(JTable tablaD) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        modeloT.addColumn("CEDULA");
        modeloT.addColumn("NOMBRE");
        modeloT.addColumn("ESTADO");

        Object[] columna = new Object[3];

        int numRegistros = modeloVendedor.listVendedores().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloVendedor.listVendedores().get(i).getCodigo();
            columna[1] = modeloVendedor.listVendedores().get(i).getNonVend();
            columna[2] = modeloVendedor.listVendedores().get(i).getEstado();
            modeloT.addRow(columna);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vistaUsuario.txtid) {
            char c = e.getKeyChar();
            if (c < '0' || c > '9' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_ENTER) {
                e.consume();
                vistaUsuario.lblniterror.setText("Ingrese solo números");
            }

        }
        if (e.getSource() == vistaUsuario.txtNombre) {
            char c = e.getKeyChar();
            if (Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_ENTER) {
                e.consume();
                vistaUsuario.lblnomerror.setText("Ingrese solo letras");
            }
        }
        if (e.getSource() == vistaVendedor.txtid) {
            char c = e.getKeyChar();
            if (c < '0' || c > '9' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_ENTER) {
                e.consume();
                vistaVendedor.lblerrornit.setText("Ingrese solo números");
            }
        }
        if (e.getSource() == vistaVendedor.txtNombre) {
            char c = e.getKeyChar();
            if (Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                e.consume();
                vistaVendedor.lblerrornom.setText("Ingrese solo letras");

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == vistaUsuario.txtid) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaUsuario.lblniterror.setText("");
                vistaUsuario.txtNombre.requestFocus();
            }
        }

        if (e.getSource() == vistaUsuario.txtNombre) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaUsuario.lblnomerror.setText("");
                vistaUsuario.txtNick.requestFocus();
            }
        }

        if (e.getSource() == vistaUsuario.txtNick) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaUsuario.txtPasswd.requestFocus();
            }
        }

        if (e.getSource() == vistaUsuario.txtPasswd) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaUsuario.cmbTipo.requestFocus();
            }
        }

        if (e.getSource() == vistaVendedor.txtid) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaVendedor.lblerrornit.setText("");
                vistaVendedor.txtNombre.requestFocus();
            }
        }

        if (e.getSource() == vistaVendedor.txtNombre) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaVendedor.lblerrornom.setText("");
                vistaVendedor.cmbEstado1.requestFocus();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
