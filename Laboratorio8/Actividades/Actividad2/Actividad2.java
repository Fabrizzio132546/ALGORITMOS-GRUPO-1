package lab08.actividades.actividad2;
import avltree.*;
import exceptions.*;

public class Actividad2 {
    public static void main(String[] args) {
        AVLTree<Integer> arbol = new AVLTree<>();

        System.out.println("=== RECREANDO EL ÁRBOL DE LA FIGURA 8.10 ===");
        try {
            int[] nodos = {
                33, 
                20, 45, 
                12, 26, 41, 56, 
                6, 15, 24, 35, 44, 48, 59, 
                17, 38, 46, 53, 65, 
                50
            };
            
            for (int clave : nodos) {
                arbol.insert(clave);
            }
            
            System.out.println("Estado Inicial del Árbol (Debe ser idéntico a la imagen):");
            arbol.drawBST();
            
            int[] aEliminar = {12, 33, 46, 59, 45, 56};
            
            for (int clave : aEliminar) {
                System.out.println("\n========================================");
                System.out.println("-> ELIMINANDO CLAVE: " + clave);
                arbol.delete(clave);
                System.out.println("Árbol resultante tras eliminar " + clave + ":");
                arbol.drawBST();
            }

        } catch (ItemDuplicated | ExceptionIsEmpty e) {
            System.out.println("Error en la ejecución: " + e.getMessage());
        }
    }
}