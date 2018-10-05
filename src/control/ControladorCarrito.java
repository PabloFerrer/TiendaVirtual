package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Carrito;
import model.Factura;
import view.VentanaCarrito;
import view.VentanaFactura;

/**
 *
 * @author Daniel
 * @author Pablo
 */

public class ControladorCarrito implements ActionListener {
    //Asignamos comandos de accion
    public final static String COMPRAR="C";
    public final static String LIMPIAR="L";	
	
    private MainController mc;
    private VentanaCarrito ventana;
    private Carrito carrito;
    private VentanaFactura factura;
    
    	
    public ControladorCarrito(VentanaCarrito ventana,VentanaFactura factura, MainController mc) {
	super();
	this.ventana = ventana;
	this.mc = mc;
        this.factura = factura;
	Carrito carr = Carrito.getCarrito();
        this.carrito = carr;
   
    }        
    
    //Un getter para el carrito, uno de los metodos mas cortos de este programa
    public Carrito getCarrito(){
        return carrito;
    }

    
    public void actionPerformed(ActionEvent e) {
        /*El metodo coge el ActionCommand de los botones del carrito, y comprueba
        si se esta vaciando, o si se esta haciendo una compra. Si se hace una compra
        se crea una factura y se desecha el carrito. Si se limpia, se desecha
        sin factura.*/
        
    	String action = (String) e.getActionCommand();
        
        if(action.equals(ControladorCarrito.COMPRAR)){        	
            Factura f = new Factura(carrito.getLista(),carrito.getPrecio());
            MainController.currentuser.addFactura(f);
            //Mensaje de Debugging
            System.out.println(f.toString());
            limpiarCarrito();
            
        }
        else if(action.equals(ControladorCarrito.LIMPIAR)){
            limpiarCarrito();
        }
    }
    
    //Borra los contenidos del carrito, borra el texto que se muesta, y pone
    //el precio total mostrado a 0.
    public void limpiarCarrito(){
           carrito.limpiarCarrito();
           ventana.area.setText("");
           ventana.precioTotal.setText("Precio Total: " + 0 + " €");
    }
    
}

