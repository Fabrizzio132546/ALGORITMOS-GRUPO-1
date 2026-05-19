
public class TestAVL {
	public static void main(String[] args) {
        try {
            // Vamos a insertar datos ordenados, el peor caso para un BST
            int[] datos = {10, 20, 30, 40, 50, 60, 70};
            
            LinkedBST<Integer> bst = new LinkedBST<>();
            AVLTree<Integer> avl = new AVLTree<>();
            
            for (int dato : datos) {
                bst.insert(dato);
                avl.insert(dato);
            }
            
            System.out.println("--- ÁRBOL BST TRADICIONAL ---");
            System.out.println("Se degrada a una lista enlazada (Desbalanceado):");
            bst.drawBST();
            System.out.println("Altura del BST: " + bst.height(10)); 
            System.out.println("Inorden: " + bst.getInOrder());
            
            System.out.println("\n--- ÁRBOL AVL ---");
            System.out.println("Se mantiene balanceado mediante rotaciones:");
            avl.drawBST();
            System.out.println("Altura del AVL: " + avl.height(40)); 
            System.out.println("Inorden: " + avl.getInOrder());
            
            // Búsquedas comparativas
            System.out.println("\nBuscando el 70 en BST: " + bst.search(70));
            System.out.println("Buscando el 70 en AVL: " + avl.search(70));

        } catch (ItemDuplicated e) {
            System.err.println("Error de duplicado en Ejercicio 2: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.err.println("Error de búsqueda en Ejercicio 2: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en Ejercicio 2: " + e.getMessage());
        }
    }
}
