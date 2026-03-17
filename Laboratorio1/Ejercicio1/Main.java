package Actividad1;

import java.util.Scanner;
import java.util.Locale;

public class Main {

    public static void mostrarRectangulo(String nombre, Rectangulo r) {
        System.out.println("Rectangulo " + nombre + " = " + r.toString());
    }

    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        double minX = Math.max(r1.getEsquina1().getX(), r2.getEsquina1().getX());
        double minY = Math.max(r1.getEsquina1().getY(), r2.getEsquina1().getY());
        double maxX = Math.min(r1.getEsquina2().getX(), r2.getEsquina2().getX());
        double maxY = Math.min(r1.getEsquina2().getY(), r2.getEsquina2().getY());

        return new Rectangulo(new Coordenada(minX, minY), new Coordenada(maxX, maxY));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        ContainerRect contenedor = new ContainerRect(10);

        double x1, y1, x2, y2;
        double x3, y3, x4, y4;
        boolean esInvalido;

        do {
            System.out.println("Ingrese una esquina del 1er rectángulo:");
            x1 = sc.nextDouble();
            y1 = sc.nextDouble();

            System.out.println("Ingrese la esquina opuesta del 1er rectángulo:");
            x2 = sc.nextDouble();
            y2 = sc.nextDouble();

            esInvalido = (x1 == x2) || (y1 == y2);

        } while (esInvalido);

        do {
            System.out.println("Ingrese una esquina del 2do rectángulo:");
            x3 = sc.nextDouble();
            y3 = sc.nextDouble();

            System.out.println("Ingrese la esquina opuesta del 2do rectángulo:");
            x4 = sc.nextDouble();
            y4 = sc.nextDouble();

            esInvalido = (x3 == x4) || (y3 == y4);

        } while (esInvalido);

        Rectangulo rA = new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
        Rectangulo rB = new Rectangulo(new Coordenada(x3, y3), new Coordenada(x4, y4));

        // 🔹 Se guarda en el contenedor
        contenedor.addRectangulo(rA);
        contenedor.addRectangulo(rB);

        System.out.println("\nResultados");
        mostrarRectangulo("A", rA);
        mostrarRectangulo("B", rB);

        if (Verificador.esSobrePos(rA, rB)) {
            System.out.println("Se sobreponen");

            Rectangulo rSobre = rectanguloSobre(rA, rB);
            double area = Math.round(rSobre.calculoArea() * 100.0) / 100.0;

            System.out.println("Area de sobreposicion = " + area);

        } else if (Verificador.esJunto(rA, rB)) {
            System.out.println("Se juntan");
        } else {
            System.out.println("Son disjuntos");
        }

        // 🔹 Muestra el contenido del contenedor
        System.out.println("\nContenido del contenedor:");
        System.out.println(contenedor);

        sc.close();
    }
}
