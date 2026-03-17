package lab01.ejercicio2;

public class Zona {

	public String mineral;
	private int cantidad;
	private double pureza;
	
	public Zona(String mineral, int cantidad, double pureza) {
		this.mineral=mineral;
		this.cantidad=cantidad;
		this.pureza=pureza;
	}
	
	int getCantidad() {
		return this.cantidad;
	}
	double getPureza() {
		return this.pureza;
	}
	
	@Override
	public String toString() {
		return mineral+"Cantidad: "+cantidad+", Puerza: "+pureza;
	}
}
