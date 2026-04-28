// 3. Clase ListLinked<T>
class ListLinked<T> {
    Node<T> head;

    public boolean isEmptyList() {
        return head == null;
    }

    public void insertFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = head;
        head = newNode;
    }

    // LÓGICA: Recorre la lista hasta que el 'next' de un nodo sea null (el último).
    // Ahí enlaza el nuevo nodo.
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

    // LÓGICA: Se detiene en el nodo ANTERIOR al que queremos eliminar.
    // Luego "puentea" el nodo objetivo (current.next = current.next.next).
    public boolean removeNode(T x) {
        if (isEmptyList()) return false;
        
        if (head.value.equals(x)) {
            head = head.next;
            return true;
        }
        
        Node<T> current = head;
        while (current.next != null && !current.next.value.equals(x)) {
            current = current.next;
        }
        
        if (current.next != null) {
            current.next = current.next.next; // Desvinculación
            return true;
        }
        return false;
    }

    public boolean search(T x) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(x)) return true; // Usa el equals() de Tarea
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

    // LÓGICA: Usa 3 punteros (prev, current, next) para no romper la cadena 
    // mientras voltea la flecha (next) de cada nodo hacia atrás.
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        
        while (current != null) {
            next = current.next; 
            current.next = prev; // Inversión real
            prev = current;      
            current = next;      
        }
        head = prev; // La antigua cola ahora es la cabeza
    }
}
