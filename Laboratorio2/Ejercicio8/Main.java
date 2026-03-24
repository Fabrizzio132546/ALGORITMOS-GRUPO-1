package Ejercicio8;


import java.util.Arrays;

public class Main {
	
	
	public static <T> void swap(T[] arreglo, int i, int j) {
		
// verificamos primero que tanto i como J esten dentro del rango del arreglo
    	if (i >=0 && i< arreglo.length && j >=0 && j <arreglo.length) {
    		
    		T copia = arreglo[i];  // copiamos contenido de posicion i
            arreglo[i] = arreglo[j];  // ponemos el contenido de posicion j en la poisiocn i 
            arreglo[j] = copia;  // ponemos el contenido q se copio en j
    	} else {
    		System.out.println("los indices son incorrectos" + i + ", " + j);
    	}

    }
	
	
	
    public static void main(String[] args) {

        String[] letras = {"A", "B", "C", "D"};
        System.out.println("Entrada: " + Arrays.toString(letras));
        swap(letras, 1, 3); 
        System.out.println("Salida: " + Arrays.toString(letras));
        
        System.out.println("");

        Integer[] numeros = {10, 20, 30, 40};
        System.out.println("Entrada: " + Arrays.toString(numeros));
        swap(numeros, 0, 2);
        System.out.println("Salida: " + Arrays.toString(numeros));

        System.out.println("");

        Chocolatina c1 = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("ferrero");
        Chocolatina[] choco = {c1, c2};
        
        System.out.println("Entrada: " + Arrays.toString(choco));
        swap(choco, 0, 1);
        System.out.println("Salida: " + Arrays.toString(choco));
        
        System.out.println("");
        
        Animales a1 = new Animales("gato");
        Animales a2 = new Animales("perro");
        
        Animales[] anima= {a1, a2};
        
        System.out.println("Entrada: " + Arrays.toString(anima));
        swap(anima, 0, 1);
        System.out.println("Salida: " + Arrays.toString(anima));
        
        
        
    }
}
