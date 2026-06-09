import java.util.ArrayList;

public class BTree<E extends Comparable<E>> {
    
    // --- 1. CLASE INTERNA BNODE ---
    public static class BNode<E extends Comparable<E>> {
        public ArrayList<E> keys;           // Claves almacenadas en el nodo
        public ArrayList<BNode<E>> childs;  // Punteros a los hijos
        public boolean isLeaf;              // Indicador de si el nodo es hoja
        private int idNode;                 // Identificador único
        private static int idCounter = 1;   // Contador para asignar IDs

        public BNode(boolean isLeaf) {
            this.keys = new ArrayList<>();
            this.childs = new ArrayList<>();
            this.isLeaf = isLeaf;
            this.idNode = idCounter++;
        }

        public int getIdNode() {
            return this.idNode;
        }

        // Método que busca secuencialmente dentro del nodo actual
        public boolean searchNode(E cl, int[] pos) {
            int i = 0;
            // Avanza mientras la clave buscada sea mayor que las claves del nodo
            while (i < keys.size() && cl.compareTo(keys.get(i)) > 0) {
                i++;
            }
            pos[0] = i; // Guarda la posición exacta o la rama por la que descender
            
            // Retorna true si encontró la clave exactamente en esa posición
            return i < keys.size() && cl.compareTo(keys.get(i)) == 0;
        }
    }

    // --- 2. ATRIBUTOS DEL ÁRBOL B ---
    private BNode<E> root;
    private int orden; // Orden del árbol (máximo número de hijos por nodo)

    public BTree(int orden) {
        if (orden < 3) throw new IllegalArgumentException("El orden debe ser al menos 3.");
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public BNode<E> getRoot() {
        return this.root;
    }

    // --- 3. TU LÓGICA DE BÚSQUEDA MEJORADA ---
    public boolean search(E cl) {
        if (root == null) {
            return false;
        }
        return search(root, cl);
    }

    private boolean search(BNode<E> current, E cl) {
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos); 
        
        if (found) {
            // Caso base 1: Éxito.
            System.out.println("La clave " + cl + " se encuentra en el nodo " + current.getIdNode() + " en el índice " + pos[0]);
            return true;
        }
        
        // Caso base 2: Fracaso (Corrección: se usa isLeaf para evitar IndexOutOfBounds)
        if (current.isLeaf) {
            System.out.println("La clave " + cl + " no se encuentra en el árbol.");
            return false;
        }
        
        // Paso recursivo: Bajamos por el hijo correspondiente.
        return search(current.childs.get(pos[0]), cl);
    }

    // --- 4. OPERACIONES ESTRUCTURALES (INSERCIÓN) ---
    public void insert(E cl) {
        if (root == null) {
            root = new BNode<>(true);
            root.keys.add(cl);
        } else {
            // Si la raíz está llena (orden - 1 es el máximo de claves), debemos dividirla
            if (root.keys.size() == orden - 1) {
                BNode<E> newRoot = new BNode<>(false);
                newRoot.childs.add(root);
                splitChild(newRoot, 0, root);
                root = newRoot;
            }
            insertNonFull(root, cl);
        }
    }

    // Inserta una clave en un nodo que sabemos que NO está lleno
    private void insertNonFull(BNode<E> node, E cl) {
        int[] pos = new int[1];
        
        if (node.isLeaf) {
            // Encontrar la posición correcta para mantener el orden e insertar
            node.searchNode(cl, pos);
            node.keys.add(pos[0], cl);
        } else {
            // Si no es hoja, encontrar el hijo por donde descender
            node.searchNode(cl, pos);
            BNode<E> child = node.childs.get(pos[0]);
            
            // Si el hijo por el que vamos a descender está lleno, lo dividimos primero
            if (child.keys.size() == orden - 1) {
                splitChild(node, pos[0], child);
                // Tras la división, la clave del medio sube. Debemos decidir a qué hijo bajar.
                if (cl.compareTo(node.keys.get(pos[0])) > 0) {
                    pos[0]++;
                }
            }
            insertNonFull(node.childs.get(pos[0]), cl);
        }
    }

    // Divide un nodo hijo que está lleno
    private void splitChild(BNode<E> parent, int i, BNode<E> fullChild) {
        BNode<E> newNode = new BNode<>(fullChild.isLeaf);
        int midIndex = (orden - 1) / 2; // Posición de la clave mediana
        
        // La clave mediana sube al padre
        parent.keys.add(i, fullChild.keys.get(midIndex));
        parent.childs.add(i + 1, newNode);
        
        // Las claves de la mitad derecha pasan al nuevo nodo
        for (int j = midIndex + 1; j < fullChild.keys.size(); j++) {
            newNode.keys.add(fullChild.keys.get(j));
        }
        
        // Los hijos de la mitad derecha pasan al nuevo nodo (si no es hoja)
        if (!fullChild.isLeaf) {
            for (int j = midIndex + 1; j <= fullChild.keys.size(); j++) {
                newNode.childs.add(fullChild.childs.get(j));
            }
        }
        
        // Eliminar las claves y los hijos que se movieron del nodo original
        int initialSize = fullChild.keys.size();
        for (int j = initialSize - 1; j >= midIndex; j--) {
            fullChild.keys.remove(j);
        }
        if (!fullChild.isLeaf) {
            for (int j = initialSize; j >= midIndex + 1; j--) {
                fullChild.childs.remove(j);
            }
        }
    }

    // --- 5. MÉTODO PARA MOSTRAR EL ÁRBOL (COMPROBACIÓN) ---
    public void printTree() {
        if (root == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        printTree(root, 0);
    }

    private void printTree(BNode<E> node, int level) {
        System.out.println("Nivel " + level + " | Nodo " + node.getIdNode() + " | Claves: " + node.keys);
        if (!node.isLeaf) {
            for (BNode<E> child : node.childs) {
                printTree(child, level + 1);
            }
        }
    }

    // --- 6. METODO MAIN PARA PROBAR ---
    public static void main(String[] args) {
        // Creamos un Árbol B de orden 4 (máximo 4 hijos, máximo 3 claves por nodo)
        BTree<Integer> bTree = new BTree<>(4);
        
        System.out.println("--- Insertando claves ---");
        int[] valores = {10, 20, 5, 6, 12, 30, 7, 17};
        for (int val : valores) {
            bTree.insert(val);
        }

        System.out.println("\n--- Estructura del Árbol B ---");
        bTree.printTree();

        System.out.println("\n--- Probando tu lógica de Búsqueda ---");
        bTree.search(12); // Debería encontrarlo
        bTree.search(100); // No debería encontrarlo
    }
}
