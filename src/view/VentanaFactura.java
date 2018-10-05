package view;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.ControladorFactura;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class VentanaFactura extends JInternalFrame{
	
	public JTextArea area;
	public JPanel panel;
	ControladorFactura cf;
            /**
	 * @param title
	 * @param resizable
	 * @param closable
	 * @param maximizable
	 * @param iconifiable
	 */
	public VentanaFactura() {
		super("Facturas", false, true, false, true);
	}
	
	public void addController (ControladorFactura cf){
		this.cf = cf;
	}
	
	
	public void crearVista(){
		this.setLayout(new BorderLayout());
		this.setSize(480,540);
		//Creo el panel
		panel = new JPanel();
		//Creo el TextArea y le paso sus caracteristicas
		area = new JTextArea(30,40);
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(false);
		area.setVisible(true);
		
		panel.add(area,BorderLayout.CENTER);
		this.getContentPane().add(panel,BorderLayout.CENTER);
		//Creo el scroll
		JScrollPane jsp = new JScrollPane();
	    jsp.setViewportView(area);
	    this.add(jsp,BorderLayout.NORTH);
		
		this.setVisible(true);
	}
	
}
