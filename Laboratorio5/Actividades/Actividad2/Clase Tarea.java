// 2. Clase Tarea
class Tarea implements Comparable<Tarea> {
    private String titulo;
    private int prioridad; // 1 = alta, 2 = media, 3 = baja
    private String estado; 

    public Tarea(String titulo, int prioridad, String estado) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public String getTitulo() { return titulo; }
    public int getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }

    // El compareTo ahora define la "identidad" de la tarea basándose en el título
    @Override
    public int compareTo(Tarea otra) {
        return this.titulo.compareTo(otra.titulo);
    }

    @Override
    public String toString() {
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }
}
