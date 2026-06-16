import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ejercicio2GUI extends JFrame {

    private JComboBox<String> comboOrigen;
    private JComboBox<String> comboDestino;
    private JLabel lblResultado;
    private GraphPanel graphPanel;
    private RedCiudades logicaRed;

    public Ejercicio2GUI() {
        super("Red Nacional de Carreteras - 24 Departamentos del Perú");
        
        logicaRed = new RedCiudades();
        inicializarDatos();

        // Ventana más grande para que quepan todos los departamentos
        setSize(950, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- PANEL SUPERIOR: CONTROLES ---
        JPanel panelControles = new JPanel();
        panelControles.setBackground(new Color(236, 240, 241));
        panelControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Los 24 departamentos ordenados alfabéticamente
        String[] ciudades = {
            "Amazonas", "Ancash", "Apurímac", "Arequipa", "Ayacucho", "Cajamarca", 
            "Cusco", "Huancavelica", "Huánuco", "Ica", "Junín", "La Libertad", 
            "Lambayeque", "Lima", "Loreto", "Madre de Dios", "Moquegua", "Pasco", 
            "Piura", "Puno", "San Martín", "Tacna", "Tumbes", "Ucayali"
        };
        
        comboOrigen = new JComboBox<>(ciudades);
        comboDestino = new JComboBox<>(ciudades);
        comboOrigen.setSelectedItem("Lima");
        comboDestino.setSelectedItem("Tacna"); 

        JButton btnCalcular = new JButton("Calcular Ruta Óptima");
        btnCalcular.setBackground(new Color(41, 128, 185));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 12));

        lblResultado = new JLabel("Seleccione origen y destino.");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 13));
        lblResultado.setForeground(new Color(192, 57, 43));

        panelControles.add(new JLabel("Origen:"));
        panelControles.add(comboOrigen);
        panelControles.add(new JLabel("Destino:"));
        panelControles.add(comboDestino);
        panelControles.add(btnCalcular);

        // --- PANEL CENTRAL: EL GRAFO ---
        graphPanel = new GraphPanel(logicaRed.getGrafo());

        // --- PANEL INFERIOR: RESULTADOS ---
        JPanel panelResultado = new JPanel();
        panelResultado.add(lblResultado);

        add(panelControles, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        add(panelResultado, BorderLayout.SOUTH);

        // --- EVENTO DEL BOTÓN ---
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String origen = (String) comboOrigen.getSelectedItem();
                String destino = (String) comboDestino.getSelectedItem();
                
                if(origen.equals(destino)){
                    lblResultado.setText("El origen y el destino son el mismo.");
                    graphPanel.setCaminoOptimo(null);
                    return;
                }

                GraphPath<String, DefaultWeightedEdge> camino = logicaRed.calcularCaminoMasCorto(origen, destino);
                
                if (camino != null) {
                    List<String> ruta = camino.getVertexList();
                    String textoRuta = String.join(" -> ", ruta);
                    lblResultado.setText("Ruta: " + textoRuta + " | Distancia: " + camino.getWeight() + " km");
                    graphPanel.setCaminoOptimo(camino);
                } else {
                    lblResultado.setText("No hay ruta disponible entre " + origen + " y " + destino);
                    graphPanel.setCaminoOptimo(null);
                }
            }
        });
    }

    private void inicializarDatos() {
        // 1. Agregar los 24 Departamentos
        String[] departamentos = {
            "Amazonas", "Ancash", "Apurímac", "Arequipa", "Ayacucho", "Cajamarca", 
            "Cusco", "Huancavelica", "Huánuco", "Ica", "Junín", "La Libertad", 
            "Lambayeque", "Lima", "Loreto", "Madre de Dios", "Moquegua", "Pasco", 
            "Piura", "Puno", "San Martín", "Tacna", "Tumbes", "Ucayali"
        };
        for (String dep : departamentos) {
            logicaRed.agregarCiudad(dep);
        }

        // 2. Conexiones (Carreteras Principales Aproximadas en KM)
        // Costa Norte
        logicaRed.agregarCarretera("Tumbes", "Piura", 280);
        logicaRed.agregarCarretera("Piura", "Lambayeque", 210);
        logicaRed.agregarCarretera("Lambayeque", "La Libertad", 210);
        logicaRed.agregarCarretera("La Libertad", "Ancash", 200);
        logicaRed.agregarCarretera("Ancash", "Lima", 400);
        
        // Costa Sur
        logicaRed.agregarCarretera("Lima", "Ica", 300);
        logicaRed.agregarCarretera("Ica", "Arequipa", 710);
        logicaRed.agregarCarretera("Arequipa", "Moquegua", 230);
        logicaRed.agregarCarretera("Moquegua", "Tacna", 160);

        // Sierra Norte y Centro
        logicaRed.agregarCarretera("Piura", "Cajamarca", 400);
        logicaRed.agregarCarretera("Lambayeque", "Cajamarca", 260);
        logicaRed.agregarCarretera("La Libertad", "San Martín", 450);
        logicaRed.agregarCarretera("Ancash", "Huánuco", 350);
        logicaRed.agregarCarretera("Lima", "Junín", 300);
        logicaRed.agregarCarretera("Junín", "Pasco", 150);
        logicaRed.agregarCarretera("Pasco", "Huánuco", 120);

        // Sierra Sur
        logicaRed.agregarCarretera("Junín", "Huancavelica", 150);
        logicaRed.agregarCarretera("Ica", "Huancavelica", 300);
        logicaRed.agregarCarretera("Ica", "Ayacucho", 400);
        logicaRed.agregarCarretera("Huancavelica", "Ayacucho", 250);
        logicaRed.agregarCarretera("Ayacucho", "Apurímac", 300);
        logicaRed.agregarCarretera("Apurímac", "Cusco", 300);
        logicaRed.agregarCarretera("Apurímac", "Arequipa", 450);
        logicaRed.agregarCarretera("Arequipa", "Cusco", 510);
        logicaRed.agregarCarretera("Cusco", "Puno", 390);
        logicaRed.agregarCarretera("Arequipa", "Puno", 300);
        logicaRed.agregarCarretera("Moquegua", "Puno", 260);
        logicaRed.agregarCarretera("Puno", "Tacna", 420);

        // Selva
        logicaRed.agregarCarretera("Cajamarca", "Amazonas", 300);
        logicaRed.agregarCarretera("Amazonas", "San Martín", 350);
        logicaRed.agregarCarretera("Amazonas", "Loreto", 700);
        logicaRed.agregarCarretera("San Martín", "Loreto", 600);
        logicaRed.agregarCarretera("San Martín", "Huánuco", 500);
        logicaRed.agregarCarretera("Huánuco", "Ucayali", 400);
        logicaRed.agregarCarretera("Pasco", "Ucayali", 450);
        logicaRed.agregarCarretera("Junín", "Ucayali", 500);
        logicaRed.agregarCarretera("Ucayali", "Loreto", 800);
        logicaRed.agregarCarretera("Ucayali", "Madre de Dios", 900);
        logicaRed.agregarCarretera("Cusco", "Madre de Dios", 500);
        logicaRed.agregarCarretera("Puno", "Madre de Dios", 450);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ejercicio2GUI().setVisible(true);
        });
    }
}

