package graph;

// pruebas basicas del ejercicio 4
public class TestEjercicio4 {

    public static void main(String[] args) {

        System.out.println("===== GRAFO DIRIGIDO =====");

        // se crea un grafo dirigido
        DirectedGraphListEdge<String> g1 = new DirectedGraphListEdge<>();

        // vertices del primer grafo
        g1.insertVertex("A");
        g1.insertVertex("B");
        g1.insertVertex("C");

        // aristas del primer grafo
        g1.insertEdge("A", "B");
        g1.insertEdge("B", "C");
        g1.insertEdge("C", "A");

        System.out.println(g1);
        System.out.println("¿Es conexo?: " + g1.isConexo());

        // segundo grafo con otra forma de nombrar vertices
        DirectedGraphListEdge<String> g2 = new DirectedGraphListEdge<>();

        g2.insertVertex("X");
        g2.insertVertex("Y");
        g2.insertVertex("Z");

        g2.insertEdge("X", "Y");
        g2.insertEdge("Y", "Z");
        g2.insertEdge("Z", "X");

        // se compara si tienen la misma estructura
        System.out.println("¿g1 es isomorfo con g2?: " + g1.isIsomorfo(g2));

        System.out.println("\n===== GRAFO DIRIGIDO AUTOCOMPLEMENTARIO =====");

        // grafo pequeño para probar autocomplementario
        DirectedGraphListEdge<String> g3 = new DirectedGraphListEdge<>();

        g3.insertVertex("A");
        g3.insertVertex("B");

        g3.insertEdge("A", "B");

        System.out.println(g3);
        System.out.println("¿Es autocomplementario?: " + g3.isAutoComplementario());

        System.out.println("\n===== GRAFO NO DIRIGIDO =====");

        // se crea un grafo no dirigido
        UndirectedGraphListEdge<Integer> g4 = new UndirectedGraphListEdge<>();

        g4.insertVertex(1);
        g4.insertVertex(2);
        g4.insertVertex(3);
        g4.insertVertex(4);

        // camino simple de cuatro vertices
        g4.insertEdge(1, 2);
        g4.insertEdge(2, 3);
        g4.insertEdge(3, 4);

        System.out.println(g4);
        System.out.println("¿Es conexo?: " + g4.isConexo());
        System.out.println("¿Es autocomplementario?: " + g4.isAutoComplementario());

        System.out.println("\n===== GRAFO PONDERADO =====");

        // ejemplo usando pesos
        UndirectedGraphListEdge<String> ciudades = new UndirectedGraphListEdge<>();

        ciudades.insertVertex("Arequipa");
        ciudades.insertVertex("Cusco");
        ciudades.insertVertex("Puno");

        // aristas con peso indicado
        ciudades.insertEdge("Arequipa", "Cusco", 510);
        ciudades.insertEdge("Cusco", "Puno", 390);

        System.out.println(ciudades);
        System.out.println("¿Es conexo?: " + ciudades.isConexo());
    }
}