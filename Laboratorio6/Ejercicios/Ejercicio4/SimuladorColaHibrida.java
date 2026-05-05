package Lab06.ejercicios.ejercicio4;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class SimuladorColaHibrida extends JFrame {

    private PriorityQueueHybrid<String, Integer> sistemaColas;
    private PanelDibujo panelDibujo;
    private int cantidadNiveles;
    private Color[] coloresNiveles;

    private JTextField txtDato;
    private JComboBox<Integer> cbPrioridad; 
    private JTextField txtValorSecundario; 

    public SimuladorColaHibrida(int niveles) {
        this.cantidadNiveles = niveles;
        
        this.sistemaColas = new PriorityQueueHybrid<>(niveles); 

        generarColoresPasteles();
        configurarVentana();
        inicializarComponentes();
    }

    private void generarColoresPasteles() {
        coloresNiveles = new Color[cantidadNiveles];
        Random rand = new Random();
        for (int i = 0; i < cantidadNiveles; i++) {
            coloresNiveles[i] = new Color(rand.nextInt(128) + 128, 
                                          rand.nextInt(128) + 128, 
                                          rand.nextInt(128) + 128);
        }
    }

    private void configurarVentana() {
        setTitle("Simulador Híbrido - " + cantidadNiveles + " Niveles (Ordenamiento Interno)");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelControles = new JPanel();
        panelControles.setBackground(new Color(245, 245, 245));
        
        txtDato = new JTextField(5);
        txtValorSecundario = new JTextField(5);

        Integer[] prioridades = new Integer[cantidadNiveles];
        for (int i = 0; i < cantidadNiveles; i++) prioridades[i] = i;
        cbPrioridad = new JComboBox<>(prioridades);
        
        JButton btnEncolar = new JButton("Encolar");
        JButton btnDesencolar = new JButton("Desencolar");
        
        btnEncolar.addActionListener(e -> encolarDato());
        btnDesencolar.addActionListener(e -> desencolarDato());

        panelControles.add(new JLabel("Dato (Ej. A):"));
        panelControles.add(txtDato);
        panelControles.add(new JLabel("Nivel (0 a N-1):"));
        panelControles.add(cbPrioridad);
        panelControles.add(new JLabel("Valor Secundario (Ej. 5):"));
        panelControles.add(txtValorSecundario);
        panelControles.add(btnEncolar);
        panelControles.add(btnDesencolar);
        
        panelDibujo = new PanelDibujo();
        panelDibujo.setBackground(Color.WHITE);
        panelDibujo.setPreferredSize(new Dimension(850, cantidadNiveles * 90 + 50));
        
        JScrollPane scrollPane = new JScrollPane(panelDibujo);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(panelControles, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void encolarDato() {
        String dato = txtDato.getText().trim(); 
        String strValorSec = txtValorSecundario.getText().trim();

        if (!dato.isEmpty() && !strValorSec.isEmpty()) {
            try {
                int prioridad = (Integer) cbPrioridad.getSelectedItem();
                int valorSec = Integer.parseInt(strValorSec);

                sistemaColas.enqueue(dato, prioridad, valorSec); 
                
                txtDato.setText("");
                txtValorSecundario.setText("");
                txtDato.requestFocus(); 
                panelDibujo.repaint(); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El valor secundario debe ser un número entero.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor llene el dato y el valor secundario.");
        }
    }

    private void desencolarDato() {
        try {
            String dato = sistemaColas.dequeue();
            JOptionPane.showMessageDialog(this, "Elemento extraído: " + dato, "Desencolar", JOptionPane.INFORMATION_MESSAGE);
            panelDibujo.repaint(); 
        } catch (Exception ex) { 
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }              
    }

    private class PanelDibujo extends JPanel {
        private final int RADIO = 60; 
        private final int SEPARACION_X = 80; 
        private final int ALTO_FILA = 90;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int startY = 40;

            for (int p = cantidadNiveles - 1; p >= 0; p--) {
                Color colorClaro = coloresNiveles[p];

                g2d.setColor(colorClaro);
                g2d.fillRect(20, startY + RADIO / 2 - 10, 20, 20);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(20, startY + RADIO / 2 - 10, 20, 20);
                
                g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
                g2d.drawString("Prioridad " + p, 50, startY + RADIO / 2 + 5);

                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawLine(20, startY + RADIO + 15, getWidth(), startY + RADIO + 15);

                List<String> elementos = sistemaColas.obtenerElementosDeCola(p);
                int startX = 160; 

                for (String dato : elementos) {
                    g2d.setColor(colorClaro);
                    g2d.fillOval(startX, startY, RADIO, RADIO);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawOval(startX, startY, RADIO, RADIO);

                    g2d.setColor(Color.BLACK);
                    FontMetrics fm = g2d.getFontMetrics();
                    int textWidth = fm.stringWidth(dato);
                    g2d.drawString(dato, startX + (RADIO - textWidth) / 2, startY + (RADIO + fm.getAscent()) / 2 - 4);

                    int arrowStartX = startX + RADIO;
                    int arrowEndX = arrowStartX + SEPARACION_X - RADIO - 5;
                    int arrowY = startY + RADIO / 2;
                    g2d.setColor(Color.GRAY);
                    g2d.drawLine(arrowStartX, arrowY, arrowEndX, arrowY);
                    g2d.fillPolygon(new int[]{arrowEndX, arrowEndX - 5, arrowEndX - 5}, 
                                    new int[]{arrowY, arrowY - 4, arrowY + 4}, 3);

                    startX += SEPARACION_X;
                }
                startY += ALTO_FILA; 
            }
        }
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Cúantos niveles de prioridad híbrida deseas simular?", "Configuración inicial", JOptionPane.QUESTION_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) { 
            try {
                int niveles = Integer.parseInt(input.trim());
                if (niveles > 0) {
                    SwingUtilities.invokeLater(() -> new SimuladorColaHibrida(niveles).setVisible(true));
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un número mayor a 0");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número entero válido.");
            }
        }
    }
}