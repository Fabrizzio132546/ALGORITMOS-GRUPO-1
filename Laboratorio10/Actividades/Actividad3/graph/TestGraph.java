package graph;

public class TestGraph {
    public static void main(String[] args) {
        GraphLink<String> g = new GraphLink<>();
        System.out.println("Insertando vertices:");
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertVertex("A");
        System.out.println("\nInsertando aristas:");
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");
        g.insertEdge("C", "E");
        g.insertEdge("A", "F");
        System.out.println("\nGrafo inicial:");
        System.out.println(g);
        System.out.println("Recorrido BFS desde A:");
        g.BFS("A");
        System.out.println("Recorrido DFS desde A:");
        g.DFS("A");
        System.out.println("El grafo esta conectado: " + g.isConnected());
        System.out.println("\nEliminando la arista A-C:");
        g.removeEdge("A", "C");
        System.out.println(g);
        System.out.println("El grafo esta conectado: " + g.isConnected());
        System.out.println("\nAgregando la arista D-C:");
        g.insertEdge("D", "C");
        System.out.println(g);
        System.out.println("El grafo esta conectado: " + g.isConnected());
        System.out.println("\nEliminando el vertice E:");
        g.removeVertex("E");
        System.out.println(g);
        System.out.println("Recorrido BFS final desde A:");
        g.BFS("A");
        System.out.println("Recorrido DFS final desde A:");
        g.DFS("A");
    }
}
