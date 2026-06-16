package graph;

// representa una arista dentro de GraphListEdge
public class EdgeList<E extends Comparable<E>> implements Comparable<EdgeList<E>> {

    // vertice desde donde sale la arista
    private Vertex<E> origin;

    // vertice al que llega la arista
    private Vertex<E> destination;

    // peso de la arista
    private int weight;

    // constructor para aristas sin peso indicado
    public EdgeList(Vertex<E> origin, Vertex<E> destination) {
        this(origin, destination, 1);
    }

    // constructor para aristas con peso indicado
    public EdgeList(Vertex<E> origin, Vertex<E> destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    // devuelve el vertice de origen
    public Vertex<E> getOrigin() {
        return origin;
    }

    // devuelve el vertice destino
    public Vertex<E> getDestination() {
        return destination;
    }

    // devuelve el peso de la arista
    public int getWeight() {
        return weight;
    }

    // compara primero por origen y luego por destino
    @Override
    public int compareTo(EdgeList<E> other) {
        int compareOrigin = this.origin.compareTo(other.origin);

        if (compareOrigin != 0) {
            return compareOrigin;
        }

        return this.destination.compareTo(other.destination);
    }

    // muestra la arista con su peso
    @Override
    public String toString() {
        return origin + " -> " + destination + " (" + weight + ")";
    }
}