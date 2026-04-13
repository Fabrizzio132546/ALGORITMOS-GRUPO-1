package Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class SumarSubconjuntos{

    public static boolean resolver(int[] arreglo, List<Integer> actual, int i, int sumaActual, int objetivo) {

    	if (sumaActual == objetivo) {
    	    System.out.println("elementos sumados: " + actual);
    	    return true;
    	}
    	if (i == arreglo.length) return false;

        int numero = arreglo[i];
        boolean posible = false;
        boolean esmultiplo3 = (numero % 3 == 0);
        boolean parprohibido = false;
        
        if (numero % 2 == 0 && i + 1 < arreglo.length && arreglo[i + 1] % 2 == 0) {
            parprohibido = true;
        }
        if (!parprohibido) {
            actual.add(numero);
            posible = resolver(arreglo, actual, i + 1, sumaActual + numero, objetivo);

            actual.remove(actual.size() - 1); 
        }
        if (!posible && !esmultiplo3) {
            posible = resolver(arreglo, actual, i + 1, sumaActual, objetivo);
        }

        return posible;
    }

    public static void main(String[] arg) {

        int[] arr1 = {3,7, 10};
        int n1 = arr1.length;   
        int objetivo1 = arr1[n1-1];  
        
        boolean resultado1 = resolver(arr1, new ArrayList<>(), 0, 0, objetivo1);
        
        System.out.print("arreglo: ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println(); 
        System.out.println("objetivo: " + objetivo1);
        System.out.println("salida: " + resultado1);
        
        
        System.out.println(); 
        
        
        int[] arr2 = {6, 5, 3, 1, 10};
        int n2 = arr2.length;   
        int objetivo2 = arr2[n2-1];  
        
        boolean resultado2 = resolver(arr2, new ArrayList<>(), 0, 0, objetivo2);
        
        System.out.print("Arreglo: ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println(); 
        System.out.println("Objetivo: " + objetivo2);
        System.out.println("Salida: " + resultado2);
        
    }
}
