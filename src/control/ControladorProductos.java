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
public class ControladorProductos implements ActionListener{
        /*Para las funciones de modificacion, se intento crea una clase que 
        heredase de esta. Aun así, esto dio problemas, así que en su lugar
        decidimos usar distintos modos en la misma clase.
        Como modified representa que elemento esta siendo modificado del array
        de productos, su valor negativo inicial evita que apunte a ningun
        elemento a no ser que lo reciba.*/
        private boolean modificar = false;
        private int modified = -1;
    
        public final static String NUEVO="N";
	public final static String EXIT="E";
	
	private VentanaProductos ventana;
	private MainController mc;

	public void actionPerformed(ActionEvent e) {
            
            //Primero, coge el actioncommand del boton pulsado
            String action = (String) e.getActionCommand();
            
            //La funcionalidad de los botones cambia si se esta modificando o no
            if (!modificar){
                if(action.equals(ControladorProductos.NUEVO)){     
                    nuevoProducto();          
                }else if(action.equals(ControladorProductos.EXIT)){
                    //EXIT, sin el modo de modificar, descarta la ventana
                    ventana.dispose();
                }
            }else{
                if(action.equals(ControladorProductos.NUEVO)){
                    //NUEVO, modificando, llama a la funcion de modificar
                    modificarProducto();                   
                }else if(action.equals(ControladorProductos.EXIT)){
                    
                    //EXIT, modificando, borra el objeto de la posicion indicada
                    MainController.listaProductos.remove(modified);
                    System.out.println("El objeto se ha borrado");
                    XMLReaderWriter.saveProductos(MainController.listaProductos);
                    ventana.dispose();
                    
                } 
            }  
	}


        
        /**
	 * @param ventana
	 * @param mc
	 */
	public ControladorProductos(VentanaProductos ventana, MainController mc) {
		super();
		this.ventana = ventana;
		this.mc = mc;
	}
        
        //El constructor esta sobrecargado, ya que los controladores que no
        //cumplen una funcion de moficar no necesitan dos de los valores
        public ControladorProductos(VentanaProductos ventana, 
                                    MainController mc, boolean md, int modified) {
		super();
		this.ventana = ventana;
		this.mc = mc;
                this.modificar = md;
                this.modified = modified;
	}
	
        
        public void nuevoProducto(){
            
            //Se crean campos para parsing, y se adquiere el nombre
            String name = ventana.campoNombre.getText();
            String descripcion = ventana.campoDescripcion.getText();
            double precio = 0;
            int stock = 0;
            
            //Se parsea el precio de String a float
            String preciotext = ventana.campoPrecio.getText();
            if (preciotext != null && !preciotext.isEmpty()) {
                precio = Double.parseDouble(preciotext);
            }
            
            //Se parsea el precio de String a int
            String preciostock = ventana.campoStock.getText();
            if (preciostock != null && !preciostock.isEmpty()) {
                stock = Integer.parseInt(preciostock);
            }
            //Para debugging, muestra los datos 
            System.out.println("Se esta intentando introducir: ");
            System.out.println(name + " " + descripcion + " " + precio
                               + " " + stock);
            
            //Si todos los campos estan llenos, se busca un articulo con igual
            //nombre, y si no existe, se añade a la lista
            if(!name.isEmpty() && !descripcion.isEmpty() 
                            && stock!=0 && precio !=0){
                               
                //Para debugging
                System.out.println("Los datos son validos");
                
                int check = 0;
                        
                //Se comprueba que no hay articulos con el mismo nombre
                for(Producto p : MainController.listaProductos){     
                    if(p.getName().equals(name)){
                        check++;                
                    }                  
                }
                
                //Si no hay articulos que el mismo nombre, se crea uno y se añade
                if (check == 0){
                    Producto nuevo = new Producto(name, descripcion, precio, stock);
                    MainController.listaProductos.add(nuevo);
                    //Para debugging
                    System.out.println("El producto se ha creado");
                    XMLReaderWriter.saveProductos(MainController.listaProductos);
                } else {
                    //Imprime un mensaje
                    System.out.println("El producto ya existe");
                }
                
            }
                                    
        }
        
	public void modificarProducto(){
            //Se crean campos para parsing, y se adquiere el nombre
            String name = ventana.campoNombre.getText();
            String descripcion = ventana.campoDescripcion.getText();
            double precio = 0;
            int stock = 0;
            
            //se parsea el precio de string a float
            String preciotext = ventana.campoPrecio.getText();
            if (preciotext != null && !preciotext.isEmpty()) {
                precio = Double.parseDouble(preciotext);
            }
            
            //Se parsea el texto de string a int
            String preciostock = ventana.campoStock.getText();
            if (preciostock != null && !preciostock.isEmpty()) {
                stock = Integer.parseInt(preciostock);
            }
            
            if(!name.isEmpty() && !descripcion.isEmpty() 
                            && stock!=0 && precio !=0){
            //Con los datos dados, se crea un producto, y se pone como nuevo
            //en la posicion del array dada
            Producto p = new Producto(name, descripcion, precio, stock);
                   
            MainController.listaProductos.set(modified,p);
                    
            //Mensaje de debug
            System.out.println("Se ha modificado el producto");
            //Se guarda la nueva lista de productos
            XMLReaderWriter.saveProductos(MainController.listaProductos);
            ventana.dispose();
            }
        }

}
