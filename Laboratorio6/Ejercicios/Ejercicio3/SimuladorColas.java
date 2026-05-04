package Ejerccio3demo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class SimuladorColas extends JFrame {

    private MultiColaPrioridad<String> sistemaColas;
    private PanelDibujo panelDibujo;
    private int cantidadNiveles;
    private Color[] coloresNiveles;

    private JTextField txtDato;
    private JComboBox<Integer> cbPrioridad; // lista de opciones de prioridad

    public SimuladorColas(int niveles) {
        this.cantidadNiveles = niveles;
        
        this.sistemaColas = new MultiColaPrioridad<>(niveles); 

        generarColoresPasteles();
        configurarVentana();
        inicializarComponentes();
    }

    private void generarColoresPasteles() {
        coloresNiveles = new Color[cantidadNiveles];
        // se crea una instancia de la clase random
        Random rand = new Random();
        for (int i = 0; i < cantidadNiveles; i++) {
        	                              // retorna un int aleatorio del 0 a 128
            coloresNiveles[i] = new Color(rand.nextInt(128) + 128, // ponemos al rango 128 - 255
                                          rand.nextInt(128) + 128, 
                                          rand.nextInt(128) + 128);
                        // de esta forma se garantiza el color sea mas claro ya q esta mas cercano a 255
                        // esto da un tono de color pastel
        }
    }

    private void configurarVentana() {
    	// texto d ela barra de titulo
        setTitle("Simulador Visual - " + cantidadNiveles + " Niveles de Prioridad");
        // dimension de la ventana
        setSize(800, 500);
        // hace que la aplicacion se cierre por completo y libere la memoria al presionar la X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // establece el esquema de organizacion , norte, sur, este, oeste y centro
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
    	// instancia un contenedor para agrupar los botones y textos
        JPanel panelControles = new JPanel();
        // coloca el color para el fondo
        panelControles.setBackground(new Color(245, 245, 245));
        // 
        txtDato = new JTextField(5);
        // crea un arreglo de objetos integer que servira para llenar la lista desplegable
        Integer[] prioridades = new Integer[cantidadNiveles];
        for (int i = 0; i < cantidadNiveles; i++) prioridades[i] = i;
        // se crea apartir del arreglo de enteros el combox
        cbPrioridad = new JComboBox<>(prioridades);
        
        JButton btnEncolar = new JButton("Encolar");
        JButton btnDesencolar = new JButton("Desencolar");
        // usa expresion lambda, cuando haga click ejecuta el metodo de conlar
        btnEncolar.addActionListener(e -> encolarDato());
        btnDesencolar.addActionListener(e -> desencolarDato());

        panelControles.add(new JLabel("Dato:"));
        panelControles.add(txtDato);
        panelControles.add(new JLabel("Prioridad:"));
        panelControles.add(cbPrioridad);
        panelControles.add(btnEncolar);
        panelControles.add(btnDesencolar);
        // crea instancia de la clase personalizda paneldibujo
        panelDibujo = new PanelDibujo();
        panelDibujo.setBackground(Color.WHITE);
        panelDibujo.setPreferredSize(new Dimension(750, cantidadNiveles * 90 + 50));
        // añade barra de desplazamineto si es mas grande 
        JScrollPane scrollPane = new JScrollPane(panelDibujo);
        // ajusta la sensibilidad de la rueda del raton 
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        // coloca los botones y el cuadro de texto en la parte norte
        add(panelControles, BorderLayout.NORTH);
        // coloca todo lo demas en el centro 
        add(scrollPane, BorderLayout.CENTER);
    }

    private void encolarDato() {
        String dato = txtDato.getText().trim(); // trim elimina espacio en blanco 
        if (!dato.isEmpty()) {
            int prioridad = (Integer) cbPrioridad.getSelectedItem();
            // inserta el dato en la cola correspondiente
            sistemaColas.enqueue(dato, prioridad); 
            // limpiean el campo de texto
            txtDato.setText("");
            txtDato.requestFocus(); // permite escribir sl siguiente dato sin usar el raton
            panelDibujo.repaint(); // permite actualizar los circulas para que se vea el nuevo elemento
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un dato válido.");
        }
    }

    private void desencolarDato() {
        try {
            String dato = sistemaColas.dequeue();// retorna string y el dato que estaba al inicio de la cola de mayor prioridad
            JOptionPane.showMessageDialog(this, "Elemento extraído: " + dato, "Desencolar", JOptionPane.INFORMATION_MESSAGE);
            panelDibujo.repaint(); // redibuja de igual forma la interfaz
        } catch (ExceptionIsEmpty ex) { 
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }              
    }

    private class PanelDibujo extends JPanel {
        private final int RADIO = 50; // daimetro de lo spixeles del circulo
        private final int SEPARACION_X = 70; // espacio entre circulos
        private final int ALTO_FILA = 90;// espacio vertical reservado para cada nivel de prioridad

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

                    startX += SEPARACION_X;
                }
                startY += ALTO_FILA; 
            }
        }
    }

    public static void main(String[] args) {
    	// lanza una ventana preguntando cuantos nuveles tendra 
        String input = JOptionPane.showInputDialog(null, "Cuantos niveles de prioridad deseas simular?", "Configuracion inicial", JOptionPane.QUESTION_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) { // verifica que el usuario no haya apretado cancelar
        	// verifica que el campo no este vacio
            try {
            	// convierte el texto ingresado en un entero
                int niveles = Integer.parseInt(input.trim());
                // 
                if (niveles > 0) {
                	// crea el objeto de la ventana y lo hace visible en la pantalla
                    SwingUtilities.invokeLater(() -> new SimuladorColas(niveles).setVisible(true));
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un numero mayor a 0");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, " ingrese un número entero valido.");
            }
        }
    }
}

