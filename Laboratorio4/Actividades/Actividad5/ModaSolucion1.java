package lab04;

public class ModaSolucion1 {

    // Devuelve la moda (valor más frecuente) del arreglo
    public static int modal(int[] array) {
        int first = 0;
        int end = array.length - 1;

        // Caso base: un solo elemento
        if (first == end) return array[first];
        
        int moda = array[first]; // valor inicial
        int maxfrec = frecuencia(array, first, end, moda); // frecuencia inicial

        // Recorre el arreglo buscando mayor frecuencia
        for (int i = first + 1; i <= end; i++) {
            int frec = frecuencia(array, i, end, array[i]); // frecuencia del elemento actual

            // Actualiza si encuentra mayor frecuencia
            if (frec > maxfrec) {
                maxfrec = frec;
                moda = array[i];
            }
        }
        return moda;
    }

    // Cuenta cuántas veces aparece 'ele' en el rango [first, end]
    private static int frecuencia(int[] array, int first, int end, int ele) {
        if (first > end) return 0; // rango inválido

        int suma = 0;
        for (int i = first; i <= end; i++) {
            if (array[i] == ele) suma++; // incrementa si coincide
        }
        return suma;
    }

    public static void main(String[] args) {
        int[] datos = {3, 4, 6, 7, 6, 2, 6};

        System.out.println("La moda (Solución 1) es: " + modal(datos));
    }
}