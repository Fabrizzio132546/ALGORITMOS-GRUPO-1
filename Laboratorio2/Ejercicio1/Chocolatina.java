package Ejercicio1;


public class Chocolatina {
	private String marca;
	
	public Chocolatina(String marca) {
		this.marca = marca;
		
	}
	
	public String getMarca() {
		return marca;
		
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@Override
	public String toString() {
		return marca;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {    // verifica si ambos tienen misma direccion 
            return true;
        }

        if (o == null) {    // verifia si el objeto es nulo
            return false;
        }

        if (o.getClass() != this.getClass()) {  // verifica que el objeto tenga la misma clase 
            return false; 
        }
        Chocolatina otra = (Chocolatina) o; //se aplica casting para que el objeto sea tratado como una chocolatina

           // si los dos atributos son nulos entonces son iguales
        if (this.marca == null && otra.marca == null) {
            return true;
        } 
           // si el nombre no es nulo entonces compara con equals q tengan mismo contenido
        if (this.marca != null) {
        	
        	// retorna true si tienen e mismo contenido
            return this.marca.equals(otra.marca);
        }

        return false;
    }
	
}
