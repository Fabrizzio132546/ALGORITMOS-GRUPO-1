package lab01.ejercicio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File archivo = new File("datos.txt"); 

        try {
            Scanner scanner = new Scanner(archivo);
            
            scanner.useLocale(Locale.US);

            int filas = scanner.nextInt();
            int columnas = scanner.nextInt();

            Zona[][] matrizZonas = new Zona[filas][columnas];

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (scanner.hasNext()) {
                        String mineral = scanner.next();
                        int cantidad = scanner.nextInt();
                        double pureza = scanner.nextDouble();

                        matrizZonas[i][j] = new Zona(mineral, cantidad, pureza);
                    }
                }
            }
            System.out.println("Matriz de " + filas + "x" + columnas + " cargada exitosamente.\n");
            scanner.close();
            
            System.out.print("Ingrese la dimesión de la Región a analizar: ");
            int k = sc.nextInt();
            MayorValor Mvalor = null;
            String[] mejoresMinerales = null;
            int[] mejoresCantidades = null;
            int mejoresDistintos = 0;
            
            if (k<=filas && k<=columnas) {
                for (int n = 0; n < filas; n++) {
                    for (int m = 0; m < columnas; m++) {
                        if (n <= filas - k && m <= columnas - k) {
                            int m1=m;
                            int n1=n;
                            double valorRegion=0;
                            int c1=0,c2=0;
                            String[] mineralesRegion = new String[k * k];
                            int[] cantidadesRegion = new int[k * k];
                            int mineralesDistintosRegion = 0;
                            
                            while(c1<=k-1) {
                                while (c2<=k-1) {
                                    double valorZona= matrizZonas[n1][m1].getCantidad() * matrizZonas[n1][m1].getPureza();
                                    valorRegion=valorRegion + valorZona;
                                    
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
                                    if (c2 <=k-1) {
                                        m1++;
                                    }
                                }
                                m1=m;
                                c2=0;
                                c1++;
                                if (c1<=k-1){
                                    n1++;
                                }
                            }	
                            
                            if (Mvalor == null || valorRegion > Mvalor.valor) {
                                Mvalor = new MayorValor(n, m, valorRegion);
                                
                                mejoresMinerales = mineralesRegion.clone();
                                mejoresCantidades = cantidadesRegion.clone();
                                mejoresDistintos = mineralesDistintosRegion;
                            }
                        }
                    }
                }
            }
            
            if (Mvalor != null) {
                String mineralMasAbundante = "";
                int maxCantidad = -1;

                for (int i = 0; i < mejoresDistintos; i++) {
                    if (mejoresCantidades[i] > maxCantidad) {
                        maxCantidad = mejoresCantidades[i];
                        mineralMasAbundante = mejoresMinerales[i];
                    }
                }
                System.out.println("\nRegión más valiosa encontrada:\n");
                System.out.println("Posición inicial: (" + Mvalor.fila+1 + "," + Mvalor.columna+1 + ")");
                System.out.println("Tamaño de la región: " + k + " x " + k + "\n");
                System.out.println("Zonas analizadas:");
                
                
                for (int i = Mvalor.fila; i < Mvalor.fila + k; i++) {
                    for (int j = Mvalor.columna; j < Mvalor.columna + k; j++) {
                        Zona z = matrizZonas[i][j];
                        System.out.println("[ " + z.mineral + ", cantidad: " + z.getCantidad() + ", pureza: " + z.getPureza() + " ]");
                    }
                }
                System.out.println("\nValor total estimado: " + Mvalor.valor + "\n");
                System.out.println("Mineral predominante en la región: " + mineralMasAbundante);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo 'datos.txt'. " +
                               "Asegúrate de que exista y la ruta sea correcta.");
        }
        
    }
}
