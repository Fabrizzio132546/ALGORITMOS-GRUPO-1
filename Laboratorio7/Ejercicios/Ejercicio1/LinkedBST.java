package lab07.ejercicios.ejercicio1;

import exceptions.ExceptionIsEmpty;
import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    
    private Node<E> root;
    
    public LinkedBST() {
        this.root = null;
    }

    // ==========================================
    // INSERCIÓN
    // ==========================================
    @Override
    public void insert(E data) throws ItemDuplicated {
        this.root = insertRec(data, this.root); // Inicia la recursión desde la raíz
    }

    // Re-enlace recursivo: Construye o mantiene el árbol de abajo hacia arriba
    protected Node<E> insertRec(E data, Node<E> actual) throws ItemDuplicated {
        Node<E> res = actual;
        if (actual == null) {
            res = new Node<E>(data); // Caso base: Espacio vacío encontrado
        } else {
            int resC = data.compareTo(actual.data);
            if (resC == 0) throw new ItemDuplicated("Elemento duplicado: " + data);
            
            // Regla BST: Menores a la izquierda, mayores a la derecha
            if (resC > 0) res.right = insertRec(data, actual.right);
            else res.left = insertRec(data, actual.left);
        }
        return res;
    }

    // ==========================================
    // RECORRIDOS (InOrden, PreOrden, PostOrden)
    // ==========================================
    public String getInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(this.root, sb);
        return sb.toString().trim();
    }
    // InOrden: Izquierda -> Raíz -> Derecha (Obtiene los elementos ordenados)
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
    // PreOrden: Raíz -> Izquierda -> Derecha (Útil para clonar árboles)
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
    // PostOrden: Izquierda -> Derecha -> Raíz (Útil para eliminar árboles desde las hojas)
    private void postOrder(Node<E> node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.data).append(" ");
        }
    }

    // ==========================================
    // BÚSQUEDA
    // ==========================================
    @Override
    public E search(E data) throws ItemNotFound {
        Node<E> buscado = searchRec(data, this.root);
        if (buscado == null) throw new ItemNotFound("Elemento no encontrado");
        return buscado.data;
    }

    // Descarta la mitad del árbol en cada recursión (Eficiencia O(log n))
    private Node<E> searchRec(E data, Node<E> actual) {
        if (actual == null) return null;
        int resC = data.compareTo(actual.data);
        if (resC == 0) return actual; // Elemento encontrado
        if (resC > 0) return searchRec(data, actual.right);
        return searchRec(data, actual.left);
    }

    // ==========================================
    // ANÁLISIS (Altura, Hojas, Mínimo y Máximo)
    // ==========================================
    
    public int height() {
        return heightRec(this.root);
    }
    // Retorna -1 si es nulo porque la altura se mide por "aristas", no por nodos
    private int heightRec(Node<E> node) {
        if (node == null) return -1; 
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public int countLeaves() {
        return countLeavesRec(this.root);
    }
    // Un nodo es "hoja" si sus dos hijos son nulos
    private int countLeavesRec(Node<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    // El mínimo siempre está iterando continuamente hacia la izquierda
    public E findMin() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío");
        Node<E> current = this.root;
        while (current.left != null) current = current.left;
        return current.data;
    }

    // El máximo siempre está iterando continuamente hacia la derecha
    public E findMax() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío");
        Node<E> current = this.root;
        while (current.right != null) current = current.right;
        return current.data;
    }

    // ==========================================
    // ELIMINACIÓN
    // ==========================================
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
            // Manejo de los 3 casos de eliminación:
            if (actual.left == null) return actual.right; // Caso 1 y 2 (Sin hijo izquierdo)
            if (actual.right == null) return actual.left; // Caso 2 (Sin hijo derecho)
            
            // Caso 3: Dos hijos. Se busca el "Sucesor" (Mínimo del subárbol derecho)
            Node<E> minNode = actual.right;
            while (minNode.left != null) minNode = minNode.left;
            
            actual.data = minNode.data; // Se copia el valor del sucesor
            actual.right = deleteRec(actual.right, minNode.data); // Se borra el sucesor original
        }
        return actual;
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
