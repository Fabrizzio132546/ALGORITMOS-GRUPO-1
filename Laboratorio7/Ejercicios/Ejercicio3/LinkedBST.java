package Ejercicio03;

import java.util.LinkedList;
import java.util.Queue;

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

    // areaBST() - Implementación iterativa
    public int areaBST() {
        if (root == null) {
            return 0; // Si está vacío, área 0
        }

        int height = -1;
        int leafCount = 0;
        
        // Utilizamos una Cola (Queue) para hacer un recorrido por niveles
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++; // Aumentamos la altura por cada nivel que bajamos
            
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                
                // Verificamos si es un nodo hoja (no tiene hijos)
                if (current.left == null && current.right == null) {
                    leafCount++;
                }
                
                // Agregamos los hijos a la cola para el siguiente nivel
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }

        // Área = número de nodos hojas x altura del árbol
        return leafCount * height;
    }

    // drawBST() utilizando el método toString()
    public void drawBST() {
        System.out.println("Estructura del árbol (Nodos y aristas):");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        if (root == null) return "El árbol está vacío.";
        StringBuilder sb = new StringBuilder();
        buildTreeString(root, "", true, sb);
        return sb.toString();
    }

    // Método extra para construir gráficamente el árbol de lado
    private void buildTreeString(Node node, String prefix, boolean isTail, StringBuilder sb) {
        if (node.right != null) {
            buildTreeString(node.right, prefix + (isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.toString()).append("\n");
        if (node.left != null) {
            buildTreeString(node.left, prefix + (isTail ? "    " : "│   "), true, sb);
        }
    }
}
