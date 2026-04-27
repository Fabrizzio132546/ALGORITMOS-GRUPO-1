package Ejercicios_1_6;

public class Ejercicios1_6 {
	
	// EJERCICIO 1
	public static <T extends Comparable<T>> boolean buscarElemento(ListLinked<T> lista, T valor) {
		// si se manda un valor nulo se retorna false porq no estara
		if(valor == null) return false;
		Node<T> actual = lista.getFirst();
		// recorre toda la lista hasta que acabe
        while (actual != null) { 
        	// se va comparando y verificando si el dato es igual a lo que se busca
            if (actual.d.compareTo(valor) == 0) {
                return true; 
            }
            actual = actual.next;
        }
        return false;
    }
	
	// EJERCICIO 2
	public static <T extends Comparable<T>> ListLinked<T> invertirLista(ListLinked<T> lista) {
    	// si la lista esta vacia entonces se borra
		if(lista == null) {
    		throw new IllegalArgumentException("La lista esta vacia");
    	}
		// se crea una lista para guardar los datos al reves
    	ListLinked<T> nuevaLista = new ListLinked<>();
    	
        Node<T> actual = lista.getFirst();
        // recorre toda la lista 
        while (actual != null) {
        	// sacamos de la lista original y se coloca en la nueva al incio
            nuevaLista.insertFirst(actual.d);
            actual = actual.next;
        }
        return nuevaLista; 
    }

	// EJERCICIO 3
	public static <T> Node<T> insertarAlFinal(Node<T> head, T valor) {
		// se verifica para cuando el valor es nulo
		if(valor == null) {
			throw new IllegalArgumentException("el valor es nulo");
		}
	    Node<T> nuevo = new Node<>(valor);
	    // asignacion del nuevo nodo como cabeza si la lista se encuentra vacia
	    if (head == null) {
	        return nuevo;
	    }
	    Node<T> actual = head;
	    // se busca el ultimo nodo
	    while (actual.next != null) {
	        actual = actual.next;
	    }
	    // se vincula del nuevo nodo al final de la secuencia
	    actual.next = nuevo;
	    return head;
	}
	
	// EJERCICIO 4
	public static <T> int contarNodos(Node<T> head) {
	    int contador = 0;
	    Node<T> actual = head;
	    // se recorre hasta llegar al final
	    while (actual != null) {
	        contador++;   
	        actual = actual.next;
	    }
	    return contador;
	}
	
	// EJERCICIO 5
	public static <T extends Comparable<T>> boolean sonIguales(ListLinked<T> lista1, ListLinked<T> lista2) {
	    // se comprueba si ambas apuntan al mismo objeto
		if (lista1 == lista2) return true;
		// se verifica si esta vacio
	    if (lista1 == null || lista2 == null) return false;

	    Node<T> actual1 = lista1.getFirst(); 
	    Node<T> actual2 = lista2.getFirst();
	    // comparando si ambos datos en cada posicion es igual
	    while (actual1 != null && actual2 != null) {
	        if (actual1.d.compareTo(actual2.d) != 0) {
	            return false; 
	        }
	        actual1 = actual1.next;
	        actual2 = actual2.next;
	    }
	    // se verifica que ambas listas acaben al mismo tiempo
	    // si ambas son null es verdadero
	    // si una no es null entonces esa es mas grande q la otra
	    return actual1 == null && actual2 == null;
	}
	
	// EJERCICIO 6
	public static <T extends Comparable<T>> ListLinked<T> concatenarListas(ListLinked<T> lista1, ListLinked<T> lista2) {
		if(lista1 == null|| lista2 == null) {
			throw new IllegalArgumentException("una de las dos lista o las dos esta vacia");
		}
	    ListLinked<T> listaunida = new ListLinked<>();
	    Node<T> actu1 = lista1.getFirst();
	    // se pasa los elementos de la primera lista 
	    if (lista1 != null) {
	        while (actu1 != null) {
	        	listaunida.insertLast(actu1.d); 
	            actu1 = actu1.next;
	        }
	    }
	    Node<T> actu2 = lista2.getFirst(); 
	    // se agrega los elementos de la segunda lista
	    if (lista2 != null) {
	        while (actu2 != null) {
	        	listaunida.insertLast(actu2.d);
	            actu2 =actu2.next;
	        }
	    }
	    // se retorna la lista
	    return listaunida;
	}
	
