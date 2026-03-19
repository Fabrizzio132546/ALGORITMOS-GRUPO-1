package Actividad1;

public class Rectangulo { 
    private Coordenada esquina1; 
    private Coordenada esquina2; 
    
    public Rectangulo(Coordenada c1, Coordenada c2) { 
        setEsquina1(c1); 
        setEsquina2(c2); 
    } 
   
    public void setEsquina1(Coordenada coo) { 
        if (this.esquina2 != null) { // verifica que exista la esquina2
        	// en esta parte se calcula el X y Y minimo para la esquina1
            double minX = (coo.getX() < this.esquina2.getX()) ? coo.getX() : this.esquina2.getX();
            double minY = (coo.getY() < this.esquina2.getY()) ? coo.getY() : this.esquina2.getY();
         // en esta parte se calcula el X y Y minimo para la esquina2
            double maxX = (coo.getX() > this.esquina2.getX()) ? coo.getX() : this.esquina2.getX();
            double maxY = (coo.getY() > this.esquina2.getY()) ? coo.getY() : this.esquina2.getY();
            // en esta parte se guarda las coordenadas para sus respectivas esquinas
            this.esquina1 = new Coordenada(minX, minY);
            this.esquina2 = new Coordenada(maxX, maxY);
        } else {
            this.esquina1 = coo;  // en caso que no exista esquina2, solo se guardala coordenada
        }
    } 
    public void setEsquina2(Coordenada coo) { 
        if (this.esquina1 != null) { // verifica que exista la esquina 1
        // en esta parte se busca de igual forma la X y Y minima
            double minX = (this.esquina1.getX() < coo.getX()) ? this.esquina1.getX() : coo.getX();
            double minY = (this.esquina1.getY() < coo.getY()) ? this.esquina1.getY() : coo.getY();
            // aqui se busca la X y Y maxima
            double maxX = (this.esquina1.getX() > coo.getX()) ? this.esquina1.getX() : coo.getX();
            double maxY = (this.esquina1.getY() > coo.getY()) ? this.esquina1.getY() : coo.getY();
            
            this.esquina1 = new Coordenada(minX, minY);
            this.esquina2 = new Coordenada(maxX, maxY);
        } else {
            this.esquina2 = coo; //En caso que no exista la Esquina1 se guarda la coordeanda
        }
    }

    public Coordenada getEsquina1() { return this.esquina1; } 
    public Coordenada getEsquina2() { return this.esquina2; }
    
    @Override
    public String toString() {  
        return "(" + this.esquina1.toString() + ", " + this.esquina2.toString() + ")";
    }
    
    public double calculoArea() {
    	// para calular el area debemos encontrar la base y la altura
        double base = this.esquina2.getX() - this.esquina1.getX();
        double altura = this.esquina2.getY() - this.esquina1.getY();
        return base * altura;
    }
}
