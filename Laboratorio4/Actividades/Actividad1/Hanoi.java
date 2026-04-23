package Actividades;
public class Hanoi {
public static void main(String[] args) {
int numDiscos = 3;
System.out.println("=== Torres de Han´oi con " + numDiscos + " disco(s) ===\n");
torresHanoi(numDiscos, 1, 2, 3);
}
// Método recursivo para la soluciónn de las Torres de Hanoi
// Parámetros:
// discos → número de discos a mover
// torre1 → torre origen
// torre2 → torre auxiliar
// torre3 → torre destino
public static void torresHanoi(int discos, int torre1, int torre2, int torre3) {
// Caso base: solo queda 1 disco, se mueve directamente
if (discos == 1) {
System.out.println("Mover disco 1 de torre " + torre1 + " a torre " + torre3);
return;
}
// Paso 1: mover n-1 discos de origen (torre1) a auxiliar (torre2)
torresHanoi(discos - 1, torre1, torre3, torre2);
// Paso 2: mover el disco más grande de origen a destino
System.out.println("Mover disco " + discos + " de torre " + torre1 + " a torre " + torre3);
// Paso 3: mover n-1 discos de auxiliar (torre2) a destino (torre3)
torresHanoi(discos - 1, torre2, torre1, torre3);
}
}

