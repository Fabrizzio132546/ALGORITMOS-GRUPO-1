
package Actividad5;

import Actividad4.*;
// creacion de una interfaz para poder implementarlo en nuestro LinedkBST
public interface BinarySearchTree<E> {
	// para insertar un dato
	void insert(E data) throws ItemDuplicated;
	// para buscar un dato
	E search(E data) throws ItemNotFound;
	// para elimiar un dato
	void delete(E data) throws ExceptionIsEmpty;
	// para verificar si esta vacio
	boolean isEmpty();

}
