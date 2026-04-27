package Ejercicios1_6;

class Node<T> {
    T d;
    Node<T> next;

    public Node(T d) {
        this.d = d;
        this.next = null;
    }
}

public class ListLinked<T extends Comparable<T>> {
    private Node<T> first;

    public ListLinked() {
        this.first = null;
    }
    
    public Node<T> getFirst() {
    	return this.first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(T d) {
        Node<T> nuevo = new Node<>(d);
        if (!isEmpty()) {
            nuevo.next = first;
        }
        first = nuevo;
    }

    public void insertLast(T d) {
        Node<T> nuevo = new Node<>(d);
        if (isEmpty()) {
            first = nuevo;
        } else {
            Node<T> actual = first;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
    }

    public boolean Buscar(T dato) {
        if (isEmpty()) {
            return false;
        }
        Node<T> actual = first;
        while (actual.next != null && actual.d.compareTo(dato) != 0) {
            actual = actual.next;
        }
        return actual.d.compareTo(dato) == 0;
    }

    public boolean eliminar(T dato) {
        if (isEmpty()) {
            return false;
        }
        if (first.d.compareTo(dato) == 0) {
            first = first.next;
            return true;
        }
        Node<T> actual = first;
        while (actual.next != null && actual.next.d.compareTo(dato) != 0) {
            actual = actual.next;
        }
        if (actual.next == null) {
            return false;
        }
        actual.next = actual.next.next;
        return true;
    }
    
    public void insertInOrder(T d) {
        if (isEmpty() || d.compareTo(first.d) < 0) {
            insertFirst(d);
        } else {
            Node<T> actual = first;
            
            while (actual.next != null && actual.next.d.compareTo(d) < 0) {
                actual = actual.next;
            }
            Node<T> nuevo = new Node<>(d);
            nuevo.next = actual.next;
            actual.next = nuevo;
        }
    }
    
    public void print() {
        Node<T> actual = this.first;

        System.out.print("[");
        while (actual != null) {
            System.out.print(actual.d);
            if (actual.next != null) {
                System.out.print(", ");
            }
            actual = actual.next;
        }
        System.out.println("]");
    }
}
