package practicapura.listas;

public class Main {

    public static void main(String[] args) {
        
        // Creamos nuestra cola de reproducción vacía (la lista doblemente enlazada)
        ColaReproduccion<Cancion> miCola = new ColaReproduccion<>();

        // Agregamos algunas canciones de prueba
        miCola.agregarCancion(new Cancion("Bohemian Rhapsody", "Queen", 354));
        miCola.agregarCancion(new Cancion("Blinding Lights", "The Weeknd", 200));
        miCola.agregarCancion(new Cancion("Shape of You", "Ed Sheeran", 234));
        miCola.agregarCancion(new Cancion("Hotel California", "Eagles", 390));
        miCola.agregarCancion(new Cancion("Rolling in the Deep", "Adele", 228));
        miCola.agregarCancion(new Cancion("Smells Like Teen Spirit", "Nirvana", 301));

        // Mostramos cómo quedó la lista original (el primer nodo tendrá la marca de [SONANDO])
        miCola.mostrarCola();

        System.out.println("--- Simulando reproductor ---");
        
        // Simulamos presionar el botón "Siguiente" 3 veces
        for (int i = 0; i < 3; i++) {
            Cancion actual = miCola.reproducirSiguiente();
            if (actual != null) {
                System.out.println("▶ Reproduciendo ahora: " + actual.getTitulo() + " - " + actual.getArtista());
            }
        }

        // Simulamos presionar el botón "Anterior" para comprobar que podemos retroceder
        Cancion anterior = miCola.reproducirAnterior();
        if (anterior != null) {
            System.out.println("◀ Anterior: " + anterior.getTitulo() + " - " + anterior.getArtista());
        }

        // Probamos la función que desordena los nodos al azar (modo shuffle)
        System.out.println("\n=== Mezclando... ===");
        miCola.mezclar();
        miCola.mostrarCola();

        // Obtenemos la suma total de segundos y le damos formato de minutos:segundos
        int totalSegundos = miCola.duracionTotal();
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        System.out.printf("Duración total: %02d:%02d\n", minutos, segundos);
    }
}