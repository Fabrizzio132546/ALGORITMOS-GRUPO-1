package Actividad1;
import java.util.*;

public class Coordenada {
    private double x;
    private double y;
    public Coordenada() {     // Constructor
        this.x = 0;
        this.y = 0;
    }
    public Coordenada(double x, double y) {     // Constructor copia
        this.x = x;
        this.y = y;
    }
    public Coordenada(Coordenada c) {
        this.x = c.getX();
        this.y = c.getY();
    }
    void setX(double x) { this.x = x;  }      // Métodos setter
    void setY(double y) {  this.y = y; }

    double getX() {  return this.x; }     // Métodos getter
    double getY() { return this.y; }
    
    double distancia(Coordenada c) {        // Método de distancia (objeto actual con otro)
        double dif_x = this.x - c.getX();
        double dif_y = this.y - c.getY();
        return Math.sqrt(Math.pow(dif_x, 2) + Math.pow(dif_y, 2));
    }
    static double distancia(Coordenada c1, Coordenada c2) {  // Método de distancia estático (entre dos objetos)
        double dif_x = c1.getX() - c2.getX();
        double dif_y = c1.getY() - c2.getY();
        return Math.sqrt(Math.pow(dif_x, 2) + Math.pow(dif_y, 2));
    }
    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
