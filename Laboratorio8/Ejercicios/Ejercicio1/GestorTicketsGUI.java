package avltree;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorTicketsGUI extends JFrame {

    private AVLTree<Integer> tickets;
    private JTextField txtTicket;
    private JTextArea txtConsola;

    public GestorTicketsGUI() {
        // Inicializamos el árbol AVL
        tickets = new AVLTree<>();

        // Configuración básica de la ventana
        setTitle("Gestor de Tickets Urgentes - Árbol AVL");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(new BorderLayout());

        // --- PANEL SUPERIOR (Entrada y Botones) ---
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel lblTicket = new JLabel("N° de Ticket:");
        txtTicket = new JTextField(10);

        JButton btnInsertar = new JButton("Insertar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEliminar = new JButton("Atender (Eliminar)");

        panelTop.add(lblTicket);
        panelTop.add(txtTicket);
        panelTop.add(btnInsertar);
        panelTop.add(btnBuscar);
        panelTop.add(btnEliminar);

        add(panelTop, BorderLayout.NORTH);

        // --- PANEL CENTRAL (Consola de salida) ---
        txtConsola = new JTextArea();
        txtConsola.setEditable(false);
        // Es VITAL usar una fuente monoespaciada para que el árbol se dibuje correctamente
        txtConsola.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtConsola.setBackground(new Color(30, 30, 30));
        txtConsola.setForeground(new Color(0, 255, 0)); // Estilo consola retro

        JScrollPane scrollPane = new JScrollPane(txtConsola);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Estado del Sistema / Consola"));
        add(scrollPane, BorderLayout.CENTER);

        // --- EVENTOS DE LOS BOTONES ---

        // Evento Insertar
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer numTicket = obtenerTicketInput();
                if (numTicket != null) {
                    try {
                        tickets.insert(numTicket);
                        imprimirMensaje("✅ Ticket " + numTicket + " registrado exitosamente.");
                        actualizarVistaArbol();
                    } catch (ItemDuplicated ex) {
                        imprimirMensaje("⚠️ Error: " + ex.getMessage());
                    }
                    txtTicket.setText("");
                }
            }
        });

        // Evento Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer numTicket = obtenerTicketInput();
                if (numTicket != null) {
                    try {
                        Integer encontrado = tickets.search(numTicket);
                        imprimirMensaje("🔍 Búsqueda: ¡El ticket " + encontrado + " sí se encuentra en el sistema!");
                    } catch (ItemNotFound ex) {
                        imprimirMensaje("❌ Búsqueda: " + ex.getMessage());
                    }
                    txtTicket.setText("");
                }
            }
        });

        // Evento Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer numTicket = obtenerTicketInput();
                if (numTicket != null) {
                    try {
                        tickets.delete(numTicket);
                        imprimirMensaje("✅ Ticket " + numTicket + " ha sido atendido y eliminado del sistema.");
                        actualizarVistaArbol();
                    } catch (ExceptionIsEmpty ex) {
                        imprimirMensaje("⚠️ Error: " + ex.getMessage());
                    } catch (Exception ex) {
                        imprimirMensaje("⚠️ Ocurrió un error al intentar eliminar el ticket.");
                    }
                    txtTicket.setText("");
                }
            }
        });

        // Mensaje inicial de bienvenida
        imprimirMensaje("Sistema iniciado. Ingrese los tickets para comenzar (Ej: 30, 10, 20...).");
    }

    // --- MÉTODOS AUXILIARES ---

    private Integer obtenerTicketInput() {
        String texto = txtTicket.getText().trim();
        if (texto.isEmpty()) {
            imprimirMensaje("⚠️ Por favor, ingrese un número de ticket válido.");
            return null;
        }
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            imprimirMensaje("⚠️ Formato inválido. Ingrese solo números enteros.");
            return null;
        }
    }

    private void actualizarVistaArbol() {
        txtConsola.append("\n--- ÁRBOL AVL ACTUALIZADO ---\n");
        txtConsola.append(tickets.toString() + "\n");
        txtConsola.append("Recorrido Inorden: " + tickets.getInOrder() + "\n");
        txtConsola.append("-----------------------------\n");
        // Auto-scroll hacia abajo
        txtConsola.setCaretPosition(txtConsola.getDocument().getLength());
    }

    private void imprimirMensaje(String mensaje) {
        txtConsola.append(mensaje + "\n");
        txtConsola.setCaretPosition(txtConsola.getDocument().getLength());
    }

    // --- MÉTODO MAIN PARA EJECUTAR LA GUI ---
    public static void main(String[] args) {
        // Forzar el look and feel del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ejecutar la interfaz en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GestorTicketsGUI app = new GestorTicketsGUI();
                app.setVisible(true);
            }
        });
    }
}
