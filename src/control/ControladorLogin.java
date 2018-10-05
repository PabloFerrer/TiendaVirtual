/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Usuario;

import view.VentanaLogin;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class ControladorLogin implements ActionListener {
    
    //Se declaran los valores que se usaran como actioncommand
    public final static String LOGIN="L";
    public final static String REGISTRO="R";
        
    private MainController mc;
    private VentanaLogin ventana;
    
    
    public void actionPerformed(ActionEvent e) {

            String action = (String) e.getActionCommand();
            
            //Esto diferencia de que boton viene el evento
            if(action.equals(ControladorLogin.LOGIN)){
                //Si un usuario intenta entrar, se llama al metodo autenticar
                System.out.println("El usuario intenta logear");
                System.out.println("Se intenta conectar: " 
                        + ventana.campoNombre.getText() + " "
                        + ventana.campoPassword.getText() );
                autenticarUsuario(ventana.campoNombre.getText(),
                                  ventana.campoPassword.getText());
                 
            }
            //Si por el contrario es un registro, se llama al metodo registrar
            else if(action.equals(ControladorLogin.REGISTRO)){
                System.out.println("El usuario intenta registrarse");
                registrarUsuario(ventana.campoNombre.getText(),
                                  ventana.campoPassword.getText());
            }
                                       
    }
    
    public void autenticarUsuario(String username,String password){
            
        
        Usuario logedon = null;
            
        /*Si el usuario se logea correctamente como admin, se llama al metodo
        que muestre las herramientas de administracion, y se le guarda como
        usuario actual*/
        if(MainController.ADMIN.getName().equals(username)&& 
           MainController.ADMIN.getPass().equals(password)){    
            
                System.out.println("Se ha conectado el administrador");
                showAdminLogon();
                MainController.currentuser = MainController.ADMIN;
        }
                        
        /* Si no se da el caso, se busca una coincidencia en el array de usuarios*/
        else if (!MainController.ADMIN.getName().equals(username)){  
            int check = 0;
            
            for (Usuario u : MainController.usuarios){            
                if (u.getName().equals(username) && u.getPass().equals(password)){
                    check++;
                    logedon = u;
                }           
            }   
            //Si hay una coincidencia, se muestran las herramientas de usuario    
            if(check == 1){ 
                System.out.println("Bienvenido " + username);
                showUserLogon();
                MainController.currentuser = logedon;
            }
            //Si no hay una coincidencia en el array de usuarios
            else{
                System.out.println("La contraseña "
                                        + "o el usuario son incorrectos para: "
                                        + username);
                mc.printUsers();
                ventana.campoNombre.setText(null);
                ventana.campoPassword.setText(null);
                MainController.currentuser = null;
            }
        }
    }
        
    public void registrarUsuario(String username, String password){
        
        int check = 0;
        
        /*NOTA: No reconoce strings iguales como iguales, es por usar ==
        Para comparar dos Strings se DEBE de usar equals.*/
        
        //Se busca el array si hay un usuario que ya exista con ese nombre
        for (Usuario u : MainController.usuarios){            
            if (u.getName().equals(username)){
                check++;
            }
            //hay que asegurarse de que no se puede crear un usuario admin
            if (MainController.ADMIN.getName().equals(username)){
                check++;
            }
        }
       
        //Se ejecuta si el nombre de usuario no esta repetido
        if (check ==0){
            Usuario user = new Usuario(username,password);
            MainController.usuarios.add(user);
            //Eln usuario se crea, se añade, y se anuncia
            System.out.println("El usuario se ha creado");
            ventana.campoNombre.setText(null);
            ventana.campoPassword.setText(null);
            
            //Tras crearlo, se copia el array en el documento XML
            XMLReaderWriter.saveUsuarios(MainController.usuarios);
            
            //Para debugging
            mc.printUsers();            
        } else {
            //Si el usuario existe, se muestra un mensaje por consola
            System.out.println("El usuario ya existe");
        }     
    }
    
    //Esto carga el menu de Admin, y se asegura de cerrar la ventana
    public void showAdminLogon(){
        mc.ventanaControlada.getContentPane().removeAll();
        mc.ventanaControlada.crearMenuAdmin();
        mc.ventanaControlada.crearVista();       
        ventana.dispose();
    }
    
    //Esto carga el emnu de Usuario, y se asegura de cerrar la ventana
    public void showUserLogon(){
        mc.ventanaControlada.getContentPane().removeAll();
        mc.ventanaControlada.crearMenu();
        mc.ventanaControlada.crearVista();       
        ventana.dispose();
    }
    
    public ControladorLogin(VentanaLogin ventana, MainController mc) {
	super();
	this.ventana = ventana;
	this.mc = mc;
    }
    
}
