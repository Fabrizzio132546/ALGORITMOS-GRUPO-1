package Codigo1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ServicioLogistico logistica = new ServicioLogistico();
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n GESTION DE ALMACEN");
            System.out.println("1 Recepcion de Mercancia");
            System.out.println("2 Generar Reporte de Stock y valorizacion");
            System.out.println("3 Salir del Sistema");
            System.out.print("Opcion: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("SKU: "); String sku = sc.nextLine();
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Cantidad: "); int cant = sc.nextInt();
                    System.out.print("Costo Unitario: "); double costo = sc.nextDouble();
                    logistica.registrarEntrada(new Producto(sku, nom, cant, costo));
                    break;
                case 2:
                    logistica.generarReporteValorizacion();
                    break;
            }
        } while (op != 3);
        System.out.println("Saliendo");
    }
}
