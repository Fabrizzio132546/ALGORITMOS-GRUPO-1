package Ejer02;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;

public class MainEjercicio2 {

    public static void main(String[] args) {
        // Instanciamos nuestro gestor de la red de ciudades
        RedCiudades red = new RedCiudades();

        // 1. Agregar ciudades (Vértices)
        red.agregarCiudad("Arequipa");
        red.agregarCiudad("Cusco");
        red.agregarCiudad("Puno");
        red.agregarCiudad("Tacna");
        red.agregarCiudad("Moquegua");

        // 2. Agregar carreteras con su distancia en km (Aristas ponderadas)
        red.agregarCarretera("Arequipa", "Cusco", 510);
        red.agregarCarretera("Arequipa", "Moquegua", 230);
        red.agregarCarretera("Moquegua", "Tacna", 160);
        red.agregarCarretera("Cusco", "Puno", 390);
        red.agregarCarretera("Puno", "Tacna", 420);

        System.out.println("=========================================");
        System.out.println(" MODELADO DE RED DE CIUDADES - JGRAPHT");
        System.out.println("=========================================\n");

        // 3. Mostrar la lista de ciudades registradas
        red.mostrarCiudades();

        // 4. Mostrar todas las conexiones (carreteras) del grafo
        red.mostrarCarreteras();

        System.out.println("=========================================");
        
        // 5. Calcular y mostrar el camino más corto usando Dijkstra
        // Ejemplo: Desde Arequipa hasta Tacna
        red.calcularCaminoMasCorto("Arequipa", "Tacna");
        
        System.out.println();
        
        // Prueba adicional: Desde Cusco hasta Moquegua
        red.calcularCaminoMasCorto("Cusco", "Moquegua");
    }
}

/**
 * Clase que encapsula la lógica de JGraphT para gestionar la red de ciudades.
 */
class RedCiudades {

    // Se declara un grafo ponderado y no dirigido utilizando JGraphT
    private Graph<String, DefaultWeightedEdge> grafo;

    public RedCiudades() {
        // Inicializamos el grafo como un SimpleWeightedGraph
        // Los vértices serán de tipo String y las aristas DefaultWeightedEdge
        this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    /**
     * Agrega una nueva ciudad al grafo (Vértice).
     * @param ciudad Nombre de la ciudad.
     */
    public void agregarCiudad(String ciudad) {
        grafo.addVertex(ciudad);
    }

    /**
     * Agrega una carretera entre dos ciudades con una distancia específica (Arista ponderada).
     * @param origen Ciudad de inicio.
     * @param destino Ciudad de destino.
     * @param distancia Distancia en kilómetros (Peso).
     */
    public void agregarCarretera(String origen, String destino, double distancia) {
        // Se crea la arista entre el origen y el destino
        DefaultWeightedEdge carretera = grafo.addEdge(origen, destino);
        
        // Si la carretera se creó exitosamente (no existía previamente), se le asigna el peso
        if (carretera != null) {
            grafo.setEdgeWeight(carretera, distancia);
        }
    }

    /**
     * Muestra en consola la lista de todos los vértices (ciudades).
     */
    public void mostrarCiudades() {
        System.out.println("-> LISTA DE CIUDADES:");
        for (String ciudad : grafo.vertexSet()) {
            System.out.println(" - " + ciudad);
        }
        System.out.println();
    }

    /**
     * Muestra en consola todas las aristas (carreteras) y su distancia.
     */
    public void mostrarCarreteras() {
        System.out.println("-> CARRETERAS REGISTRADAS:");
        for (DefaultWeightedEdge carretera : grafo.edgeSet()) {
            String origen = grafo.getEdgeSource(carretera);
            String destino = grafo.getEdgeTarget(carretera);
            double distancia = grafo.getEdgeWeight(carretera);
            
            System.out.println(" - De " + origen + " a " + destino + " : " + distancia + " km");
        }
        System.out.println();
    }

    /**
     * Calcula la ruta más corta entre dos ciudades utilizando el algoritmo de Dijkstra.
     * @param origen Ciudad de partida.
     * @param destino Ciudad de llegada.
     */
    public void calcularCaminoMasCorto(String origen, String destino) {
        // Verificamos que ambas ciudades existan en el grafo
        if (!grafo.containsVertex(origen) || !grafo.containsVertex(destino)) {
            System.out.println("Error: Una o ambas ciudades no están registradas en la red.");
            return;
        }

        System.out.println("-> CALCULANDO RUTA MÁS CORTA (DIJKSTRA): " + origen + " -> " + destino);

        // Instanciamos el algoritmo Dijkstra de JGraphT
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);
        
        // Obtenemos el camino entre el origen y el destino
        GraphPath<String, DefaultWeightedEdge> camino = dijkstra.getPath(origen, destino);

        if (camino == null) {
            System.out.println("No existe una ruta posible entre " + origen + " y " + destino);
            return;
        }

        // Obtenemos la lista de ciudades que componen la ruta
        List<String> ciudadesRuta = camino.getVertexList();
        
        // Imprimimos la ruta paso a paso
        System.out.print("Ruta óptima: ");
        for (int i = 0; i < ciudadesRuta.size(); i++) {
            System.out.print(ciudadesRuta.get(i));
            if (i < ciudadesRuta.size() - 1) {
                System.out.print(" -> ");
            }
        }
        
        // Imprimimos el costo total (distancia)
        System.out.println("\nCosto total (Distancia): " + camino.getWeight() + " km");
    }
}
