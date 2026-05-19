package lab08.implementaciones;

import exceptions.*;

public class ClinicaTurnos {
    public static void main(String[] args) {
        // Cada turno se representa con un número entero
        AVLTree<Integer> turnos = new AVLTree<>();

        try {
            System.out.println("--- INSERTANDO TURNOS NUEVOS ---");
            turnos.insert(10);
            turnos.insert(20);
            turnos.insert(30);
            System.out.println("Turnos en espera (InOrden): " + turnos.getInOrder());
            
            // Para ver la estructura y saber cuál es la raíz
            System.out.print("Estructura en PreOrden (Raíz primero): ");
            turnos.preOrderAVL(); 

            System.out.println("\n--- BUSCANDO UN TURNO ---");
            Integer turnoBuscado = turnos.search(20);
            System.out.println("Turno verificado en sistema: " + turnoBuscado);

            System.out.println("\n--- ELIMINANDO TURNO ATENDIDO ---");
            turnos.delete(10); // El paciente con turno 10 ya fue atendido
            System.out.println("Turnos restantes tras atención (InOrden): " + turnos.getInOrder());

        } catch (ItemDuplicated | ExceptionIsEmpty e) {
            System.out.println("Error de operación: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.out.println("Error: El turno buscado no existe.");
        }
    }
}