package lab09.ejercicios.ejercicio1;

public class Main {
    public static void main(String[] args) {
        // Inicializamos el árbol
        BTree<Integer> arbolB = new BTree<>(5);

        // ENSAMBLAJE MANUAL DEL ÁRBOL PARA PODER PROBAR LA BÚSQUEDA
        // Nodo Raíz
        BNode<Integer> raiz = new BNode<>(5);
        raiz.keys.set(0, 50);
        raiz.count = 1;
        arbolB.root = raiz;

        // Hijo Izquierdo (Hoja)
        BNode<Integer> hijoIzq = new BNode<>(5);
        hijoIzq.keys.set(0, 10);
        hijoIzq.keys.set(1, 20);
        hijoIzq.keys.set(2, 30);
        hijoIzq.count = 3;

        // Hijo Derecho (Hoja)
        BNode<Integer> hijoDer = new BNode<>(5);
        hijoDer.keys.set(0, 60);
        hijoDer.keys.set(1, 80);
        hijoDer.keys.set(2, 100);
        hijoDer.count = 3;

        // Conectamos los hijos a la raíz
        raiz.childs.set(0, hijoIzq);
        raiz.childs.set(1, hijoDer);

        // --- EJECUCIÓN DE LAS PRUEBAS ---
        System.out.println("--- PRUEBAS EJERCICIO 01 ---");

        System.out.print("\n1. Buscando en raíz (50): \n");
        boolean res1 = arbolB.search(50);
        System.out.println("Retorno: " + res1);

        System.out.print("\n2. Buscando en hoja inicial (10): \n");
        boolean res2 = arbolB.search(10);
        System.out.println("Retorno: " + res2);

        System.out.print("\n3. Buscando en hoja final (100): \n");
        boolean res3 = arbolB.search(100);
        System.out.println("Retorno: " + res3);

        System.out.print("\n4. Buscando dato inexistente (999): \n");
        boolean res4 = arbolB.search(999); 
        System.out.println("Retorno: " + res4);
    }
}
