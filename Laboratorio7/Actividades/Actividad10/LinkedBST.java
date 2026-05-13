package lab07.actividades.actividad10;

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

    // Mantiene la propiedad fundamental del BST: Izquierda < Raíz < Derecha
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
            // Nodo a eliminar encontrado. Manejo de los 3 casos:
            if (actual.left == null && actual.right == null) {
                return null; // Caso 1: Hoja
            }
            if (actual.left == null) {
                return actual.right; // Caso 2: Un hijo (derecho)
            }
            if (actual.right == null) {
                return actual.left; // Caso 2: Un hijo (izquierdo)
            }

            // Caso 3: Dos hijos. Se busca el sucesor (el mínimo del subárbol derecho)
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

    // ==========================================
    // BÚSQUEDA DE EXTREMOS (MIN / MAX)
    // Patrón Wrapper: Un método público sin parámetros que llama a uno privado recursivo
    // ==========================================

    // Helper interno: Busca iterativamente hacia la rama izquierda
    private E findMinNode(Node<E> node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Subárbol vacío");
        }
        Node<E> actual = node;
        // El menor elemento siempre está en el extremo izquierdo
        while (actual.left != null) {
            actual = actual.left;
        }
        return search(actual.data); // Validación extra requerida por tu laboratorio
    }

    // Método público: Inicia la búsqueda del mínimo desde la raíz del árbol
    public E findMin() throws ItemNotFound {
        if (isEmpty()) {
            throw new ItemNotFound("El árbol BST está vacío");
        }
        return findMinNode(this.root);
    }

    // Helper interno: Busca iterativamente hacia la rama derecha
    private E findMaxNode(Node<E> node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Subárbol vacío");
        }
        Node<E> actual = node;
        // El mayor elemento siempre está en el extremo derecho
        while (actual.right != null) {
            actual = actual.right;
        }
        return search(actual.data);
    }

    // Método público: Inicia la búsqueda del máximo desde la raíz
    public E findMax() throws ItemNotFound {
        if (isEmpty()) {
            throw new ItemNotFound("El árbol BST está vacío");
        }
        return findMaxNode(this.root);
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(this.root, sb);
        return sb.toString();
    }

    // Recorrido In-Orden: Izquierda -> Raíz -> Derecha
    // PROPIEDAD CLAVE: En un BST, este recorrido SIEMPRE devuelve los datos ordenados de menor a mayor.
    private void inOrder(Node<E> actual, StringBuilder sb) {
        if (actual != null) {
            inOrder(actual.left, sb);
            sb.append(actual.data).append(" ");
            inOrder(actual.right, sb);
        }
    }
}
