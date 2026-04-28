package lab05.ejercicio8;
public class NodeDoble<T> {
	private T dato;              // 1. La información
	private NodeDoble<T> next;   // 2. La flecha que apunta al nodo SIGUIENTE
	private NodeDoble<T> prev;   // 3. La flecha que apunta al nodo ANTERIOR
	
	public NodeDoble(T d) {
		this.dato = d;
		this.next = null;
		this.prev = null;
	}

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