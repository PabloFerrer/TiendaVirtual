package control;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.*;
import view.*;

/**
 *
 * @author Daniel
 * @author Pablo
 */

/*NOTA SOBRE AUTORIA: Aunque ambos han trabajado en la mayoría de las clases de
forma conjunta, por lo general, Pablo llevaba toda la sección de view, y Daniel
se encargaba de la sección de control.

Por lo tanto, es probable encontrar una diferencia en el estilo de comentarios, 
así como en los estilos de indentación.

El modelado ha sido un trabajo conjunto a partes iguales.*/

public class MainController implements ActionListener {
    
        //Se crean como valores estaticos el admin, el usuario que estara logeado
        //y los arrays de productos y usuarios
    
	public static Usuario currentuser;
        public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        public static ArrayList<Producto> listaProductos = new ArrayList<Producto>();
       
        /*Debido a que la autenticacion funciona por nombre de usuario, el admin
        no se puede guardar en el array, ya que esto permitiria borrar el XML, y
        crear un usuario con ese nombre, para poder acceder a sus privilegios*/
        public static final Usuario ADMIN = new Usuario("admin","1234") ;
    
        //Todo esto son identificadores de comandos
        public final static String CATALOGO="C";
	public final static String FACTURAS="F";
        public final static String MODIFICAR="M";
        public final static String ADD="A";
        public final static String LOGOUT="L";
	
	VentanaPrincipal ventanaControlada;
	
        //Constructor para el maincontroller, recibe la ventana principal
	public MainController(VentanaPrincipal win){
                ventanaControlada=win;
	}
	
	//Cuando recibe una accion, el cursor pasa a ser de espera, y se coge
        //el actioncomand para abrir una ventana dependiendo de que boton se ha usado
	public void actionPerformed(ActionEvent e){
		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
        ventanaControlada.setCursor(cur);        
        
    	String cmd = (String)(e.getActionCommand());
        abrirVentana(cmd);
		
		// Dejo el cursor como estaba
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
        ventanaControlada.setCursor(cur);        
    } 
    
    public void abrirVentana(String cmd) {
    	        
        
            //Aparentemente Java no usa switches con String. Viva el codigo spaghetti.
            if (cmd.equals(MainController.CATALOGO)||cmd.equals(MainController.MODIFICAR)){
                
                /* Originalmente el catalogo y modificar era acciones distintas
                , pero debido a que la diferencia era practicamente nula, crear 
                un par de instancias mas en el de modificar, los he puesto como mismo comando*/
                VentanaCatalogo catalogo = new VentanaCatalogo();
                VentanaCarrito carrito = new VentanaCarrito();
                VentanaProductos modificar = new VentanaProductos();
                VentanaFactura factura = new VentanaFactura();
                ControladorCatalogo cc = new ControladorCatalogo(catalogo,this,carrito);
                ControladorModificar cm = new ControladorModificar(modificar,this);
                ControladorCarrito ccar = new ControladorCarrito(carrito,factura,this);
                
                //El carrito y el catalogo se abren a la vez        
                catalogo.addController(cc,cm);
                catalogo.crearVista();
                carrito.addController(cc, ccar);
                carrito.crearVista();
                ventanaControlada.getContentPane().add(catalogo);
                ventanaControlada.getContentPane().add(carrito);
  
            }
            else if (cmd.equals(MainController.ADD)){
            
                //Si se presiona añadir, se crea una ventana que recibe datos
                //para crear un nuevo producto
                VentanaProductos interna = new VentanaProductos();

		ControladorProductos cp = new ControladorProductos(interna, this);

		interna.addController(cp);
		interna.crearVista();

		ventanaControlada.getContentPane().add(interna);    
            }    
            else if (cmd.equals(MainController.LOGOUT)){
                //Mensaje para debugging
                System.out.println("El comando LOGOUT se ha introducido");
                
                /* Todo este bloque intenta crear una clase PrintStream, la cual
                sirve para imprimir en archivos de texto. En este caso, se imprimen
                las facturas del usuario*/
                try{
                    PrintStream out = new PrintStream("Factura.txt");
                
                    String factura = "";
                        
                    for (Factura f : MainController.currentuser.getFacturas()){
                        factura = factura.concat(f.toString());
                        factura = factura.concat("###################\n");
                    }
                    System.out.println(factura);
                    out.println(factura);
                //Se tira una excepcion de nivel aviso si esto falla
                } catch (Exception ex) {
                    Logger.getLogger(XMLReaderWriter.class.getName()).log(Level.WARNING, 
                                null, ex);
                }        
                
                //Se borra la instancia del usuario actual, y se cierra la ventana
                MainController.currentuser = null;
		ventanaControlada.dispose();
                //Se cierra la ejecución, para evitar que el programa siga funcionando
		System.exit(0);  
            }
            else if (cmd.equals(MainController.FACTURAS)){
                
                //Si se solicita ver la ventana de facturas, se crea esta ventana
                //y se le pide al controlador que las imprima en su textbox
                VentanaFactura ventanafac = new VentanaFactura();
                ControladorFactura cf = new ControladorFactura(ventanafac);
                ventanafac.addController(cf);
                ventanafac.crearVista();
                cf.printFacturas();
                ventanaControlada.getContentPane().add(ventanafac);
                
            }

        }	

        
        public void abrirLogin(){
            
            /*Este codigo abre la ventana de login, que tiene una funcion
            especifica en el main, ya que esta es la ventana que se vera
            en cuanto se abra el programa.*/
                
            VentanaLogin login = new VentanaLogin();

            ControladorLogin cl = new ControladorLogin(login, this);

            login.addController(cl);
            login.crearVista();

            ventanaControlada.getContentPane().add(login);       
        }
        
      
        //Esto solo sirve para añadir productos predefinidos para probar.
        //Solo se utiliza con fines de debugging y de demostracion
        public void stockDePrueba(){
            
            Producto p1 = new Producto ("Durex","Connecting People", (double) 1.69, 8);
            listaProductos.add(p1);
            Producto p2 = new Producto ("Patatas","El Producto Original", (double) 7.99, 4);
            listaProductos.add(p2);
            Producto p5 = new Producto ("Harry Potter y la madre que le pario",
                    "Porque J.K. Rowling no dejara de escribirlos", (double) 0.49, 32);
            listaProductos.add(p5);
        }
        
