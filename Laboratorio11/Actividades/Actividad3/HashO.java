package hash;

import listlinked.ListLinked;
import listlinked.Node;

public class HashO<E> {

    private ListLinked<Register<E>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("El tamaño debe ser mayor que cero.");
        }

        this.size = size;
        this.table = (ListLinked<Register<E>>[]) new ListLinked[size];

        for (int i = 0; i < size; i++) {
            table[i] = new ListLinked<>();
        }
    }

    private int hash(int key) {
        return Math.floorMod(key, size);
    }

    public void insert(Register<E> reg) {
        if (reg == null) {
            return;
        }

        int pos = hash(reg.getKey());

        Node<Register<E>> actual = table[pos].getFirst();

        while (actual != null) {
            Register<E> current = actual.getData();

            if (current.getKey() == reg.getKey()) {
                actual.setData(reg);
                return;
            }

            actual = actual.getNext();
        }

        table[pos].addLast(reg);
    }

    public Register<E> search(int key) {
        int pos = hash(key);

        Node<Register<E>> actual = table[pos].getFirst();

        while (actual != null) {
            Register<E> current = actual.getData();

            if (current.getKey() == key) {
                return current;
            }

            actual = actual.getNext();
        }

        return null;
    }

    public void delete(int key) {
        int pos = hash(key);

        Node<Register<E>> actual = table[pos].getFirst();

        while (actual != null) {
            Register<E> current = actual.getData();

            if (current.getKey() == key) {
                table[pos].removeNode(current);
                return;
            }

            actual = actual.getNext();
        }
    }

    public void printTable() {
        for (int pos = 0; pos < size; pos++) {
            System.out.print(pos + ": ");

            if (table[pos].isEmpty()) {
                System.out.println("vacio");
            } else {
                Node<Register<E>> actual = table[pos].getFirst();

                while (actual != null) {
                    System.out.print(actual.getData() + " -> ");
                    actual = actual.getNext();
                }

                System.out.println("null");
            }
        }
    }
    
    
    
    public int[] obtenerPosicionYNodo(int key) {
        int pos = hash(key);

        Node<Register<E>> actual = table[pos].getFirst();
        int numeroNodo = 0;

        while (actual != null) {
            Register<E> current = actual.getData();

            if (current.getKey() == key) {
                return new int[]{pos, numeroNodo};
            }

            actual = actual.getNext();
            numeroNodo++;
        }

        return null;
    }
    
    
    public int contarNodosEnCadena(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IllegalArgumentException("Índice fuera de rango.");
        }

        int contador = 0;
        Node<Register<E>> actual = table[pos].getFirst();

        while (actual != null) {
            contador++;
            actual = actual.getNext();
        }

        return contador;
    }
    
}








