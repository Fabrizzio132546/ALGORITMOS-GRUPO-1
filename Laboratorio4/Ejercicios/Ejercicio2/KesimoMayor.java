package Ejercicio2;

public class KesimoMayor {
	
	public static int k_esimo(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k);
    }
    private static int quickSelect(int[] arr, int izquierda, int derecha, int k) {
        if (izquierda <= derecha) {

            int indicePivote = particion(arr, izquierda, derecha);

            if (indicePivote == k - 1) {
                return arr[indicePivote];
            } 
            else if (indicePivote > k - 1) {
                return quickSelect(arr, izquierda, indicePivote - 1, k);
            } 
            else {
                return quickSelect(arr, indicePivote + 1, derecha, k);
            }
        }
        return -1;
    }
    private static int particion(int[] arr, int izquierda, int derecha) {
        int pivote = arr[derecha];
        int i = izquierda;
        for (int j = izquierda; j < derecha; j++) {
            if (arr[j] >= pivote) {
                intercambiar(arr, i, j);
                i++;
            }
        }
        intercambiar(arr, i, derecha);
        return i;
    }
    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

	public static void main(String[] args) {
        int[] arr1 = {4, 2, 7, 10, 4, 17};
        int k1 = 3;
        
        int[] arr2 = {4, 2, 7, 10, 1, 6};
        int k2 = 4;
        
        int[] arr3 = {4, 2, 7, 1, 4, 6};
        int k3 = 7;
        
        int[] arr4 = {9, 2, 7, 1, 7};
        int k4 = 4;
        
        System.out.print("Entrada: ");
        for(int i = 0; i < arr1.length; i++) {
        	System.out.print(arr1[i] + " ");
        }
        System.out.print(", k = " + k1);
        System.out.println();
        System.out.println("Salida: " + k_esimo(arr1, k1));
        
        
        System.out.print("Entrada: ");
        for(int i = 0; i < arr2.length; i++) {
        	System.out.print(arr2[i] + " ");
        }
        System.out.print(", k = " + k2);
        System.out.println();
        System.out.println("Salida: " + k_esimo(arr2, k2));
    }
}