        /*Usado para debugging, imprine una lista de los usuarios registrados
        No es la mejor idea en cuanto a seguridad, lo necesario seria quitarlo
        en un programa que se hiciese para una empresa de verdad. Dado que el fin
        en este es demostrar la funcionalidad y no que sea seguro, se queda*/
        public void printUsers(){
            System.out.println("La lista de usuarios permitidos es: ");
            for (Usuario u : usuarios){
                System.out.println(u.getName() + " " + u.getPass());
            }
        }

        //El main se ejecuta a partir de aqui
	public static void main(String args[]){
            System.out.println("Starting VentanaPrincipal...");
		
            // Leyendo el Properties
		
            Properties appProps = new Properties();
				
            // Creo la ventana
            VentanaPrincipal mainFrame = new VentanaPrincipal();
		
            // Creo el controlador pasando la ventana
            MainController mc=new MainController(mainFrame);
		
            /*Este metodo copia los usuarios guardados en un archivo XML
            al array de usuarios estatico de esta clase.
            Se comprueba que no sean null por si acaso no hay XML
            Un array null provocaria que el sistema no pudiese 
            acceder a los usuarios*/
            usuarios = XMLReaderWriter.getUsuarios();
            if (usuarios == null){
                usuarios = new ArrayList<>();
            }
            
            //Misma comprobacion con el XML de productos
            listaProductos = XMLReaderWriter.getProductos();
            if (listaProductos == null){
                listaProductos = new ArrayList<>();
            }
                
            // Le Asociamos el controlador a la ventana
            mainFrame.addController(mc);
            mainFrame.crearVista();
                
            //Se abre la ventana de login
            mc.abrirLogin();
            mc.printUsers();
                
           /*A partir de aqui todos los metodos introducidos son de debugging
            y pruebas. No corresponden al funcionamiento normal de un programa*/
           

            mc.clearFacturas();
            //mc.clearUsers();
            mc.clearProductos();
            mc.stockDePrueba();
	}
        
 
        //Estos tres comandos se utilizan para debugging, concretamente
        //para eliminar las listas de facturas, productos, y usuarios
        public void clearUsers(){
            usuarios.clear();
            XMLReaderWriter.saveUsuarios(usuarios);
        }
       
        public void clearProductos(){
            listaProductos.clear();
            XMLReaderWriter.saveProductos(listaProductos);
        }
        
        public void clearFacturas(){
            for (Usuario u : usuarios){
                u.deleteFacturas();
            }
            XMLReaderWriter.saveUsuarios(usuarios);
        }
}
