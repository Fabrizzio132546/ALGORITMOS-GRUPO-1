package lab01.ejercicio2;

public class Zona {

    public String mineral;
    private int cantidad;      // Encapsulado para proteger la integridad del dato
    private double pureza;     // Encapsulado para proteger el nivel de pureza
    
    // Constructor para inicializar una zona con datos específicos del terreno
    public Zona(String mineral, int cantidad, double pureza) {
        this.mineral = mineral;
        this.cantidad = cantidad;
        this.pureza = pureza;
    }
    
    public int getCantidad() { return this.cantidad; }
    public double getPureza() { return this.pureza; }
    
    // Representación en cadena para cumplir con el formato de los casos de prueba
    @Override
    public String toString() {
        return mineral + " Cantidad: " + cantidad + ", Pureza: " + pureza;
    }
}
