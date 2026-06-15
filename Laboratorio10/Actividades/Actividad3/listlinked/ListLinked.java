package listlinked;

public class ListLinked<T extends Comparable<T>> {
    private Node<T> first;

    public ListLinked() {
        this.first = null;
    }
    
    public Node<T> getFirst() {
        return this.first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (!isEmpty()) {
            newNode.setNext(first);
        }
        first = newNode;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            first = newNode;
        } else {
            Node<T> actual = first;
            while (actual.getNext() != null) {
                actual = actual.getNext();
            }
            actual.setNext(newNode);
        }
    }

    public boolean search(T data) {
        if (isEmpty()) {
            return false;
        }
        Node<T> actual = first;
        while (actual.getNext() != null && actual.getData().compareTo(data) != 0) {
            actual = actual.getNext();
        }
        return actual.getData().compareTo(data) == 0;
    }

    public boolean removeNode(T data) {
        if (isEmpty()) {
            return false;
        }
        if (first.getData().compareTo(data) == 0) {
            first = first.getNext();
            return true;
        }
        Node<T> actual = first;
        while (actual.getNext() != null && actual.getNext().getData().compareTo(data) != 0) {
            actual = actual.getNext();
        }
        if (actual.getNext() == null) {
            return false;
        }
        actual.setNext(actual.getNext().getNext());
        return true;
    }
    
    public void insertInOrder(T data) {
        if (isEmpty() || data.compareTo(first.getData()) < 0) {
            insertFirst(data);
        } else {
            Node<T> actual = first;
            while (actual.getNext() != null && actual.getNext().getData().compareTo(data) < 0) {
                actual = actual.getNext();
            }
            Node<T> newNode = new Node<>(data);
            newNode.setNext(actual.getNext());
            actual.setNext(newNode);
        }
    }
    
    public void print() {
        Node<T> actual = this.first;
        System.out.print("[");
        while (actual != null) {
            System.out.print(actual.getData());
            if (actual.getNext() != null) {
                System.out.print(", ");
            }
            actual = actual.getNext();
        }
        System.out.println("]");
    }
    
    public int size() {
        int count = 0;
        Node<T> actual = first;

        while (actual != null) {
            count++;
            actual = actual.getNext();
        }

        return count;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Índice negativo: " + index);
        }

        Node<T> actual = first;
        int i = 0;

        while (actual != null && i < index) {
            actual = actual.getNext();
            i++;
        }

        if (actual == null) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        return actual.getData();
    }
}











