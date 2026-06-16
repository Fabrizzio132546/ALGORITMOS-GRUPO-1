package graph;

import listlinked.ListLinked;
import listlinked.Node;
import queuelink.QueueLink;

public class GraphLink<E extends Comparable<E>> implements Graph<E, Edge<E>> {

    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    @Override
    public void insertVertex(E data) {
        if (data == null) {
            return;
        }

        if (findVertex(data) != null) {
            return;
        }

        Vertex<E> vertex = new Vertex<>(data);
        graph.addLast(new AdjList<>(vertex));
    }

    @Override
    public void insertEdge(E origin, E destination) {
        if (origin == null || destination == null) {
            return;
        }

        if (origin.compareTo(destination) == 0) {
            return;
        }

        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return;
        }

        Edge<E> edge1 = new Edge<>(v2.getVertex());
        Edge<E> edge2 = new Edge<>(v1.getVertex());

        if (!v1.getEdges().search(edge1)) {
            v1.getEdges().addLast(edge1);
        }

        if (!v2.getEdges().search(edge2)) {
            v2.getEdges().addLast(edge2);
        }
    }

    @Override
    public boolean removeVertex(E data) {
        AdjList<E> vertexToRemove = findVertex(data);

        if (vertexToRemove == null) {
            return false;
        }

        while (!vertexToRemove.getEdges().isEmpty()) {
            Edge<E> edge = vertexToRemove.getEdges().getFirst().getData();

            Vertex<E> neighbor = edge.getDestination();

            AdjList<E> neighborAdj = findVertex(neighbor.getData());

            if (neighborAdj != null) {
                neighborAdj.getEdges().removeNode(
                        new Edge<>(vertexToRemove.getVertex())
                );
            }

            vertexToRemove.getEdges().removeNode(edge);
        }

        return graph.removeNode(vertexToRemove);
    }

    @Override
    public boolean removeEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return false;
        }

        boolean removed1 = v1.getEdges().removeNode(new Edge<>(v2.getVertex()));
        boolean removed2 = v2.getEdges().removeNode(new Edge<>(v1.getVertex()));

        return removed1 && removed2;
    }

    @Override
    public boolean searchVertex(E data) {
        return findVertex(data) != null;
    }

    @Override
    public boolean searchEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return false;
        }

        return v1.getEdges().search(new Edge<>(v2.getVertex()));
    }

    @Override
    public ListLinked<E> adjacentVertices(E data) {
        ListLinked<E> adjacent = new ListLinked<>();

        AdjList<E> vertex = findVertex(data);

        if (vertex == null) {
            return adjacent;
        }

        Node<Edge<E>> edgeNode = vertex.getEdges().getFirst();

        while (edgeNode != null) {
            E neighborData = edgeNode.getData().getDestination().getData();
            adjacent.addLast(neighborData);

            edgeNode = edgeNode.getNext();
        }

        return adjacent;
    }


    public void BFS(E startData) {
        AdjList<E> startVertex = findVertex(startData);
        if (startVertex == null) {
            return;
        }
        ListLinked<E> visited = new ListLinked<>();
        QueueLink<AdjList<E>> queue = new QueueLink<>();
        visited.addLast(startData);
        queue.enqueue(startVertex);
        while (!queue.isEmpty()) {
            AdjList<E> current = queue.dequeue();
            System.out.print(current.getVertex().getData() + " ");
            for (int i = 0; i < current.getEdges().size(); i++) {
                E neighbor = current.getEdges().get(i).getDestination().getData();
                if (!visited.search(neighbor)) {
                    visited.addLast(neighbor);
                    AdjList<E> next = findVertex(neighbor);
                    if (next != null) {
                        queue.enqueue(next);
                    }
                }
            }
        }
        System.out.println();
    }

    public void DFS(E startData) {
        AdjList<E> start = findVertex(startData);
        if (start == null) {
            return;
        }
        ListLinked<E> visited = new ListLinked<>();
        dfsrecursive(start, visited);
        System.out.println();
    }

    private void dfsrecursive(AdjList<E> current, ListLinked<E> visited) {
        E data = current.getVertex().getData();
        visited.addLast(data);
        System.out.print(data + " ");
        for (int i = 0; i < current.getEdges().size(); i++) {
            E neighbor = current.getEdges().get(i).getDestination().getData();
            if (!visited.search(neighbor)) {
                AdjList<E> next = findVertex(neighbor);
                if (next != null) {
                    dfsrecursive(next, visited);
                }
            }
        }
    }

    public boolean isConnected() {
        if (graph.isEmpty()) {
            return true;
        }

        ListLinked<E> visited = new ListLinked<>();
        QueueLink<AdjList<E>> queue = new QueueLink<>();

        AdjList<E> start = graph.getFirst().getData();

        visited.addLast(start.getVertex().getData());
        queue.enqueue(start);

        int count = 0;

        while (!queue.isEmpty()) {
            AdjList<E> current = queue.dequeue();
            count++;

            Node<Edge<E>> edgeNode = current.getEdges().getFirst();

            while (edgeNode != null) {
                E neighbor = edgeNode.getData().getDestination().getData();

                if (!visited.search(neighbor)) {
                    visited.addLast(neighbor);

                    AdjList<E> next = findVertex(neighbor);

                    if (next != null) {
                        queue.enqueue(next);
                    }
                }

                edgeNode = edgeNode.getNext();
            }
        }

        return count == graph.size();
    }

    private AdjList<E> findVertex(E data) {
        if (data == null) {
            return null;
        }

        Node<AdjList<E>> actual = graph.getFirst();

        while (actual != null) {
            AdjList<E> adj = actual.getData();

            if (adj.getVertex().getData().compareTo(data) == 0) {
                return adj;
            }

            actual = actual.getNext();
        }

        return null;
    }

    @Override
    public String toString() {
        String result = "";

        Node<AdjList<E>> actual = graph.getFirst();

        while (actual != null) {
            AdjList<E> adj = actual.getData();

            result += adj.getVertex() + " -> ";

            Node<Edge<E>> edgeNode = adj.getEdges().getFirst();

            while (edgeNode != null) {
                result += edgeNode.getData() + " ";
                edgeNode = edgeNode.getNext();
            }

            result += "\n";

            actual = actual.getNext();
        }

        return result;
    }
}









