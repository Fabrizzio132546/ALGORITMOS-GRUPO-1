package listlinked;

public class ListLinked<E> {

    private Node<E> first;
    public ListLinked() {
        this.first = null;
    }
    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
        } else {
            Node<E> actual = first;
            while (actual.getNext() != null) {
                actual = actual.getNext();
            }
            actual.setNext(newNode);
        }
    }

    public int size() {
        int count = 0;
        Node<E> actual = first;
        while (actual != null) {
            count++;
            actual = actual.getNext(); 
        }
        return count;
    }

    public E get(int index) {
        if (index < 0) return null;
        
        Node<E> actual = first;
        for (int i = 0; i < index; i++) {
            if (actual == null) return null;
            actual = actual.getNext(); 
        }
        
        return actual != null ? actual.getData() : null; 
    }

    public E removeAt(int index) {
        if (index < 0 || first == null) return null;

        if (index == 0) {
            E data = first.getData();
            first = first.getNext();
            return data;
        }

        Node<E> previous = first;
        for (int i = 0; i < index - 1; i++) {
            if (previous.getNext() == null) return null;
            previous = previous.getNext();
        }

        Node<E> current = previous.getNext();
        if (current == null) return null;

        previous.setNext(current.getNext());
        return current.getData();
    }

    public boolean isEmpty() {
        return first == null;
    }
}
