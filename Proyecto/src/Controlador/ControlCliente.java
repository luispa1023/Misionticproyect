/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ClienteDAO;
import Modelo.ClientesVO;
import Modelo.DepartamentoDAO;
import Modelo.DepartamentosVO;
import Modelo.MunicipioDAO;
import Modelo.MunicipioVO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.UsuariosDAO;
import modelo.VendedorDAO;
import modelo.VendedorVO;
import vista.JFAgregarCliente;
import vista.JFPrincipal;

/**
 *
 * @author John
 */
public class ControlCliente implements ActionListener, KeyListener, ItemListener {

    JFPrincipal principal = new JFPrincipal();
    JFAgregarCliente vistaCliente = new JFAgregarCliente();
    VendedorVO vendedorvo = new VendedorVO();
    ClientesVO clientevo = new ClientesVO();
    UsuariosDAO usuariosdao = new UsuariosDAO();
    VendedorDAO vendedordao = new VendedorDAO();
    DepartamentoDAO departamentodao = new DepartamentoDAO();
    ClienteDAO modeloCliente = new ClienteDAO();
    MunicipioDAO municipiodao = new MunicipioDAO();
   

    public ControlCliente(JFAgregarCliente vistaCliente) {
        int n = 0;
        this.vistaCliente = vistaCliente;       
        this.vistaCliente.btnGuardar.addActionListener(this);
        this.vistaCliente.btnSalir.addActionListener(this);
        this.vistaCliente.cbodepto.addItemListener(this);
        this.vistaCliente.txtnit.addKeyListener(this);
        this.vistaCliente.txtcliente.addKeyListener(this);
        this.vistaCliente.txtdireccion.addKeyListener(this);
        this.vistaCliente.txtemail.addKeyListener(this);
        this.vistaCliente.txttelefono.addKeyListener(this);
        

        listarDptos(n);
        listarMcpios(n);
        listarVend(n);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaCliente.btnGuardar) {
            boolean res = vaciosr();
            if (res != true) {

                if (!vistaCliente.txtemail.getText().trim().isEmpty()) {

                    guardarCliente();

                } else {
                    int resp = JOptionPane.showConfirmDialog(principal.jdpescritorio, "El campo email esta vacío\n¿Desea guardar la información?", "Alerta!", JOptionPane.YES_NO_OPTION);
                    if (resp != 1) {
                        guardarCliente();
                    } else {
                        vistaCliente.txtemail.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(principal.jdpescritorio, "El único campo que puede estar vacío es Email");
            }
        }
        if (e.getSource() == vistaCliente.btnSalir) {
            limpiar();
            vistaCliente.dispose();

        }

    }   
        
                

    private boolean vaciosr() {
        boolean r = vistaCliente.txtnit.getText().isEmpty();
        r |= vistaCliente.txtcliente.getText().isEmpty();
        r |= vistaCliente.txtdireccion.getText().isEmpty();
        r |= vistaCliente.txttelefono.getText().isEmpty();
        return r;
    }

 

    private void listarVend(int n) {
        @SuppressWarnings("UnusedAssignment")
        DefaultComboBoxModel vendedor = new DefaultComboBoxModel();
        vendedor = vendedordao.CargarVend();
        if (n == 0) {
            vistaCliente.cbovendedor.setModel(vendedor);
        } else {
           // vistaManejarCliente.cbovendedor.setModel(vendedor);
        }
    }
    


    private void guardarCliente() {
        MunicipioVO municipiosvo = (MunicipioVO) vistaCliente.cbociudad.getSelectedItem();
        VendedorVO vendedoresvo = (VendedorVO) vistaCliente.cbovendedor.getSelectedItem();
        clientevo.setNit(vistaCliente.txtnit.getText());
        clientevo.setCliente(vistaCliente.txtcliente.getText());
        clientevo.setDireccion(vistaCliente.txtdireccion.getText());
        clientevo.setTelefono(vistaCliente.txttelefono.getText());
        clientevo.setEmail(vistaCliente.txtemail.getText());
        clientevo.setIdMcpio(municipiosvo.getIdMcpio());
        clientevo.setIdVend(vendedoresvo.getCodigo());
        clientevo.setIdEstado(1);
        String respuesta = modeloCliente.registrarCliente(clientevo);
       

        if (respuesta != null) {
            JOptionPane.showMessageDialog(principal.jdpescritorio, respuesta);
             limpiar();
            
        } else {
            JOptionPane.showMessageDialog(principal.jdpescritorio, "Registro fallido");
        }
    }

    

    private void listarDptos(int n) {
        @SuppressWarnings("UnusedAssignment")
        DefaultComboBoxModel departamento = new DefaultComboBoxModel();
        departamento = departamentodao.CargarDptos();
        if (n == 0) {
            vistaCliente.cbodepto.setModel(departamento);
        } else {
            //vistaManejarCliente.cbodepto.setModel(departamento);
        }

    }

    private void listarMcpios(int n) {
        if (n == 0) {
            DepartamentosVO departamento = (DepartamentosVO) vistaCliente.cbodepto.getSelectedItem();
            int id = departamento.getIddpto();
            @SuppressWarnings("UnusedAssignment")
            DefaultComboBoxModel municipio = new DefaultComboBoxModel();
            municipio = municipiodao.CargarMcpio(id);
            vistaCliente.cbociudad.setModel(municipio);
        } 

    }

    
   

    private void limpiar() {
        vistaCliente.txtnit.setText("");
        vistaCliente.txtcliente.setText("");
        vistaCliente.txtdireccion.setText("");
        vistaCliente.txttelefono.setText("");
        vistaCliente.txtemail.setText("");
        vistaCliente.jLabel23.setText("");
        vistaCliente.txtnit.requestFocus();

    }

    

    public boolean validEmail(String mail) {

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        String email = mail;

        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vistaCliente.txtnit) {
            char C = e.getKeyChar();
            if (Character.isLetter(C)) {
                e.consume();
                vistaCliente.jLabel11.setText("Ingrese solo números");
                vistaCliente.txtnit.setCursor(null);
            } else if ((int) e.getKeyChar() > 32 && (int) e.getKeyChar() <= 47
                    || (int) e.getKeyChar() >= 58 && (int) e.getKeyChar() <= 64
                    || (int) e.getKeyChar() >= 91 && (int) e.getKeyChar() <= 96
                    || (int) e.getKeyChar() >= 123 && (int) e.getKeyChar() <= 255) {
                e.consume();
                vistaCliente.jLabel11.setText("Ingrese solo numeros");
                vistaCliente.txtnit.setCursor(null);
            }
        }
        if (e.getSource() == vistaCliente.txttelefono) {
            char C = e.getKeyChar();
            if (Character.isLetter(C)) {
                e.consume();
                vistaCliente.jLabel22.setText("Ingrese solo números");
                vistaCliente.txtnit.setCursor(null);
            } else if ((int) e.getKeyChar() > 32 && (int) e.getKeyChar() <= 47
                    || (int) e.getKeyChar() >= 58 && (int) e.getKeyChar() <= 64
                    || (int) e.getKeyChar() >= 91 && (int) e.getKeyChar() <= 96
                    || (int) e.getKeyChar() >= 123 && (int) e.getKeyChar() <= 255) {
                e.consume();
                vistaCliente.jLabel22.setText("Ingrese solo numeros");
                vistaCliente.txttelefono.setCursor(null);
            }
        }

        if (e.getSource() == vistaCliente.txtcliente) {
            char C = e.getKeyChar();

            if (Character.isDigit(C)) {
                e.consume();
                vistaCliente.jLabel20.setText("Ingrese solo letras");
                vistaCliente.txtcliente.setCursor(null);
            } else if ((int) e.getKeyChar() > 32 && (int) e.getKeyChar() <= 47
                    || (int) e.getKeyChar() >= 58 && (int) e.getKeyChar() <= 64
                    || (int) e.getKeyChar() >= 91 && (int) e.getKeyChar() <= 96
                    || (int) e.getKeyChar() >= 123 && (int) e.getKeyChar() <= 255) {
                e.consume();
                vistaCliente.jLabel20.setText("Ingrese solo letras");
                vistaCliente.txtcliente.setCursor(null);
            }
        }

    }

        

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == vistaCliente.txtnit) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                String r = modeloCliente.buscarNit(vistaCliente.txtnit.getText());
                if (r != null) {
                    JOptionPane.showMessageDialog(principal.jdpescritorio, "El Cliente ya está registrado");
                    vistaCliente.jLabel11.setText("");
                    vistaCliente.txtnit.setText("");
                    vistaCliente.txtnit.requestFocus();
                } else {
                    vistaCliente.jLabel11.setText("");
                    //vistaCliente.txtalmacen.requestFocus();

                }
            }
        }
        
        if (e.getSource() == vistaCliente.txtcliente) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaCliente.jLabel20.setText("");
                vistaCliente.txtdireccion.requestFocus();
            }
        }
        if (e.getSource() == vistaCliente.txtdireccion) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaCliente.jLabel21.setText("");
                vistaCliente.txttelefono.requestFocus();
            }
        }

        if (e.getSource() == vistaCliente.txttelefono) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                vistaCliente.jLabel22.setText("");
                vistaCliente.txtemail.requestFocus();
            }
        }

        if (e.getSource() == vistaCliente.txtemail) {

            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                boolean r = validEmail(vistaCliente.txtemail.getText());
                if (r == true) {
                    vistaCliente.jLabel23.setText("");
                    vistaCliente.cbodepto.requestFocus();
                } else {
                    vistaCliente.jLabel23.setText("Email incorrecto, validar Email");
                    vistaCliente.txtemail.setCursor(null);
                }

            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == vistaCliente.cbodepto) {
            int n = 0;
            listarMcpios(n);
        }
       
    }

}
