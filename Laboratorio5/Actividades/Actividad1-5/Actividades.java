// 1. Clase Node<T>
class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}

// 2. Clase Tarea
class Tarea implements Comparable<Tarea> {
    private String titulo;
    private int prioridad; // 1 = alta, 2 = media, 3 = baja
    private String estado; 

    public Tarea(String titulo, int prioridad, String estado) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public String getTitulo() { return titulo; }
    public String getEstado() { return estado; }

    // IMPORTANTE: Compara la prioridad para establecer un "orden" entre tareas.
    // Retorna negativo si 'this' tiene un número menor (es decir, mayor prioridad).
    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    @Override
    public String toString() {
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }

    // IMPORTANTE: Sobrescribir equals cambia la regla de igualdad. 
    // Ahora Java sabe que si buscamos o eliminamos una tarea con el mismo TÍTULO, 
    // es la misma tarea, aunque sea una instancia diferente en memoria.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarea tarea = (Tarea) obj;
        return titulo.equals(tarea.titulo); 
    }
}

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

// 4. Clase GestorDeTareas<T>
// IMPORTANTE: <T extends Comparable<T>> asegura que el objeto que maneje 
// este gestor obligatoriamente tenga el método compareTo().
class GestorDeTareas<T extends Comparable<T>> {
    ListLinked<T> lista;

    public GestorDeTareas() {
        lista = new ListLinked<>();
    }

    public void agregarTarea(T tarea) { lista.insertLast(tarea); }
    public boolean eliminarTarea(T tarea) { return lista.removeNode(tarea); }
    public boolean contieneTarea(T tarea) { return lista.search(tarea); }
    public void imprimirTareas() { lista.print(); }
    public int contarTareas() { return lista.length(); }
    public void invertirTareas() { lista.reverse(); }

    // LÓGICA: Recorre la lista guardando el elemento más pequeño (según compareTo).
    // Como prioridad 1 es menor que 2 o 3 matemáticamente, halla la más prioritaria.
    public T obtenerTareaMasPrioritaria() {
        if (lista.isEmptyList()) return null;
        
        Node<T> current = lista.head;
        T masPrioritaria = current.value;

        while (current != null) {
            if (current.value.compareTo(masPrioritaria) < 0) {
                masPrioritaria = current.value;
            }
            current = current.next;
        }
        return masPrioritaria;
    }
}

// 5. Programa principal (Main)
public class Main {
    public static void main(String[] args) {
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        gestor.agregarTarea(new Tarea("Diseñar BD", 2, "pendiente"));
        gestor.agregarTarea(new Tarea("Deploy produccion", 1, "pendiente"));
        gestor.agregarTarea(new Tarea("Documentar API", 3, "completada"));
        gestor.agregarTarea(new Tarea("Code review", 2, "pendiente"));
        gestor.agregarTarea(new Tarea("Corregir bug #42", 1, "completada"));

        System.out.println("--- Tareas Iniciales ---");
        gestor.imprimirTareas();

        System.out.println("\n--- Eliminando 'Code review' ---");
        gestor.eliminarTarea(new Tarea("Code review", 2, "pendiente"));
        gestor.imprimirTareas();

        System.out.println("\n--- Mas prioritaria ---");
        System.out.println(gestor.obtenerTareaMasPrioritaria());

        System.out.println("\n--- Lista Invertida ---");
        gestor.invertirTareas();
        gestor.imprimirTareas();
        
        System.out.println("\n--- Tareas Completadas (Nueva Lista) ---");
        ListLinked<Tarea> listaCompletadas = new ListLinked<>();
        Node<Tarea> current = gestor.lista.head;
        while (current != null) {
            if ("completada".equals(current.value.getEstado())) {
                listaCompletadas.insertLast(current.value); 
            }
            current = current.next;
        }
        listaCompletadas.print(); 
    }
}
