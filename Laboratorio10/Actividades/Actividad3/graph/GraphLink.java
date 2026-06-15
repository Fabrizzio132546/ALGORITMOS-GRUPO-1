package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import listlinked.ListLinked;

public class GraphLink<E> {

    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (findVertex(data) != null) {
            return;
        }

        Vertex<E> vertex = new Vertex<>(data);

        graph.addLast(new AdjList<>(vertex));
    }

    private AdjList<E> findVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);

            if (Objects.equals(adj.getVertex().getData(), data)) {
                return adj;
            }
        }

        return null;
    }

    public void insertEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return;
        }

        if (!edgeExists(v1, destination)) {
            v1.getEdges().addLast(new Edge<>(v2.getVertex()));
        }

        if (!edgeExists(v2, origin)) {
            v2.getEdges().addLast(new Edge<>(v1.getVertex()));
        }
    }

    public void removeVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);

            if (Objects.equals(adj.getVertex().getData(), data)) {
                graph.removeAt(i);
                break;
            }
        }

        for (int i = 0; i < graph.size(); i++) {
            removeEdgeFromList(graph.get(i), data);
        }
    }

    public void removeEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return;
        }

        removeEdgeFromList(v1, destination);
        removeEdgeFromList(v2, origin);
    }

    public String BFS(E start) {
        AdjList<E> startVertex = findVertex(start);

        if (startVertex == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Set<E> visited = new HashSet<>();
        Queue<AdjList<E>> queue = new LinkedList<>();

        visited.add(startVertex.getVertex().getData());
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            AdjList<E> current = queue.poll();
            E currentData = current.getVertex().getData();

            appendVisit(result, currentData);

            for (int i = 0; i < current.getEdges().size(); i++) {
                E nextData = current.getEdges().get(i).getDestination().getData();

                if (!visited.contains(nextData)) {
                    AdjList<E> nextVertex = findVertex(nextData);

                    if (nextVertex != null) {
                        visited.add(nextData);
                        queue.add(nextVertex);
                    }
                }
            }
        }

        return result.toString();
    }

    public String DFS(E start) {
        AdjList<E> startVertex = findVertex(start);

        if (startVertex == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Set<E> visited = new HashSet<>();

        DFS(startVertex, visited, result);

        return result.toString();
    }

    public boolean isConnected() {
        if (graph.isEmpty()) {
            return true;
        }

        return countReachableVertices(graph.get(0)) == graph.size();
    }

    private void DFS(AdjList<E> current, Set<E> visited, StringBuilder result) {
        E currentData = current.getVertex().getData();

        visited.add(currentData);
        appendVisit(result, currentData);

        for (int i = 0; i < current.getEdges().size(); i++) {
            E nextData = current.getEdges().get(i).getDestination().getData();

            if (!visited.contains(nextData)) {
                AdjList<E> nextVertex = findVertex(nextData);

                if (nextVertex != null) {
                    DFS(nextVertex, visited, result);
                }
            }
        }
    }

    private boolean edgeExists(AdjList<E> origin, E destination) {
        for (int i = 0; i < origin.getEdges().size(); i++) {
            E current = origin.getEdges().get(i).getDestination().getData();

            if (Objects.equals(current, destination)) {
                return true;
            }
        }

        return false;
    }

    private int countReachableVertices(AdjList<E> startVertex) {
        Set<E> visited = new HashSet<>();
        Queue<AdjList<E>> queue = new LinkedList<>();

        visited.add(startVertex.getVertex().getData());
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            AdjList<E> current = queue.poll();

            for (int i = 0; i < current.getEdges().size(); i++) {
                E nextData = current.getEdges().get(i).getDestination().getData();

                if (!visited.contains(nextData)) {
                    AdjList<E> nextVertex = findVertex(nextData);

                    if (nextVertex != null) {
                        visited.add(nextData);
                        queue.add(nextVertex);
                    }
                }
            }
        }

        return visited.size();
    }

    private void removeEdgeFromList(AdjList<E> origin, E destination) {
        int i = 0;

        while (i < origin.getEdges().size()) {
            E current = origin.getEdges().get(i).getDestination().getData();

            if (Objects.equals(current, destination)) {
                origin.getEdges().removeAt(i);
            } else {
                i++;
            }
        }
    }

    private void appendVisit(StringBuilder result, E data) {
        if (result.length() > 0) {
            result.append(" ");
        }

        result.append(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);

            sb.append(adj.getVertex())
              .append(" -> ");

            for (int j = 0; j < adj.getEdges().size(); j++) {
                sb.append(adj.getEdges().get(j)).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
