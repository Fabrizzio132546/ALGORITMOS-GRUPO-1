package Lab06.actividades.actividad3;

public class Test {
    public static void main(String[] args) {
        PriorityQueueLinkSort<String, Integer> pq = new PriorityQueueLinkSort<>();

        System.out.println("--- INICIANDO PRUEBAS DE COLA DE PRIORIDAD ---");

        try {
            System.out.println("\nInsertando elementos...");
            pq.enqueue("Paciente C (Resfriado)", 1);
            pq.enqueue("Paciente A (Ataque al corazón)", 5);
            pq.enqueue("Paciente B (Hueso roto)", 3);
            pq.enqueue("Paciente D (Corte profundo)", 3);
            
            System.out.println("\nEstado de la cola (Mayor a menor prioridad):");
            System.out.println(pq.toString());

            System.out.println("\nProbar first() [Debería ser Paciente A]:");
            System.out.println("-> " + pq.first());

            System.out.println("\nProbar last() [Debería ser Paciente C]:");
            System.out.println("-> " + pq.last());
            
            System.out.println("\nProbar dequeue() [Atendiendo al más urgente]:");
            System.out.println("-> Se atendió a: " + pq.dequeue());

            System.out.println("\nEstado de la cola después del dequeue:");
            System.out.println(pq.toString());
            
            System.out.println("\nProbar dequeue() otra vez [Debería ser Paciente B antes que D]:");
            System.out.println("-> Se atendió a: " + pq.dequeue());
            System.out.println("Estado final de la cola: \n" + pq.toString());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}