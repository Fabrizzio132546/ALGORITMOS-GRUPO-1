package Ejercicio4_1;

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
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(E d) throws ItemDuplicated {
        root = insertRec(root, d);
    }

    private Node<E> insertRec(Node<E> n, E d) throws ItemDuplicated {
        if (n == null) {
            return new Node<>(d);
        }

        int cmp = d.compareTo(n.data); 
        if (cmp < 0) {
            n.left = insertRec(n.left, d);
        } else if (cmp > 0) {
            n.right = insertRec(n.right, d);
        } else {
            throw new ItemDuplicated("El dato " + d + " ya existe");
        }
        return n;
    }

    @Override
    public E search(E d) throws ItemNotFound {
        return searchRec(root, d);
    }

    private E searchRec(Node<E> n, E d) throws ItemNotFound {
        if (n == null) {
            throw new ItemNotFound("El dato " + d + " no se encuentra en el BST"); 
        }

        int cmp = d.compareTo(n.data); 

        if (cmp < 0) {
            return searchRec(n.left, d);
        } else if (cmp > 0) {
            return searchRec(n.right, d);
        } else {
            return n.data; 
        }
    }

    @Override
    public void delete(E d) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }
        root = deleteRec(root, d);
    }

    private Node<E> deleteRec(Node<E> n, E d) {
        if (n == null) return null;

        int cmp = d.compareTo(n.data);

        if (cmp < 0) {
            n.left = deleteRec(n.left, d);
        } else if (cmp > 0) {
            n.right = deleteRec(n.right, d);
        } else {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;

            E d2 = findMin(n.right);
            n.data = d2;
            n.right = deleteRec(n.right, d2);
        }
        return n;
    }
    
    private E findMin(Node<E> n) {
        while (n.left != null) {
            n = n.left;
        }
        return n.data;
    }
    
    @Override
    public String toString() {
        return "BST: [" + toStringRecursivo(root) + "]";
    }
    
    private String toStringRecursivo(Node<E> n) {
        if (n == null) return "";
        return toStringRecursivo(n.left) + " " + n.data + " " + toStringRecursivo(n.right);
    }
    
    public void preOrder() {
        System.out.print("PreOrder: "); 
        preOrder(this.root);          
        System.out.println();         
    }

    private void preOrder(Node<E> n) {
        if (n == null) return; 

        System.out.print(n.data + " "); 
        preOrder(n.left); 
        preOrder(n.right); 
    }
    
    // EJERCICIO 4----------------------	
    public void parenthesize() {
        parenthesizeRec(this.root, 0);
    }


    private void parenthesizeRec(Node<E> n, int level) {
        if (n == null) return;

        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        
        System.out.print(n.data);
        if (n.left != null || n.right != null) {
            System.out.println(" ("); 

            parenthesizeRec(n.left, level + 1);
            parenthesizeRec(n.right, level + 1);

            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println(")");
        } else {
            System.out.println();
        }
    }

}
