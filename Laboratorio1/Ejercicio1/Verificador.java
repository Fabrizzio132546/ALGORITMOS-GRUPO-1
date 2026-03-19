package Actividad1;

public class Verificador {

    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        // Verifica si se intersectan en ambos ejes (condición de solapamiento)
        boolean seCruzanEnX = r1.getEsquina1().getX() < r2.getEsquina2().getX() && 
                             r1.getEsquina2().getX() > r2.getEsquina1().getX();
                              
        boolean seCruzanEnY = r1.getEsquina1().getY() < r2.getEsquina2().getY() && 
                             r1.getEsquina2().getY() > r2.getEsquina1().getY();

        return seCruzanEnX && seCruzanEnY;
    }

    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        // Si se sobreponen, no pueden ser solo "juntos"
        if (esSobrePos(r1, r2)) return false;

        boolean chocanBordeX = (r1.getEsquina2().getX() == r2.getEsquina1().getX() || 
                               r1.getEsquina1().getX() == r2.getEsquina2().getX());
        
        boolean compartenAlturaY = (r1.getEsquina1().getY() <= r2.getEsquina2().getY() && 
                                   r1.getEsquina2().getY() >= r2.getEsquina1().getY());

        boolean chocanBordeY = (r1.getEsquina2().getY() == r2.getEsquina1().getY() || 
                               r1.getEsquina1().getY() == r2.getEsquina2().getY());
        
        boolean compartenBaseX = (r1.getEsquina1().getX() <= r2.getEsquina2().getX() && 
                                 r1.getEsquina2().getX() >= r2.getEsquina1().getX());

        return (chocanBordeX && compartenAlturaY) || (chocanBordeY && compartenBaseX);
    }

    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        // No se tocan ni se superponen
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
}
