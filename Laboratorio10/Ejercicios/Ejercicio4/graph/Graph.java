package graph;

import listlinked.ListLinked;

public interface Graph<V extends Comparable<V>, A extends Comparable<A>> {

    void insertVertex(V data);

    void insertEdge(V origin, V destination);

    boolean removeVertex(V data);

    boolean removeEdge(V origin, V destination);

    boolean searchVertex(V data);

    boolean searchEdge(V origin, V destination);

    ListLinked<V> adjacentVertices(V data);
}