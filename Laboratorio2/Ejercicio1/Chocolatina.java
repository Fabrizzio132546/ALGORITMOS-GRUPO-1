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

	@Override
	public int compareTo(Chocolatina o) {
	    // si la otra chocolatina no existe la actual se considera mayor
	    if (o == null) return 1; 
	    // si ninguna de las dos chocolatinas tiene marca se consideran iguales
	    if (this.marca == null && o.marca == null) return 0;
	    // si mi chocolatina no tiene marca pero la otra si la mia va primero
	    if (this.marca == null) return -1;
	    // si la otra chocolatina no tiene marca pero la mia si, la mia va despues 
	    if (o.marca == null) return 1;
	    // si ambas tienen marca se comparan alfabeticamente usando string
	    return this.marca.compareTo(o.marca);
	}
	
}
