package Actividad01;

import java.util.Objects;

public class Chocolatina {
    private String marca;

    public Chocolatina(String marca) {
        this.marca = marca;
    }

    public String getMarca() { return marca; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chocolatina that = (Chocolatina) o;
        return Objects.equals(marca, that.marca);
    }

    @Override
    public String toString() { return marca; }
}
