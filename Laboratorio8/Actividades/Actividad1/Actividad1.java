package avltree;

import Exceptions.ItemDuplicated;

public class Actividad1 {

    // Método específico creado para resolver la Actividad 1
    public void simularInsercionesYRotaciones() {
        AVLTree<Integer> arbol = new AVLTree<>();
        int[] claves = {30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75};

        System.out.println("=== EJECUTANDO ACTIVIDAD 1: INSERCIÓN Y BALANCEO AVL ===");
        
        try {
            for (int clave : claves) {
                System.out.println("\nInsertando clave: " + clave);
                arbol.insert(clave);
                // Evidenciamos el árbol después de cada inserción/reestructuración
                arbol.drawBST();
            }
            System.out.println("\nRecorrido Inorden final (Debe estar ordenado): " + arbol.getInOrder());
            
        } catch (ItemDuplicated e) {
            System.out.println("Error durante la inserción: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // El main ahora solo instancia la clase y llama al método resolutor
        Actividad1 actividad = new Actividad1();
        actividad.simularInsercionesYRotaciones();
    }
}
