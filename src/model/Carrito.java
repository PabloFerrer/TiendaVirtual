/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class Carrito {
     
    private ArrayList<Producto> lista = new ArrayList<Producto>();
    static private Carrito singleton = null;
    
    /*Uno de los bugs mas problematicos fue el asegurar que a todos los controladores
    que utilizasen un carrito se les estuviese pasando la misma instancia. Para 
    solucionarlo, ya que necesitabamos que solo hubiese una instancia del carrito,
    usamos un singleton*/
    private Carrito(){
        
    }
            
    static public Carrito getCarrito(){
        if(singleton == null){
            singleton = new Carrito();
        }
        return singleton;
    }
    
    public void limpiarCarrito(){
        lista.clear();
    }
    
    //Recorre el array de productos y devuelve el precio combinado de todos
    public float getPrecio(){
    	float precio = 0;   	
    	for(Producto p : lista){
    		precio+= p.getPrecio();
    		//Debug
    		System.out.println(precio);
    	}
    	return precio;
    }
    
    public ArrayList<Producto> getLista(){
        return lista;
    }
    
    public void addProducto(Producto p){
            lista.add(p);
    }
    
}


