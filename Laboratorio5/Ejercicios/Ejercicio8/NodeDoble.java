package practicapura.listas;

public class NodeDoble<T> {
	// Cada nodo guarda tres cosas:
	private T dato;              // 1. La información (en este caso, una Canción)
	private NodeDoble<T> next;   // 2. La flecha que apunta al nodo SIGUIENTE
	private NodeDoble<T> prev;   // 3. La flecha que apunta al nodo ANTERIOR
	
	// Constructor: Crea un nodo "suelto" con su dato, pero sin conectar a nada aún
	public NodeDoble(T d) {
		this.dato = d;
		this.next = null;
		this.prev = null;
	}
	
	// --- Métodos Getters y Setters (Para leer o cambiar los datos y las flechas) ---

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodeDoble<T> getNext() {
        return next;
    }

    // Conecta este nodo con el que va adelante
    public void setNext(NodeDoble<T> siguiente) {
        this.next = siguiente;
    }
    
    public NodeDoble<T> getPrev() {
        return prev;
    }

    // Conecta este nodo con el que va atrás
    public void setPrev(NodeDoble<T> previo) {
        this.prev = previo;
    }
}