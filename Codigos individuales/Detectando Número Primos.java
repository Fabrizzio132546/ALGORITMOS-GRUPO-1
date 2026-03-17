public class MatrizPrimos {

    public static boolean esPrimo(int num) {
        if (num <= 1) return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[][] matriz = {
            {4, 7, 10},
            {5, 6, 11},
            {8, 9, 13}
        };

        int filas = matriz.length;
        int columnas = matriz[0].length;

        int[][] resultado = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                if (esPrimo(matriz[i][j])) {
                    resultado[i][j] = matriz[i][j];
                } else {
                    resultado[i][j] = 0;
                }

            }
        }

        System.out.println("Matriz original:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nMatriz resultado:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(resultado[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
