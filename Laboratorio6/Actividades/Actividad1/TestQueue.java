package actividad1;

public class TestQueue {
    public static void main(String[] args) {
        try {
            System.out.println("--- Prueba con Integer ---");
            QueueArray<Integer> queueInt = new QueueArray<>(3);
            queueInt.enqueue(10);
            queueInt.enqueue(20);
            queueInt.enqueue(30);
            System.out.println("Cola entera: " + queueInt.toString());
            System.out.println("Dequeue: " + queueInt.dequeue());
            System.out.println("Cola entera tras dequeue: " + queueInt.toString());

            System.out.println("\n--- Prueba con String ---");
            QueueArray<String> queueStr = new QueueArray<>(2);
            queueStr.enqueue("Hola");
            queueStr.enqueue("Mundo");
            System.out.println("Cola string: " + queueStr.toString());
            System.out.println("Frente de la cola: " + queueStr.front());
            
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
