package ejercicio2;
import actividad1.ExceptionIsEmpty;

public class ColaArreglo {
    private int[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ColaArreglo(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(int id) {
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        // El módulo (%) divide (rear + 1) entre la capacidad máxima (5); el residuo será el índice exacto para colocar al nuevo cliente sin salir del arreglo.
        rear = (rear + 1) % capacity;
        array[rear] = id;
        size++;
    }

    public int dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        int id = array[front];
        // El módulo (%) divide (front + 1) entre la capacidad máxima (5); el residuo será el nuevo índice del frente, indicando quién es el siguiente cliente en la fila.
        front = (front + 1) % capacity;
        size--;
        return id;
    }

    public int front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacía");
        }
        return array[front];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }
    
 // Método para la interfaz gráfica: retorna los elementos como una lista estándar
    public java.util.List<Integer> getElementosComoLista() {
        java.util.List<Integer> lista = new java.util.ArrayList<>();
        int current = front;
        for (int i = 0; i < size; i++) {
            lista.add(array[current]);
            current = (current + 1) % capacity;
        }
        return lista;
    }
}
