package lab09.ejercicios.ejercicio4;

public class Libro implements Comparable<Libro> {
    private String isbn;
    private String titulo;
    private String autor;
    private int anio;

    public Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }

    @Override
    public int compareTo(Libro otro) {
        return this.isbn.compareTo(otro.getIsbn());
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: '%s' | Autor: %s | Año: %d", isbn, titulo, autor, anio);
    }
}