package lab11.ejercicios.ejercicio5;
import hash.*;

public class TestEjercicio5 {
    public static void main(String[] args) {
        HashC<String> tabla = new HashC<>(7);
        
        System.out.println("Estado Inicial de la tabla:");
        tabla.printTable();
        System.out.println("-------------------------\n");

        @SuppressWarnings("unchecked")
        Register<String>[] registros = new Register[] {
            new Register<>(2, "A"),
            new Register<>(9, "B"),
            new Register<>(16, "C"),
            new Register<>(23, "D"),
            new Register<>(4, "E"),
            new Register<>(11, "F") 
        };

        for (Register<String> reg : registros) {
            System.out.println("Insertando clave: " + reg.getKey());
            tabla.insert(reg);
            System.out.println("-");
        }

        System.out.println("\nEstado de la tabla DESPUÉS del rehashing:");
        tabla.printTable();
    }
}