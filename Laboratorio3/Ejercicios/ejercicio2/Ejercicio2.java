package Lab03.ejercicio2;

public class Ejercicio2 {

    public static void main(String[] args) {
    }

    public static int conteo(int[] v, int n) {
        int c = 0;                             // O(1)

        for (int i = 0; i <= n - 2; i++) {     // O(n)
            
            for (int j = i + 1; j <= n - 1; j++) { // O(n)
                
                if (v[i] == v[j]) {            // O(1)
                    c = c + 1;                 // O(1)
                }
            }
        }
        return c;                              // O(1)
    }
}