// =================================================================
// CLASE PARA DIBUJAR EL GRAFO (El JPanel personalizado)
// =================================================================
class GraphPanel extends JPanel {
    private Graph<String, DefaultWeightedEdge> grafo;
    private Map<String, Point> coordenadas;
    private GraphPath<String, DefaultWeightedEdge> caminoOptimo;

    public GraphPanel(Graph<String, DefaultWeightedEdge> grafo) {
        this.grafo = grafo;
        setBackground(Color.WHITE);
        
        // Mapeo aproximado a la silueta geográfica del Perú
        coordenadas = new HashMap<>();
        coordenadas.put("Tumbes", new Point(100, 50));
        coordenadas.put("Piura", new Point(120, 120));
        coordenadas.put("Lambayeque", new Point(150, 190));
        coordenadas.put("La Libertad", new Point(200, 250));
        coordenadas.put("Ancash", new Point(250, 340));
        coordenadas.put("Lima", new Point(300, 450));
        coordenadas.put("Ica", new Point(350, 550));
        coordenadas.put("Arequipa", new Point(480, 680));
        coordenadas.put("Moquegua", new Point(540, 730));
        coordenadas.put("Tacna", new Point(590, 780));
        
        coordenadas.put("Cajamarca", new Point(230, 160));
        coordenadas.put("Amazonas", new Point(280, 100));
        coordenadas.put("San Martín", new Point(350, 180));
        coordenadas.put("Huánuco", new Point(380, 300));
        coordenadas.put("Pasco", new Point(390, 380));
        coordenadas.put("Junín", new Point(420, 440));
        coordenadas.put("Huancavelica", new Point(440, 500));
        coordenadas.put("Ayacucho", new Point(480, 540));
        coordenadas.put("Apurímac", new Point(550, 560));
        coordenadas.put("Cusco", new Point(620, 540));
        coordenadas.put("Puno", new Point(720, 650));
        
        coordenadas.put("Loreto", new Point(550, 100));
        coordenadas.put("Ucayali", new Point(550, 300));
        coordenadas.put("Madre de Dios", new Point(750, 420));
    }

