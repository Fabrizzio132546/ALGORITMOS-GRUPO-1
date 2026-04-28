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
