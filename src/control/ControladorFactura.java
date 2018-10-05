package control;

import model.Factura;
import view.VentanaFactura;

/**
 *
 * @author Daniel
 * @author Pablo
 */

public class ControladorFactura {

	VentanaFactura factura;
	
	public ControladorFactura (VentanaFactura factura){
		super();
		this.factura = factura;	
	}
        
        //Esta funcion se encarga de colocar las facturas en el texto de la
        //ventana de facturas
        public void printFacturas(){
            for (Factura f : MainController.currentuser.getFacturas()){
                System.out.println(f.toString());
                factura.area.append(f.toString());
                factura.area.append("###################\n");
            } 
        }	
}
