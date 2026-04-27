package Ejercicio7;

public class SortedListLinked<T extends Comparable<T>> extends ListLinked<T> {

    public void insertOrden(T x) {
    	// se valida que el dato no sea nulo
    	if(x == null)  throw new IllegalArgumentException("el valor x es nulo");
    	// se inserta al incio si la lista esta vacia o si el valor es el menor
        if (isEmpty() || x.compareTo(getFirst().d) < 0) {
            insertFirst(x); 
        } else {
            Node<T> actual = getFirst();
            // avance del puntero mientras el valor sea mayor al siguiente nodo
            while (actual.next != null && x.compareTo(actual.next.d) > 0) {
                actual = actual.next;
            }
         // creacion del nuevo nodo y ajuste de las referencias para su integracion
            Node<T> nuevo = new Node<>(x);
            nuevo.next = actual.next;
            actual.next = nuevo;
        }
    }
    
    public static void main(String[] args) {
        SortedListLinked<Integer> listaOrdenada = new SortedListLinked<>();
        
        listaOrdenada.insertOrden(55);
        listaOrdenada.insertOrden(14);
        listaOrdenada.insertOrden(3);
        listaOrdenada.insertOrden(30009);
        // listaOrdenada.insertOrden(null);

        listaOrdenada.print(); 
    }
    
}
