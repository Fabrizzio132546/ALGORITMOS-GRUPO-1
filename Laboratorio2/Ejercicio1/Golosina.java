package Ejercicio1;

public class Golosina {

    private String nombre;

    public Golosina(String nombre, double peso) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) {    // verifica si ambos tienen misma direccion 
            return true; 
        }

        if (o == null) {     // verifia si el objeto es nulo
            return false; 
        }
        if (o.getClass() != this.getClass()) {  // verifica que el objeto tenga la misma clase 
            return false; 
        }
        Golosina otra = (Golosina) o;   //se aplica casting para que el objeto sea tratado como una golosina
              
        
           // si los dos atributos son nulos entonces son iguales
        if (this.nombre == null && otra.nombre == null) {
            return true;
        } 
           // si el nombre no es nulo entonces compara con equals q tengan mismo contenido
        if (this.nombre != null) {
            return this.nombre.equals(otra.nombre);
               // retorna true si tienen e mismo contenido
        }

        return false;
    }


    @Override
    public int compareTo(Golosina otra) {
        // si la otra golosina no existe la actual se considera mayor
        if (otra == null) return 1;
        // si ninguna de las dos golosinas tiene nombre se consideran iguales
        if (this.nombre == null && otra.nombre == null) return 0;
        // si mi golosina no tiene nombre pero la otra sí la mia va primero 
        if (this.nombre == null) return -1;
        // si la otra golosina no tiene nombre pero la mia si la mia va después 
        if (otra.nombre == null) return 1;
        // retorna 0 si son iguales, negativo si va antes, positivo si va despues
        return this.nombre.compareTo(otra.nombre);
    } 
}
