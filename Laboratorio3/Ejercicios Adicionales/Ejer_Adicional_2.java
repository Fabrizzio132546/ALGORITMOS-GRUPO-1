package Lab03.ejercicioAdi;

import java.util.Arrays;

public class Ejer_Adicional_2 {

    public static void main(String[] args) {
        int[] datos = {10, 80, 30, 90, 40, 50, 70};
        quickSort(datos, 0, datos.length - 1);
        System.out.println("Arreglo ordenado: " + Arrays.toString(datos));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {                                  // O(1)
            int pi = partition(arr, low, high);            // O(n)

            quickSort(arr, low, pi - 1);                   // T(k)
            quickSort(arr, pi + 1, high);                  // T(n-k-1)
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];                             // O(1)
        int i = (low - 1);                                 // O(1)
       
        for (int j = low; j < high; j++) {                 // O(n)
            if (arr[j] < pivot) {                          // O(1)
                i++;
                int temp = arr[i];                         // O(1)
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];                             // O(1)
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;                                      // O(1)
    }
}