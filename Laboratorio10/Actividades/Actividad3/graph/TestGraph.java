package graph;

public class TestGraph {

    public static void main(String[] args) {
        GraphLink<String> g = new GraphLink<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");

        System.out.println(g);
        System.out.println("BFS desde A: " + g.BFS("A"));
        System.out.println("DFS desde A: " + g.DFS("A"));
        System.out.println("Es conexo: " + g.isConnected());

        g.removeEdge("A", "C");
        System.out.println("Despues de eliminar la arista A-C:");
        System.out.println(g);

        g.removeVertex("D");
        System.out.println("Despues de eliminar el vertice D:");
        System.out.println(g);
    }
}
