/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Consultas;
import Modelo.Producto;
import Vista.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jarre
 */
public class Control implements ActionListener, MouseListener {
    
    private Producto mod;
    private Consultas cons;
    private Panel panel;
    
    public Control(Producto mod, Consultas cons, Panel panel){
        
        this.mod = mod;
        this.cons = cons;
        this.panel = panel;
        this.panel.btnGuardar.addActionListener(this);
        this.panel.btnActualizar.addActionListener(this);
        this.panel.btnConsultar.addActionListener(this);
        this.panel.btnEliminar.addActionListener(this);
        this.panel.jtConsulta.addMouseListener(this);
    }
    
    public void iniciar(){
        panel.setTitle("Producto");
        panel.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == panel.btnGuardar){
                    
            mod.setNombre(panel.txtNombre.getText());
            mod.setCantidad(Integer.parseInt(panel.txtCantidad.getText()));
            mod.setCategoria((String)panel.jcCategoria.getSelectedItem());
            mod.setPrecio(Double.parseDouble(panel.txtPrecio.getText()));
            
            if(cons.registrar(mod)){
                JOptionPane.showMessageDialog(null, "Registro guardado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
        }
        
      if(e.getSource() == panel.btnConsultar){
            
          LLenarTabla(panel.jtConsulta);
            
        }  
        
      if(e.getSource() == panel.btnActualizar){
          
            mod.setId(Integer.parseInt(panel.txtid1.getText()));
            mod.setNombre(panel.txtNombre.getText());
            mod.setCantidad(Integer.parseInt(panel.txtCantidad.getText()));
            mod.setCategoria((String)panel.jcCategoria.getSelectedItem());
            mod.setPrecio(Double.parseDouble(panel.txtPrecio.getText()));
            
            if(cons.actualizar(mod)){
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Actualizar");
            }
        }   
        
     if(e.getSource() == panel.btnEliminar){
         
            mod.setId(Integer.parseInt(panel.txtid1.getText()));
                        
            if(cons.eliminar(mod)){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar1();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        }    
    }
    
    public void limpiar(){
        panel.txtNombre.setText(null);
        panel.txtCantidad.setText(null);
        panel.txtPrecio.setText(null);
        
    }
    
    public void limpiar1(){
        panel.txtid1.setText(null);
        panel.txtNombre1.setText(null);
        panel.txtCantidad1.setText(null);
        panel.txtPrecio1.setText(null);
        
    }
            
    public void LLenarTabla(JTable tabla) {
       
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoria");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        
        Object[] fila = new Object[modelo.getColumnCount()];
        ArrayList<Producto>lista = cons.Consultar();
        
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getId();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getCategoria();
            fila[3] = lista.get(i).getCantidad();
            fila[4] = lista.get(i).getPrecio();
            modelo.addRow(fila);
        }

}

    @Override
    public void mouseClicked(MouseEvent me) {
       if (me.getSource() == panel.jtConsulta){
           int seleccion = panel.jtConsulta.rowAtPoint(me.getPoint());
           panel.txtid1.setText(String.valueOf(panel.jtConsulta.getValueAt(seleccion, 0)));
           panel.txtNombre1.setText(String.valueOf(panel.jtConsulta.getValueAt(seleccion, 1)));
           //panel.jcCategoria1.addItem(String.valueOf(panel.jtConsulta.getValueAt(seleccion, 2)));
           panel.txtCantidad1.setText(String.valueOf(panel.jtConsulta.getValueAt(seleccion, 3)));
           panel.txtPrecio1.setText(String.valueOf(panel.jtConsulta.getValueAt(seleccion, 4)));
           
       }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}