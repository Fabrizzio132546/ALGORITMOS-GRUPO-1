package lab10.ejercicios.ejercicio1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        GraphLink<String> mapa = new GraphLink<>();

        mapa.insertVertex("A");
        mapa.insertVertex("B");
        mapa.insertVertex("C");
        mapa.insertVertex("D");
        mapa.insertVertex("E");

        mapa.insertEdgeWeight("A", "B", 10);
        mapa.insertEdgeWeight("A", "C", 3);
        mapa.insertEdgeWeight("C", "B", 4);
        mapa.insertEdgeWeight("C", "E", 2);
        mapa.insertEdgeWeight("C", "D", 8);
        mapa.insertEdgeWeight("E", "D", 3);
        mapa.insertEdgeWeight("B", "D", 2);

        System.out.println("=== ESTRUCTURA DEL GRAFO (Lista de Adyacencia) ===");
        System.out.println(mapa.toString());

        System.out.println("=== PRUEBA DE CONEXIÓN ===");
        System.out.println("¿El grafo es conexo?: " + mapa.isConexo());
        System.out.println();

        System.out.println("=== PRUEBA RUTA MÁS CORTA (DIJKSTRA) ===");
        String origen = "A";
        String destino = "D";
        
        ArrayList<String> ruta = mapa.shortPath(origen, destino);
        
        System.out.print("La mejor ruta de " + origen + " a " + destino + " es: ");
        if (ruta.isEmpty()) {
            System.out.println("No hay ruta disponible.");
        } else {
            for (int i = 0; i < ruta.size(); i++) {
                System.out.print(ruta.get(i));
                if (i < ruta.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}