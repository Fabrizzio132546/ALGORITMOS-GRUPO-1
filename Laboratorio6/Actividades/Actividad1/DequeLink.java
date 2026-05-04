package Actividad2;

class Node<E> {
	private E d;
	private Node<E> next;
	
	public Node(E d) {
		this.d=d;
		this.next = null;
	}
	
	public E getD() {
		return d;
	}
	
	public Node<E> getNext() {
		return next;
	}
	public void setNext(Node<E> next) { 
		this.next = next; }
}

public class DequeLink<E> implements Deque<E>{
	private Node<E> first;
	private Node<E> last;
	public DequeLink() {
		this.first = null;
		this.last = null;
		
	}
	
	public void addFirst(E x) { 
        Node<E> nuevo = new Node<>(x);
        if (isEmpty()) {
            first = nuevo;
            last = nuevo;
        } else {
        	nuevo.setNext(first);
        	first = nuevo;
        }
    }
	
	public void addLast(E x) {
		Node<E> nuevo = new Node<>(x);
		if(isEmpty() ) {
			first = nuevo;
			last = nuevo;
		} else {
			last.setNext(nuevo);
			last = nuevo;
		}
	}
	
	public E removeFirst() throws ExceptionIsEmpty {
		if(isEmpty()) { // verificamos si esta vacio
			throw new ExceptionIsEmpty("La cola esta vacia");
		} 
		E dato = first.getD();
		first = first.getNext();
		// si al eliminar el primero la lista queda vacia, se pone null a last 
		if(first == null) { 
			last = null;
		}
		return dato;
	}
	
	public E removeLast() throws ExceptionIsEmpty {
		if(isEmpty()) {
			throw new ExceptionIsEmpty("la cola esta vacia");
		}
		E dato = last.getD();
		// en caso q solo haya un elemento se coloca null al inicio y final
		if( first == last) {
			first = null;
			last = null;
		} else {
			Node<E> actual = first;
			// debemos buscar el penultimo elemento
			while (actual.getNext() != last) {
				actual = actual.getNext();
			}
			// cambiamos el siguiente del penultimo
			actual.setNext(null);
			// el penultimo pasa a ser el ultimo
			last = actual;
		}
		// se retorna el ultimo q se elimino
		return dato;
	}
	
	public E getFirst() throws ExceptionIsEmpty { 
		// sirve para obtener el primer nodo
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El Deque está vacío.");
        }
        return first.getD();
    }
	
	public E getLast() throws ExceptionIsEmpty { 
		// sirve apra obtener el ultimo nodo
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El Deque está vacío.");
        }
        return last.getD();
    }
	
	public boolean isEmpty() {
		// tambien puede estar vacio cuando last esta null
        return first == null;
    }
	
	public String toString() { 
        StringBuilder sb = new StringBuilder();
        Node<E> actual = first;
        sb.append("[");
        while (actual != null) {
        	// agregamos el dato del nodo actual
            sb.append(actual.getD());
            // se verifica si el siguiente es null
            if (actual.getNext() != null) {
            	// si no es null se coloca coma
                sb.append(", ");
            }
            actual = actual.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
	
}
