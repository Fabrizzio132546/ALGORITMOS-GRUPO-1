import java.util.Stack;
public class DescuentoCompra {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();

        System.out.print("Ingrese el porcentaje de descuento: ");
        double descuento = scanner.nextDouble();

        double precioFinal = precio - (precio * descuento / 100);

        System.out.println("El precio final es: " + precioFinal);

        scanner.close();
    }
}
