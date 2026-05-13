package lab07.actividades.actividad9;

import exceptions.ExceptionIsEmpty;
import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    private Node<E> root;

    public LinkedBST() {
        this.root = null;
    }

    // Método envoltorio (wrapper) que inicia la recursión desde la raíz
    @Override
    public void insert(E data) throws ItemDuplicated {
        this.root = insertRec(data, this.root);
    }

    // Utiliza "Re-enlace recursivo" para reconstruir/mantener las conexiones del árbol al subir
    protected Node<E> insertRec(E data, Node<E> actual) throws ItemDuplicated {
        Node<E> res = actual;
        if (actual == null) {
            res = new Node<E>(data); // Caso base: encontramos el lugar vacío y creamos el nodo
        } else {
            int resC = data.compareTo(actual.data);
            if (resC == 0) {
                // En un BST estricto (Set) no se permiten valores repetidos
                throw new ItemDuplicated("Elemento duplicado");
            }
            if (resC > 0) {
                res.right = insertRec(data, actual.right); // Valores mayores van a la derecha
            } else {
                res.left = insertRec(data, actual.left);   // Valores menores van a la izquierda
            }
        }
        return res; // Retorna la referencia del nodo a su padre
    }

    @Override
    public E search(E data) throws ItemNotFound {
        Node<E> buscado = searchRec(data, this.root);
        if (buscado == null) {
            throw new ItemNotFound("Elemento no encontrado");
        }
        return buscado.data;
    }

    // Aprovecha la propiedad del BST para descartar la mitad del árbol en cada paso (O(log n))
    private Node<E> searchRec(E data, Node<E> actual) {
        if (actual == null) {
            return null; // El elemento no existe en esta rama
        }
        int resC = data.compareTo(actual.data);

        if (resC == 0) {
            return actual; // Hit: Elemento encontrado
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
        
        // Fase de búsqueda del nodo a eliminar
        if (resC < 0) {
            actual.left = deleteRec(actual.left, data);
        } else if (resC > 0) {
            actual.right = deleteRec(actual.right, data);
        } else {
            // FASE DE ELIMINACIÓN: Nodo encontrado
            
            // Caso 1: Es un nodo hoja (no tiene hijos)
            if (actual.left == null && actual.right == null) {
                return null;
            }
            // Caso 2: Tiene un solo hijo (se "puentea" retornando al único hijo)
            if (actual.left == null) {
                return actual.right;
            }
            if (actual.right == null) {
                return actual.left;
            }

            // Caso 3: Tiene dos hijos. 
            // Buscamos el "sucesor in-orden" (el menor de los mayores)
            try {
                E sucesor = findMinNode(actual.right);
                actual.data = sucesor; // Reemplazamos el valor actual por el del sucesor
                // Eliminamos el nodo sucesor original de su antigua posición
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

        // El valor mínimo en un BST SIEMPRE está lo más a la izquierda posible
        while (actual.left != null) {
            actual = actual.left;
        }
        
        // Reutilizamos el método search público (Requisito del laboratorio)
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

    // Recorrido Post-Orden: 1° Izquierda, 2° Derecha, 3° Raíz (Visita al final)
    private void postOrder(Node<E> actual, StringBuilder sb) {
        if (actual != null) {
            postOrder(actual.left, sb);     
            postOrder(actual.right, sb);    
            sb.append(actual.data).append(" "); 
        }
    }
}
