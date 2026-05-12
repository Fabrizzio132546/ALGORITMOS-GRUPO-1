package lab07.ejercicios.ejercicio4p2;

import exceptions.ExceptionIsEmpty;
import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    private Node<E> root;
    public LinkedBST() {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        this.root = insertRec(data, this.root);
    }

    protected Node<E> insertRec(E data, Node<E> actual) throws ItemDuplicated {
        Node<E> res = actual;
        if (actual == null) {
            res = new Node<E>(data);
        } else {
            int resC = data.compareTo(actual.data);
            if (resC == 0) throw new ItemDuplicated("Elemento duplicado: " + data);
            
            if (resC > 0) res.right = insertRec(data, actual.right);
            else res.left = insertRec(data, actual.left);
        }
        return res;
    }

    public String getInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(this.root, sb);
        return sb.toString().trim();
    }
    private void inOrder(Node<E> node, StringBuilder sb) {
        if (node != null) {
            inOrder(node.left, sb);
            sb.append(node.data).append(" ");
            inOrder(node.right, sb);
        }
    }

    public String getPreOrder() {
        StringBuilder sb = new StringBuilder();
        preOrder(this.root, sb);
        return sb.toString().trim();
    }
    private void preOrder(Node<E> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data).append(" ");
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }
    }

    public String getPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrder(this.root, sb);
        return sb.toString().trim();
    }
    private void postOrder(Node<E> node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.data).append(" ");
        }
    }

    @Override
    public E search(E data) throws ItemNotFound {
        Node<E> buscado = searchRec(data, this.root);
        if (buscado == null) throw new ItemNotFound("Elemento no encontrado");
        return buscado.data;
    }

    private Node<E> searchRec(E data, Node<E> actual) {
        if (actual == null) return null;
        int resC = data.compareTo(actual.data);
        if (resC == 0) return actual;
        if (resC > 0) return searchRec(data, actual.right);
        return searchRec(data, actual.left);
    }

    public int height() {
        return heightRec(this.root);
    }
    private int heightRec(Node<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public int countLeaves() {
        return countLeavesRec(this.root);
    }
    private int countLeavesRec(Node<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    public E findMin() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío");
        Node<E> current = this.root;
        while (current.left != null) current = current.left;
        return current.data;
    }

    public E findMax() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío");
        Node<E> current = this.root;
        while (current.right != null) current = current.right;
        return current.data;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol BST está vacío");
        this.root = deleteRec(this.root, data);
    }

    private Node<E> deleteRec(Node<E> actual, E data) {
        if (actual == null) return null;
        int resC = data.compareTo(actual.data);
        if (resC < 0) actual.left = deleteRec(actual.left, data);
        else if (resC > 0) actual.right = deleteRec(actual.right, data);
        else {
            if (actual.left == null) return actual.right;
            if (actual.right == null) return actual.left;
            Node<E> minNode = actual.right;
            while (minNode.left != null) minNode = minNode.left;
            actual.data = minNode.data;
            actual.right = deleteRec(actual.right, minNode.data);
        }
        return actual;
    }

    public boolean isValidBST() {
        return isValidBSTRec(this.root, null, null);
    }

    private boolean isValidBSTRec(Node<E> node, E min, E max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.data.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && node.data.compareTo(max) >= 0) {
            return false;
        }

        return isValidBSTRec(node.left, min, node.data) && 
               isValidBSTRec(node.right, node.data, max);
    }
    

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }
    
    

    @Override
    public String toString() {
        return getInOrder();
    }
}