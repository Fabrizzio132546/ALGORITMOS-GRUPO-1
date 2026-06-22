package hash;

public class TestEjercicio3 {
    public static void main(String[] args) {
        HashO<String> tabla = new HashO<>(7);

        tabla.insert(new Register<>(10, "Juan"));
        tabla.insert(new Register<>(17, "Ana"));
        tabla.insert(new Register<>(24, "Luis"));
        tabla.insert(new Register<>(31, "Rosa"));
        tabla.insert(new Register<>(5, "Pedro"));
        tabla.insert(new Register<>(12, "Carla"));

        System.out.println("Tabla despues de insertar:");
        tabla.printTable();

        System.out.println("\nBusqueda de la clave 24:");
        Register<String> buscado = tabla.search(24);

        if (buscado != null) {
            System.out.println("Nombre encontrado: " + buscado.getName());

            int[] ubicacion = tabla.obtenerPosicionYNodo(24);
            System.out.println("Posicion en la tabla: " + ubicacion[0]);
            System.out.println("Nodo en la lista: " + (ubicacion[1] + 1));
        } else {
            System.out.println("Clave no encontrada.");
        }

        tabla.delete(17);

        System.out.println("\nTabla despues de eliminar la clave 17:");
        tabla.printTable();

        System.out.println("\nNodos restantes en la cadena 3: "
                + tabla.contarNodosEnCadena(3));
    }
}












