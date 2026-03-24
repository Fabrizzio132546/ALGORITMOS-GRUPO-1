package Actividad01;

public class Caja<T> {
    private String color;
    private T contenido;

    public Caja(String color, T contenido) {
        this.color = color;
        this.contenido = contenido;
    }

    public String getColor() { return color; }
    public T getContenido() { return contenido; }

    @Override
    public String toString() { return contenido.toString(); }
}
