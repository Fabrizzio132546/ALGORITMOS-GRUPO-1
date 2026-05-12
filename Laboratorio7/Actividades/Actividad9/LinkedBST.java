package lab07.actividades.actividad9;

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
            if (resC == 0) {
                throw new ItemDuplicated("Elemento duplicado");
            }
            if (resC > 0) {
                res.right = insertRec(data, actual.right);
            } else {
                res.left = insertRec(data, actual.left);
            }
        }
        return res;
    }

    @Override
    public E search(E data) throws ItemNotFound {
        Node<E> buscado = searchRec(data, this.root);
        if (buscado == null) {
            throw new ItemNotFound("Elemento no encontrado");
        }
        return buscado.data;
    }

    private Node<E> searchRec(E data, Node<E> actual) {
        if (actual == null) {
            return null;
        }
        int resC = data.compareTo(actual.data);

        if (resC == 0) {
            return actual;
        }
        if (resC > 0) {
            return searchRec(data, actual.right);
        }
        return searchRec(data, actual.left);
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol BST está vacío");
        }
        this.root = deleteRec(this.root, data);
    }

    private Node<E> deleteRec(Node<E> actual, E data) {
        if (actual == null) {
            return null;
        }
        int resC = data.compareTo(actual.data);
        if (resC < 0) {
            actual.left = deleteRec(actual.left, data);
        } else if (resC > 0) {
            actual.right = deleteRec(actual.right, data);
        } else {
            if (actual.left == null && actual.right == null) {
                return null;
            }
            if (actual.left == null) {
                return actual.right;
            }
            if (actual.right == null) {
                return actual.left;
            }

            try {
                E sucesor = findMinNode(actual.right);
                actual.data = sucesor;
                actual.right = deleteRec(actual.right, sucesor);
            } catch (ItemNotFound e) {
                System.out.println(e.getMessage());
            }
        }
        return actual;
    }

    private E findMinNode(Node<E> node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Subárbol vacío");
        }
        Node<E> actual = node;

        while (actual.left != null) {
            actual = actual.left;
        }
        return search(actual.data);
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        postOrder(this.root, sb);
        return sb.toString();
    }


    private void postOrder(Node<E> actual, StringBuilder sb) {
        if (actual != null) {
            postOrder(actual.left, sb);     
            postOrder(actual.right, sb);    
            sb.append(actual.data).append(" "); 
        }
    }
}
