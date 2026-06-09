public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public BNode<E> getRoot() {
        return this.root;
    }

    public boolean search(E cl) {
        if (root == null) {
            return false; // El árbol está vacío, detiene la búsqueda
        }
        return search(root, cl); // Inicia la recursividad desde la raíz
    }

    private boolean search(BNode<E> current, E cl) {
        // Se usa un arreglo de un elemento para simular el paso por referencia.
        // Al ejecutar searchNode, pos[0] almacenará la posición exacta de la clave,
        // o en su defecto, la posición de la rama por la que debemos descender.
        int[] pos = new int[1];
        
        // Busca secuencial o binariamente dentro de las claves del nodo actual
        boolean found = current.searchNode(cl, pos); 
        
        if (found) {
            // Caso base 1: Éxito. La clave está en el nodo actual.
            System.out.println(cl + " se encuentra en el nodo " + current.getIdNode() + " en la posición " + pos[0]);
            return true;
        }
        
        // Caso base 2: Fracaso. No se encontró la clave y no hay más ramas por donde bajar (llegamos a una hoja).
        if (current.childs.get(pos[0]) == null) {
            return false;
        }
        
        // Paso recursivo: La clave no está aquí, así que bajamos por el hijo correspondiente a la posición evaluada.
        return search(current.childs.get(pos[0]), cl);
    }
}
