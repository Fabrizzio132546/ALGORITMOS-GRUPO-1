package Ejercicios;

public class Ejercicio7 {
	public static void main(String[] args) {
	    int[] arr = {5, 2, 4, 7, 1, 3, 6};          // O(1)

	    mergeSort(arr, 0, arr.length - 1);          // O(n log n)

	    System.out.print("Arreglo ordenado: ");     // O(1)
	    for (int num : arr) {                       // O(n)
	        System.out.print(num + " ");            // O(1)
	    }
	}

	public static void mergeSort(int[] arr, int left, int right) {
	    if (left < right) {                         // O(1)

	        int mid = left + (right - left) / 2;    // O(1)

	        mergeSort(arr, left, mid);              // T(n/2)
	        mergeSort(arr, mid + 1, right);         // T(n/2)

	        merge(arr, left, mid, right);           // O(n)
	    }
	}

	public static void merge(int[] arr, int left, int mid, int right) {

	    int n1 = mid - left + 1;                    // O(1)
	    int n2 = right - mid;                       // O(1)

	    int[] L = new int[n1];                      // O(n)
	    int[] R = new int[n2];                      // O(n)

	    for (int i = 0; i < n1; i++) {              // O(n)
	        L[i] = arr[left + i];                   // O(1)
	    }

	    for (int j = 0; j < n2; j++) {              // O(n)
	        R[j] = arr[mid + 1 + j];                // O(1)
	    }

	    int i = 0, j = 0, k = left;                 // O(1)

	    while (i < n1 && j < n2) {                  // O(n)
	        if (L[i] <= R[j]) {                     // O(1)
	            arr[k++] = L[i++];                  // O(1)
	        } else {
	            arr[k++] = R[j++];                  // O(1)
	        }
	    }

	    while (i < n1) {                            // O(n)
	        arr[k++] = L[i++];                      // O(1)
	    }

	    while (j < n2) {                            // O(n)
	        arr[k++] = R[j++];                      // O(1)
	    }
	}
}
