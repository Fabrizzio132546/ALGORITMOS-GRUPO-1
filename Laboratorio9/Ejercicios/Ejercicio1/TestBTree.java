public class TestBTree {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(5); 

        //llenar el árbol con los datos 
        // bTree.insert(52);

        System.out.println("==== INICIO DE PRUEBAS DE BÚSQUEDA ====\n");

        // 1. Evaluación del nodo raíz
        System.out.println("-> Buscando dato en el root:");
        boolean resRoot = bTree.search(52); 
        System.out.println("Retorno: " + resRoot + "\n");

        // 2. Evaluación de las hojas en los extremos del árbol
        System.out.println("-> Buscando dato en hoja (Extremo inicial):");
        boolean resHojaIni = bTree.search(10); 
        System.out.println("Retorno: " + resHojaIni + "\n");

        System.out.println("-> Buscando dato en hoja (Extremo final):");
        boolean resHojaFin = bTree.search(99); 
        System.out.println("Retorno: " + resHojaFin + "\n");

        // 3. Evaluación de un dato inexistente
        System.out.println("-> Buscando un dato que NO existe:");
        boolean resNoEncontrado = bTree.search(999); 
        System.out.println("Retorno: " + resNoEncontrado + "\n");
    }
}
