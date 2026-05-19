package avltree;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class GestorTicketsAVL {
    
    // Atributo principal del sistema
    private AVLTree<Integer> tickets;

    // Constructor
    public GestorTicketsAVL() {
        this.tickets = new AVLTree<>();
    }

    // Método para insertar un nuevo ticket
    public void registrarTicket(int numeroTicket) {
        try {
            tickets.insert(numeroTicket);
            System.out.println("Ticket " + numeroTicket + " registrado con éxito.");
        } catch (ItemDuplicated e) {
            System.out.println("El ticket " + numeroTicket + " ya está registrado.");
        }
    }

    // Método para buscar un ticket específico
    public void buscarTicket(int numeroTicket) {
        try {
            Integer encontrado = tickets.search(numeroTicket);
            System.out.println("Buscando ticket " + numeroTicket + ": ¡Encontrado (" + encontrado + ") en el sistema!");
        } catch (ItemNotFound e) {
            System.out.println("Buscando ticket " + numeroTicket + ": No existe en el sistema.");
        }
    }

    // Método para eliminar (atender) un ticket
    public void atenderTicket(int numeroTicket) {
        try {
            System.out.println("\nAtendiendo (eliminando) el ticket: " + numeroTicket + "...");
            tickets.delete(numeroTicket);
            System.out.println("Ticket " + numeroTicket + " atendido.");
        } catch (ExceptionIsEmpty e) {
            System.out.println("No se puede atender el ticket " + numeroTicket + ". El sistema está vacío.");
        }
    }

    // Método para mostrar el estado actual del árbol de tickets
    public void mostrarEstadoSistema() {
        System.out.println("\n--- ESTADO ACTUAL DE LOS TICKETS ---");
        tickets.drawBST();
        System.out.println("Recorrido inorden (Prioridad): " + tickets.getInOrder());
        System.out.println("------------------------------------\n");
    }

    // Main exclusivo para probar la funcionalidad del gestor según el PDF
    public static void main(String[] args) {
        GestorTicketsAVL gestor = new GestorTicketsAVL();
        
        System.out.println("=========================================");
        System.out.println("       INICIANDO GESTOR DE TICKETS       ");
        System.out.println("=========================================");

        // Inserción de tickets requerida
        int[] ticketsNuevos = {30, 10, 20, 40, 50, 25};
        for (int t : ticketsNuevos) {
            gestor.registrarTicket(t);
        }
        gestor.mostrarEstadoSistema();

        // Búsqueda requerida
        gestor.buscarTicket(20);
        gestor.buscarTicket(60);

        // Eliminación requerida
        int[] ticketsAtendidos = {10, 40, 30};
        for (int t : ticketsAtendidos) {
            gestor.atenderTicket(t);
            gestor.mostrarEstadoSistema(); // Mostrar y calcular después de cada operación relevante
        }
    }
}
