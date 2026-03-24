package lab02.ejercicio9;

public class Laptop implements Cargable{
	private String marca;
	private double consumoVatios;
	private int nivelBateria=0;
	
	public Laptop(String m,double cV){
		this.marca=m;
		this.consumoVatios=cV;
	}
	public String getMarca() {
		return this.marca;
	}
	public void setModelo(String ma) {
		this.marca=ma;
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
		Laptop uno= (Laptop) obj; //Casting para poder comparar los atributos
		return marca.equals(uno.marca);
	}
	@Override
	public String toString() {
		return "Laptop - Modelo: "+this.marca+" Consumo en Vatios: "+this.consumoVatios+" Nivel de batería: "+this.nivelBateria;
	}
}
