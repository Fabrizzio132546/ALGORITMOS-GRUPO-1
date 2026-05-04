package Ejercicio3;

public interface Queue<E> {
	void enqueue(E x);
	E dequeue() throws ExceptionIsEmpty;
	boolean isEmpty();
}
