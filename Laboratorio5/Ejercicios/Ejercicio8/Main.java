package lab05.ejercicio8;

public class Main {

    public static void main(String[] args) {
        
        ColaReproduccion miCola = new ColaReproduccion();

        miCola.agregar(new Cancion("Bohemian Rhapsody", "Queen", 354));
        miCola.agregar(new Cancion("Blinding Lights", "The Weeknd", 200));
        miCola.agregar(new Cancion("Shape of You", "Ed Sheeran", 234));
        miCola.agregar(new Cancion("Hotel California", "Eagles", 390));
        miCola.agregar(new Cancion("Rolling in the Deep", "Adele", 228));
        miCola.agregar(new Cancion("Smells Like Teen Spirit", "Nirvana", 301));

        miCola.mostrarCola();

        System.out.println("--- Simulando reproductor ---");
        
        for (int i = 0; i < 3; i++) {
            Cancion actual = miCola.reproducirSiguiente();
            if (actual != null) {
                System.out.println("▶ Reproduciendo ahora: " + actual.getTitulo() + " - " + actual.getArtista());
            }
        }

        Cancion anterior = miCola.reproducirAnterior();
        if (anterior != null) {
            System.out.println("◀ Anterior: " + anterior.getTitulo() + " - " + anterior.getArtista());
        }

        System.out.println("\n=== Mezclando... ===");
        miCola.mezclar();
        miCola.mostrarCola();

        int totalSegundos = miCola.duracionTotal();
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        System.out.printf("Duración total: %02d:%02d\n", minutos, segundos);
    }
}