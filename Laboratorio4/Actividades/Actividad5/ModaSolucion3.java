package lab04;

import java.util.*;

public class ModaSolucion3 {

    // Representa un subarreglo con límites
    static class Limits {
        int[] a; int prim, ult;

        Limits(int[] a, int p, int u) { 
            this.a = a; 
            this.prim = p; 
            this.ult = u; 
        }

        // Tamaño del subarreglo
        int size() { return ult - prim + 1; }
    }

    // Encuentra la moda usando particiones parecido a quickselect
    public static int moda3(int[] a) {
        if (a.length == 0) return -1;

        // Subarreglos mezclados (heterogéneos)
        PriorityQueue<Limits> heterog = new PriorityQueue<>(
            (o1, o2) -> o2.size() - o1.size()
        );

        // Subarreglos de un solo valor (homogéneos)
        PriorityQueue<Limits> homog = new PriorityQueue<>(
            (o1, o2) -> o2.size() - o1.size()
        );

        // Inicialmente todo el arreglo es heterogéneo
        heterog.add(new Limits(a, 0, a.length - 1));
        
        // Procesa mientras haya candidatos mejores
        while (!heterog.isEmpty() && 
              (homog.isEmpty() || heterog.peek().size() > homog.peek().size())) {

            Limits p = heterog.poll(); // toma el mayor bloque

            // Usa mediana como pivote
            int mediana = p.a[(p.prim + p.ult) / 2];

            // Divide en <, =, >
            List<Integer> l1 = new ArrayList<>(), 
                          l2 = new ArrayList<>(), 
                          l3 = new ArrayList<>();

            for (int i = p.prim; i <= p.ult; i++) {
                if (a[i] < mediana) l1.add(a[i]);
                else if (a[i] == mediana) l2.add(a[i]);
                else l3.add(a[i]);
            }

            // Reescribe el arreglo particionado
            int curr = p.prim;

            for (int x : l1) a[curr++] = x;

            int startP2 = curr; // inicio del bloque =
            for (int x : l2) a[curr++] = x;

            int startP3 = curr; // inicio del bloque >
            for (int x : l3) a[curr++] = x;

            // Agrega nuevos subproblemas
            if (!l1.isEmpty()) heterog.add(new Limits(a, p.prim, startP2 - 1));
            if (!l2.isEmpty()) homog.add(new Limits(a, startP2, startP3 - 1));
            if (!l3.isEmpty()) heterog.add(new Limits(a, startP3, p.ult));
        }

        // Retorna el valor del bloque homogéneo más grande
        return a[homog.peek().prim];
    }

    public static void main(String[] args) {
        int[] datos = {3, 4, 6, 7, 2, 6};

        // Imprime la moda
        System.out.println("La moda (Solución 3) es: " + moda3(datos));
    }
}