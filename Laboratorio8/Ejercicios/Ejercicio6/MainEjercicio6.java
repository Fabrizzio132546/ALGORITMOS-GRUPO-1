package lab08.ejercicios.ejercicio6;

import exceptions.*;

public class MainEjercicio6 {
    public static void main(String[] args) {
        try {
            System.out.println("=== EJERCICIO 6: VERIFICACIÓN DE RECORRIDO PREORDEN EN ÁRBOLES AVL ===\n");

            // 1. Caso de Rotación Simple a la Izquierda (Inserción ascendente)
            System.out.println("1. Secuencia: 10, 20, 30 (Fuerza Rotación Simple Izquierda)");
            AVLTree<Integer> arbol1 = new AVLTree<>();
            arbol1.insert(10);
            arbol1.insert(20);
            arbol1.insert(30);
            // Si no rotara, la raíz sería 10. Como rota, la nueva raíz debe ser 20.
            arbol1.preOrderAVL(); 
            System.out.println();

            // 2. Caso de Rotación Simple a la Derecha (Inserción descendente)
            System.out.println("2. Secuencia: 30, 20, 10 (Fuerza Rotación Simple Derecha)");
            AVLTree<Integer> arbol2 = new AVLTree<>();
            arbol2.insert(30);
            arbol2.insert(20);
            arbol2.insert(10);
            // Si no rotara, la raíz sería 30. Como rota, la nueva raíz debe ser 20.
            arbol2.preOrderAVL();
            System.out.println();

            // 3. Caso de Rotación Doble Izquierda-Derecha (Forma de "<")
            System.out.println("3. Secuencia: 30, 10, 20 (Fuerza Rotación Doble Izquierda-Derecha)");
            AVLTree<Integer> arbol3 = new AVLTree<>();
            arbol3.insert(30);
            arbol3.insert(10);
            arbol3.insert(20);
            // Primero el 20 sube a la izquierda del 30, luego el 20 sube a la raíz.
            arbol3.preOrderAVL();
            System.out.println();

            // 4. Caso de Rotación Doble Derecha-Izquierda (Forma de ">")
            System.out.println("4. Secuencia: 10, 30, 20 (Fuerza Rotación Doble Derecha-Izquierda)");
            AVLTree<Integer> arbol4 = new AVLTree<>();
            arbol4.insert(10);
            arbol4.insert(30);
            arbol4.insert(20);
            // Primero el 20 sube a la derecha del 10, luego el 20 sube a la raíz.
            arbol4.preOrderAVL();
            System.out.println();

            System.out.println("Nota: En los 4 casos, el árbol resultante debe estar perfectamente balanceado.");
            System.out.println("El preorden esperado en todos es: 20(bf=0) 10(bf=0) 30(bf=0)");

        } catch (ItemDuplicated e) {
            System.out.println("Error de inserción: " + e.getMessage());
        }
    }
}
