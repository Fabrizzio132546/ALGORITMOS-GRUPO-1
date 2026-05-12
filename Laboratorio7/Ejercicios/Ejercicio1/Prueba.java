package lab07.ejercicios.ejercicio1;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> arbol = new LinkedBST<>();

        System.out.println("=== a) CONSTRUCCIÓN DEL ÁRBOL ===");
        try {
            int[] valores = {15, 8, 22, 5, 12, 18, 30};
            for (int val : valores) {
                arbol.insert(val);
                System.out.print(val + " insertado. ");
            }
            System.out.println("\nÁrbol construido correctamente.");
        } catch (ItemDuplicated e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }

        System.out.println("\n=== b) RECORRIDOS ===");
        System.out.println("InOrden   : " + arbol.getInOrder());
        System.out.println("PreOrden  : " + arbol.getPreOrder());
        System.out.println("PostOrden : " + arbol.getPostOrder());

        System.out.println("\n=== c) BÚSQUEDAS ===");
        
        try {
            System.out.print("Buscando 12... ");
            Integer resultado = arbol.search(12);
            System.out.println("¡Éxito! Se encontró el nodo con valor " + resultado);
        } catch (ItemNotFound e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        try {
            System.out.print("Buscando 21... ");
            Integer resultado = arbol.search(21);
            System.out.println("¡Éxito! Se encontró el nodo con valor " + resultado);
        } catch (ItemNotFound e) {
            System.out.println("\nLo que ocurre: El programa lanza una excepción porque la recursión llega a un nodo nulo.");
            System.out.println("Mensaje de la excepción capturada: " + e.getMessage());
        }

        System.out.println("\n=== d) ANÁLISIS BÁSICO ===");
        try {
            System.out.println("1. Altura del árbol : " + arbol.height());
            System.out.println("2. Nodos hoja       : " + arbol.countLeaves());
            System.out.println("3. Nodo Mínimo      : " + arbol.findMin());
            System.out.println("   Nodo Máximo      : " + arbol.findMax());
        } catch (ItemNotFound e) {
            System.out.println("Error al calcular min/max: " + e.getMessage());
        }
    }
}