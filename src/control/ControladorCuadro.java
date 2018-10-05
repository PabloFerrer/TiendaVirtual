package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PanelProducto;
import model.Producto;
import control.MainController;

/**
 *
 * @author Daniel
 * @author Pablo
 */

public class ControladorCuadro implements ActionListener {
	
	private ControladorCatalogo cc;
	private PanelProducto panel;
	
	public void actionPerformed(ActionEvent e) {
                /*Coge la posicion del arraylist en la lista (asignada al boton)
                como un actioncommand.
                Lo transforma en un int, y coge el producto en esa posicion
                Para despues añadirlo al Carrito*/
                System.out.println(e.getActionCommand());               
                Producto p = MainController.listaProductos.get(
                                Integer.parseInt(e.getActionCommand())); 
                cc.getCarrito().getLista().add(p);               
                System.out.println(p.toString());
                cc.print();                       
	}
	
	public ControladorCuadro (PanelProducto panel,ControladorCatalogo cc){
		super();
		this.panel = panel;
		this.cc = cc;
		
	}

}