    public void setCaminoOptimo(GraphPath<String, DefaultWeightedEdge> camino) {
        this.caminoOptimo = camino;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Dibujar todas las carreteras
        for (DefaultWeightedEdge edge : grafo.edgeSet()) {
            String origen = grafo.getEdgeSource(edge);
            String destino = grafo.getEdgeTarget(edge);
            Point p1 = coordenadas.get(origen);
            Point p2 = coordenadas.get(destino);

            boolean esRutaOptima = (caminoOptimo != null && caminoOptimo.getEdgeList().contains(edge));

            if (esRutaOptima) {
                g2.setColor(new Color(231, 76, 60)); // Rojo intenso
                g2.setStroke(new BasicStroke(3.5f)); 
            } else {
                g2.setColor(new Color(220, 220, 220)); // Gris muy claro para no saturar
                g2.setStroke(new BasicStroke(1.5f));
            }

            g2.drawLine(p1.x, p1.y, p2.x, p2.y);

            // Solo mostrar los pesos si es parte de la ruta o si es un color discreto
            int midX = (p1.x + p2.x) / 2;
            int midY = (p1.y + p2.y) / 2;
            
            if (esRutaOptima) {
                g2.setColor(new Color(231, 76, 60));
                g2.setFont(new Font("Arial", Font.BOLD, 12));
            } else {
                g2.setColor(Color.GRAY);
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
            }
            g2.drawString(String.valueOf((int)grafo.getEdgeWeight(edge)), midX - 5, midY);
        }

        // 2. Dibujar las ciudades (Nodos ajustados en tamaño)
        int radio = 12; // Más pequeños para evitar saturación
        for (String ciudad : grafo.vertexSet()) {
            Point p = coordenadas.get(ciudad);
            
            if (caminoOptimo != null && caminoOptimo.getVertexList().contains(ciudad)) {
                g2.setColor(new Color(46, 204, 113)); // Verde
            } else {
                g2.setColor(new Color(52, 152, 219)); // Azul
            }
            
            g2.fillOval(p.x - radio, p.y - radio, radio * 2, radio * 2);
            
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawOval(p.x - radio, p.y - radio, radio * 2, radio * 2);

            // Texto de los Departamentos
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 11));
            // Offset dinámico para que no tape los nodos
            g2.drawString(ciudad, p.x + 14, p.y + 4);
        }
    }
}

// =================================================================
// CLASE LÓGICA (RedCiudades)
// =================================================================
class RedCiudades {

    private Graph<String, DefaultWeightedEdge> grafo;

    public RedCiudades() {
        this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public Graph<String, DefaultWeightedEdge> getGrafo() {
        return this.grafo;
    }

    public void agregarCiudad(String ciudad) {
        grafo.addVertex(ciudad);
    }

    public void agregarCarretera(String origen, String destino, double distancia) {
        DefaultWeightedEdge carretera = grafo.addEdge(origen, destino);
        if (carretera != null) {
            grafo.setEdgeWeight(carretera, distancia);
        }
    }

    public GraphPath<String, DefaultWeightedEdge> calcularCaminoMasCorto(String origen, String destino) {
        if (!grafo.containsVertex(origen) || !grafo.containsVertex(destino)) {
            return null;
        }
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);
        return dijkstra.getPath(origen, destino);
    }
}
