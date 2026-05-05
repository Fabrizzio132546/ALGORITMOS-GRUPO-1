package actividadPilaLista;
import actividad1.ExceptionIsEmpty;

class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }

    public void push(E x) {
        Node<E> newNode = new Node<>(x);
        // Conecta el nuevo nodo al tope actual y luego lo convierte en el nuevo tope, haciendo que el último elemento ingresado sea el primero en la lista.
        newNode.setNext(this.top);
        this.top = newNode;
    }

    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía.");
        }
        E element = this.top.getData();
        // Obtiene el nodo que está debajo del tope actual y lo convierte en el nuevo tope, eliminando así el primer elemento.
        this.top = this.top.getNext();
        return element;
    }

    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía.");
        }
        // Devuelve el dato almacenado en el nodo 'top', dándonos el valor de la cima sin borrarlo.
        return this.top.getData();
    }

    public boolean isEmpty() {
        // Evalúa si el nodo tope es igual a null, dando como resultado true si no hay elementos.
        return this.top == null;
    }

    public String toString() {
        if (isEmpty()) return "Pila vacía";
        StringBuilder sb = new StringBuilder("Tope -> ");
        Node<E> current = this.top;
        // El bucle evalúa que el nodo actual tenga datos; al hacer current.getNext(), el resultado será el siguiente nodo a imprimir hasta llegar a null.
        while (current != null) {
            sb.append(current.getData()).append("\n        ");
            current = current.getNext();
        }
        return sb.toString().trim();
    }
}
