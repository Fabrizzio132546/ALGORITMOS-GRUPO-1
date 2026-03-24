package Actividad01;

import java.util.Objects;

public class Golosina {
    private String nombre;
    private double peso;

    public Golosina(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Golosina golosina = (Golosina) o;
        return Double.compare(golosina.peso, peso) == 0 && Objects.equals(nombre, golosina.nombre);
    }

    @Override
    public String toString() { return nombre + " (" + peso + "g)"; }
}
