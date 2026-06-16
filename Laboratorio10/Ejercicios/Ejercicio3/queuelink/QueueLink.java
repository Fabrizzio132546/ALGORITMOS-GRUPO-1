package queuelink;

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
    private int size; 

    public QueueLink() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return inicio == null;
    }

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
        size++; 
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
        size--; 
        return d;
    }
}














