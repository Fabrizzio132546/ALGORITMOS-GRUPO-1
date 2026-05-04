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
        return inicio == null; // verificamos si esta vacia
    }
    
    @Override
    public void enqueue(E x) {
    	// creamos el nuevo nodo
        Node<E> nuevo = new Node<>(x);
        if (isEmpty()) {
        	// si esta vacio decimos que el inicio y el fin son el nuevo
            inicio = nuevo;
            fin = nuevo;
        } else {
        	// sino el siguiente de fun es el nuevo y este pasa a ser el fin
            fin.next = nuevo; 
            fin = nuevo;
        }
    }
    
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("la cola esta vacia");
        }
        // guardamos el dato del inicio
        E d = inicio.data; 
        inicio = inicio.next;  
        // si el inicio es null, osea que ya no hay mas elementos se pone el fin tambien null
        if (inicio == null) {    
            fin = null;
        }
        return d;
    }
    
    public Node<E> getInicio() {
        return inicio;
    }

    public Node<E> getFin() {
        return fin;
    }
    // esteme metodo sirve para poder mandar una lista
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
