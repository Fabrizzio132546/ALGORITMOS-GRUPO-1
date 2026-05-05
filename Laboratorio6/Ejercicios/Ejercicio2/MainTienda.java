package ejercicio2;

import actividad1.ExceptionIsEmpty;

public class MainTienda {
    public static void main(String[] args) {
        ColaArreglo tienda = new ColaArreglo(5); // Capacidad fija de 5 clientes

        try {
            // 1. Encola los clientes: 101, 102, 103, 104, 105
            tienda.enqueue(101);
            tienda.enqueue(102);
            tienda.enqueue(103);
            tienda.enqueue(104);
            tienda.enqueue(105);

            // 2. Intenta encolar un cliente más (106) -> debe mostrar Cola llena
            tienda.enqueue(106);

            // 3. Desencola 2 clientes
            System.out.println("Atendiendo cliente: " + tienda.dequeue());
            System.out.println("Atendiendo cliente: " + tienda.dequeue());

            // 4. Muestra el cliente que está al frente
            System.out.println("Cliente en frente: " + tienda.front());

            // 5. Encola 2 clientes más: 106, 107
            tienda.enqueue(106);
            tienda.enqueue(107);

            // 6. Desencola todos los elementos hasta que la cola quede vacía
            while (!tienda.isEmpty()) {
                System.out.println("Atendiendo cliente: " + tienda.dequeue());
            }

            // 7. Intenta desencolar uno más -> debe mostrar Cola vacía
            tienda.dequeue();

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
