package Actividades;
import java.util.Arrays;
public class MergeSort {
// Divide el arreglo recursivamente y lo ordena al combinar
public static void mergeSort(int[] arr, int inicio, int fin) {
// Caso base: un solo elemento ya está ordenado
if (inicio >= fin) return;
int medio = (inicio + fin) / 2;
// Divide en mitad izquierda y mitad derecha
mergeSort(arr, inicio, medio);
mergeSort(arr, medio + 1, fin);
// Combina las dos mitades ordenadas
merge(arr, inicio, medio, fin);
}
// Combina dos subarreglos ordenados en uno solo
public static void merge(int[] arr, int inicio, int medio, int fin) {
int n1 = medio - inicio + 1;
int n2 = fin - medio;
int[] izq = new int[n1];
int[] der = new int[n2];
// Copia los datos a arreglos temporales
for (int i = 0; i < n1; i++) izq[i] = arr[inicio + i];
for (int j = 0; j < n2; j++) der[j] = arr[medio + 1 + j];
int i = 0, j = 0, k = inicio;
// Mezcla comparando elemento por elemento
while (i < n1 && j < n2) {
if (izq[i] <= der[j]) {
arr[k++] = izq[i++];
} else {
arr[k++] = der[j++];
}
}
// Copia los elementos restantes de cada mitad
while (i < n1) arr[k++] = izq[i++];
while (j < n2) arr[k++] = der[j++];
}
public static void main(String[] args) {
// Arreglo de 5 elementos
int[] arr5 = {8, 3, 5, 2, 7};
System.out.println("Arreglo 5 elementos");
System.out.println("Antes: " + Arrays.toString(arr5));
mergeSort(arr5, 0, arr5.length - 1);
System.out.println("Despu´es: " + Arrays.toString(arr5));
// Arreglo de 8 elementos
int[] arr8 = {4, 1, 9, 6, 3, 8, 2, 5};
System.out.println("\nArreglo 8 elementos");
System.out.println("Antes: " + Arrays.toString(arr8));
mergeSort(arr8, 0, arr8.length - 1);
System.out.println("Despu´es: " + Arrays.toString(arr8));
// Arreglo de 10 elementos
int[] arr10 = {7, 4, 2, 9, 1, 6, 8, 3, 5, 10};
System.out.println("\nArreglo 10 elementos");
System.out.println("Antes: " + Arrays.toString(arr10));
mergeSort(arr10, 0, arr10.length - 1);
System.out.println("Despu´es: " + Arrays.toString(arr10));
}
}

