package Lab06.ejercicios.ejercicio4;

public interface PriorityQueue<E, N> {
    void enqueue(E x, N pr);
    E dequeue() throws ExceptionIsEmpty;
    E first() throws ExceptionIsEmpty;
    E last() throws ExceptionIsEmpty;
}
