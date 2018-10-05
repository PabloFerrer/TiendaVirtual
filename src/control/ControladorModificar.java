/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Producto;
import view.VentanaProductos;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class ControladorModificar implements ActionListener {
    
        VentanaProductos ventana;
        MainController mc;
        ControladorProductos cp;
    
	public ControladorModificar(VentanaProductos ventana, MainController mc) {
		super();
		this.ventana = ventana;
		this.mc = mc;
	}    

       
        public void actionPerformed(ActionEvent e) {
            
            //El actioncommand sirve para identificar una posicion en el array.
            System.out.println(e.getActionCommand());
            //Despues, se parsea
            int i = Integer.parseInt(e.getActionCommand());
            Producto p = 
            MainController.listaProductos.get(i);               
            
            //Para debugging. Se imprime el articulo que esta cogiendose
            System.out.println(p.toString());
            
            /*Se crea una ventana de productos, pasandole al controlador los valores
            que la marcan como una ventana de modificacion*/
            this.ventana = new VentanaProductos();
            ventana.addController(this);
            ventana.addController(cp = new ControladorProductos(ventana,mc,true,i));
            ventana.crearVista();  
            ventana.toFront();
            mc.ventanaControlada.getContentPane().add(ventana);
            
            /*Los valores que tendran los campos de la ventana nada mas se abra
            seran los que tiene el producto que se esta intentando modificar*/
            ventana.campoNombre.setText(p.getName());
            ventana.campoDescripcion.setText(p.getDescrip());
            ventana.campoPrecio.setText("" + p.getPrecio());
            ventana.campoStock.setText("" + p.getStock());
        }        
}
