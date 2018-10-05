/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.MainController;
import java.util.ArrayList;
import control.XMLReaderWriter;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class Usuario {
    
    private String username;
    private String password;
    private ArrayList<Factura> facturas;
    
    public Usuario(String username, String password){       
        this.username = username;
        this.password = password;
        facturas = new ArrayList<>();
    }
       
    public String getName(){
        return this.username;
    }
    
    public String getPass(){
        return this.password;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void addFactura(Factura f) {
        this.facturas.add(f);
        XMLReaderWriter.saveUsuarios(MainController.usuarios);
    }
    
    //Pena que no sea tan facil en la vida, eh?
    public void deleteFacturas(){
        this.facturas.clear();
        XMLReaderWriter.saveUsuarios(MainController.usuarios);
    }
}
