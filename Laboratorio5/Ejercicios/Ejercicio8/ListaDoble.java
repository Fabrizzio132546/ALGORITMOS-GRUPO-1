package lab05.ejercicio8;

public class ListaDoble<T> {
    protected NodeDoble<T> first; // Apunta al primer elemento
    protected NodeDoble<T> last;  // Apunta al último elemento
    protected int size;           // Lleva la cuenta de cuántos elementos hay
    
    public ListaDoble() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void agregar(T dato) {
        NodeDoble<T> nuevoNodo = new NodeDoble<>(dato);
        
        if (estaVacia()) {
            first = nuevoNodo;
            last = nuevoNodo;
        } else {
            last.setNext(nuevoNodo);
            nuevoNodo.setPrev(last);
            last = nuevoNodo;
        }
        size++;
    }
    
    public void agregarAlInicio(T dato) {
        NodeDoble<T> nuevoNodo = new NodeDoble<>(dato);
        
        if (estaVacia()) {
            first = nuevoNodo;
            last = nuevoNodo;
        } else {
            nuevoNodo.setNext(first);
            first.setPrev(nuevoNodo);
            first = nuevoNodo;
        }
        size++;
    }

    public T eliminarUltimo() {
        if (estaVacia()) return null;
        
        T datoEliminado = last.getDato();
        
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.getPrev();
            last.setNext(null);
        }
        size--;
        return datoEliminado;
    }

    public boolean estaVacia() {
        return first == null;
    }
    
    public int getSize() {
        return size;
    }
    
    public void imprimirLista() {
        NodeDoble<T> actual = first;
        System.out.print("[ ");
        while(actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getNext();
        }
        System.out.println("]");
    }
}
