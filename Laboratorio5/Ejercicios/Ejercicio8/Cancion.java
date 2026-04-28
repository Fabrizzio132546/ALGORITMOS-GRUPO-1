package practicapura.listas;

public class Cancion {
	// Atributos principales de la canción
	private String titulo;
	private String artista;
	private int duracionSeg; // Tiempo en segundos
	
	// Constructor: inicializa los datos al crear una nueva canción
	Cancion(String t, String a, int d){
		this.titulo = t;
		this.artista = a;
		this.duracionSeg = d;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	public String getArtista() {
		return this.artista;
	}
	
	public void setDuracion(int duracion) {
		this.duracionSeg = duracion;
	}
	
	public int getDuracion() {
		return this.duracionSeg;
	}
	
	// Da formato al texto para que se vea bien al imprimir la lista
	@Override
	public String toString() {
	    return titulo + " - " + artista + " (" + duracionSeg + "s)";
	}
}