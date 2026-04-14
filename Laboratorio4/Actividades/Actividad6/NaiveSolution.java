package lab04.actividades.actividad6;

public class NaiveSolution {

    // Calcula el valor máximo (problema de corte de varilla - solución recursiva)
    static int getValue(int[] values, int length) {

        // Caso base: longitud 0 → valor 0
        if (length <= 0) return 0;

        int tmpMax = -1; // guarda el máximo valor encontrado

        // Prueba todos los posibles cortes
        for (int i = 0; i < length; i++) {

            // values[i] = valor del primer corte
            // getValue(...) = valor del resto
            tmpMax = Math.max(
                tmpMax, 
                values[i] + getValue(values, length - i - 1)
            );
        }

        return tmpMax; // mejor combinación encontrada
    }

    public static void main(String[] args) {
        int[] values = new int[]{3, 7, 1, 3, 9};
        int rodLength = values.length;

        // Imprime el valor máximo posible
        System.out.println("(Solución recursiva) El valor maximo: " + getValue(values, rodLength));
    }
}