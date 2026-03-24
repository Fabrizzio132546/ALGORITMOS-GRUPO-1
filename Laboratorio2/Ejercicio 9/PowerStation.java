package lab02.ejercicio9;
import java.util.ArrayList;

public class PowerStation<T extends Cargable> {
	private ArrayList<T> lista = new ArrayList<T>();
	
	public PowerStation() {
	}
	
	public void conectar(T dispositivo) {
		lista.add(dispositivo);
	}
	
	public double calcularConsumoTotal() {
		double consumoTotal=0;
		for (T dispositivo : lista) { 
	        consumoTotal += dispositivo.getConsumoVatios();
	    }
		return consumoTotal;
	}
	
	public int buscarDispositivo(T prototipo) {
		
		for(int i =0; i<lista.size();i++) {
			if (lista.get(i)!=null && lista.get(i).equals(prototipo)) {
				return i+1;
			}
		}
		return -1;
	}
	public void mostrarReporte() {
	    System.out.println("Posición                                    Objeto");
	    System.out.println("-----------------------------------------------------------------------------------");

	    for (int i = 0; i < lista.size(); i++) {
	        System.out.print(i+1);

	        if (i+1 < 10) {
	            System.out.print("              ");
	        } else {
	            System.out.print("             ");
	        }
	        System.out.println(lista.get(i).toString());
	    }
	}
}
