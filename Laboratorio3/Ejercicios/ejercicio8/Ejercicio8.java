package Ejercicios;

public class Ejercicio8 {

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 7, 1, 3, 6};      // Inicialización del arreglo O(1)

        mergeSort(arr);                         // Llamada al algoritmo principal O(n log n)

        System.out.print("Arreglo ordenado: "); // Impresión mensaje O(1)
        for (int num : arr) {                   // Recorrido del arreglo O(n)
            System.out.print(num + " ");        // Impresión de cada elemento O(1)
        }
    }

    // Método principal que inicializa el arreglo auxiliar
    public static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length];        // Creación de arreglo auxiliar O(n)
        mergeSort(arr, aux, 0, arr.length - 1); // Llamada recursiva inicial O(n log n)
    }

    // Método recursivo divide y vencerás
    private static void mergeSort(int[] arr, int[] aux, int left, int right) {
        if (left >= right) return;              // Caso base: subarreglo de tamaño 1 O(1)

        int mid = left + (right - left) / 2;    // Cálculo del punto medio O(1)

        mergeSort(arr, aux, left, mid);         // Llamada recursiva izquierda T(n/2)
        mergeSort(arr, aux, mid + 1, right);    // Llamada recursiva derecha T(n/2)

        // Optimización: si ya están ordenados, evita el merge
        if (arr[mid] <= arr[mid + 1]) return;   // Verificación O(1)

        merge(arr, aux, left, mid, right);      // Proceso de fusión O(n)
    }

    // Método que combina los subarreglos ordenados
    private static void merge(int[] arr, int[] aux, int left, int mid, int right) {

        // Copia los elementos al arreglo auxiliar
        System.arraycopy(arr, left, aux, left, right - left + 1); // Copia O(n)

        int i = left, j = mid + 1, k = left; // Inicialización de índices O(1)

        // Mezcla los dos subarreglos ordenados
        while (i <= mid && j <= right) {     // Se recorre como máximo n veces O(n)
            if (aux[i] <= aux[j]) {          // Comparación O(1)
                arr[k++] = aux[i++];         // Asignación O(1)
            } else {
                arr[k++] = aux[j++];         // Asignación O(1)
            }
        }

        // Copia los elementos restantes del subarreglo izquierdo (si existen)
        while (i <= mid) {                   // En total O(n)
            arr[k++] = aux[i++];             // Asignación O(1)
        }

        // Nota: no es necesario copiar el subarreglo derecho
        // porque ya está en su posición correcta
    }
}
