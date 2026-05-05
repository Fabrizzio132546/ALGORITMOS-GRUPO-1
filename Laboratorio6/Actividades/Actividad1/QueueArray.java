package actividad1;

class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public QueueArray(int n) {
        array = (E[]) new Object[n];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(E x) {
        if (isFull()) {
            System.out.println("La cola está llena.");
            return;
        }
        // El módulo (%) divide (rear + 1) entre el tamaño del arreglo y nos da el residuo, ese residuo será el nuevo índice donde se insertará el elemento (siendo 0 si llegamos al tope).
        rear = (rear + 1) % array.length; 
        array[rear] = x;
        size++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        E element = array[front];
        // El módulo (%) divide (front + 1) entre el tamaño del arreglo; el residuo resultante será la nueva posición del frente (retornando a 0 si sobrepasa el límite).
        front = (front + 1) % array.length;
        size--;
        return element;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        return array[front];
    }

    public boolean isEmpty() { return size == 0; }
    
    public boolean isFull() { 
        // Compara el tamaño actual con la longitud del arreglo, el resultado será un booleano (true si están igualados).
        return size == array.length; 
    }

    public String toString() {
        if (isEmpty()) return "Cola vacía";
        StringBuilder sb = new StringBuilder("[");
        int current = front;
        for (int i = 0; i < size; i++) {
            sb.append(array[current]);
            if (i < size - 1) sb.append(", ");
            // El módulo (%) divide (current + 1) entre el tamaño del arreglo; el residuo será el índice del siguiente elemento a leer en la lista.
            current = (current + 1) % array.length;
        }
        sb.append("]");
        return sb.toString();
    }
}
