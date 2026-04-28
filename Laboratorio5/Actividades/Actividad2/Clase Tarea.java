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
    public String getEstado() { return estado; }

    // IMPORTANTE: Compara la prioridad para establecer un "orden" entre tareas.
    // Retorna negativo si 'this' tiene un número menor (es decir, mayor prioridad).
    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    @Override
    public String toString() {
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }

    // IMPORTANTE: Sobrescribir equals cambia la regla de igualdad. 
    // Ahora Java sabe que si buscamos o eliminamos una tarea con el mismo TÍTULO, 
    // es la misma tarea, aunque sea una instancia diferente en memoria.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarea tarea = (Tarea) obj;
        return titulo.equals(tarea.titulo); 
    }
}
