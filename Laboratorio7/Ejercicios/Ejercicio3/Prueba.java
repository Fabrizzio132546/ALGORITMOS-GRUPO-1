package Ejercicio03;

public class Prueba {
    
    // Método que retorna si dos árboles binarios diferentes tienen la misma área
    public static boolean sameArea(LinkedBST arbol1, LinkedBST arbol2) {
        return arbol1.areaBST() == arbol2.areaBST();
    }

    public static void main(String[] args) {
        // Árbol 1
        LinkedBST arbol1 = new LinkedBST();
        int[] valores1 = {15, 8, 22, 5, 12, 18, 30};
        for (int valor : valores1) {
            arbol1.insert(valor);
        }
        
        // Primer árbol
        System.out.println("--- ÁRBOL 1 ---");
        arbol1.drawBST();
        System.out.println("Área del Árbol 1: " + arbol1.areaBST()); 
        // Explicación: Altura 2, Hojas 4 (5, 12, 18, 30) -> Área = 4 * 2 = 8

        // Segundo árbol: Un árbol diferente para comparar
        LinkedBST arbol2 = new LinkedBST();
        int[] valores2 = {50, 25, 75, 10, 30, 60, 90}; // Estructura similar pero datos diferentes
        for (int valor : valores2) {
            arbol2.insert(valor);
        }

        System.out.println("\n--- ÁRBOL 2 ---");
        arbol2.drawBST();
        System.out.println("Área del Árbol 2: " + arbol2.areaBST());

        // Tercer árbol: Otro árbol de diferente forma y tamaño
        LinkedBST arbol3 = new LinkedBST();
        arbol3.insert(10);
        arbol3.insert(5);
        arbol3.insert(1); // Altura 2, hoja 1 -> Área 2

        System.out.println("\n--- COMPARACIONES (sameArea) ---");
        System.out.println("¿Árbol 1 y Árbol 2 tienen la misma área? : " + sameArea(arbol1, arbol2)); // true
        System.out.println("¿Árbol 1 y Árbol 3 tienen la misma área? : " + sameArea(arbol1, arbol3)); // false
    }
}
