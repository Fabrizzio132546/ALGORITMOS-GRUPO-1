package Ejercicio4_1;

import Actividad4.*;
public class PruebaParenthesize {
    public static void main(String[] args) {
        LinkedBST<Integer> arbol = new LinkedBST<>();

        try {

            arbol.insert(400); 
            arbol.insert(100); 
            arbol.insert(700); 
            arbol.insert(50);  
            arbol.insert(200);
            arbol.insert(75); 

            System.out.println("Visualizacion con parentesis: ");
            arbol.parenthesize(); 

        } catch (ItemDuplicated e) {
            System.err.println("Error de insercion: " + e.getMessage());
        }
    }
}
