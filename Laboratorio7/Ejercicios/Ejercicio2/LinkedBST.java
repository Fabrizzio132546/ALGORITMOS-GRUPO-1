package Ejercicio02;

// Excepción personalizada para cuando el árbol está vacío
class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}

// Clase principal BST Genérico
class LinkedBST<T extends Comparable<T>> {
    
    private class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    // Método para insertar y armar el árbol inicial
    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, T data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Método destroyNodes()
    public void destroyNodes() throws ExceptionIsEmpty {
        if (this.root == null) {
            throw new ExceptionIsEmpty("El árbol ya está vacío. No hay nodos para destruir.");
        }

        // Al quitar la referencia a la raíz, el Garbage Collector elimina los nodos
        this.root = null;

        System.out.println("Todos los nodos han sido destruidos.");
    }

    // Método countAllNodes() -> Retorna todos los nodos
    public int countAllNodes() {
        return countAllNodesRec(this.root);
    }

    private int countAllNodesRec(Node node) {
        if (node == null) return 0;

        return 1 + countAllNodesRec(node.left) + countAllNodesRec(node.right);
    }

    // Método countNodes() -> Retorna nodos no-hoja
    public int countNodes() {
        return countNodesRec(this.root);
    }

    private int countNodesRec(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }

        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    // Método height(x) -> Iterativo
    public int height(T x) {

        // Buscar el nodo iterativamente
        Node current = root;

        while (current != null) {

            int cmp = x.compareTo(current.data);

            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // Si no existe el nodo
        if (current == null) {
            return -1;
        }

        // Calcular altura usando Cola
        int h = -1;

        QueueLink<Node> queue = new QueueLink<>();
        queue.enqueue(current);

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            h++;

            for (int i = 0; i < levelSize; i++) {

                Node tempNode = queue.dequeue();

                if (tempNode.left != null)
                    queue.enqueue(tempNode.left);

                if (tempNode.right != null)
                    queue.enqueue(tempNode.right);
            }
        }

        return h;
    }

    // Método amplitude()
    public int amplitude() {

        if (root == null)
            return 0;

        int maxAmplitude = 0;

        // Altura total del árbol
        int treeHeight = height(root.data);

        // Recorrer niveles
        for (int i = 0; i <= treeHeight; i++) {

            int currentAmplitude = getNodesAtLevel(root, i);

            if (currentAmplitude > maxAmplitude) {
                maxAmplitude = currentAmplitude;
            }
        }

        return maxAmplitude;
    }

    // Obtener nodos en un nivel específico
    private int getNodesAtLevel(Node node, int level) {

        if (node == null)
            return 0;

        if (level == 0)
            return 1;

        return getNodesAtLevel(node.left, level - 1)
                + getNodesAtLevel(node.right, level - 1);
    }
}
