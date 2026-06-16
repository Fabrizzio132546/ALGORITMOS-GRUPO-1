package lab10.actividades.actividad2;

import graph.*;

public class Main {
    public static void main(String[] args) {
        
        GraphLink<String> graph = new GraphLink<>();

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");
        graph.insertVertex("H");

        graph.insertEdge("C", "E");
        graph.insertEdge("A", "D");
        graph.insertEdge("A", "B");
        graph.insertEdge("D", "G");
        graph.insertEdge("B", "E");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "C");
        graph.insertEdge("B", "H");
        graph.insertEdge("B", "G");
        graph.insertEdge("C", "F");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "G");
        graph.insertEdge("F", "H");
        graph.insertEdge("A", "H");
        graph.insertEdge("D", "E");
        graph.insertEdge("D", "H");
        graph.insertEdge("E", "G");

        // Imprimimos la lista de adyacencia para verificar la estructura en memoria
        System.out.println("=== LISTA DE ADYACENCIA GENERADA ===");
        System.out.println(graph.toString());

        // 3. Hallamos el recorrido en Anchura (BFS) empezando por 'A'
        System.out.println("=== RECORRIDO EN ANCHURA (BFS) ===");
        System.out.print("Ruta: ");
        graph.BFS("A");
        
        System.out.println(); // Salto de línea por estética

        // 4. Hallamos el recorrido en Profundidad (DFS) empezando por 'A'
        System.out.println("=== RECORRIDO EN PROFUNDIDAD (DFS) ===");
        System.out.print("Ruta: ");
        graph.DFS("A");
    }
}