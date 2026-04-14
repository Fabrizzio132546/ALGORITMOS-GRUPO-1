package lab04.actividades.actividad6;

public class DPSolution {

    // Versión con programación dinámica (bottom-up)
    static int getValue(int[] values, int rodLength) {

        int[] subSolutions = new int[rodLength + 1]; 
        // subSolutions[i] = mejor valor para longitud i

        // Construye soluciones desde 1 hasta rodLength
        for (int i = 1; i <= rodLength; i++) {

            int tmpMax = -1; // máximo para longitud i

            // Prueba todos los cortes posibles
            for (int j = 0; j < i; j++) {

                // values[j] = valor del primer corte
                // subSolutions[...] = solución ya calculada
                tmpMax = Math.max(
                    tmpMax, 
                    values[j] + subSolutions[i - j - 1]
                );
            }

            subSolutions[i] = tmpMax; // guarda mejor resultado
        }

        return subSolutions[rodLength]; // resultado final
    }

    public static void main(String[] args) {
        int[] values = new int[] {3, 7, 1, 3, 9};
        int rodLength = values.length;

        // Imprime el valor máximo
        System.out.println("(Solución con Programación Dinámica) El valor maximo: " + getValue(values, rodLength));
    }
}