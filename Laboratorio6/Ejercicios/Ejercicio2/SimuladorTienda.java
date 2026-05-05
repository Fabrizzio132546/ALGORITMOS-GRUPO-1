package ejercicio2;

import actividad1.ExceptionIsEmpty;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class SimuladorTienda extends JFrame {

    private ColaArreglo colaTienda;
    private PanelDibujo panelDibujo;
    private Color colorPastel;
    private JTextField txtDato;
    private int capacidad;

    public SimuladorTienda(int capacidad) {
        this.capacidad = capacidad;
        this.colaTienda = new ColaArreglo(capacidad);
        generarColorPastel();
        configurarVentana();
        inicializarComponentes();
    }

    private void generarColorPastel() {
        Random rand = new Random();
        colorPastel = new Color(rand.nextInt(128) + 128, 
                                rand.nextInt(128) + 128, 
                                rand.nextInt(128) + 128);
    }

    private void configurarVentana() {
        setTitle("Simulador Visual - Cola de Tienda (Capacidad: " + capacidad + ")");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelControles = new JPanel();
        panelControles.setBackground(new Color(245, 245, 245));

        txtDato = new JTextField(5);
        JButton btnEncolar = new JButton("Llegó Cliente (Enqueue)");
        JButton btnDesencolar = new JButton("Atender Cliente (Dequeue)");

        btnEncolar.addActionListener(e -> encolarCliente());
        btnDesencolar.addActionListener(e -> atenderCliente());

        panelControles.add(new JLabel("ID Cliente (Número):"));
        panelControles.add(txtDato);
        panelControles.add(btnEncolar);
        panelControles.add(btnDesencolar);

        panelDibujo = new PanelDibujo();
        panelDibujo.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(panelDibujo);

        add(panelControles, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void encolarCliente() {
        if (colaTienda.isFull()) {
            JOptionPane.showMessageDialog(this, "La cola de la tienda está llena.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(txtDato.getText().trim());
            colaTienda.enqueue(id);
            txtDato.setText("");
            txtDato.requestFocus();
            panelDibujo.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID numérico válido.");
        }
    }

    private void atenderCliente() {
        try {
            int id = colaTienda.dequeue();
            JOptionPane.showMessageDialog(this, "Cliente atendido con ID: " + id, "Atención", JOptionPane.INFORMATION_MESSAGE);
            panelDibujo.repaint();
        } catch (ExceptionIsEmpty ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class PanelDibujo extends JPanel {
        private final int RADIO = 60;
        private final int SEPARACION_X = 80;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            List<Integer> elementos = colaTienda.getElementosComoLista();
            int startX = 50;
            int startY = 100;

            g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
            
            // Dibujar un letrero de inicio de la fila
            g2d.setColor(Color.GRAY);
            g2d.drawString("FRENTE (Caja)", startX, startY - 20);

            for (Integer id : elementos) {
                // Dibujar cliente (círculo)
                g2d.setColor(colorPastel);
                g2d.fillOval(startX, startY, RADIO, RADIO);
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawOval(startX, startY, RADIO, RADIO);

                // Dibujar ID dentro del círculo
                g2d.setColor(Color.BLACK);
                FontMetrics fm = g2d.getFontMetrics();
                String text = String.valueOf(id);
                int textWidth = fm.stringWidth(text);
                g2d.drawString(text, startX + (RADIO - textWidth) / 2, startY + (RADIO + fm.getAscent()) / 2 - 4);

                startX += SEPARACION_X;
            }
            
            // Ajustar tamaño para scroll horizontal si hay muchos clientes
            setPreferredSize(new Dimension(startX + 100, getHeight()));
            revalidate();
        }
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "¿Cuál es la capacidad máxima de la cola de la tienda?", "Configuración inicial", JOptionPane.QUESTION_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) {
            try {
                int cap = Integer.parseInt(input.trim());
                if (cap > 0) {
                    SwingUtilities.invokeLater(() -> new SimuladorTienda(cap).setVisible(true));
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar una capacidad mayor a 0.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número entero válido.");
            }
        }
    }
}
