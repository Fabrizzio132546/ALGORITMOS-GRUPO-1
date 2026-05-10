package Ejercicio5_GUI;

public class Producto implements Comparable<Producto> {
    private int codigo;
    private String nombre;
    private int cantidad; 

    public Producto(int codigo, String nombre, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }

    @Override
    public int compareTo(Producto otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - Stock: %d", codigo, nombre, cantidad);
    }
}