package lab08.implementaciones;

public class Producto implements Comparable<Producto> {
    private int codigo;
    private String nombre;
    private int stock;
    private double precio;

    public Producto(int codigo, String nombre, int stock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getStock() { return stock; }
    
    public void reducirStock(int cantidad) {
        if (this.stock >= cantidad) this.stock -= cantidad;
    }
    @Override
    public int compareTo(Producto otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (Stock: %d)", codigo, nombre, stock);
    }
}