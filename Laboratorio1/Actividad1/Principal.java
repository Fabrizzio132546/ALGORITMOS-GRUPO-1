package Actividad1;

import java.util.Scanner;
import java.util.Locale;

public class Principal {

    public static void mostrarRectangulo(String nombre, Rectangulo r) {
        System.out.println("Rectangulo " + nombre + " = " + r.toString());
    }

    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
    	// definimos las variables primero para hacerlo mas comprensible
        double x1_r1 = r1.getEsquina1().getX(); double y1_r1 = r1.getEsquina1().getY();
        double x1_r2 = r2.getEsquina1().getX(); double y1_r2 = r2.getEsquina1().getY();
        
        double x2_r1 = r1.getEsquina2().getX(); double y2_r1 = r1.getEsquina2().getY();
        double x2_r2 = r2.getEsquina2().getX(); double y2_r2 = r2.getEsquina2().getY();
        // tratamos de buscar el minimo de las X y de las Y
        double minX = (x1_r1 > x1_r2) ? x1_r1 : x1_r2;
        double minY = (y1_r1 > y1_r2) ? y1_r1 : y1_r2;
        	// buscamos el maximo el minimo de X y Y
        double maxX = (x2_r1 < x2_r2) ? x2_r1 : x2_r2;
        double maxY = (y2_r1 < y2_r2) ? y2_r1 : y2_r2;

        return new Rectangulo(new Coordenada(minX, minY), new Coordenada(maxX, maxY));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); 

        double x1, y1, x2, y2;  //definimos variables
        double x3, y3, x4, y4;
        boolean esInvalido;
        do {    // aplicamos do while para evitar errores al ingresar coordenadas
            System.out.println("Ingrese una esquina del 1er rectángulo:");
            x1 = sc.nextDouble();
            y1 = sc.nextDouble();
            System.out.println("Ingrese la esquina opuesta del 1er rectángulo:");
            x2 = sc.nextDouble();
            y2 = sc.nextDouble();
            esInvalido = (x1 == x2) || (y1 == y2);

            if (esInvalido) {
                System.out.println("Hubo un error con las coordenadas");
                System.out.println("Ingresa coordenadas validas\n");
            }
        } while (esInvalido); 

        do {
            System.out.println("Ingrese una esquina del 2do rectángulo:");
            x3 = sc.nextDouble();
            y3 = sc.nextDouble();
            System.out.println("Ingrese la esquina opuesta del 2do rectángulo:");
            x4 = sc.nextDouble();
            y4 = sc.nextDouble();
            esInvalido = (x3 == x4) || (y3 == y4);

            if (esInvalido) {
                System.out.println("Hubo un error con las coordenadas");
                System.out.println("Ingresa coordenadas validas\\n");
            }
        } while (esInvalido);

        Rectangulo rA = new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
        Rectangulo rB = new Rectangulo(new Coordenada(x3, y3), new Coordenada(x4, y4));

        System.out.println("\nResultados");

        mostrarRectangulo("A", rA);
        mostrarRectangulo("B", rB);

        if (Verificador.esSobrePos(rA, rB)) {
            System.out.println("Rectangulos A y B se sobreponen.");

            Rectangulo rSobre = rectanguloSobre(rA, rB);

            double areaRedondeada = Math.round(rSobre.calculoArea() * 100.0) / 100.0;
            System.out.println("Area de sobreposicion = " + areaRedondeada);
            
        } else if (Verificador.esJunto(rA, rB)) {
            System.out.println("Rectangulos A y B se juntan");
            
        } else if (Verificador.esDisjunto(rA, rB)) {
            System.out.println("Rectangulos A y B son disjuntos");
        }

        sc.close();
    }
}
