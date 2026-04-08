package Lab03.ejercicio4;

public class Ejercicio4 {

    public static void main(String[] args) {
        System.out.println("Resultado: " + potenciaRapida(2, 10));
    }

    public static int potenciaRapida(int x, int y) {
        if (y == 0) {                                     // O(1)
            return 1;                                     // O(1)
        } else if (y % 2 == 0) {                          // O(1)
            int mitad = potenciaRapida(x, y / 2);         // T(y/2)
            return mitad * mitad;                         // O(1)
        } else {                                          // O(1)
            return x * potenciaRapida(x, y - 1);          // T(y-1)
        }
    }
}