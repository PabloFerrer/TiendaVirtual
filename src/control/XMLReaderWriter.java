/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Producto;
import model.Usuario;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class XMLReaderWriter {
    
    private static XStream xstream = new XStream(new StaxDriver());
    private static PrintStream ps;
    
    
    /*Este metodo crea un usuarios.xml, en el cual se llamara a una funcion
    de la libreria importada xstream para guardar los usuarios dentro de este,
    sustituyendo todo contenido que hubiese en el*/
    public static void saveUsuarios(ArrayList usuarios) {
        try {
            ps = new PrintStream("usuarios.xml");
            String xml = xstream.toXML(usuarios);
            ps.println(xml);
        } catch (Exception ex) {
            //Si no se puede guardar, por cualquier motivo, hay una excepcion severa
            Logger.getLogger(XMLReaderWriter.class.getName()).log(Level.SEVERE, 
                        null, ex);
        }        
    }
    
    /*Coge el archivo, y guarda sus contenidos en el array de usuarios*/
    public static ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios;
        
        try {
            File f = new File("usuarios.xml");
            usuarios = (ArrayList) xstream.fromXML(f);
            return usuarios;
        } catch (Exception ex) {
            //Si hay una excepcion, se muestra un mensaje
            System.out.println("Hubo un error: "+ex.getMessage());
            return null;
        }
    }
    
    //A partir de aqui, se implementan los mismos metodos, pero para productos
    public static void saveProductos(ArrayList productos) {
        try {
            ps = new PrintStream("productos.xml");
            String xml = xstream.toXML(productos);
            ps.println(xml);
        } catch (Exception ex) {
            Logger.getLogger(XMLReaderWriter.class.getName()).log(Level.SEVERE, 
                        null, ex);
        } 
    }
    
    public static ArrayList<Producto> getProductos() {
        ArrayList<Producto> productos;
        
        try {
            File f = new File("productos.xml");
            productos = (ArrayList) xstream.fromXML(f);
            return productos;
        } catch (Exception ex) {
            System.out.println("Hubo un error: "+ex.getMessage());
            return null;
        }
    }
}
