package lab07.actividades.actividad9;
import exceptions.*;

public interface BinarySearchTree<E> {
    // La firma obliga a manejar los errores semánticos del BST
    void insert(E data) throws ItemDuplicated;
    E search (E data) throws ItemNotFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();
}
