public class TestAVL {
	public static void main(String[] args) {
        try {
            AVLTree<Integer> avlAmp = new AVLTree<>();
            int[] arrAmp = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
            for (int i : arrAmp) avlAmp.insert(i);
            
            System.out.println("Árbol para recorrido:");
            avlAmp.drawBST();
            
            System.out.println("\nRecorrido Inorden (Para comparar):");
            System.out.println(avlAmp.getInOrder());
            
            System.out.println("\nRecorrido Preorden (Para comparar):");
            System.out.println(avlAmp.getPreOrder());
            
            System.out.println("\nRecorrido por Amplitud (Recursivo) - Por Niveles:");
            avlAmp.recorridoAmplitudRecursivo();

        } catch (ItemDuplicated e) {
            System.err.println("Error de duplicado en Ejercicio 4: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en Ejercicio 4: " + e.getMessage());
        }
    }
}
