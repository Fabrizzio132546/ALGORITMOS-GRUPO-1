package lab09.ejercicios.ejercicio4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Biblioteca {
    private BTree<Libro> arbolLibros;
    private boolean inicializada = false;

    // Cargar desde archivo
    public String cargarDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine();
            if (linea == null) return "Archivo vacío.";

            int orden = Integer.parseInt(linea.trim());
            arbolLibros = new BTree<>(orden);
            inicializada = true;
            
            StringBuilder log = new StringBuilder("Árbol inicializado con orden " + orden + "\n");
            
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    Libro libro = new Libro(partes[0].trim(), partes[1].trim(), partes[2].trim(), Integer.parseInt(partes[3].trim()));
                    if (arbolLibros.insert(libro)) {
                        log.append("Insertado: ").append(libro.getTitulo()).append("\n");
                    } else {
                        log.append("Omitido (Duplicado): ISBN ").append(libro.getIsbn()).append("\n");
                    }
                }
            }
            return log.toString();
        } catch (IOException | NumberFormatException e) {
            return "Error al cargar archivo: " + e.getMessage();
        }
    }

    public boolean agregarLibro(String isbn, String titulo, String autor, int anio) {
        if (!inicializada) arbolLibros = new BTree<>(4); // Orden por defecto si no hay txt
        inicializada = true;
        return arbolLibros.insert(new Libro(isbn, titulo, autor, anio));
    }

    public String buscarLibroPorISBN(String isbn) {
        if (!inicializada) return "Biblioteca vacía.";
        Libro dummy = new Libro(isbn, "", "", 0);
        return arbolLibros.searchWithPath(dummy);
    }

    public boolean eliminarLibro(String isbn) {
        if (!inicializada) return false;
        Libro dummy = new Libro(isbn, "", "", 0);
        return arbolLibros.remove(dummy);
    }

    public List<Libro> obtenerTodosOrdenados() {
        if (!inicializada) return null;
        return arbolLibros.getInOrder();
    }

    public int obtenerAltura() {
        return inicializada ? arbolLibros.getHeight() : 0;
    }

    public int obtenerCantidadLibros() {
        return inicializada ? arbolLibros.getSize() : 0;
    }
}