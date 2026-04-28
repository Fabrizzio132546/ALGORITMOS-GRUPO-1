// 3. Clase ListLinked<T>
// Exigimos que T sea Comparable para poder usar compareTo() en sus métodos
class ListLinked<T extends Comparable<T>> {
    Node<T> head;

    public boolean isEmptyList() {
        return head == null;
    }

    public void insertFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = head;
        head = newNode;
    }

    public void insertLast(T x) {
        Node<T> newNode = new Node<>(x);
        if (isEmptyList()) {
            head = newNode;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public boolean removeNode(T x) {
        if (isEmptyList()) return false;
        
        // Uso de compareTo en lugar de equals para la cabeza
        if (head.value.compareTo(x) == 0) {
            head = head.next;
            return true;
        }
        
        Node<T> current = head;
        // Uso de compareTo en lugar de equals para el resto de la lista
        while (current.next != null && current.next.value.compareTo(x) != 0) {
            current = current.next;
        }
        
        if (current.next != null) {
            current.next = current.next.next;
            return true;
        }
        return false;
    }

    public boolean search(T x) {
        Node<T> current = head;
        while (current != null) {
            // Uso de compareTo en lugar de equals
            if (current.value.compareTo(x) == 0) return true;
            current = current.next;
        }
        return false;
    }

    public int length() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        
        while (current != null) {
            next = current.next; 
            current.next = prev; 
            prev = current;      
            current = next;      
        }
        head = prev; 
    }
}

