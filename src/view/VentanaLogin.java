package view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import control.ControladorLogin;

import java.awt.FlowLayout;
import javax.swing.JPasswordField;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class VentanaLogin extends JInternalFrame{
	
	private ControladorLogin cl;

	JLabel titulo;
	JLabel etiquetaNombre;
        JLabel etiquetaPassword;
	public JTextField campoNombre;
        public JPasswordField campoPassword;
	public JButton botonLogin;
        public JButton botonRegistro;
	
	static int xPos=100;
	static int yPos=100;
	
	
    /**
	 * @param title
	 * @param resizable
	 * @param closable
	 * @param maximizable
	 * @param iconifiable
	 */
	public VentanaLogin() {
		super("LogIn", false, false, true, false);
	}
	
	public void addController (ControladorLogin cl){
		 
		this.cl=cl;
	}

	public void crearVista(){
    	this.getContentPane().setLayout(new FlowLayout());
		
		JPanel panelDelCombo = new JPanel();
		JPanel panelDelUsuario = new JPanel();
                JPanel panelDePassword = new JPanel();
		
		this.getContentPane().add(panelDelCombo);
		this.getContentPane().add(panelDelUsuario);
                this.getContentPane().add(panelDePassword);
		this.setSize(500,200);
		this.setLocation(xPos, yPos);
        xPos=xPos+10;
        yPos=yPos+10;
                

                
		// Dibujo el panel del Combo
		titulo = new JLabel ("Iniciar Sesión");
        panelDelCombo.add (titulo);
               		
		// Dibujo el panel del Usuario
        panelDelUsuario.setLayout(new FlowLayout());
		
        etiquetaNombre= new JLabel ("Nombre de Usuario");
    	campoNombre = new JTextField (20);
    	botonLogin = new JButton ("Inicio");
        
        
        panelDelUsuario.add(etiquetaNombre);
		panelDelUsuario.add(campoNombre);
		botonLogin.addActionListener(this.cl);
                botonLogin.setActionCommand(cl.LOGIN);
                panelDelUsuario.add(botonLogin);
                
                
                //Dibujo el panel de la contraseña
        
        panelDePassword.setLayout(new FlowLayout());
        
        etiquetaPassword = new JLabel("Contraseña");
        campoPassword = new JPasswordField(20);
        botonRegistro = new JButton("Registrarse");
        
        panelDePassword.add(etiquetaPassword);
        panelDePassword.add(campoPassword);
        botonRegistro.addActionListener(this.cl);
        botonRegistro.setActionCommand(cl.REGISTRO);
        panelDePassword.add(botonRegistro);
                
        this.getRootPane().setDefaultButton(botonLogin);
        this.setVisible(true);
        
    }
}
