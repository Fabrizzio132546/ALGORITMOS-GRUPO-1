package lab04;

import java.util.Arrays;

public class ModaSolucion2 {

    // Devuelve la moda usando ordenamiento previo (más eficiente)
    public static int moda2(int[] array) {
        Arrays.sort(array); // Ordena para agrupar iguales

        int first = 1;       // puntero que recorre
        int p = 0;           // inicio del grupo actual
        int end = array.length - 1;

        int moda = array[0]; // valor inicial
        int frec = 1;        // frecuencia actual
        int maxfrec = 0;     // máxima frecuencia encontrada

        // Recorre el arreglo ya ordenado
        while (first <= end) {
            if (array[p] == array[first]) {
                frec++;      // mismo valor → aumenta frecuencia
                first++;
            } else {
                // cambio de valor → evalúa el grupo anterior
                if (frec > maxfrec) {
                    maxfrec = frec;
                    moda = array[p];
                }
                p = first;       // nuevo grupo
                first = p + 1;
                frec = 1;
            }
        }

        // Evalúa el último grupo
        if (frec > maxfrec) {
            moda = array[p];
        }

        return moda;
    }

    public static void main(String[] args) {
        int[] datos = {3, 4, 6, 7, 6, 2, 6};

        // Imprime la moda
        System.out.println("La moda (Solución 2) es: " + moda2(datos));
    }
}