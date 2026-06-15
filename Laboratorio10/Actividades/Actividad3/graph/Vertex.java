package graph;

public class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>> {

    private E data;

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public int compareTo(Vertex<E> other) {
        return this.data.compareTo(other.getData());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}