	public static void main(String[] args) {
		
		System.out.println("EJERCICIO 1: --------------------------------------------");
		System.out.println(" ");
		ListLinked<Integer> l1 = new ListLinked<>();
        l1.insertLast(10);
        l1.insertLast(20);
        l1.insertLast(30);

        boolean encontrado = buscarElemento(l1, 20); 
        System.out.println("existe el 20?: " + encontrado);
        
        boolean noExiste = buscarElemento(l1, 50);
        System.out.println("existe el 50?: " + noExiste);
        
        System.out.println(" ");
        System.out.println("EJERCICIO 2: --------------------------------------------");
        System.out.println(" ");
        
        ListLinked<Integer> l2 = new ListLinked<>();
        l2.insertLast(10);
        l2.insertLast(20);
        l2.insertLast(30);
        
        l2.print(); 
        
        
        System.out.println(" ");
        System.out.println("EJERCICIO 3: --------------------------------------------");
        System.out.println(" ");
        
        Node<Integer> head = null;

	    head = insertarAlFinal(head, 10); 

	    insertarAlFinal(head, 20); 
	    insertarAlFinal(head, 30);

	    Node<Integer> actual = head;
	    while (actual != null) {
	        System.out.print(actual.d + ", ");
	        actual = actual.next;
	    }
	    System.out.println("null");
        
	    System.out.println(" ");
        System.out.println("EJERCICIO 4: --------------------------------------------");
        System.out.println(" ");
        
        Node<Integer> l3 = new Node<>(10);
        l3.next = new Node<>(20);
        l3.next.next = new Node<>(30);
        int c1 = contarNodos(l3); 

        System.out.println("La lista tiene: " + c1 + " nodos");
        
        //otra forma con el anterior ejercicio
        Node<String> l4 = null;

        l4 = insertarAlFinal(l4, "A");
        l4 = insertarAlFinal(l4, "B");
        l4 = insertarAlFinal(l4, "C");
        l4 = insertarAlFinal(l4, "P");
        l4 = insertarAlFinal(l4, "Z");

        int c2 = contarNodos(l4);
        System.out.println("La lista tiene: " + c2 + " nodos");
        
        
        System.out.println(" ");
        System.out.println("EJERCICIO 5: --------------------------------------------");
        System.out.println(" ");
        
        ListLinked<Integer> L5 = new ListLinked<>();
        L5.insertLast(5); 
        L5.insertLast(3); 
        L5.insertLast(7);

	    ListLinked<Integer> L6 = new ListLinked<>();
	    L6.insertLast(5); 
	    L6.insertLast(3); 
	    L6.insertLast(7);

	    ListLinked<Integer> L7 = new ListLinked<>();
	    L7.insertLast(1); 
	    L7.insertLast(2); 

	    System.out.println("son iguales l1 y l2?: " + sonIguales(L5, L6)); 
	    System.out.println("con iguales l1 y l3?: " + sonIguales(L5, L7));
        
        
	    System.out.println(" ");
        System.out.println("EJERCICIO 6: --------------------------------------------");
        System.out.println(" ");
        
        ListLinked<String> letras1 = new ListLinked<>();
	    letras1.insertLast("A");
	    letras1.insertLast("B");

	    ListLinked<String> letras2 = new ListLinked<>();
	    letras2.insertLast("C");
	    letras2.insertLast("D");

	    // se unen las listas
	    ListLinked<String> combinada = concatenarListas(letras1, letras2);

	    System.out.print("la lista unida: ");
	    combinada.print();  
    }
}

