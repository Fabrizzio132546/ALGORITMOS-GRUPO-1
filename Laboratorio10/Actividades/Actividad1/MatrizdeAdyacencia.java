public class MatricesAdyacencia {

    public static void main(String[] args) {
        System.out.println("=== A. GRAFO NO DIRIGIDO ===");
        int[][] matrizA = {
            {0, 1, 1, 0, 0, 0},
            {1, 0, 1, 1, 0, 0},
            {1, 1, 0, 1, 1, 0},
            {0, 1, 1, 0, 1, 1},
            {0, 0, 1, 1, 0, 1},
            {0, 0, 0, 1, 1, 0}
        };
        imprimirMatriz(matrizA, new String[]{"1", "2", "3", "4", "5", "6"});

        System.out.println("\n=== B. GRAFO DIRIGIDO ===");
        int[][] matrizB = {
            {0, 1, 1, 0, 1},
            {0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 1, 1, 0}
        };
        imprimirMatriz(matrizB, new String[]{"A", "B", "C", "D", "E"});

        System.out.println("\n=== C. GRAFO PONDERADO ===");
        int[][] matrizC = {
            {0, 5, 0, 0, 0},
            {0, 0, 0, 6, 0},
            {0, 0, 0, 0, 0},
            {0, 3, 0, 0, 2},
            {4, 0, 1, 0, 0}
        };
        imprimirMatriz(matrizC, new String[]{"1", "2", "3", "4", "5"});
    }

    // Método auxiliar para imprimir las matrices con formato
    private static void imprimirMatriz(int[][] matriz, String[] etiquetas) {
        System.out.print("  ");
        for (String etiqueta : etiquetas) {
            System.out.print(etiqueta + " ");
        }
        System.out.println();

        for (int i = 0; i < matriz.length; i++) {
            System.out.print(etiquetas[i] + " ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
