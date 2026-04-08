package Ejercicios_Adicionales;
public class EjercicioHeapSort {

    static void heapSort(int[] v) {
        int n = v.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(v, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = v[0];
            v[0] = v[i];
            v[i] = temp;

            heapify(v, i, 0);
        }
    }

    static void heapify(int[] v, int n, int i) {
        int mayor = i;
        int izq = 2 * i + 1;
        int der = 2 * i + 2;

        if (izq < n && v[izq] > v[mayor]) mayor = izq;
        if (der < n && v[der] > v[mayor]) mayor = der;

        if (mayor != i) {
            int temp = v[i];
            v[i] = v[mayor];
            v[mayor] = temp;

            heapify(v, n, mayor);
        }
    }

    public static void main(String[] args) {
        int[] v = {12, 11, 13, 5, 6, 7};

        heapSort(v);

        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i] + " ");
        }
    }
}
