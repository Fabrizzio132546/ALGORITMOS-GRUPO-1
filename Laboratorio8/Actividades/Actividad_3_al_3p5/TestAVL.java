package avltree;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class TestAVL {
	public static void main(String[] args) {
		
		
        System.out.println(" INICIANDO PRUEBAS DE ARBOL AVL ");

        try {
            // 1. Inserción sin rotación
            System.out.println("\n--------------------------------------------------");
            System.out.println("1. Prueba: Inserción sin rotación (Mantiene equilibrio)");
            AVLTree<Integer> arbol1 = new AVLTree<>();
            arbol1.insert(20);
            arbol1.insert(10);
            arbol1.insert(30);
            arbol1.drawBST();
            System.out.println("Inorden: " + arbol1.getInOrder());

            // 2. Inserción con Rotación Simple Derecha (RSR) - Caso Izquierda-Izquierda
            System.out.println("\n--------------------------------------------------");
            System.out.println("2. Prueba: Rotación Simple Derecha (RSR)");
            System.out.println("Insertando: 30, 20, 10 (Provoca desbalance en 30)");
            AVLTree<Integer> arbol2 = new AVLTree<>();
            arbol2.insert(30);
            arbol2.insert(20);
            arbol2.insert(10); 
            arbol2.drawBST();
            System.out.println("Inorden: " + arbol2.getInOrder());

            // 3. Inserción con Rotación Simple Izquierda (RSL) - Caso Derecha-Derecha
            System.out.println("\n--------------------------------------------------");
            System.out.println("3. Prueba: Rotación Simple Izquierda (RSL)");
            System.out.println("Insertando: 10, 20, 30 (Provoca desbalance en 10)");
            AVLTree<Integer> arbol3 = new AVLTree<>();
            arbol3.insert(10);
            arbol3.insert(20);
            arbol3.insert(30); 
            arbol3.drawBST();
            System.out.println("Inorden: " + arbol3.getInOrder());

            // 4. Inserción con Rotación Doble Derecha (RDR) - Caso Izquierda-Derecha
            System.out.println("\n--------------------------------------------------");
            System.out.println("4. Prueba: Rotación Doble Derecha (RDR)");
            System.out.println("Insertando: 30, 10, 20 (Provoca RSL en 10 y RSR en 30)");
            AVLTree<Integer> arbol4 = new AVLTree<>();
            arbol4.insert(30);
            arbol4.insert(10);
            arbol4.insert(20); 
            arbol4.drawBST();
            System.out.println("Inorden: " + arbol4.getInOrder());

            // 5. Inserción con Rotación Doble Izquierda (RDL) - Caso Derecha-Izquierda
            System.out.println("\n--------------------------------------------------");
            System.out.println("5. Prueba: Rotación Doble Izquierda (RDL)");
            System.out.println("Insertando: 10, 30, 20 (Provoca RSR en 30 y RSL en 10)");
            AVLTree<Integer> arbol5 = new AVLTree<>();
            arbol5.insert(10);
            arbol5.insert(30);
            arbol5.insert(20); 
            arbol5.drawBST();
            System.out.println("Inorden: " + arbol5.getInOrder());

         // ==================================================
            //               PRUEBAS DE ELIMINACIÓN             
            // ==================================================
            
            AVLTree<Integer> arbol6 = new AVLTree<>();
            // Insertamos un árbol controlado
            int[] datos = {40, 20, 75, 10, 30, 60, 80}; 
            for(int d : datos) arbol6.insert(d);
            
            System.out.println("\nÁrbol inicial para eliminaciones básicas:");
            arbol6.drawBST();
            
            // 6. Eliminación de nodo hoja
            System.out.println("\n--- 6. Prueba: Eliminación de nodo hoja (10) ---");
            arbol6.delete(10);
            arbol6.drawBST();
            
            // 7. Eliminación de nodo con un hijo
            System.out.println("\n--- 7. Prueba: Eliminación de nodo con 1 hijo (20) ---");
            // Al borrar el 10, el 20 se quedó solo con el hijo 30.
            arbol6.delete(20);
            arbol6.drawBST();
            
            // 8. Eliminación de nodo con dos hijos
            System.out.println("\n--- 8. Prueba: Eliminación de nodo con 2 hijos (75) ---");
            // El 75 tiene dos hijos: 60 y 80. Probará el uso del sucesor/antecesor inorden.
            arbol6.delete(75); 
            arbol6.drawBST();
            System.out.println("Inorden actual: " + arbol6.getInOrder());

            System.out.println("\n--------------------------------------------------");
            System.out.println("NUEVO ESCENARIO: Eliminaciones con Rebalanceo");
            AVLTree<Integer> arbol7 = new AVLTree<>();
            int[] datosRotacion = {50, 30, 70, 20, 40, 60, 80, 10};
            for(int d : datosRotacion) arbol7.insert(d);
            
            System.out.println("\nÁrbol antes de provocar desbalance:");
            arbol7.drawBST();
            
            // 9. Eliminación con rotación
            System.out.println("\n--- 9. Prueba: Eliminación con rotación ---");
            System.out.println("Eliminando 40: Provocará desbalance en el nodo 30 y aplicará rotación.");
            arbol7.delete(40);
            arbol7.drawBST();
            
            // 10. Eliminación con más de una rotación
            System.out.println("\n--- 10. Prueba: Eliminación con más de una rotación ---");
            System.out.println("Eliminando 60 y luego 80 para forzar un rebalanceo en cadena hacia la raíz:");
            arbol7.delete(60);
            arbol7.delete(80);
            arbol7.drawBST();
            
            System.out.println("\nInorden final de todo el proceso: " + arbol7.getInOrder());

        } catch (Exception e) {
            System.out.println("Error durante la ejecución: " + e.getMessage());
            e.printStackTrace(); // Esto te ayudará a ver en qué línea exacta falló si hay un error
        }
    
        
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        
        
        try {
            System.out.println("==================================================");
            System.out.println("EJERCICIO 2: COMPARACIÓN ENTRE BST TRADICIONAL Y AVL");
            System.out.println("==================================================");
            
            // Vamos a insertar datos ordenados, el peor caso para un BST
            int[] datos = {10, 20, 30, 40, 50, 60, 70};
            
            LinkedBST<Integer> bst = new LinkedBST<>();
            AVLTree<Integer> avl = new AVLTree<>();
            
            for (int dato : datos) {
                bst.insert(dato);
                avl.insert(dato);
            }
            
            System.out.println("--- ÁRBOL BST TRADICIONAL ---");
            System.out.println("Se degrada a una lista enlazada (Desbalanceado):");
            bst.drawBST();
            System.out.println("Altura del BST: " + bst.height(10)); 
            System.out.println("Inorden: " + bst.getInOrder());
            
            System.out.println("\n--- ÁRBOL AVL ---");
            System.out.println("Se mantiene balanceado mediante rotaciones:");
            avl.drawBST();
            System.out.println("Altura del AVL: " + avl.height(40)); 
            System.out.println("Inorden: " + avl.getInOrder());
            
            // Búsquedas comparativas
            System.out.println("\nBuscando el 70 en BST... Encontrado: " + bst.search(70));
            System.out.println("Buscando el 70 en AVL... Encontrado: " + avl.search(70));

        } catch (ItemDuplicated e) {
            System.err.println("Error de duplicado en Ejercicio 2: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.err.println("Error de búsqueda en Ejercicio 2: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en Ejercicio 2: " + e.getMessage());
        }
        
        try {
            System.out.println("\n==================================================");
            System.out.println("EJERCICIO 3: ELIMINACIÓN EN UN ÁRBOL AVL");
            System.out.println("==================================================");
            
            AVLTree<Integer> avlDel = new AVLTree<>();
            int[] insertados = {40, 20, 75, 10, 30, 50, 80, 26, 90};
            for (int i : insertados) avlDel.insert(i);
            
            System.out.println("Estado inicial del árbol:");
            avlDel.drawBST();
            
            System.out.println("\n1. Eliminando 26 (Caso 1: Nodo Hoja) ...");
            avlDel.delete(26);
            avlDel.drawBST();
            
            System.out.println("\n2. Eliminando 30 (Caso 2: Nodo con 1 hijo) ...");
            avlDel.delete(30);
            avlDel.drawBST();
            
            System.out.println("\n3. Eliminando 75 (Caso 3: Nodo con 2 hijos, usa sucesor) ...");
            avlDel.delete(75);
            avlDel.drawBST();

        } catch (ItemDuplicated e) {
            System.err.println("Error de duplicado en Ejercicio 3: " + e.getMessage());
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error de árbol vacío en Ejercicio 3: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en Ejercicio 3: " + e.getMessage());
        }

        try {
            System.out.println("\n==================================================");
            System.out.println("EJERCICIO 4: RECORRIDO POR AMPLITUD RECURSIVO");
            System.out.println("==================================================");
            
            AVLTree<Integer> avlAmp = new AVLTree<>();
            int[] arrAmp = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
            for (int i : arrAmp) avlAmp.insert(i);
            
            System.out.println("Árbol para recorrido:");
            avlAmp.drawBST();
            
            System.out.println("\nRecorrido Inorden (Para comparar):");
            System.out.println(avlAmp.getInOrder());
            
            System.out.println("\nRecorrido Preorden (Para comparar):");
            System.out.println(avlAmp.getPreOrder());
            
            System.out.println("\nRecorrido por Amplitud (Recursivo) - Por Niveles:");
            avlAmp.recorridoAmplitudRecursivo();

        } catch (ItemDuplicated e) {
            System.err.println("Error de duplicado en Ejercicio 4: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en Ejercicio 4: " + e.getMessage());
        }
    }
}