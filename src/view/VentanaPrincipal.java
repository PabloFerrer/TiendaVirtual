package view;



import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.MainController;



/**
 *
 * @author Daniel
 * @author Pablo
 */
public class VentanaPrincipal extends JFrame
{
	MainController controlador;
	
	public VentanaPrincipal () {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Creamos la vista 
		setSize(1400, 800);
		setTitle("VentanaPrincipal");	
	}
	
	public void addController (MainController mc) { 
		controlador=mc;		
	}
	
	public void crearVista ()
	{	
		setContentPane(new JDesktopPane());
		setVisible(true);
	}
	
	public void crearMenu ()
	{	
        
          JMenuBar menuBar;
          JMenu menu;
          JMenuItem menuItem;
          
          //Crea la barra de menú
          menuBar = new JMenuBar();
          setJMenuBar(menuBar);

          //Primer menú
          menu = new JMenu("Menú");
          menu.setMnemonic(KeyEvent.VK_M);
 
          //Submenú 1
          menuItem = new JMenuItem("Catálogo",
                                   KeyEvent.VK_C);
          menuItem.getAccessibleContext().setAccessibleDescription(
                  "Catálogo");
          // Añadimos controlador al item de menú
          menuItem.addActionListener(controlador);
          // Asociamos un comando del controlador
          menuItem.setActionCommand(MainController.CATALOGO);
          menu.add(menuItem);
          
          //Submenú 2
          //Esto pone una descripcion, no idea, y asigna tecla rapida)
          menuItem = new JMenuItem("Facturas",
                                   KeyEvent.VK_F);
          menuItem.getAccessibleContext().setAccessibleDescription(
                  "Facturas");
          // Añadimos controlador al item de menú
          menuItem.addActionListener(controlador);
          // Asociamos un comando del controlador
          //Esto es importante para el if que hay en el MainController.
          //Es, basicamente, una flag, que hara que ejecute el codigo que hay
          //en el if asociado a Mostrar
          menuItem.setActionCommand(MainController.FACTURAS);
          menu.add(menuItem);
          
          menuItem = new JMenuItem("Logout",
                                   KeyEvent.VK_L);
          menuItem.getAccessibleContext().setAccessibleDescription(
                  "Logout");
          // Añadimos controlador al item de menú
          menuItem.addActionListener(controlador);
          // Asociamos un comando del controlador
          menuItem.setActionCommand(MainController.LOGOUT);
          menu.add(menuItem);

          menuBar.add(menu);
     	}
        
        public void crearMenuAdmin(){
            
          JMenuBar menuBar;
          JMenu menu;
          JMenuItem menuItem;
          
          menuBar = new JMenuBar();
          setJMenuBar(menuBar);
          
          
          menu = new JMenu("Admin");
          menu.setMnemonic(KeyEvent.VK_A);
          
          
          menuItem = new JMenuItem("Modificar",
                                   KeyEvent.VK_M);
          menuItem.getAccessibleContext().setAccessibleDescription(
                  "Modificar");
          // Añadimos controlador al item de menú
          menuItem.addActionListener(controlador);
          // Asociamos un comando del controlador
          menuItem.setActionCommand(MainController.MODIFICAR);
          menu.add(menuItem);
          
          menuItem = new JMenuItem("Añadir",
                                   KeyEvent.VK_A);
          menuItem.getAccessibleContext().setAccessibleDescription(
                  "Añadir");
          // Añadimos controlador al item de menú
          menuItem.addActionListener(controlador);
          // Asociamos un comando del controlador
          menuItem.setActionCommand(MainController.ADD);
          menu.add(menuItem);
          
          menuItem = new JMenuItem("Logout",
                                   KeyEvent.VK_L);
          menuItem.getAccessibleContext().setAccessibleDescription(
                  "Logout");
          // Añadimos controlador al item de menú
          menuItem.addActionListener(controlador);
          // Asociamos un comando del controlador
          menuItem.setActionCommand(MainController.LOGOUT);
          menu.add(menuItem);
          
          menuBar.add(menu);
        }



}
