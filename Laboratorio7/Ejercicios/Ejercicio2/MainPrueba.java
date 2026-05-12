package Ejercicio02;

public class MainPrueba {
    public static void main(String[] args) {
        LinkedBST arbol = new LinkedBST();

        // Insertar secuencia 15, 8, 22, 5, 12, 18, 30
        int[] valores = {15, 8, 22, 5, 12, 18, 30};
        for (int valor : valores) {
            arbol.insert(valor);
        }
        /*
          El árbol se observaria así:
                     15
                   /    \
                  8      22
                 / \    /  \
                5  12  18  30
        */

        System.out.println("b. Total de nodos (countAllNodes): " + arbol.countAllNodes()); // Debe ser 7
        System.out.println("c. Nodos no-hojas (countNodes): " + arbol.countNodes());       // Debe ser 3 (15, 8, 22)
        
        System.out.println("d. Altura del nodo 15 (height(15)): " + arbol.height(15)); // Debe ser 2
        System.out.println("d. Altura del nodo 8 (height(8)): " + arbol.height(8));   // Debe ser 1
        System.out.println("d. Altura de nodo inexistente (height(99)): " + arbol.height(99)); // Debe ser -1

        System.out.println("e. Amplitud máxima del árbol (amplitude()): " + arbol.amplitude()); // Debe ser 4 (nodos 5, 12, 18, 30)

        // a. Probar destroyNodes y ExceptionIsEmpty
        try {
            arbol.destroyNodes();
            // Si intentamos destruirlo de nuevo, lanzará la excepción
            arbol.destroyNodes(); 
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}
