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
	
    // ACTIVIDAD 8----------------------	
	// Imprimir el título y empezar la cadena de visitas
    public void preOrder() {
		
        System.out.print("PreOrder: "); 
		// Le decimos a la funcion recursiva que empiece desde la raíz
        preOrder(this.root);          
        System.out.println();         
    }
	// esta es la funcion recursiva
    private void preOrder(Node<E> n) {
		// caso base
        if (n == null) return; 
		// Mostramos el dato de la raiz
        System.out.print(n.data + " "); 
		// analiza todo el camino de la izquierda
        preOrder(n.left); 
		// cuando termian con la izquierda empieza con al derecha
        preOrder(n.right); 
    }
    
    // EJERCICIO 4----------------------	
	// Método público que inicia la visualización jerárquica desde la raíz con nivel cero
    public void parenthesize() {
        parenthesizeRec(this.root, 0);
    }
	// Procesa el árbol de forma recursiva aplicando sangría y paréntesis según la profundidad
    private void parenthesizeRec(Node<E> n, int level) {
		// Finaliza la ejecución si el nodo actual no existe
        if (n == null) return;
		// Imprime espacios proporcionales al nivel actual para representar la sangría
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        // Muestra el dato del nodo y verifica si tiene descendientes para abrir la agrupación
        System.out.print(n.data);
        if (n.left != null || n.right != null) {
            System.out.println(" ("); 
			// Explora los subárboles izquierdo y derecho incrementando el nivel de profundidad
            parenthesizeRec(n.left, level + 1);
            parenthesizeRec(n.right, level + 1);
			// Repite la sangría antes de cerrar la agrupación del nodo padre actual
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println(")");
        } else {
			// Finaliza la línea si el nodo es una hoja sin descendientes
            System.out.println();
        }
    }
    
    // EJERCICIO 5--------------------------
	// Inicia la búsqueda de elementos dentro de un rango de valores específico
    public String searchRange(E min, E max) {
        return searchRangeRec(this.root, min, max);
    }
	// Filtra el árbol recursivamente para encontrar nodos entre los límites min y max
    private String searchRangeRec(Node<E> n, E min, E max) {
		// Retorna cadena vacía si el nodo es nulo o llegamos al final de una rama
        if (n == null) {
            return "";
        }
        String resultado = "";
		// Si el valor mínimo es menor que el dato actual, explora el subárbol izquierdo
        if (min.compareTo(n.data) < 0) {
            resultado += searchRangeRec(n.left, min, max);
        }
		// Si el dato actual está dentro del rango inclusivo, lo añade al resultado
        if (min.compareTo(n.data) <= 0 && max.compareTo(n.data) >= 0) {
            resultado += "\n   " + n.data.toString();
        }
		// Si el valor máximo es mayor que el dato actual, explora el subárbol derecho
        if (max.compareTo(n.data) > 0) {
            resultado += searchRangeRec(n.right, min, max);
        }

        return resultado;
    }
	// Retorna la cantidad de hojas
    public int countLeaves() {
     	return countLeavesRec(this.root);
 	}
	// Suma 1 si el nodo es una hoja o 0 si es nulo, recorriendo todo el árbol
 	private int countLeavesRec(Node<E> n) {
 		if (n == null) return 0;
		// Un nodo es hoja si sus dos referencias a hijos son nula
 		if (n.left == null && n.right == null) return 1;
     	return countLeavesRec(n.left) + countLeavesRec(n.right);
 	}
	// Muestra por consola los datos del árbol ordenados de mayor a menor
 	public void printDescending() {
     	System.out.print("Productos en descendente: ");
     	printDescendingRec(this.root);
     	System.out.println();
 	}
	// Realiza un recorrido In-Order inverso
 	private void printDescendingRec(Node<E> n) {
 		if (n == null) return;
		// Procesa primero los valores mayores, derecha, para lograr el orden descendente
 		printDescendingRec(n.right);
 		System.out.print(n.data + " ");
 		printDescendingRec(n.left);
 	}
 	
 	
 // GUI ------------------
	// Genera una cadena de texto con los elementos en orden descendente
 	public String getDescendingString() {
 	    StringBuilder sb = new StringBuilder();
 	    getDescendingRec(this.root, sb);
 	    return sb.toString();
 	}
	// Acumula los datos en un StringBuilder siguiendo el recorrido derecha-raíz-izquierda
 	private void getDescendingRec(Node<E> n, StringBuilder sb) {
 	    if (n == null) return;
 	    getDescendingRec(n.right, sb);
 	    // Concatena el dato con formato de salto de línea y sangría para la interfaz
 	    sb.append("\n   ").append(n.data.toString());
 	    getDescendingRec(n.left, sb);
 	}

}
