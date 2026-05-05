package Lab06.ejercicios.ejercicio4;

public class PriorityQueueHybrid<E, V extends Comparable<V>> implements PriorityQueue<E, Integer> {

    // Clase auxiliar para guardar el dato y su valor de desempate en el Nodo
    private class Entry {
        E element;
        V secValue;

        Entry(E element, V secValue) {
            this.element = element;
            this.secValue = secValue;
        }
    }

    private Node<Entry>[] queues;
    private int levels;

    public PriorityQueueHybrid(int levels) {
        this.levels = levels;
        // Creamos el arreglo de Nodos (cabeceras de cada nivel)
        this.queues = new Node[levels];
    }

    public void enqueue(E x, int level, V secValue) {
        if (level < 0 || level >= levels) {
            throw new IllegalArgumentException("Nivel fuera de rango");
        }

        Entry newEntry = new Entry(x, secValue); // Se crea el paquete
        Node<Entry> newNode = new Node<>(newEntry); // Se crea el nodo con el paquete como dato
        Node<Entry> actual = queues[level]; // Se asigna el actual al primer elemento de la cola

        if (actual == null || secValue.compareTo(actual.getData().secValue) < 0) { //compara si está vacío o si el valor es menor que la del primer nodo
            newNode.setNext(actual); //se conecta 
            queues[level] = newNode; //se asigna nuevo first
            return;
        }

        while (actual.getNext() != null && actual.getNext().getData().secValue.compareTo(secValue) <= 0) { // se compara si está en la posición
            actual = actual.getNext();
        }

        newNode.setNext(actual.getNext());
        actual.setNext(newNode);
    }

    @Override
    public void enqueue(E x, Integer pr) {
        throw new UnsupportedOperationException("Use el método enqueue(E x, int level, V secValue) para la cola híbrida.");
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = levels - 1; i >= 0; i--) {
            if (queues[i] != null) {
                E result = queues[i].getData().element;
                queues[i] = queues[i].getNext();
                return result;
            }
        }
        throw new ExceptionIsEmpty("La cola está vacía");
    }

    @Override
    public E first() throws ExceptionIsEmpty {
        for (int i = levels - 1; i >= 0; i--) {
            if (queues[i] != null) {
                return queues[i].getData().element;
            }
        }
        throw new ExceptionIsEmpty("La cola está vacía");
    }

    @Override
    public E last() throws ExceptionIsEmpty {
        for (int i = 0; i < levels; i++) {
            if (queues[i] != null) {
                Node<Entry> actual = queues[i];
                while (actual.getNext() != null) {
                    actual = actual.getNext();
                }
                return actual.getData().element;
            }
        }
        throw new ExceptionIsEmpty("La cola está vacía");
    }
    
    public java.util.List<String> obtenerElementosDeCola(int nivel) {
        java.util.List<String> lista = new java.util.ArrayList<>();
        if (nivel >= 0 && nivel < levels) {
            Node<Entry> actual = queues[nivel];
            while (actual != null) {
                lista.add(actual.getData().element + "(" + actual.getData().secValue + ")");
                actual = actual.getNext();
            }
        }
        return lista;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = levels - 1; i >= 0; i--) {
            sb.append("Nivel ").append(i).append(": ");
            Node<Entry> actual = queues[i];
            if (actual == null) {
                sb.append("vacío");
            } else {
                while (actual != null) {
                    sb.append("(").append(actual.getData().element).append(",")
                      .append(actual.getData().secValue).append(")");
                    if (actual.getNext() != null) sb.append(" -> ");
                    actual = actual.getNext();
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
