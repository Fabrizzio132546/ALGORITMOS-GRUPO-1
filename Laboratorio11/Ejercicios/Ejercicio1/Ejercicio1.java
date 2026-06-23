public class Ejercicio1 {
    public static void main(String[] args) {
        int m = 11;
        int[] tabla = new int[m];
        
        // Inicializar tabla con -1
        for (int i = 0; i < m; i++) {
            tabla[i] = -1;
        }
        
        int[] valores = {3, 14, 25, 36, 47, 58};
        
        // Inserción directa (sobrescribe en caso de colisión)
        for (int i = 0; i < valores.length; i++) {
            int valor = valores[i];
            int pos = valor % m;
            tabla[pos] = valor; 
        }
        
        // Imprimir tabla
        System.out.print("Tabla Hash Final: [");
        for (int i = 0; i < m; i++) {
            System.out.print(tabla[i]);
            if (i < m - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
