package graph;

import listlinked.ListLinked;
import listlinked.Node;
import queuelink.QueueLink;

public class GraphLink<E extends Comparable<E>> {

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

    public void insertEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) {
            return;
        }
        v1.getEdges().addLast(new Edge<>(v2.getVertex()));
        v2.getEdges().addLast(new Edge<>(v1.getVertex()));
    }

    public void removeVertex(E data) {
        AdjList<E> vertexToRemove = findVertex(data);
        if (vertexToRemove == null) {
            return;
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
        graph.removeNode(vertexToRemove);
    }

    public void removeEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;
        v1.getEdges().removeNode(new Edge<>(v2.getVertex()));
        v2.getEdges().removeNode(new Edge<>(v1.getVertex()));
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

    private AdjList<E> findVertex(E data) { 
        if (data == null)  return null;
        for (int i = 0; i < graph.size(); i++) { 
            AdjList<E> adj = graph.get(i); 
            if (adj.getVertex().getData().compareTo(data) == 0) {
                return adj; 
            }
        } 
        return null; 
    }

    
    public boolean isConnected(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return false;
        }

        for (int i = 0; i < v1.getEdges().size(); i++) {
            if (v1.getEdges().get(i).getDestination().getData().compareTo(destination) == 0) {
                return true;
            }
        }

        return false;
    }
    
    public boolean isConnected() {
        if (graph.isEmpty()) return true;

        ListLinked<Vertex<E>> visited = new ListLinked<>();
        QueueLink<AdjList<E>> queue = new QueueLink<>();
        AdjList<E> start = graph.getFirst().getData();
        visited.addLast(start.getVertex());
        queue.enqueue(start);
        int count = 0;
        while (!queue.isEmpty()) {

            AdjList<E> current = queue.dequeue();
            count++;
            Node<Edge<E>> edgeNode = current.getEdges().getFirst();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().getDestination();
                if (!visited.search(neighbor)) {
                    visited.addLast(neighbor);
                    queue.enqueue(findVertex(neighbor.getData()));
                }
                edgeNode = edgeNode.getNext();
            }
        }
        return count == graph.size();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<AdjList<E>> actual = graph.getFirst();
        while (actual != null) {
            AdjList<E> adj = actual.getData();
            sb.append(adj.getVertex()).append(" -> ");
            Node<Edge<E>> edgeNode = adj.getEdges().getFirst();
            while (edgeNode != null) {
                sb.append(edgeNode.getData()).append(" ");
                edgeNode = edgeNode.getNext();
            }
            sb.append("\n");
            actual = actual.getNext();
        }

        return sb.toString();
    }
}













