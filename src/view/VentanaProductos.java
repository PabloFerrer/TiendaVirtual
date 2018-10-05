package view;

import control.ControladorModificar;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.ControladorProductos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


/**
 *
 * @author Daniel
 * @author Pablo
 */
public class VentanaProductos extends JInternalFrame{
	
	private ControladorProductos cp;
        private ControladorModificar cm;

	JLabel titulo;
	JLabel etiquetaNombre;
        JLabel etiquetaDescripcion;
        JLabel etiquetaPrecio;
        JLabel etiquetaStock;
	public JTextField campoNombre;
        public JTextField campoDescripcion;
        public JTextField campoPrecio;
        public JTextField campoStock;
        
	public JButton botonAñadirProducto;
        public JButton botonCancel;
	
	static int xPos=100;
	static int yPos=100;
	
	
    /**
	 * @param title
	 * @param resizable
	 * @param closable
	 * @param maximizable
	 * @param iconifiable
	 */
	public VentanaProductos() {
		super("Añadir Producto", false, true, true, true);
	}
	
	public void addController (ControladorProductos cp){
		 
		this.cp=cp;
	}
        
        public void addController (ControladorModificar cm){
		 
		this.cm=cm;
	}

	public void crearVista() {
    	this.getContentPane().setLayout(new GridBagLayout());

                //Unas pocas direcciones mas, y el proyecto sera un GPS
		GridBagConstraints direcciones = new GridBagConstraints();
		GridBagConstraints direcciones2 = new GridBagConstraints();
		GridBagConstraints direcciones3 = new GridBagConstraints();
		GridBagConstraints direcciones4 = new GridBagConstraints();
		GridBagConstraints direcciones5 = new GridBagConstraints();
		GridBagConstraints direcciones6 = new GridBagConstraints();
		GridBagConstraints direcciones7 = new GridBagConstraints();
		
		
		JPanel panelDelCombo = new JPanel(new GridBagLayout());
		JPanel panelDelNombre = new JPanel(new GridBagLayout());
        JPanel panelDescripcion = new JPanel(new GridBagLayout());
        JPanel panelPrecio = new JPanel(new GridBagLayout());
        JPanel panelStock = new JPanel(new GridBagLayout());
		
        //Direcciones
        direcciones.insets = new Insets(10,5,10,5);
        direcciones.gridx = 2;
		direcciones.gridy = 0;
		
		//Direcciones2
		direcciones.insets = new Insets(5,5,10,5);
        direcciones2.gridx = 2;
		direcciones2.gridy = 1;
		
		//Direcciones3
        direcciones3.gridx = 2;
		direcciones3.gridy = 3;
		
		//Direcciones4
        direcciones4.gridx = 2;
		direcciones4.gridy = 5;
	
		//Direcciones5
        direcciones5.gridx = 2;
		direcciones5.gridy = 7;
		
		//Direcciones6
        direcciones6.gridx = 3;
		direcciones6.gridy = 9;
		
		//Direcciones7
        direcciones7.gridx = 1;
		direcciones7.gridy = 9;
		
		this.getContentPane().add(panelDelCombo,direcciones);
		this.getContentPane().add(panelDelNombre,direcciones2);
        this.getContentPane().add(panelDescripcion,direcciones3);
        this.getContentPane().add(panelPrecio,direcciones4);
        this.getContentPane().add(panelStock,direcciones5);
		this.setSize(650,250);
		this.setLocation(xPos, yPos);
        xPos=xPos+10;
        yPos=yPos+10;

		// Dibujo el panel del Combo
		titulo = new JLabel ("Añadir un producto nuevo");
		
				
        panelDelCombo.add (titulo);
               		
		// Dibujo el panel del Usuario
        //panelDelNombre.setLayout(new GridBagLayout());
        //La colocacion mas spaghetti de la historia
        etiquetaNombre= new JLabel ("       Nombre");
    	campoNombre = new JTextField (20);
    	etiquetaNombre.setBorder(new EmptyBorder(10,10,10,10));

        
        panelDelNombre.add(etiquetaNombre);
        panelDelNombre.add(campoNombre);
        
        //Creacion del panel de Descripcion
                                
        //panelDescripcion.setLayout(new FlowLayout());
        
        etiquetaDescripcion = new JLabel("Descripción");
        campoDescripcion = new JTextField(20);
        etiquetaDescripcion.setBorder(new EmptyBorder(10,10,10,10));

        
        
        panelDescripcion.add(etiquetaDescripcion);
        panelDescripcion.add(campoDescripcion);
        
        
        //panelPrecio.setLayout(new FlowLayout());
        
        etiquetaPrecio = new JLabel("Precio");
        campoPrecio = new JTextField(5);
        etiquetaPrecio.setBorder(new EmptyBorder(10,10,10,10));
        
        panelPrecio.add(etiquetaPrecio);
        panelPrecio.add(campoPrecio);
        
        //panelStock.setLayout(new FlowLayout());
        
        etiquetaStock = new JLabel("Stock");
        campoStock = new JTextField (5);
        etiquetaStock.setBorder(new EmptyBorder(10,10,10,10));
        
        botonAñadirProducto = new JButton ("Añadir");
        botonCancel = new JButton("Borrar");
        
        panelStock.add(etiquetaStock);
        panelStock.add(campoStock);
        		
        this.add(botonAñadirProducto,direcciones6);
        botonAñadirProducto.addActionListener(this.cp);
        botonAñadirProducto.setActionCommand(ControladorProductos.NUEVO);
        this.add(botonCancel,direcciones7);
        botonCancel.addActionListener(this.cp);
        botonCancel.setActionCommand(ControladorProductos.EXIT);
        
        this.setVisible(true);
        
    }
}