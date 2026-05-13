package lab07.ejercicios.ejercicio4p2;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> arbol = new LinkedBST<>();

        System.out.println("=== 1. CONSTRUCCIÓN DEL ÁRBOL ===");
        try {
            // Construiremos un árbol válido
            int[] valores = {50, 30, 80, 20, 40, 70, 90};
            for (int val : valores) {
                arbol.insert(val);
            }
            System.out.println("Árbol construido correctamente.");
            System.out.println("InOrden (debe estar ordenado): " + arbol.getInOrder());
            
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== 2. VERIFICACIÓN DE PROPIEDADES DEL BST ===");
        
        // Probamos el método nuevo
        boolean esValido = arbol.isValidBST();
        
        if (esValido) {
            System.out.println("RESULTADO: El árbol ES un Árbol Binario de Búsqueda válido (Retornó true).");
            System.out.println("Explicación: Todos los subárboles izquierdos son menores a sus raíces y los derechos son mayores.");
        } else {
            System.out.println("RESULTADO: El árbol NO ES un BST válido (Retornó false).");
        }

        System.out.println("\n=== 3. OTRAS MÉTRICAS ===");
        try {
            System.out.println("Altura    : " + arbol.height());
            System.out.println("Hojas     : " + arbol.countLeaves());
            System.out.println("Mín / Máx : " + arbol.findMin() + " / " + arbol.findMax());
        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
