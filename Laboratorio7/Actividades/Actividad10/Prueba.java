package lab07.actividades.actividad10;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class Prueba {

    public static void main(String[] args) {

        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            // Construimos un árbol balanceado:
            //       50
            //     /    \
            //   30      80
            //  /  \    /  \
            // 10  40  70  90
            bst.insert(50);
            bst.insert(30);
            bst.insert(80);
            bst.insert(10);
            bst.insert(40);
            bst.insert(70);
            bst.insert(90);

            // Al ser In-Orden, imprimirá: 10 30 40 50 70 80 90
            System.out.println("BST en InOrder:");
            System.out.println(bst);

            // Búsqueda del extremo izquierdo
            System.out.println("\nMínimo del BST:");
            System.out.println(bst.findMin()); // Debería ser 10

            // Búsqueda del extremo derecho
            System.out.println("\nMáximo del BST:");
            System.out.println(bst.findMax()); // Debería ser 90

        }
        // Atrapamos ambas excepciones controladas
        catch (ItemDuplicated e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        catch (ItemNotFound e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
