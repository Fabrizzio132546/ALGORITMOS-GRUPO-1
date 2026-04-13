package Actividad2;

import java.util.ArrayList;
import java.util.List;

public class Subconjuntos {
	public static void generarSubconjuntos(int[] arreglo, List<Integer> actual, int i) {

		if(i == arreglo.length) {
			System.out.println(actual);
			return;
			
		}
		actual.add(arreglo[i]);
		generarSubconjuntos(arreglo, actual, i+1);
		
		//bactraking
		actual.remove(actual.size() - 1);
		// no se incluye el elemento
		generarSubconjuntos(arreglo, actual, i + 1);
	}
	
	public static void main(String[] arg) {
		int[] arreglo = {1, 2, 3};
		generarSubconjuntos(arreglo, new ArrayList<>(), 0);
	}

}
