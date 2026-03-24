package Ejer_1_Complementando_Act_5;


public class TestGen {
	static <T extends Comparable<T>> boolean igualArray (T[] x, T[] y) {
		if (x.length != y.length)  {
			return false;
		}
		for (int i = 0; i < x.length; i++) {
			if (!x[i].equals(y[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	public static <T> boolean exist(T[] arreglo, T elemento) {
		
		//se usa un bucle for para poder recorrer todo el arreglo
        for (int i = 0; i < arreglo.length; i++) {
        	// se busca que el elemento sea igual q alguno en las posiciones del arreglo
            if (arreglo[i].equals(elemento)) {
                return true; 
            }
        }
        return false; 
    }
	

	public static void main(String[] arg) {
		
		
		String[] v = {"Perez", "Sanchez", "Rodriguez"};
		Integer[] w = {12, 34, 65};
		
		System.out.println(exist(v, "Sanchez")); 
		System.out.println(exist(w, 34));  
		
		//System.out.println(exist(w, "Salas"));   //Error intencional los tipos son incompatibles
		
		Chocolatina c1 = new Chocolatina("Milka");
		Chocolatina c2 = new Chocolatina("Ferrero");
		
		Chocolatina[] choco = {c1, c2};
		
		//Chocolatina c3 = new Chocolatina("Milka");
		
		Chocolatina c4 = new Chocolatina("otra cosa");
		
		//System.out.println("existe?: " + exist(choco, c3));
		
		System.out.println("existe?: " + exist(choco, c4));
		System.out.println("existe?: " + exist(choco, c1));
		

        
	}
}
