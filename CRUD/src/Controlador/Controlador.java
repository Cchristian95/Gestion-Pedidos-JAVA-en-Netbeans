
package Controlador;

import Vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Administrar;
import modelo.Producto;

public class Controlador implements ActionListener {

    Producto product = new Producto();
    vista view = new vista();
    Administrar admin = new Administrar();
    DefaultTableModel modelo = new DefaultTableModel();
    
    //El constructor vacio se crea 
    // con el objetivo de que cada vez que se inicie el controlador 
    //obligatoriamente le solicita un objeto VISTA 
    // es como una validacion y se prepara el codigo para iniciar
    public Controlador(vista v){
        this.view = v;
        this.view.btnGuardar.addActionListener(this);
        this.view.btnListar.addActionListener(this);
        this.view.btnEditar.addActionListener(this);
        this.view.btnActualizar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnGuardar){
            //se ejecuta el metodo agregar
            agregar();
            limpiar();
            listar(view.Tabla);
        }
        if (e.getSource() == view.btnListar){
            //se ejecuta el metodo listar
            limpiar();
            listar(view.Tabla);
        }
        if (e.getSource() == view.btnEditar) {
            int fila = view.Tabla.getSelectedRow();//captura el valor de la fila
            if(fila == -1){
                JOptionPane.showMessageDialog(view, "Seleccionar fila");
            }else{
                view.txtId.setText((String)view.Tabla.getValueAt(fila, 0));
                view.txtNombre.setText((String)view.Tabla.getValueAt(fila, 1));
                view.txtCodigo.setText((String)view.Tabla.getValueAt(fila, 2));
                view.txtPrecio.setText((String)view.Tabla.getValueAt(fila, 3));
            }
        }
        if (e.getSource() == view.btnActualizar){
            Actualizar_c();
            limpiar();
            listar(view.Tabla);
        }
        if (e.getSource() == view.btnEliminar){
            int fila = view.Tabla.getSelectedRow();//captura el valor de la fila
            String id = (String)view.Tabla.getValueAt(fila,0);
            
            if(fila == -1){
                JOptionPane.showMessageDialog(view, "Selecciona una fila");
            } else{
                admin.delete(id);
                JOptionPane.showMessageDialog(view, "Se elimino la fila correctamente");
            }
        }
        limpiar();
        listar(view.Tabla);
    }
    
    public void agregar(){
        
        product.setNom(view.txtNombre.getText());
        product.setCod(view.txtCodigo.getText());
        product.setPre(view.txtPrecio.getText());
        
        int r = admin.agregar_a(product);
        if (r == 1){
            JOptionPane.showMessageDialog(view, "Agrego un producto");
        }else{
            JOptionPane.showMessageDialog(view, "Error al agregar el produto");
        }
    }
    
    public void listar(JTable Tabla){
        //Preparando la tabla para iniciar a guardar informacion
        modelo = (DefaultTableModel)Tabla.getModel();
        List<Producto> lista = admin.Listar();
        //Vamos a crear un vector primitivo
        Object[] obje = new Object[4];
        for(int i = 0; i < lista.size();i++){
            // en el objeto 1 trae lo que esta en el primer espacio
            // y envialo por el metodo get a la casilla id
            obje[0] = lista.get(i).getId();
            obje[1] = lista.get(i).getNom();
            obje[2] = lista.get(i).getCod();
            obje[3] = lista.get(i).getPre();
        
            modelo.addRow(obje);
        }
        view.Tabla.setModel(modelo);
    }
    
    public void Actualizar_c(){
        product.setId(view.txtId.getText());
        product.setNom(view.txtNombre.getText());
        product.setCod(view.txtCodigo.getText());
        product.setPre(view.txtPrecio.getText());
    
        int r = admin.Actualizar(product);
        if (r == 1){
            JOptionPane.showMessageDialog(view, "Producto actualizado");
        }else{
            JOptionPane.showMessageDialog(view, "Error al actualizar el produto");
        }
    }
    
    public void limpiar(){
        for(int i = 0; i < view.Tabla.getRowCount();i++){
            // a modelo porfavor remueve los elementos dentro de mi tabla
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
