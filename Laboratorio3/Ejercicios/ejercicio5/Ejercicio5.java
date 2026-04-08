package Lab03.ejercicio5;

public class Ejercicio5 {

    public static void main(String[] args) {
        int n = 6;
        System.out.println("Fibonacci de " + n + ": " + fibonacci(n));
    }

    static int fibonacci(int n) {
        if (n <= 1) {                                     // O(1)
            return n;                                     // O(1)
        }
        return fibonacci(n - 1) + fibonacci(n - 2);      // T(n-1) + T(n-2)
    }
}