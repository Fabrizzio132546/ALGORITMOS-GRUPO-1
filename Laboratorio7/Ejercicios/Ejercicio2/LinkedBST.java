package Ejercicio02;

import java.util.LinkedList;
import java.util.Queue;

// Excepción personalizada para cuando el árbol está vacío
class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}

// Clase principal BST
class LinkedBST {
    
    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
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
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    // Método destroyNodes()
    public void destroyNodes() throws ExceptionIsEmpty {
        if (this.root == null) {
            throw new ExceptionIsEmpty("El árbol ya está vacío. No hay nodos para destruir.");
        }
        // Al quitar la referencia a la raíz, el Recolector de Basura (Garbage Collector) 
        // Se encarga de eliminar todos los nodos automáticamente.
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

    // Método countNodes() -> Retorna el número de nodos no-hojas (nodos internos con por lo menos 1 hijo o 2)
    public int countNodes() {
        return countNodesRec(this.root);
    }

    private int countNodesRec(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            // Si es nulo o es una hoja, no se cuenta
            return 0;
        }
        // Si tiene al menos un hijo, es un nodo no-hoja, se cuenta y se busca en los hijos
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    // Método height(x) -> Iterativo (utiliza recorrido por niveles con una Cola)
    public int height(int x) {
        // Buscar el nodo iterativamente
        Node current = root;
        while (current != null) {
            if (current.data == x) {
                break; // Nodo encontrado
            } else if (x < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // Si no existe el nodo en el árbol
        if (current == null) {
            return -1;
        }

        // Calcular la altura de manera iterativa usando una  Cola 
        int h = -1; // Se asume que un nodo hoja tiene altura 0 (entonces empezamos en -1)
        Queue<Node> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            h++; // Por cada nivel que bajamos, aumentamos la altura en 1
            for (int i = 0; i < levelSize; i++) {
                Node tempNode = queue.poll();
                if (tempNode.left != null) queue.add(tempNode.left);
                if (tempNode.right != null) queue.add(tempNode.right);
            }
        }
        return h;
    }

    // Método amplitude(Nivel) -> Utiliza el método height()
    // Se usa la altura para recorrer sus niveles.
    public int amplitude() {
        if (root == null) return 0;
        
        int maxAmplitude = 0;
        // Obtenemos la altura total del árbol llamando al método height de la raíz
        int treeHeight = height(root.data); 

        // Recorremos cada nivel (desde el 0 hasta treeHeight)
        for (int i = 0; i <= treeHeight; i++) {
            int currentAmplitude = getNodesAtLevel(root, i);
            if (currentAmplitude > maxAmplitude) {
                maxAmplitude = currentAmplitude;
            }
        }
        return maxAmplitude;
    }

    // Método extra para obtener cuántos nodos hay en un nivel específico
    private int getNodesAtLevel(Node node, int level) {
        if (node == null) return 0;
        if (level == 0) return 1;
        return getNodesAtLevel(node.left, level - 1) + getNodesAtLevel(node.right, level - 1);
    }
}
