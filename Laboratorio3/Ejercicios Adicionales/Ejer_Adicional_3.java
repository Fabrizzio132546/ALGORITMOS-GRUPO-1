package EjercicioExtra;

public class CountingSort {

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};     // Inicialización del arreglo O(1)

        countingSort(arr);                     // Llamada al algoritmo O(n + k)

        System.out.print("Arreglo ordenado: "); // Impresión de mensaje O(1)
        for (int num : arr) {                  // Recorrido del arreglo O(n)
            System.out.print(num + " ");       // Impresión de cada elemento O(1)
        }
    }

    // Método principal de Counting Sort
    static void countingSort(int[] v) {

        int max = v[0];                        // Inicialización del valor máximo O(1)

        // Búsqueda del valor máximo en el arreglo
        for (int i = 1; i < v.length; i++) {   // Recorrido completo O(n)
            if (v[i] > max) {                  // Comparación O(1)
                max = v[i];                    // Actualización O(1)
            }
        }

        int[] count = new int[max + 1];        // Creación del arreglo de conteo O(k)

        // Conteo de la frecuencia de cada elemento
        for (int i = 0; i < v.length; i++) {   // Recorrido completo O(n)
            count[v[i]]++;                    // Incremento de frecuencia O(1)
        }

        int index = 0;                         // Inicialización del índice O(1)

        // Reconstrucción del arreglo ordenado
        for (int i = 0; i < count.length; i++) { // Recorrido del rango de valores O(k)

            while (count[i] > 0) {             // En total se ejecuta n veces O(n)
                v[index++] = i;                // Asignación en el arreglo original O(1)
                count[i]--;                   // Decremento del contador O(1)
            }
        }
    }
}
