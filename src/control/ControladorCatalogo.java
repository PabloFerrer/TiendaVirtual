/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VentanaCarrito;
import view.VentanaCatalogo;
import model.Carrito;
import model.Producto;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class ControladorCatalogo implements ActionListener {
        private MainController mc;
	private VentanaCatalogo ventana;
        private Carrito carrito;
        private VentanaCarrito vc;
        
    public ControladorCatalogo(VentanaCatalogo ventana, MainController mc,VentanaCarrito vc) {
	super();
	this.ventana = ventana;
	this.mc = mc;
	this.vc = vc;
        Carrito carrito = Carrito.getCarrito();
        this.carrito = carrito;
    }        
    	
    
    public Carrito getCarrito(){
        return carrito;
    }
    
    /*Este es el metodo más importante del controlador del carrito, a parte
    de su funcion de permitir acceso al carrito a los botones de producto
    
    Este metodo recorre el array de productos del carrito, y añade a la ventana
    que lo muestra una linea por cada producto, mediante el metodo toString()*/
    
    public void print(){
    	
    	vc.area.setText("");
    	for(Producto p : carrito.getLista()){
            vc.area.append(p.toString()+ "\n");
    	}
    	vc.precioTotal.setText("Precio Total: " + carrito.getPrecio() + " €"); 	
    }
    
    
    /*De momento, el catalogo no cuenta con más botones, por lo que no recibe
    eventos. En el caso de querer añadir botones no asociados al producto al
    catalogo, se usaría este escuchador.*/
    @Override
    public void actionPerformed(ActionEvent e) {
    		
    }
    
    /*NOTA: Originalmente, esta clase era bastante necesaria, ya que el catalogo
    no tenia un scrollbar, en su lugar, tenía botones. Por lo tanto, aunque parezca
    poco útil en la actualidad, se debe al cambio de diseño durante el desarrollo,
    además de posibilitar mejores opciones si se quiere expandir su funcionalidad*/
}
