package Actividad1;

public class Verificador {

    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        // aqui verificamos para cuando Se cruza en X
        boolean seCruzanEnX = r1.getEsquina1().getX() < r2.getEsquina2().getX() && 
                              r1.getEsquina2().getX() > r2.getEsquina1().getX();
              // verificamos para cuando se cruza el Y,                 
        boolean seCruzanEnY = r1.getEsquina1().getY() < r2.getEsquina2().getY() && 
                              r1.getEsquina2().getY() > r2.getEsquina1().getY();
        // solo obtendremos verdadero cuando se crucen en X y Y
        return seCruzanEnX && seCruzanEnY;
    }

    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        if (esSobrePos(r1, r2)) {  // se verifica primero que no este sobrepuesto
            return false;   
        }
        // para que choquen en X, deben tener alguna coordenada de X igual
        boolean chocanBordeX = (r1.getEsquina2().getX() == r2.getEsquina1().getX() || 
                                r1.getEsquina1().getX() == r2.getEsquina2().getX());
        // no solamente es necesario saber si chocan en X, sino ver si comparten el Y
        boolean compartenAlturaY = (r1.getEsquina1().getY() <= r2.getEsquina2().getY() && 
                                    r1.getEsquina2().getY() >= r2.getEsquina1().getY());
        				// se verifica para cuando chocan en esquina tambien con el <= o >=
        // se realiza la misma verifiaccion para el eje Y y luego X
        boolean chocanBordeY = (r1.getEsquina2().getY() == r2.getEsquina1().getY() || 
                                r1.getEsquina1().getY() == r2.getEsquina2().getY());
        
        boolean compartenBaseX = (r1.getEsquina1().getX() <= r2.getEsquina2().getX() && 
                                  r1.getEsquina2().getX() >= r2.getEsquina1().getX());
        // para que sea verdad debe cumplir una de las dos condiciones 
        return (chocanBordeX && compartenAlturaY) || (chocanBordeY && compartenBaseX);
    }

    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
    	// es disjunto para cuando no se cumple con lo anterior
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
}
