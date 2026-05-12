package Actividad6;

import Actividad4.*;
import Actividad5.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
	protected class Node<T extends Comparable<T>> {
	    T data; 
	    Node<T> left;
	    Node<T> right;

	    public Node(T data) {
	        this.data = data;
	        this.left = null;
	        this.right = null;
	    }
	}
    private Node<E> root;

    public LinkedBST() {
        this.root = null; // el arbol empieza vacio
    }
	// sobreescribimos el metodo
    @Override
    public boolean isEmpty() {
		// permite verificar si el nodo raiz esta vacio
        return root == null;
    }

    @Override
    public void insert(E d) throws ItemDuplicated {
        root = insertRec(root, d); // Actualizamos la raíz con los cambios
    }
	// Esta función busca el "hueco" vacío para poner el nuevo dato.
    private Node<E> insertRec(Node<E> n, E d) throws ItemDuplicated {
		// caso base, si esta vacio se argega en ese lugar
        if (n == null) {
            return new Node<>(d);
        }

        int cmp = d.compareTo(n.data); 
		// se realizan xomparaciones, para ver si es menor, mayor o igual
        if (cmp < 0) {
			// Si es menor, intentamos meterlo por el camino de la izquierda
            n.left = insertRec(n.left, d);
        } else if (cmp > 0) {
			// Si es mayor, intentamos por el camino de la derecha
            n.right = insertRec(n.right, d);
        } else {
			// Si son iguales, lanzamos un error porque no queremos repetidos
            throw new ItemDuplicated("El dato " + d + " ya existe");
        }
        return n;
    }
	// Método para buscar un dato
    @Override
    public E search(E d) throws ItemNotFound {
        return searchRec(root, d);
    }

    private E searchRec(Node<E> n, E d) throws ItemNotFound {
		// Si llegamos al final y no hay nada, el dato no existe
        if (n == null) {
            throw new ItemNotFound("El dato " + d + " no se encuentra en el BST"); 
        }

        int cmp = d.compareTo(n.data); 

        if (cmp < 0) {
            return searchRec(n.left, d); // Buscamos en los menores de la izquierda
        } else if (cmp > 0) {
            return searchRec(n.right, d); // Buscamos en los mayores de la derecha
        } else {
            return n.data; // se encontro y se devuelve el valor
        }
    }
	// Método para borrar un dato
    @Override
    public void delete(E d) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }
        root = deleteRec(root, d);
    }

    private Node<E> deleteRec(Node<E> n, E d) {
		// Si no hay nada que borrar, no hacemos nada
        if (n == null) return null;

        int cmp = d.compareTo(n.data);

        if (cmp < 0) {
            n.left = deleteRec(n.left, d); // Buscamos por la izquierda
        } else if (cmp > 0) {
            n.right = deleteRec(n.right, d); // Buscamos por la derecha
        } else {
			// Si no tiene hijo izquierdo, el hijo derecho sube a su lugar
            if (n.left == null) return n.right;
			// Si no tiene hijo derecho, el hijo izquierdo sube
            if (n.right == null) return n.left;

			
			// caso cuando tiene dos hijos
			// Buscamos al "reemplazo": el más pequeño del lado derecho
            E d2 = findMin(n.right);
            n.data = d2; // El actual toma el valor del reemplazo
			// Borramos el nodo original del reemplazo que ahora está duplicado
            n.right = deleteRec(n.right, d2);
        }
        return n;
    }
    // Función para buscar el valor más pequeño de un grupo ya sea izquierda o derecha
	// en este caso lo usamos para buscar el menor de los mayores
    private E findMin(Node<E> n) {
        while (n.left != null) {
            n = n.left;
        }
        return n.data;
    }
    // Para mostrar el árbol como texto
    @Override
    public String toString() {
        return "BST: [" + toStringRecursivo(root) + "]";
    }
    // Esto hace que los números salgan ordenaditos de menor a mayor
    private String toStringRecursivo(Node<E> n) {
        if (n == null) return "";
        return toStringRecursivo(n.left) + " " + n.data + " " + toStringRecursivo(n.right);
    }

}
