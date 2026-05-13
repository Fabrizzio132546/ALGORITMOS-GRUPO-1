package lab07.actividades.actividad10;

class Node<T> {
    T data;
    Node<T> left;  // Puntero al subárbol de elementos menores
    Node<T> right; // Puntero al subárbol de elementos mayores

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
