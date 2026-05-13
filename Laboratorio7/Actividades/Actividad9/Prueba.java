package lab07.actividades.actividad9;

import exceptions.ItemDuplicated;

public class Prueba {

    public static void main(String[] args) {
        
        LinkedBST<Integer> arbol = new LinkedBST<>();

        System.out.println("--- PRUEBA DE ÁRBOL BINARIO DE BÚSQUEDA ---");

        // Uso obligatorio de try-catch porque insert() lanza una Checked Exception
        try {
            System.out.println("Insertando 400 (Raíz)..."); // El primer insert define la raíz
            arbol.insert(400);
            
            System.out.println("Insertando 100...");
            arbol.insert(100);
            
            System.out.println("Insertando 700...");
            arbol.insert(700);
            
            System.out.println("Insertando 50...");
            arbol.insert(50);
            
            System.out.println("Insertando 200...");
            arbol.insert(200);
            
            System.out.println("Insertando 75...");
            arbol.insert(75);

            System.out.println("\n--- RESULTADO DEL RECORRIDO ---");
            System.out.println("Recorrido Post-Orden esperado : 75 50 200 100 700 400");
            System.out.print("Recorrido Post-Orden obtenido : ");
            
            // Llama implícitamente a toString(), que ejecuta el postOrder
            System.out.println(arbol.toString()); 

        } catch (ItemDuplicated e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }
}
