package lab10.ejercicios.ejercicio1;
import graph.*;
import listlinked.ListLinked;
import listlinked.Node;
import queue.QueueLink;
import queue.PriorityQueueLink;
import stack.StackLink;
import java.util.ArrayList;

public class GraphLink<E extends Comparable<E>> {

    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (findVertex(data) != null) return;
        Vertex<E> vertex = new Vertex<>(data);
        graph.addLast(new AdjList<>(vertex));
    }

    public void insertEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;
        v1.getEdges().addLast(new Edge<>(v2.getVertex()));
        v2.getEdges().addLast(new Edge<>(v1.getVertex()));
    }

    public void insertEdgeWeight(E origin, E destination, int weight) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;
        
        v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
        v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
    }

    public boolean isConexo() {
        return isConnected();
    }

    public StackLink<E> Dijsktra(E v, E w) {
        StackLink<E> path = new StackLink<>();
        int[] prev = getDijkstraPrev(v);
        
        if (prev == null) return path;

        int targetIndex = getIndex(w);
        if (targetIndex == -1 || (prev[targetIndex] == -1 && v.compareTo(w) != 0)) {
            return path; // No hay ruta
        }

        int curr = targetIndex;
        while (curr != -1) {
            path.push(getDataByIndex(curr));
            curr = prev[curr];
        }
        return path;
    }

    public ArrayList<E> shortPath(E v, E z) {
        ArrayList<E> path = new ArrayList<>();
        StackLink<E> pathStack = Dijsktra(v, z);
        
        while (!pathStack.isEmpty()) {
            path.add(pathStack.pop());
        }
        return path;
    }

    private class NodeDist implements Comparable<NodeDist> {
        int index;
        int distance;

        public NodeDist(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDist other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private int[] getDijkstraPrev(E start) {
        int n = graph.size();
        int[] dist = new int[n];
        int[] prev = new int[n];
        
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        int startIndex = getIndex(start);
        if (startIndex == -1) return null;
        dist[startIndex] = 0;

        PriorityQueueLink<NodeDist> pq = new PriorityQueueLink<>();
        pq.enqueue(new NodeDist(startIndex, 0));

        while (!pq.isEmpty()) {
            NodeDist current = pq.dequeue();
            int u = current.index;

            if (current.distance > dist[u]) continue;

            AdjList<E> adjU = getAdjListByIndex(u);
            if (adjU != null) {
                Node<Edge<E>> edgeNode = adjU.getEdges().getFirst();
                while (edgeNode != null) {
                    Edge<E> edge = edgeNode.getData();
                    int v_index = getIndex(edge.getDestination().getData());
                    
                    if (v_index != -1 && dist[u] + edge.getWeight() < dist[v_index]) {
                        dist[v_index] = dist[u] + edge.getWeight();
                        prev[v_index] = u;
                        pq.enqueue(new NodeDist(v_index, dist[v_index]));
                    }
                    edgeNode = edgeNode.getNext();
                }
            }
        }
        return prev;
    }

    private int getIndex(E data) {
        int index = 0;
        Node<AdjList<E>> curr = graph.getFirst();
        while (curr != null) {
            if (curr.getData().getVertex().getData().compareTo(data) == 0) return index;
            curr = curr.getNext();
            index++;
        }
        return -1;
    }

    private AdjList<E> getAdjListByIndex(int index) {
        int currentIndex = 0;
        Node<AdjList<E>> curr = graph.getFirst();
        while (curr != null) {
            if (currentIndex == index) return curr.getData();
            curr = curr.getNext();
            currentIndex++;
        }
        return null;
    }

    private E getDataByIndex(int index) {
        AdjList<E> adj = getAdjListByIndex(index);
        return adj != null ? adj.getVertex().getData() : null;
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

    private AdjList<E> findVertex(E data) { 
        if (data == null) return null;
        for (int i = 0; i < graph.size(); i++) { 
            AdjList<E> adj = graph.get(i); 
            if (adj.getVertex().getData().compareTo(data) == 0) {
                return adj; 
            }
        } 
        return null; 
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
                sb.append(edgeNode.getData()).append("(").append(edgeNode.getData().getWeight()).append(") ");
                edgeNode = edgeNode.getNext();
            }
            sb.append("\n");
            actual = actual.getNext();
        }
        return sb.toString();
    }
}