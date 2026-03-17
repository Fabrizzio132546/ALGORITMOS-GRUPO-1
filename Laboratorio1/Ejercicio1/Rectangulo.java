package Actividad1;

public class Rectangulo { 
    private Coordenada esquina1; 
    private Coordenada esquina2; 
    
    public Rectangulo(Coordenada c1, Coordenada c2) { 
        setEsquina1(c1); 
        setEsquina2(c2); 
    } 
   
    public void setEsquina1(Coordenada coo) { 
        if (this.esquina2 != null) {
            double minX = Math.min(coo.getX(), this.esquina2.getX());
            double minY = Math.min(coo.getY(), this.esquina2.getY());
            double maxX = Math.max(coo.getX(), this.esquina2.getX());
            double maxY = Math.max(coo.getY(), this.esquina2.getY());
            
            this.esquina1 = new Coordenada(minX, minY);
            this.esquina2 = new Coordenada(maxX, maxY);
        } else {
            this.esquina1 = coo;
        }
    } 
 
    public void setEsquina2(Coordenada coo) { 
        if (this.esquina1 != null) {
            double minX = Math.min(this.esquina1.getX(), coo.getX());
            double minY = Math.min(this.esquina1.getY(), coo.getY());
            double maxX = Math.max(this.esquina1.getX(), coo.getX());
            double maxY = Math.max(this.esquina1.getY(), coo.getY());
            
            this.esquina1 = new Coordenada(minX, minY);
            this.esquina2 = new Coordenada(maxX, maxY);
        } else {
            this.esquina2 = coo;
        }
    }

    public Coordenada getEsquina1() { return this.esquina1; } 
    public Coordenada getEsquina2() { return this.esquina2; }
    
    @Override
    public String toString() { 
        return "(" + this.esquina1.toString() + ", " + this.esquina2.toString() + ")";
    }
    
    public double calculoArea() {
        double base = this.esquina2.getX() - this.esquina1.getX();
        double altura = this.esquina2.getY() - this.esquina1.getY();
        return base * altura;
    }
}
