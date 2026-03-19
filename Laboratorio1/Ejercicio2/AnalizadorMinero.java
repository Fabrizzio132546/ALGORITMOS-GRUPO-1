package lab01.ejercicio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class AnalizadorMinero {

    private String rutaArchivo;
    private Zona[][] matrizZonas;

    public AnalizadorMinero(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    // Coordina el flujo principal del análisis
    public void iniciarAnalisis() {
        if (!cargarMatrizDesdeArchivo()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la dimensión de la Región a analizar: ");
        int k = sc.nextInt();

        ResultadoAnalisis resultado = buscarMejorRegion(k);

        if (resultado.mValor != null) {
            mostrarResultados(k, resultado);
        }
        
        sc.close();
    }
    // Persistencia: Carga datos desde archivo y configura el punto decimal
    private boolean cargarMatrizDesdeArchivo() {
        try {
            Scanner scanner = new Scanner(new File(this.rutaArchivo));
            scanner.useLocale(Locale.US);

            int filas = scanner.nextInt();
            int columnas = scanner.nextInt();
            this.matrizZonas = new Zona[filas][columnas];

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (scanner.hasNext()) {
                        this.matrizZonas[i][j] = new Zona(scanner.next(), scanner.nextInt(), scanner.nextDouble());
                    }
                }
            }
            System.out.println("Matriz de " + filas + "x" + columnas + " cargada exitosamente.\n");
            scanner.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo '" + this.rutaArchivo + "'. " +
                               "Asegúrate de que exista y la ruta sea correcta.");
            return false;
        }
    }
 // Algoritmo de ventana deslizante para recorrer subregiones k x k
    private ResultadoAnalisis buscarMejorRegion(int k) {
        int filas = matrizZonas.length;
        int columnas = matrizZonas[0].length;
        ResultadoAnalisis resultadoGlobal = new ResultadoAnalisis();

        if (k <= filas && k <= columnas) {
            for (int n = 0; n < filas; n++) {
                for (int m = 0; m < columnas; m++) {
                    if (n <= filas - k && m <= columnas - k) {
                        evaluarSubRegion(k, n, m, resultadoGlobal);
                    }
                }
            }
        }
        return resultadoGlobal;
    }
 // Calcula el valor económico y determina el mineral predominante
    private void evaluarSubRegion(int k, int n, int m, ResultadoAnalisis resultadoGlobal) {
        int m1 = m;
        int n1 = n;
        double valorRegion = 0;
        int c1 = 0, c2 = 0;
        
        String[] mineralesRegion = new String[k * k];
        int[] cantidadesRegion = new int[k * k];
        int mineralesDistintosRegion = 0;
        
     // Escaneo de la ventana actual de tamaño k
        while (c1 <= k - 1) {
            while (c2 <= k - 1) {
                double valorZona = matrizZonas[n1][m1].getCantidad() * matrizZonas[n1][m1].getPureza();
                valorRegion = valorRegion + valorZona;
                
                String mineralActual = matrizZonas[n1][m1].mineral;
                int cantidadActual = matrizZonas[n1][m1].getCantidad();

                boolean encontrado = false;
                for (int x = 0; x < mineralesDistintosRegion; x++) {
                    if (mineralesRegion[x].equals(mineralActual)) {
                        cantidadesRegion[x] += cantidadActual;
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    mineralesRegion[mineralesDistintosRegion] = mineralActual;
                    cantidadesRegion[mineralesDistintosRegion] = cantidadActual;
                    mineralesDistintosRegion++;
                }
                c2++;
                if (c2 <= k - 1) {
                    m1++;
                }
            }
            m1 = m;
            c2 = 0;
            c1++;
            if (c1 <= k - 1) {
                n1++;
            }
        }	
     // Actualiza la mejor región encontrada si el valor actual es superior
        if (resultadoGlobal.mValor == null || valorRegion > resultadoGlobal.mValor.valor) {
            resultadoGlobal.mValor = new MayorValor(n, m, valorRegion);
            resultadoGlobal.mejoresMinerales = mineralesRegion.clone();
            resultadoGlobal.mejoresCantidades = cantidadesRegion.clone();
            resultadoGlobal.mejoresDistintos = mineralesDistintosRegion;
        }
    }
    // Presentación de resultados finales en consola
    private void mostrarResultados(int k, ResultadoAnalisis resultado) {
        String mineralMasAbundante = "";
        int maxCantidad = -1;

        for (int i = 0; i < resultado.mejoresDistintos; i++) {
            if (resultado.mejoresCantidades[i] > maxCantidad) {
                maxCantidad = resultado.mejoresCantidades[i];
                mineralMasAbundante = resultado.mejoresMinerales[i];
            }
        }
        
        System.out.println("\nRegión más valiosa encontrada:\n");
        System.out.println("Posición inicial: (" + (resultado.mValor.fila + 1) + "," + (resultado.mValor.columna + 1) + ")");
        System.out.println("Tamaño de la región: " + k + " x " + k + "\n");
        System.out.println("Zonas analizadas:");
        
        for (int i = resultado.mValor.fila; i < resultado.mValor.fila + k; i++) {
            for (int j = resultado.mValor.columna; j < resultado.mValor.columna + k; j++) {
                Zona z = matrizZonas[i][j];
                System.out.println("[ " + z.mineral + ", cantidad: " + z.getCantidad() + ", pureza: " + z.getPureza() + " ]");
            }
        }
        System.out.println("\nValor total estimado: " + resultado.mValor.valor + "\n");
        System.out.println("Mineral predominante en la región: " + mineralMasAbundante);
    }

    private class ResultadoAnalisis {
        MayorValor mValor = null;
        String[] mejoresMinerales = null;
        int[] mejoresCantidades = null;
        int mejoresDistintos = 0;
    }
}
