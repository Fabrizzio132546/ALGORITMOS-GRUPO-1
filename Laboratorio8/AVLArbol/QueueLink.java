package Queue;

class Node<E> {
    E data;       
    Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
}

public class QueueLink<E> {
    private Node<E> inicio; 
    private Node<E> fin; 
    private int size; // Corrección: Variable para rastrear el tamaño

    public QueueLink() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return inicio == null;
    }
    
    // Corrección: Retorna la cantidad de elementos en la cola
    public int size() {
        return this.size;
    }
    
    public void enqueue(E x) {
        Node<E> nuevo = new Node<>(x);
        
        if (isEmpty()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.next = nuevo; 
            fin = nuevo;
        }
        size++; // Corrección: Incrementar tamaño
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("La cola está vacía");
        }
        E d = inicio.data; 
        inicio = inicio.next;  
        
        if (inicio == null) {    
            fin = null;
        }
        size--; // Corrección: Decrementar tamaño
        return d;
    }
}