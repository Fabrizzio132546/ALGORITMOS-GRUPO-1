package Actividad07;

class LinkedBST {
    
    // Clase interna para los nodos del árbol
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

    // Método público que llama al método recursivo privado
    public void inOrder() {
        inOrder(this.root);
        System.out.println();
    }

    // Método privado inOrder
    private void inOrder(Node node) {
        if (node != null) {
            // Izquierda
            inOrder(node.left);       
            // Cabecera (Raíz)
            System.out.print(node.data + " "); 
            // Derecha
            inOrder(node.right);      
        }
    }

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
}
