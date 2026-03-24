package Actividad01;

import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria<T> implements Iterable<Caja<T>> {
    private ArrayList<Caja<T>> lista = new ArrayList<>();
    private int tope;

    public Cajoneria(int tope) { this.tope = tope; }

    public void add(Caja<T> caja) {
        if (lista.size() < tope) lista.add(caja);
        else throw new RuntimeException("Cajonería llena");
    }

    public String search(T elemento) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getContenido().equals(elemento))
                return "Posición: " + (i + 1) + ", Color: " + lista.get(i).getColor();
        }
        return "No encontrado";
    }

    public T delete(T elemento) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getContenido().equals(elemento))
                return lista.remove(i).getContenido();
        }
        return null;
    }

    public int contar(T elemento) {
        int c = 0;
        for (Caja<T> caja : lista) if (caja.getContenido().equals(elemento)) c++;
        return c;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-15s %-20s\n", "Posición", "Color Caja", "Objeto"));
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> c = lista.get(i);
            sb.append(String.format("%-10d %-15s %-20s\n", (i+1), c.getColor(), c.getContenido()));
        }
        return sb.toString();
    }

    @Override
    public Iterator<Caja<T>> iterator() { return lista.iterator(); }
}
