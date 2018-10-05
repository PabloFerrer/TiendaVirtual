package model;

/**
 *
 * @author Daniel
 * @author Pablo
 */
public class Producto {


	private String nombre;
        private String descripcion;
        
        /*Coómo. Cómo. CÓMO. Cómo es posible que usar floats en vez de doubles
        haya causado tantisimos problemas. Pablo, que sepas que tenias razón
        
        En resumen, no habia forma de hacer que se imprimiesen con solo dos digitos.
        Usar doubles en su lugar ha solucionado el problema muy rapidamente.
        */
        private double precio;
        
        private int stock;
	
	/**
	 * @param nombre
         * @param descripcion
         * @param precio
         * @param stock
	 */
	public Producto(String nombre,String descripcion, double precio, int stock) {
		this.nombre = nombre;
                this.descripcion = descripcion;
                this.precio = precio;
                this.stock = stock;
	}
        
        //Este metodo se usa para imprimir en el carrito por consola
        @Override
	public String toString() {
            return  nombre + ", cada unidad cuesta " + precio + " €";
	}
        
        public String getName(){
            return nombre;
        }
        
        public double getPrecio(){
            return precio;
        }
	
        public int getStock(){
            return stock;
        }
        
        public String getDescrip(){
            return descripcion;
        }
}
