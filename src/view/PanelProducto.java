package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.ControladorCuadro;
import control.ControladorModificar;
import control.MainController;
import model.Producto;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class PanelProducto extends JPanel {
	
        //Se declaran los elementos que guardara
	ControladorCuadro controlador = null;
        ControladorModificar cm = null;
	MainController mc;
        Producto p;
	
	//SE declaran todos los elementos visuales
	public JLabel nombreProducto;
	public JLabel stock;
	public JLabel precio;
	public JTextField campoNombre;
	public JTextArea descripcion;
	public JButton botonComprarProducto;
        public JButton botonModificarProducto;
        public JButton botonBorrarProducto;

	public void addController(ControladorCuadro cc,ControladorModificar cm){
		this.controlador = cc;
                this.cm = cm;
	}

	public void crearCuadro(Producto p, int i){
//		Declaramos que queremos el gridbaglayout
		this.setLayout(new GridBagLayout());
		GridBagConstraints separatorConstraint = new GridBagConstraints();
		GridBagConstraints direcciones = new GridBagConstraints();
		
		this.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
		
//		Aqui le voy a pasar las direcciones que quiero de layout al nombre
		direcciones.gridx = 1;	//Le paso las direcciones de la primera columna
		direcciones.gridy = 0;	//Le paso las direcciones de la primera fila
		direcciones.gridwidth = 1; //Ocupa 1 columna
		direcciones.gridheight = 1; //Ocupa 1 fila

		Font fuente = new Font("", 1, 20);
		nombreProducto = new JLabel (p.getName());
		nombreProducto.setFont(fuente);
		
		//Añadimos la etiqueta al panel
		this.add(nombreProducto,direcciones);
	
		
//		Aqui le voy a pasar las direcciones que quiero de layout a la descripcion	
		direcciones.gridx = 1;	//Le paso las direcciones de la primera columna
		direcciones.gridy = 1;	//Le paso las direcciones de la primera fila 	IMPORTANTE AQUI HABIA UN 1
		direcciones.gridwidth = 3; //Ocupa 3 columna
		direcciones.gridheight = 2; //Ocupa 2 fila	
		
		
		descripcion = new JTextArea (4,32);
		descripcion.setEditable(false);
		
		descripcion.setText(p.getDescrip());
		//Este hace que no se extienda y mantenga un tamaño fijo.
		descripcion.setLineWrap(true);
		descripcion.setWrapStyleWord(false);
		
		//Añado la descripcion al panel
				this.add(descripcion,direcciones);
		
                //Aqui le voy a pasar las direcciones que quiero de layout a la cantidad de stock
		direcciones.anchor = GridBagConstraints.SOUTHWEST;
		direcciones.gridx = 4;	//Le paso las direcciones de la primera columna
		direcciones.gridy = 2;	//Le paso las direcciones de la primera fila IMPORTANTE AQUI HABIA UN 1
		direcciones.gridwidth = 2; //Ocupa 1 columna
		direcciones.gridheight = 1; //Ocupa 1 fila
		
		//Creo la etiqueta de stock
		stock = new JLabel ("   " + p.getStock());
		
		//Añado el stock al panel
		this.add(stock,direcciones);
		
                //Aqui le voy a pasar las direcciones que quiero de layout al precio
		direcciones.anchor = GridBagConstraints.SOUTHEAST;
		direcciones.gridx = 5;	//Le paso las direcciones de la primera columna
		direcciones.gridy = 2;	//Le paso las direcciones de la primera fila IMPORTANTE AQUI HABIA UN 1
		direcciones.gridwidth = 1; //Ocupa 1 columna
		direcciones.gridheight = 1; //Ocupa 1 fila
				
		//Creo la etiqueta de precio
		precio = new JLabel ("    " + p.getPrecio());
				
		//Añado el stock al panel
		this.add(precio,direcciones);	
				
		//Crea los botones dependiendo de admin o usuario.
                
                if(MainController.currentuser.getName().equals("admin")){
                    crearBotonesAdmin(direcciones, i);
                } else {
                    crearBotones(direcciones, i);
                }
                
                //Aqui le paso los separadores 
		separatorConstraint.insets = new Insets(5,5,5,5);
		separatorConstraint.gridx = 1;
		separatorConstraint.gridy = 3;
		separatorConstraint.gridheight = 2; //Ocupa 1 fila
		separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;
		this.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
		
		
	}

        
   public void crearBotones(GridBagConstraints direcciones, int i){
       
            // Aqui le voy a pasar las direcciones que quiero de layout al boton
		
                direcciones.anchor = GridBagConstraints.CENTER;
		direcciones.insets = new Insets(5,5,5,5);
		direcciones.gridx = 4;	//Le paso las direcciones de la primera columna
		direcciones.gridy = 1;	//Le paso las direcciones de la primera fila
		direcciones.gridwidth = 2; //Ocupa 1 columna
		direcciones.gridheight = 1; //Ocupa 1 fila
		botonComprarProducto = new JButton("Añadir al carrito");
		this.add(botonComprarProducto,direcciones);
                //El boton de usuario y de admin tienen controladores distintos
		botonComprarProducto.addActionListener(this.controlador);
                botonComprarProducto.setActionCommand(Integer.toString(i));
		
   }     
   public void crearBotonesAdmin(GridBagConstraints direcciones, int i){
       
            //Aqui le voy a pasar las direcciones que quiero de layout al boton de modificar

		direcciones.insets = new Insets(5,5,5,5);
		direcciones.gridx = 4;	//Le paso las direcciones de la primera columna
		direcciones.gridy = 1;	//Le paso las direcciones de la primera fila
		direcciones.gridwidth = 2; //Ocupa 1 columna
		direcciones.gridheight = 1; //Ocupa 1 fila
		botonModificarProducto = new JButton("Modificar");
		this.add(botonModificarProducto,direcciones);
                //El boton de usuario y de admin tienen controladores distintos
		botonModificarProducto.addActionListener(this.cm);
		botonModificarProducto.setActionCommand(Integer.toString(i));
                
   }
}
