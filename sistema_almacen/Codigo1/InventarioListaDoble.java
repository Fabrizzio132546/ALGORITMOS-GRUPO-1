public class InventarioListaDoble {
    private Nodo cabeza;
    private Nodo cola;

    public void agregarAlFinal(Producto p) {
        Nodo nuevo = new Nodo(p);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    public Nodo getCabeza() { 
    	return cabeza; 
    }
    
    public boolean estaVacio() { 
    	return cabeza == null; 
    }
}
