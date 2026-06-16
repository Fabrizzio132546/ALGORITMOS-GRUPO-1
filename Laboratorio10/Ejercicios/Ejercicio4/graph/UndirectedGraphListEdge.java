package graph;

// implementacion para grafo no dirigido
public class UndirectedGraphListEdge<E extends Comparable<E>>
        extends GraphListEdge<E> {

    // devuelve false porque las aristas no tienen direccion
    @Override
    protected boolean isDirected() {
        return false;
    }

    // crea otro grafo no dirigido del mismo tipo
    @Override
    protected GraphListEdge<E> createSameType() {
        return new UndirectedGraphListEdge<>();
    }
}