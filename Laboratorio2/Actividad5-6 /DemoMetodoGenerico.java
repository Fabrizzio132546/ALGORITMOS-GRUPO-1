package Acttividad5;

public class DemoMetodoGenerico {
	static <T extends Comparable<T>> boolean igualArray (T[] x, T[] y) {
		
			// primero se debe verificar que ambos arreglos tengan misma longitud
		if (x.length != y.length)  {
			return false;
		}
		
		 // se recorre el arreglo para ver si cada uno de los elementos son iguales
		for (int i = 0; i < x.length; i++) {
			if (!x[i].equals(y[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] arg) {
		Integer nums1[] = {1,2,3,4,5};
		Integer nums2[] = {1,2,3,4,5};
		Integer nums3[] = {1,2,7,4,5};
		Integer nums4[] = {1,2,7,4,5,6};
		
		if (igualArray(nums1, nums1)) {
			System.out.println("nums1 es igual a nums1");
		}
		if (igualArray(nums1, nums2)) {
			System.out.println("nums1 es igual a nums2");
		}
		
		if (igualArray(nums1, nums3)) {
			System.out.println("nums1 es igual a nums3");
		}
		if (igualArray(nums1, nums4)) {
			System.out.println("nums1 es igual a nums4");
		}
		
		//Crea un array de double
		// Double dvals[] = {1.1,2.2,3.3,4.4,5.6};
		// if (igualArray(nums1, dvals)) {
		//	System.out.println("nums es igual a dvlas");
		//}
	}

}
