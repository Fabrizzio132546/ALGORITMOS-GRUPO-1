package lab08.ejercicios.ejercicio7;

import exceptions.*;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> arbol = new AVLTree<>();

        try {
            System.out.println("--- INSERCIONES Y ROTACIONES ---");
            // 1. Forzar Rotación Simple Izquierda (RSL) insertando 10, 20, 30
            arbol.insert(10);
            arbol.insert(20);
            arbol.insert(30); 
            System.out.println("Tras insertar 10, 20, 30 (Debe aplicar Rotación Simple Izquierda):");
            arbol.preOrderAVL();

            // 2. Forzar Rotación Doble (Derecha-Izquierda) insertando 25
            arbol.insert(40);
            arbol.insert(25);
            System.out.println("Tras insertar 40 y 25 (Debe aplicar Rotación Doble):");
            arbol.preOrderAVL(); 

            System.out.println("\n--- ELIMINACIONES ---");
            // 3. Eliminar un nodo para forzar rebalanceo
            arbol.delete(10);
            System.out.println("Tras eliminar el 10 (Afecta el balance y puede causar rotación):");
            arbol.preOrderAVL();

        } catch (ItemDuplicated | ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}