package Actividad2;

public class PotenciaRecursiva {

    static double potencia(double x, int y) {

        // Complejidad: O(1)
        if (y == 0)
            return 1.0;

        // Si el exponente es impar
        // Reduce el problema en 1 unidad
        // Complejidad: T(n) = T(n-1) → peor caso lineal en esta rama
        if (y % 2 == 1)
            return x * potencia(x, y - 1);

        // Si el exponente es par
        // Divide el problema a la mitad
        // Complejidad: T(n) = T(n/2) → logarítmica
        else {
            double t = potencia(x, y / 2);
            return t * t;
        }
    }

    // Metodo principal
    public static void main(String[] args) {

        double base = 2;
        int exponente = 10;

        // Llamada al metodo
        double resultado = potencia(base, exponente);

        // Mostrar resultado
        System.out.println("Resultado: " + base + "^" + exponente + " = " + resultado);

        // Complejidad general comentario clave:
        // Mejor y caso promedio: O(log n)
        // Peor caso : O(n)
    }
}
