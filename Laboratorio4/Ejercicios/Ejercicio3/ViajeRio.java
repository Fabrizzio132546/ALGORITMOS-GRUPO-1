package Actividades;
import java.util.Arrays;
public class ViajeRio {
public static void main(String[] args) {
// Matriz de tarifas T (triangular superior)
// T[i][j] = costo de ir del embarcadero i al j directamente
// Se usa Integer.MAX_VALUE/2 donde no hay ruta directa
int INF = Integer.MAX_VALUE / 2;
int[][] T = {
{0, 2, 5, 9, INF},
{INF, 0, 3, 6, 8 },
{INF, INF, 0, 2, 7 },
{INF, INF, INF, 0, 3 },
{INF, INF, INF, INF, 0 }
};
int n = T.length;
// Matriz C donde se almacenan los costos mínimos
int[][] C = new int[n][n];
// Inicializar C con la matriz de tarifas directas
for (int[] fila : C)
Arrays.fill(fila, INF);
// Caso base: costo de ir de i a i es 0
for (int i = 0; i < n; i++)
C[i][i] = 0;
// Llenar C usando programación dinámica
// gap = distancia entre embarcaderos
for (int gap = 1; gap < n; gap++) {
for (int i = 0; i + gap < n; i++) {
int j = i + gap;
// Primero considerar el viaje directo
C[i][j] = T[i][j];
// Luego verificar si hacer escala en k es más barato
for (int k = i + 1; k < j; k++) {
if (T[i][k] + C[k][j] < C[i][j]) {
C[i][j] = T[i][k] + C[k][j];
}
}
}
}
// Mostrar la matriz de costos mínimos
System.out.println("=== Matriz de costos m´ınimos C ===\n");
for (int i = 0; i < n; i++) {
for (int j = 0; j < n; j++) {
if (C[i][j] == INF)
System.out.printf("%5s", "INF");
else
System.out.printf("%5d", C[i][j]);
}
System.out.println();
}
// Mostrar el costo mínimo entre cada par de embarcaderos
System.out.println("\n=== Costos m´ınimos entre embarcaderos ===\n");
for (int i = 0; i < n; i++) {
for (int j = i + 1; j < n; j++) {
System.out.println("Embarcadero " + i + " → Embarcadero " + j
+ " : costo m´ınimo = " + C[i][j]);
}
}
}
}

