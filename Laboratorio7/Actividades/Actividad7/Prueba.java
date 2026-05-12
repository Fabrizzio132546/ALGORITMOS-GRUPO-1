package Actividad07;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST arbol = new LinkedBST();

        // Construyendo el árbol 
        // El orden de inserción determina la forma en un BST
        arbol.insert(400); // Raíz
        arbol.insert(100); // Izquierda de 400
        arbol.insert(700); // Derecha de 400
        arbol.insert(50);  // Izquierda de 100
        arbol.insert(200); // Derecha de 100
        arbol.insert(75);  // Derecha de 50

        System.out.println("Salida del recorrido In-Orden (debe ser: 50 75 100 200 400 700):");
        
        // Ejecutando la prueba
        arbol.inOrder();
    }
}
