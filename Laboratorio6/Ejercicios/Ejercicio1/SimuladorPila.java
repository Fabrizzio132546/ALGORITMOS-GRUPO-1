package actividadPilaLista;

import actividad1.ExceptionIsEmpty;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class SimuladorPila extends JFrame {

    private StackLink<String> pilaTextos;
    private PanelDibujo panelDibujo;
    private Color colorPastel;
    private JTextField txtDato;

    public SimuladorPila() {
        this.pilaTextos = new StackLink<>();
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
        setTitle("Simulador Visual - Pila (LIFO)");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelControles = new JPanel();
        panelControles.setBackground(new Color(245, 245, 245));

        txtDato = new JTextField(10);
        JButton btnApilar = new JButton("Push (Apilar)");
        JButton btnDesapilar = new JButton("Pop (Desapilar)");

        btnApilar.addActionListener(e -> apilarDato());
        btnDesapilar.addActionListener(e -> desapilarDato());

        panelControles.add(new JLabel("Dato:"));
        panelControles.add(txtDato);
        panelControles.add(btnApilar);
        panelControles.add(btnDesapilar);

        panelDibujo = new PanelDibujo();
        panelDibujo.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(panelDibujo);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(panelControles, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void apilarDato() {
        String dato = txtDato.getText().trim();
        if (!dato.isEmpty()) {
            pilaTextos.push(dato);
            txtDato.setText("");
            txtDato.requestFocus();
            panelDibujo.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un dato válido.");
        }
    }

    private void desapilarDato() {
        try {
            String dato = pilaTextos.pop();
            JOptionPane.showMessageDialog(this, "Elemento extraído: " + dato, "Pop", JOptionPane.INFORMATION_MESSAGE);
            panelDibujo.repaint();
        } catch (ExceptionIsEmpty ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class PanelDibujo extends JPanel {
        private final int ANCHO_RECT = 120;
        private final int ALTO_RECT = 40;
        private final int SEPARACION_Y = 50;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            List<String> elementos = pilaTextos.getElementosComoLista();
            int startX = (getWidth() - ANCHO_RECT) / 2; // Centrado
            int startY = 50;

            g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
            g2d.setColor(Color.GRAY);
            g2d.drawString("TOPE DE LA PILA", startX, startY - 15);

            for (String dato : elementos) {
                // Dibujar el rectángulo del elemento
                g2d.setColor(colorPastel);
                g2d.fillRect(startX, startY, ANCHO_RECT, ALTO_RECT);
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(startX, startY, ANCHO_RECT, ALTO_RECT);

                // Dibujar el texto centrado
                g2d.setColor(Color.BLACK);
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(dato);
                g2d.drawString(dato, startX + (ANCHO_RECT - textWidth) / 2, startY + (ALTO_RECT + fm.getAscent()) / 2 - 4);

                startY += SEPARACION_Y;
            }
            
            // Ajustar el tamaño del panel para el Scroll
            setPreferredSize(new Dimension(getWidth(), startY + 50));
            revalidate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimuladorPila().setVisible(true));
    }
}
