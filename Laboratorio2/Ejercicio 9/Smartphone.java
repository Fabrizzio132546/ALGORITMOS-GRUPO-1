package lab02.ejercicio9;

public class Smartphone implements Cargable{
	private String modelo;
	private double consumoVatios;
	private int nivelBateria=0;
	
	Smartphone(String m,double cV){
		this.modelo=m;
		this.consumoVatios=cV;
	}
	
	public String getModelo() {
		return this.modelo;
	}
	public void setModelo(String mo) {
		this.modelo=mo;
	}
	@Override
	public double getConsumoVatios() {
		return this.consumoVatios;
	}
	public void setConsumoVatios(int cv) {
		this.consumoVatios=cv;
	}
	
	@Override
	public int getNivelBateria() {
		return this.nivelBateria;
	}
	@Override
	public void cargar(int cantidad) {
		if( this.nivelBateria+cantidad<100)
			this.nivelBateria+=cantidad;
		else
			System.out.println("La carga sobrepasa el límite (100%)");
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) 	//verifica si están en el mismo espacio de memoria
			return true;
		if (obj == null)	//verifica que el objeto a comparar no sea vacío
			return false; 
		if (this.getClass() != obj.getClass())	//verifica si son de clases diferentes
			return false; 
		Smartphone uno= (Smartphone) obj; //Casting para poder comparar los atributos
		return modelo.equals(uno.modelo);
	}
	@Override
	public String toString() {
		return "Smartphone - Modelo: "+this.modelo+" Consumo en Vatios: "+this.consumoVatios+" Nivel de batería: "+this.nivelBateria;
	}
}
