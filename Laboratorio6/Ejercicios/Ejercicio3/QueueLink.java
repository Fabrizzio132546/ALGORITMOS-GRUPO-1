package Ejercicio3;

import java.util.ArrayList;
import java.util.List;


class Node<E> {
    E data;       
    Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
}

public class QueueLink<E> implements Queue<E>{
    private Node<E> inicio; 
    private Node<E> fin; 

    public QueueLink() {
        this.inicio = null;
        this.fin = null;
    }
    
    @Override
    public boolean isEmpty() {
        return inicio == null;
    }
    
    @Override
    public void enqueue(E x) {
        Node<E> nuevo = new Node<>(x);
        if (isEmpty()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.next = nuevo; 
            fin = nuevo;
        }
    }
    
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("la cola esta vacia");
        }
        E d = inicio.data; 
        inicio = inicio.next;  
        
        if (inicio == null) {    
            fin = null;
        }
        return d;
    }

    public List<E> getElementosComoLista() {
        List<E> lista = new ArrayList<>();
        Node<E> actual = inicio;
        while (actual != null) {
            lista.add(actual.data);
            actual = actual.next;
        }
        return lista;
    }
}
