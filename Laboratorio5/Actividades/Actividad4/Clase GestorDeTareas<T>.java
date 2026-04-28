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
