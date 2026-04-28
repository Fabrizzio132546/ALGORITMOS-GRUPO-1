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
