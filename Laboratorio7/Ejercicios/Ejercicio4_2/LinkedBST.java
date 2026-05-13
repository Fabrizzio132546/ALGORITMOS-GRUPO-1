package lab07.ejercicios.ejercicio4p2;

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
        this.root = insertRec(data, this.root);
    }

    // Re-enlace recursivo: Mantiene la estructura del árbol al subir en la recursión
    protected Node<E> insertRec(E data, Node<E> actual) throws ItemDuplicated {
        Node<E> res = actual;
        if (actual == null) {
            res = new Node<E>(data); // Caso base: Crea el nodo en el espacio vacío
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
    // RECORRIDOS
    // ==========================================
    public String getInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(this.root, sb);
        return sb.toString().trim();
    }
    // InOrden: Retorna los elementos ordenados de menor a mayor
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
    // PreOrden: Raíz primero. Útil para copiar la estructura del árbol.
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
    // PostOrden: Raíz al final. Útil para eliminar el árbol de abajo hacia arriba.
    private void postOrder(Node<E> node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.data).append(" ");
        }
    }

    // ==========================================
    // BÚSQUEDA Y ANÁLISIS
    // ==========================================
    @Override
    public E search(E data) throws ItemNotFound {
        Node<E> buscado = searchRec(data, this.root);
        if (buscado == null) throw new ItemNotFound("Elemento no encontrado");
        return buscado.data;
    }

    // O(log n) en promedio: Descarta la mitad del árbol en cada paso
    private Node<E> searchRec(E data, Node<E> actual) {
        if (actual == null) return null;
        int resC = data.compareTo(actual.data);
        if (resC == 0) return actual;
        if (resC > 0) return searchRec(data, actual.right);
        return searchRec(data, actual.left);
    }

    // Altura: Se mide en aristas (por eso retorna -1 si es nulo)
    public int height() {
        return heightRec(this.root);
    }
    private int heightRec(Node<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    // Hojas: Nodos sin hijos (left y right son null)
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
        while (current.left != null) current = current.left; // Mínimo = extremo izquierdo
        return current.data;
    }

    public E findMax() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío");
        Node<E> current = this.root;
        while (current.right != null) current = current.right; // Máximo = extremo derecho
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
            // Nodo a eliminar encontrado.
            if (actual.left == null) return actual.right; // Caso 1 y 2: Falla izquierdo
            if (actual.right == null) return actual.left; // Caso 2: Falla derecho
            
            // Caso 3: Dos hijos. Buscamos el sucesor (mínimo de los mayores)
            Node<E> minNode = actual.right;
            while (minNode.left != null) minNode = minNode.left;
            actual.data = minNode.data; // Reemplazamos valor
            actual.right = deleteRec(actual.right, minNode.data); // Eliminamos sucesor
        }
        return actual;
    }

    // ==========================================
    // VERIFICACIÓN DE PROPIEDADES (NUEVO)
    // ==========================================
    public boolean isValidBST() {
        // Se inicia sin límites (null) porque la raíz principal no tiene restricciones
        return isValidBSTRec(this.root, null, null);
    }

    // Validación por rangos (DFS): Garantiza que los nodos profundos respeten a sus ancestros
    private boolean isValidBSTRec(Node<E> node, E min, E max) {
        if (node == null) {
            return true; // Un nodo vacío es válido por defecto
        }
        // Si hay un límite inferior y el nodo lo rompe (es menor o igual), es inválido
        if (min != null && node.data.compareTo(min) <= 0) {
            return false;
        }
        // Si hay un límite superior y el nodo lo rompe (es mayor o igual), es inválido
        if (max != null && node.data.compareTo(max) >= 0) {
            return false;
        }

        // Al ir a la izquierda, el máximo permitido ahora es el nodo actual.
        // Al ir a la derecha, el mínimo permitido ahora es el nodo actual.
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
