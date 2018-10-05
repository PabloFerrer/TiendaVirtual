package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.ControladorCuadro;
import control.MainController;
import control.ControladorCatalogo;
import control.ControladorModificar;
import model.Producto;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public  class VentanaCatalogo extends JInternalFrame {
	
	ControladorCatalogo controlador;
        ControladorModificar cm;
	PanelProducto pp;
	public JButton siguiente;
	public JButton anterior;
	public JPanel panel;
	
        /**
	 * @param title
	 * @param resizable
	 * @param closable
	 * @param maximizable
	 * @param iconifiable
	 */
	public VentanaCatalogo () {		
		super("Catalogo",false,true,false,true);
		
	}
	
	public void addController (ControladorCatalogo cc, ControladorModificar cm){
		this.controlador = cc;
		this.cm = cm;
	}
	
		
	public void crearVista (){
		this.setLayout(new BorderLayout(60,60));
		this.setPreferredSize(new Dimension(800, 640));
		this.setSize(this.getPreferredSize());
        JPanel panel = new JPanel();
       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for(int i = 0;i<MainController.listaProductos.size();i++){
                        //Añadir control de limite de array

			Producto p = new Producto("","",(double)0.00,0);
			p = MainController.listaProductos.get(i);
			
			PanelProducto pp = new PanelProducto();
			
			ControladorCuadro cc = new ControladorCuadro(pp,controlador);
			
			pp.addController(cc, cm);
			pp.crearCuadro(MainController.listaProductos.get(i),i);
			
			panel.add(pp);
		
			}
        
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(panel);
        this.add(jsp,"Center");
        this.setVisible(true);

		}
	
	}


