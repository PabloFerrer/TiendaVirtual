package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.ControladorCarrito;
import control.ControladorCatalogo;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class VentanaCarrito extends JInternalFrame {
	
        ControladorCatalogo cc;
        ControladorCarrito ccar;
        static int xPos=800;
        static int yPos=0;
        public JButton botonComprar;
        public JButton botonLimpiar;
        public JLabel precioTotal;
         
        public JTextArea area;	

	public void addController(ControladorCatalogo cc, ControladorCarrito ccar){
		this.cc = cc;
		this.ccar = ccar;
	}

	public VentanaCarrito(){
	super("Carrito",false,true,false,true);
	
	}
	
	public void crearVista(){

		//Le paso la layout al internalframe del carrito
		this.setLayout(new BorderLayout());	
		
		//Le paso la posicion predeterminada
		this.setLocation(xPos,yPos);
		//Le paso tamaño preferido al panel y se lo asocio
		this.setPreferredSize(new Dimension(558, 640));
		this.setSize(this.getPreferredSize());
		//Creo un panel 
		JPanel panel = new JPanel();
		area = new JTextArea(10,20);
		//Le asigno las caracteristicas al textarea
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(false);
		panel.add(area,BorderLayout.NORTH);
		this.getContentPane().add(panel,BorderLayout.NORTH);
		
		//Creo el panel de los botones
		JPanel panel2 = new JPanel(new BorderLayout());
		this.getContentPane().add(panel2,BorderLayout.EAST);
		JPanel panel3 = new JPanel(new BorderLayout());
		this.getContentPane().add(panel3,BorderLayout.WEST);
		
                //Creo el panel del precio total
		JPanel panel4 = new JPanel(new BorderLayout());
		precioTotal = new JLabel("Precio Total: " + 0 + " €");
		panel4.add(precioTotal,BorderLayout.WEST);
		this.getContentPane().add(panel4,BorderLayout.SOUTH);
		
		//Creo el boton comprar
		botonComprar = new JButton("Comprar");
		//Añado el boton comprar al panel de botones
		botonComprar.setActionCommand(ccar.COMPRAR);
		botonComprar.addActionListener(ccar);
		panel3.add(botonComprar,BorderLayout.NORTH);
		
		
		//Creo el boton de limpiar
		botonLimpiar = new JButton("Limpiar");
		//Añado el boton comprar al panel de botones
		botonLimpiar.setActionCommand(ccar.LIMPIAR);
		botonLimpiar.addActionListener(ccar);
		panel2.add(botonLimpiar,BorderLayout.NORTH);
		
                //Creo el scroll
                JScrollPane jsp = new JScrollPane();
                jsp.setViewportView(area);
                this.add(jsp,BorderLayout.NORTH);
	     
                this.setVisible(true);
	}

}
