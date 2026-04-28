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
    public int getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }

    // El compareTo ahora define la "identidad" de la tarea basándose en el título
    @Override
    public int compareTo(Tarea otra) {
        return this.titulo.compareTo(otra.titulo);
    }

    @Override
    public String toString() {
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }
}

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

// 4. Clase GestorDeTareas<T>
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

    public T obtenerTareaMasPrioritaria() {
        if (lista.isEmptyList()) return null;
        
        Node<T> current = lista.head;
        T masPrioritaria = current.value;

        while (current != null) {
            // Validamos que los objetos sean de tipo Tarea para comparar sus prioridades numéricas
            if (current.value instanceof Tarea && masPrioritaria instanceof Tarea) {
                Tarea tActual = (Tarea) current.value;
                Tarea tMax = (Tarea) masPrioritaria;
                
                // Prioridad 1 es mejor que 2
                if (tActual.getPrioridad() < tMax.getPrioridad()) {
                    masPrioritaria = current.value;
                }
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

        System.out.println("\n--- Verificando existencia ---");
        System.out.println("¿Existe 'Diseñar BD'?: " + gestor.contieneTarea(new Tarea("Diseñar BD", 2, "pendiente")));

        System.out.println("\n--- Mas prioritaria ---");
        System.out.println("Mas prioritaria: " + gestor.obtenerTareaMasPrioritaria());

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
