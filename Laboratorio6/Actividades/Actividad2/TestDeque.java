package Actividad2;

public class TestDeque {
    public static void main(String[] args) {
        Deque<String> D1 = new DequeLink<>();

        // 1. Probamos agregar al frente y al final
        System.out.println("AGREGANDO");
        D1.addFirst("Fabrizzio");    // Fabrizzio
        D1.addLast("Samir");  // Fabrizzio, Samir
        D1.addFirst("Melo");  // Melo, Fabrizzio, Samir
        D1.addLast("Juan");   // Melo, Fabrizzio, Samir, Juan
        
        System.out.println(D1.toString());
        
        System.out.println();
        
        System.out.println("PRIMER Y ULTIMO ELEMENTO");

        // obteniendo el primer y ultimo elemneto
        try {
            System.out.println("Primer elemento: " + D1.getFirst());
            System.out.println("ÚUtimo elemento: " + D1.getLast());
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        // Pruebas para remover
        System.out.println("REMOVIENDO");
        try {
            System.out.println("Removido del frente: " + D1.removeFirst()); 
            System.out.println("Luego de remover el primero: " + D1.toString());

            System.out.println("Removido del final: " + D1.removeLast());
            System.out.println("Luego de remover el ultimo: " + D1.toString());

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println();

        System.out.println("PROBANDO EXCEPCIONES");
        try {
        	D1.removeFirst(); 
        	D1.removeFirst(); 
            System.out.println("Esta vacio? " + D1.isEmpty());
            
            D1.removeFirst(); 
            
        } catch (ExceptionIsEmpty e) {
            System.out.println("Hubo una excepcion: " + e.getMessage());
        }
    }
}
