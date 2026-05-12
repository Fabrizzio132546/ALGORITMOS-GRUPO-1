package Ejercicio03;

import java.util.LinkedList;
import java.util.Queue;

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

        @Override
        public String toString() {
            return String.valueOf(this.data);
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    // Método para insertar y armar el árbol
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

    // areaBST() - Implementación iterativa
    public int areaBST() {

        if (root == null) {
            return 0;
        }

        int height = -1;
        int leafCount = 0;

        // Cola para recorrido por niveles
        QueueLink<Node> queue = new LinkedList<>();

        queue.enqueue(root);

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            height++;

            for (int i = 0; i < levelSize; i++) {

                Node current = queue.dequeue();

                // Verificar si es hoja
                if (current.left == null && current.right == null) {
                    leafCount++;
                }

                // Agregar hijos
                if (current.left != null) {
                    queue.enqueue(current.left);
                }

                if (current.right != null) {
                    queue.enqueue(current.right);
                }
            }
        }

        // Área = hojas × altura
        return leafCount * height;
    }

    // drawBST()
    public void drawBST() {

        System.out.println("Estructura del árbol (Nodos y aristas):");

        System.out.println(this.toString());
    }

    @Override
    public String toString() {

        if (root == null)
            return "El árbol está vacío.";

        StringBuilder sb = new StringBuilder();

        buildTreeString(root, "", true, sb);

        return sb.toString();
    }

    // Construcción gráfica del árbol
    private void buildTreeString(
            Node node,
            String prefix,
            boolean isTail,
            StringBuilder sb) {

        if (node.right != null) {

            buildTreeString(
                    node.right,
                    prefix + (isTail ? "│   " : "    "),
                    false,
                    sb);
        }

        sb.append(prefix)
          .append(isTail ? "└── " : "┌── ")
          .append(node.toString())
          .append("\n");

        if (node.left != null) {

            buildTreeString(
                    node.left,
                    prefix + (isTail ? "    " : "│   "),
                    true,
                    sb);
        }
    }
}
