package Ejercicios;

public class Ejercicio7 {

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 7, 1, 3, 6};          // Inicialización del arreglo O(1)

        mergeSort(arr, 0, arr.length - 1);          // Llamada al algoritmo Merge Sort O(n log n)

        System.out.print("Arreglo ordenado: ");     // Impresión de mensaje O(1)
        for (int num : arr) {                       // Recorrido del arreglo O(n)
            System.out.print(num + " ");            // Impresión de cada elemento O(1)
        }
    }

    // Método recursivo que divide el arreglo
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {                         // Verificación del caso base O(1)

            int mid = left + (right - left) / 2;    // Cálculo del punto medio O(1)

            mergeSort(arr, left, mid);              // Llamada recursiva izquierda T(n/2)
            mergeSort(arr, mid + 1, right);         // Llamada recursiva derecha T(n/2)

            merge(arr, left, mid, right);           // Proceso de combinación O(n)
        }
    }

    // Método que fusiona dos subarreglos ordenados
    public static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;                    // Tamaño del subarreglo izquierdo O(1)
        int n2 = right - mid;                       // Tamaño del subarreglo derecho O(1)

        int[] L = new int[n1];                      // Creación de arreglo auxiliar izquierdo O(n)
        int[] R = new int[n2];                      // Creación de arreglo auxiliar derecho O(n)

        // Copia de datos al subarreglo izquierdo
        for (int i = 0; i < n1; i++) {              // Recorrido O(n)
            L[i] = arr[left + i];                   // Asignación O(1)
        }

        // Copia de datos al subarreglo derecho
        for (int j = 0; j < n2; j++) {              // Recorrido O(n)
            R[j] = arr[mid + 1 + j];                // Asignación O(1)
        }

        int i = 0, j = 0, k = left;                 // Inicialización de índices O(1)

        // Comparación y mezcla de ambos subarreglos
        while (i < n1 && j < n2) {                  // Se ejecuta como máximo n veces O(n)
            if (L[i] <= R[j]) {                     // Comparación O(1)
                arr[k++] = L[i++];                  // Asignación O(1)
            } else {
                arr[k++] = R[j++];                  // Asignación O(1)
            }
        }

        // Copia los elementos restantes del subarreglo izquierdo
        while (i < n1) {                            // O(n)
            arr[k++] = L[i++];                      // O(1)
        }

        // Copia los elementos restantes del subarreglo derecho
        while (j < n2) {                            // O(n)
            arr[k++] = R[j++];                      // O(1)
        }
    }
}
