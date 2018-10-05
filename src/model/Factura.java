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
public class Factura {
    
    private ArrayList<Producto> lista;
    float preciototal;
    
    
    public Factura(ArrayList<Producto> a, float p){
        this.preciototal = p;
        //Copia el array para evitar que al borrar el carrito, se vaya
        //tambien la factura. Gracias, java.
        this.lista = new ArrayList<>(a);
        
    }
    //Este toString() es el que se utiliza para imprimir las facturas en la
    //pantalla y en el .txt
    @Override
    public String toString(){
        String response = new String();
        System.out.println(lista.size());
        response = response.concat("Esta factura es de la compra que contiene: \n");
        for(Producto p : lista){
            response = response.concat(p.toString()+ "\n");
        }
        response = response.concat("Y el precio total es de " + preciototal+ " €\n");
        return response;
    }

    public ArrayList<Producto> getLista() {
        return lista;
    }

    public float getPreciototal() {
        return preciototal;
    }
    
}