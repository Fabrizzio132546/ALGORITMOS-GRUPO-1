package lab04.ejercicios;

public class LaberintoBacktracking {

    // Método principal que inicializa el proceso
    public static boolean resolverLaberinto(int[][] laberinto) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        
        // Matriz para guardar y marcar nuestro camino
        int[][] solucion = new int[filas][columnas];

        // Empezamos la búsqueda desde la posición (0,0)
        if (buscarCamino(laberinto, 0, 0, solucion)) {
            System.out.println("Resultado: true (¡Camino encontrado!)");
            imprimirMatriz(solucion);
            return true;
        } else {
            System.out.println("Resultado: false (No hay salida.)");
            return false;
        }
    }

    // Método recursivo que explora el laberinto usando backtracking
    private static boolean buscarCamino(int[][] laberinto, int x, int y, int[][] solucion) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;

        // CASO BASE: Si llegamos a la esquina inferior derecha y está libre
        if (x == filas - 1 && y == columnas - 1 && laberinto[x][y] == 0) {
            solucion[x][y] = 1; // Marcamos el paso final
            return true;
        }

        // Verificamos si podemos pisar la celda actual
        if (esSeguro(laberinto, x, y, solucion)) {
            // Marcamos la celda como parte de nuestro camino temporal
            solucion[x][y] = 1;

            // Intentamos movernos en las 4 direcciones posibles:
            // 1. Abajo
            if (buscarCamino(laberinto, x + 1, y, solucion)) return true;
            // 2. Derecha
            if (buscarCamino(laberinto, x, y + 1, solucion)) return true;
            // 3. Arriba
            if (buscarCamino(laberinto, x - 1, y, solucion)) return true;
            // 4. Izquierda
            if (buscarCamino(laberinto, x, y - 1, solucion)) return true;

            // BACKTRACKING: Si ninguna de las 4 direcciones funcionó, 
            // desmarcamos esta celda porque este camino no lleva a la salida.
            solucion[x][y] = 0;
            return false;
        }

        return false; // Retornamos falso si la celda no es segura (es pared o ya pasamos por ahí)
    }

    // Método auxiliar para saber si una coordenada es válida
    private static boolean esSeguro(int[][] laberinto, int x, int y, int[][] solucion) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        
        // Es seguro si estamos dentro de los límites, es un 0 (camino libre) y no hemos pasado por ahí
        return (x >= 0 && x < filas && y >= 0 && y < columnas 
                && laberinto[x][y] == 0 && solucion[x][y] == 0);
    }

    // Método auxiliar para mostrar la matriz en consola
    private static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Probando el Ejemplo 1 de la guía
        int[][] laberinto1 = {
            {0, 0, 1},
            {1, 0, 1},
            {1, 0, 0}
        };
        System.out.println("Ejemplo 1");
        resolverLaberinto(laberinto1);

        System.out.println();

        // Probando el Ejemplo 2 de la guía
        int[][] laberinto2 = {
            {0, 1},
            {1, 0}
        };
        System.out.println("Ejemplo 2");
        resolverLaberinto(laberinto2);
    }
}