package actividadPilaLista;

import actividad1.ExceptionIsEmpty;

public class TestStack {
    public static void main(String[] args) {
        // Instanciamos la pila indicando que almacenará cadenas de texto (String)
        StackLink<String> pilaTextos = new StackLink<>();

        try {
            System.out.println("--- Agregando elementos a la pila (PUSH) ---");
            pilaTextos.push("Página Web 1");
            pilaTextos.push("Página Web 2");
            pilaTextos.push("Página Web 3");
            
            // Mostramos la pila (debería mostrar Página Web 3 en el tope)
            System.out.println(pilaTextos.toString());

            System.out.println("\n--- Consultando el tope (TOP) ---");
            // top() solo muestra el elemento sin sacarlo de la pila
            System.out.println("El elemento en la cima es: " + pilaTextos.top());

            System.out.println("\n--- Sacando elementos de la pila (POP) ---");
            // pop() saca el último elemento que entró (LIFO)
            System.out.println("Se extrajo: " + pilaTextos.pop());
            System.out.println("Se extrajo: " + pilaTextos.pop());

            System.out.println("\n--- Estado actual de la pila ---");
            System.out.println(pilaTextos.toString());

            System.out.println("\n--- Vaciando la pila ---");
            System.out.println("Se extrajo: " + pilaTextos.pop());
            
            // Verificamos si quedó vacía
            System.out.println("¿La pila está vacía? " + pilaTextos.isEmpty());

            System.out.println("\n--- Forzando la excepción ---");
            // Al intentar hacer pop en una pila vacía, saltará la excepción
            pilaTextos.pop();

        } catch (ExceptionIsEmpty e) {
            // Aquí capturamos el error y mostramos el mensaje de la excepción
            System.out.println("Error: " + e.getMessage());
        }
    }
}
