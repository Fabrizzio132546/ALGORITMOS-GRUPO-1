package Codigo1;

public class Producto {
    private String sku; 
    private String nombre;
    private int cantidad;
    private double precioUnitario;

    public Producto(String sku, String nombre, int cantidad, double precioUnitario) {
        this.sku = sku;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getSku() { return sku; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precioUnitario; }

    @Override
    public String toString() {
        return "PRODUCTO -> SKU: " + sku + 
               " | Nombre: " + nombre + 
               " | Cantidad: " + cantidad + 
               " | Precio Unit.: S/" + precioUnitario + 
               " | Subtotal: S/" + (cantidad * precioUnitario);
    }
}
