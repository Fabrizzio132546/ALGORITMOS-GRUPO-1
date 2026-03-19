package Actividad1;

public class ContainerRect {

    private Rectangulo[] rectangulos;
    private double[] distancias;
    private double[] areas;
    private int n;
    private static int numRec = 0; // Contador global de rectángulos

    public ContainerRect(int n) {
        this.n = n;
        rectangulos = new Rectangulo[n];
        distancias = new double[n];
        areas = new double[n];
    }

    public void addRectangulo(Rectangulo r) {
        // Inserta el rectángulo si hay espacio disponible
        if (numRec < n) {
            rectangulos[numRec] = r;

            Coordenada c1 = r.getEsquina1();
            Coordenada c2 = r.getEsquina2();

            // Guarda la diagonal y el área
            distancias[numRec] = c1.distancia(c2);
            areas[numRec] = r.calculoArea();

            numRec++;
        } else {
            System.out.println("Contenedor lleno");
        }
    }

    @Override
    public String toString() {
        String res = "Rectangulo\tCoordenadas\t\tDistancia\tArea\n";

        for (int i = 0; i < numRec; i++) {
            res += (i + 1) + "\t\t"
                    + rectangulos[i].toString() + "\t"
                    + String.format("%.3f", distancias[i]) + "\t\t"
                    + String.format("%.2f", areas[i]) + "\n";
        }

        return res;
    }
}
