public class Ejercicio2 {

    public static void main(String[] args) {
        int m = 7;
        int[] valores = {10, 17, 24, 31, 4};

        System.out.println("--- SONDEO LINEAL ---");
        int[] tablaLineal = new int[m];
        limpiarTabla(tablaLineal);
        for (int i = 0; i < valores.length; i++) {
            insertarLineal(tablaLineal, valores[i], m);
        }

        System.out.println("\n--- SONDEO CUADRÁTICO ---");
        int[] tablaCuadratica = new int[m];
        limpiarTabla(tablaCuadratica);
        for (int i = 0; i < valores.length; i++) {
            insertarCuadratico(tablaCuadratica, valores[i], m);
        }
    }

    private static void limpiarTabla(int[] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = -1;
        }
    }

    private static int hash(int x, int m) {
        return x % m;
    }

    private static void insertarLineal(int[] tabla, int valor, int m) {
        int inicio = hash(valor, m);
        int pos = inicio;
        int exploradas = 0;

        do {
            exploradas++;
            if (tabla[pos] == -1) {
                tabla[pos] = valor;
                System.out.print("Insertado " + valor + " en pos " + pos + " (Exploradas: " + exploradas + ") -> ");
                imprimirTabla(tabla);
                return;
            }
            pos = (pos + 1) % m;
        } while (pos != inicio);
    }

    private static void insertarCuadratico(int[] tabla, int valor, int m) {
        int inicio = hash(valor, m);
        int i = 0;
        int exploradas = 0;

        while (i < m) {
            exploradas++;
            int pos = (inicio + (i * i)) % m; 
            
            if (tabla[pos] == -1) {
                tabla[pos] = valor;
                System.out.print("Insertado " + valor + " en pos " + pos + " (Exploradas: " + exploradas + ") -> ");
                imprimirTabla(tabla);
                return;
            }
            i++;
        }
    }

    private static void imprimirTabla(int[] tabla) {
        System.out.print("[");
        for (int i = 0; i < tabla.length; i++) {
            System.out.print(tabla[i]);
            if (i < tabla.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
