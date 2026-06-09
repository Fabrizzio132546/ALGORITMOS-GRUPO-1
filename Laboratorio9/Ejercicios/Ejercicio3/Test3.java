package lab09.ejercicios.ejercicio3;

public class Test3 {
    public static void main(String[] args) {
        // Instanciamos un Árbol B de orden 4
        BTree<Integer> bTree = new BTree<>(4);

        System.out.println("=========================================");
        System.out.println("   FASE 1: INSERCIÓN DE CLAVES");
        System.out.println("=========================================");
        int[] keysToInsert = {10, 20, 5, 6, 12, 30, 7, 17, 25, 3};
        
        for (int key : keysToInsert) {
            System.out.println("Insertando: " + key);
            bTree.insert(key); 
        }

        System.out.println("\n--- Estado inicial del Árbol B ---");
        System.out.println(bTree); 


        System.out.println("\n=========================================");
        System.out.println("   FASE 2: ELIMINACIÓN DE CLAVES");
        System.out.println("=========================================");

        // Prueba 1: Eliminar una clave que está en una hoja
        System.out.println("\n-> Eliminando clave 6 (Hoja)...");
        bTree.remove(6);
        System.out.println(bTree);

        // Prueba 2: Eliminar una clave de un nodo interno 
        System.out.println("\n-> Eliminando clave 10 (Nodo interno)...");
        bTree.remove(10);
        System.out.println(bTree);

        // Prueba 3: Eliminar una clave que provoque redistribución o fusión
        System.out.println("\n-> Eliminando clave 20...");
        bTree.remove(20);
        System.out.println(bTree);
        
        // Prueba 4: Intentar eliminar una clave que no existe
        System.out.println("\n-> Intentando eliminar clave 99 (No existe)...");
        bTree.remove(99);
        
        System.out.println("\n--- Estado final del Árbol B ---");
        System.out.println(bTree);
    }
}