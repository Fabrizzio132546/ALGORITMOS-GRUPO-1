package lab07.actividades.actividad10;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class Prueba {

    public static void main(String[] args) {

        LinkedBST<Integer> bst = new LinkedBST<>();

        try {

            bst.insert(50);
            bst.insert(30);
            bst.insert(80);
            bst.insert(10);
            bst.insert(40);
            bst.insert(70);
            bst.insert(90);

            System.out.println("BST en InOrder:");
            System.out.println(bst);

            System.out.println("\nMínimo del BST:");
            System.out.println(bst.findMin());

            System.out.println("\nMáximo del BST:");
            System.out.println(bst.findMax());

        }

        catch (ItemDuplicated e) {

            System.out.println("ERROR: " + e.getMessage());

        }

        catch (ItemNotFound e) {

            System.out.println("ERROR: " + e.getMessage());
        }
    }
}