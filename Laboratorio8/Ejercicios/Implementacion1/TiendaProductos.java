package lab08.implementaciones;

import exceptions.*;

public class TiendaProductos {
    public static void main(String[] args) {
        AVLTree<Producto> inventario = new AVLTree<>();

        try {
            System.out.println("--- REGISTRANDO PRODUCTOS EN ALMACÉN ---");
            inventario.insert(new Producto(105, "Teclado Mecánico Redragon", 15, 120.50));
            inventario.insert(new Producto(201, "Mouse Logitech G203", 30, 85.00));
            inventario.insert(new Producto(350, "Monitor LG 24'' 144Hz", 5, 650.00)); 

            System.out.println("Catálogo ordenado por código:");
            System.out.println(inventario.getInOrder());

            System.out.println("\n--- BÚSQUEDA Y VENTA ---");
            // Para buscar, creamos un producto "ficticio" solo con el código que queremos encontrar
            Producto criterioBusqueda = new Producto(201, "", 0, 0);
            Producto encontrado = inventario.search(criterioBusqueda);
            
            System.out.println("Cliente compró 1 unidad de: " + encontrado.getNombre());
            encontrado.reducirStock(1);
            System.out.println("Stock actualizado: " + encontrado);

            System.out.println("\n--- DESCONTINUANDO PRODUCTO ---");
            Producto aEliminar = new Producto(105, "", 0, 0);
            inventario.delete(aEliminar);
            System.out.println("El producto 105 fue retirado del inventario.");
            
            System.out.println("\nInventario final:");
            System.out.println(inventario.getInOrder());

        } catch (ItemDuplicated | ExceptionIsEmpty e) {
            System.out.println("Error de inventario: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.out.println("Error de búsqueda: El producto no existe en el catálogo.");
        }
    }
}