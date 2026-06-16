package graph;

// implementacion para grafo dirigido
public class DirectedGraphListEdge<E extends Comparable<E>>
        extends GraphListEdge<E> {

    // devuelve true porque las aristas tienen direccion
    @Override
    protected boolean isDirected() {
        return true;
    }

    // crea otro grafo dirigido del mismo tipo
    @Override
    protected GraphListEdge<E> createSameType() {
        return new DirectedGraphListEdge<>();
    }
}