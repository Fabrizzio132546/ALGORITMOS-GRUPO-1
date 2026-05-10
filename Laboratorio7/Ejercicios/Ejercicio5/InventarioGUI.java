package Ejercicio5_GUI;

import Actividad4.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioGUI extends JFrame {

	private LinkedBST<Producto> inventario;
    private JTextField txtCodigoInsertar, txtMin, txtMax, txtNombreInsertar, txtCantidadInsertar;
    private JTextArea txtConsola;

    // Colores
    private final Color COLOR_FONDO_LATERAL = new Color(236, 240, 241);
    private final Color COLOR_PRIMARIO = new Color(44, 62, 80);
    private final Color COLOR_EXITO = new Color(39, 174, 96);
    private final Color COLOR_INFO = new Color(41, 128, 185);
    private final Color COLOR_LIMPIAR = new Color(192, 57, 43);

    public InventarioGUI() {
        inventario = new LinkedBST<>();
        setTitle("Dashboard - Gestión de Inventario BST");
        setSize(850, 600); // Un poco más ancho para mejor visualización
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // --- PANEL DE CABECERA (Header) ---
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_PRIMARIO);
        panelHeader.setPreferredSize(new Dimension(0, 60));
        JLabel lblTitulo = new JLabel("SISTEMA DE CONTROL DE INVENTARIO");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        // --- PANEL IZQUIERDO: CONTROLES ---
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
        panelControles.setBackground(COLOR_FONDO_LATERAL);
        panelControles.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelControles.setPreferredSize(new Dimension(300, 0));

        // Estilo de Bordes con Título
        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 12);

        // 1. Sección: Insertar
        JPanel panelInsertar = new JPanel(new GridLayout(7, 1, 2, 2));
        panelInsertar.setBackground(COLOR_FONDO_LATERAL);
        panelInsertar.setBorder(BorderFactory.createTitledBorder(
        	    BorderFactory.createLineBorder(COLOR_PRIMARIO, 1), // Línea del borde
        	    "REGISTRO DE PRODUCTO", 
        	    TitledBorder.LEFT, 
        	    TitledBorder.TOP, 
        	    new Font("Segoe UI", Font.BOLD, 13), 
        	    COLOR_PRIMARIO // Color del texto del título
        	));

        txtCodigoInsertar = new JTextField();
        txtNombreInsertar = new JTextField();
        txtCantidadInsertar = new JTextField();
        JButton btnInsertar = crearBotonPersonalizado("Insertar Producto", COLOR_EXITO);

        panelInsertar.add(new JLabel("Código:")); panelInsertar.add(txtCodigoInsertar);
        panelInsertar.add(new JLabel("Nombre:")); panelInsertar.add(txtNombreInsertar);
        panelInsertar.add(new JLabel("Cantidad:")); panelInsertar.add(txtCantidadInsertar);
        panelInsertar.add(btnInsertar);

        // 2. Sección: Rango
        JPanel panelRango = new JPanel(new GridLayout(3, 2, 5, 5));
        panelRango.setBackground(COLOR_FONDO_LATERAL);
        panelRango.setBorder(BorderFactory.createTitledBorder(null, "FILTRAR POR RANGO", TitledBorder.LEFT, TitledBorder.TOP, fuenteTitulo, COLOR_PRIMARIO));
        
        txtMin = new JTextField(); txtMax = new JTextField();
        JButton btnBuscarRango = crearBotonPersonalizado("Buscar", COLOR_INFO);
        
        panelRango.add(new JLabel("Min:")); panelRango.add(txtMin);
        panelRango.add(new JLabel("Max:")); panelRango.add(txtMax);
        panelRango.add(new JLabel("")); panelRango.add(btnBuscarRango);

        // 3. Sección: Consultas
        JPanel panelOperaciones = new JPanel(new GridLayout(3, 1, 8, 8));
        panelOperaciones.setBackground(COLOR_FONDO_LATERAL);
        panelOperaciones.setBorder(BorderFactory.createTitledBorder(null, "MANTENIMIENTO", TitledBorder.LEFT, TitledBorder.TOP, fuenteTitulo, COLOR_PRIMARIO));
        
        JButton btnContarHojas = crearBotonPersonalizado("Contar Hojas", COLOR_PRIMARIO);
        JButton btnDescendente = crearBotonPersonalizado("Listado Descendente", COLOR_PRIMARIO);
        JButton btnLimpiarLog = crearBotonPersonalizado("Limpiar Consola", COLOR_LIMPIAR);

        panelOperaciones.add(btnContarHojas);
        panelOperaciones.add(btnDescendente);
        panelOperaciones.add(btnLimpiarLog);

        panelControles.add(panelInsertar);
        panelControles.add(Box.createRigidArea(new Dimension(0, 10)));
        panelControles.add(panelRango);
        panelControles.add(Box.createRigidArea(new Dimension(0, 10)));
        panelControles.add(panelOperaciones);

        // --- PANEL CENTRAL: RESULTADOS ---
        txtConsola = new JTextArea();
        txtConsola.setEditable(false);
        txtConsola.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtConsola.setBackground(Color.WHITE);
        txtConsola.setForeground(new Color(33, 33, 33));
        JScrollPane scrollConsola = new JScrollPane(txtConsola);
        scrollConsola.setBorder(BorderFactory.createTitledBorder(null, "LOG DE ACTIVIDAD", TitledBorder.LEFT, TitledBorder.TOP, fuenteTitulo, COLOR_PRIMARIO));

        add(panelControles, BorderLayout.WEST);
        add(scrollConsola, BorderLayout.CENTER);

        // --- EVENTOS ---
        btnInsertar.addActionListener(e -> insertarProducto());
        btnBuscarRango.addActionListener(e -> buscarRango());
        btnContarHojas.addActionListener(e -> contarHojas());
        btnDescendente.addActionListener(e -> mostrarDescendente());
        btnLimpiarLog.addActionListener(e -> txtConsola.setText(""));
    }

    // Método para evitar repetir código de diseño de botones
    private JButton crearBotonPersonalizado(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        
        // --- ESTA ES LA CLAVE PARA EL COLOR SÓLIDO ---
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE); // Letras blancas
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);    // Quita el borde gris de Windows
        btn.setContentAreaFilled(true); // Fuerza a que el color llene el botón
        btn.setOpaque(true);            // Asegura que sea opaco
        
        // Fuente más gruesa y visible
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto visual: Cambia un poco el color cuando pasas el ratón
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorFondo.brighter()); // Se aclara al pasar el mouse
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorFondo); // Vuelve al original
            }
        });

        return btn;
    }

    // --- MÉTODOS LÓGICOS DE LOS BOTONES ---

    private void insertarProducto() {
        try {
            int codigo = Integer.parseInt(txtCodigoInsertar.getText().trim());
            String nombre = txtNombreInsertar.getText().trim();
            int cantidad = Integer.parseInt(txtCantidadInsertar.getText().trim()); // Capturar cantidad
            
            if (nombre.isEmpty()) {
                mostrarError("El nombre no puede estar vacío.");
                return;
            }

            // Creamos el producto con los 3 datos
            Producto nuevo = new Producto(codigo, nombre, cantidad);
            inventario.insert(nuevo);
            
            log("✅ Producto registrado: " + nuevo);
            
            // Limpiar campos y foco
            txtCodigoInsertar.setText("");
            txtNombreInsertar.setText("");
            txtCantidadInsertar.setText("");
            txtCodigoInsertar.requestFocus();
            
        } catch (NumberFormatException ex) {
            mostrarError("Código y Cantidad deben ser números enteros.");
        } catch (ItemDuplicated ex) {
            mostrarError("El código ya existe.");
        }
    }

    private void buscarRango() {
        try {
            int minCod = Integer.parseInt(txtMin.getText().trim());
            int maxCod = Integer.parseInt(txtMax.getText().trim());
            
            Producto pMin = new Producto(minCod, "", 0);
            Producto pMax = new Producto(maxCod, "", 0);
            
            String resultado = inventario.searchRange(pMin, pMax);
            
            if (resultado.isEmpty()) {
                log("🔍 Rango [" + minCod + " - " + maxCod + "]: No hay productos.");
            } else {
                // El título termina en ":" y "resultado" empieza con "\n"
                log("🔍 Rango [" + minCod + " - " + maxCod + "]:" + resultado);
            }
            
        } catch (Exception ex) {
            mostrarError("Error en los valores del rango.");
        }
    }

    private void contarHojas() {
        int hojas = inventario.countLeaves();
        log("🍃 Total de productos en nodos hoja: " + hojas);
    }

    private void mostrarDescendente() {
        String resultado = inventario.getDescendingString();
        if (resultado.isEmpty()) {
            log("📉 El inventario está vacío.");
        } else {
            // Lo mismo aquí: el título y luego el bloque de texto
            log("📉 Productos (Descendente):" + resultado);
        }
    }

    // Métodos de ayuda visual
    private void log(String mensaje) {
        txtConsola.append(mensaje + "\n"); // El mensaje ya trae sus propios saltos de línea internos
        txtConsola.setCaretPosition(txtConsola.getDocument().getLength()); 
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
    }

    // --- MÉTODO MAIN PARA EJECUTAR LA INTERFAZ ---
    public static void main(String[] args) {
        // Aspecto visual moderno (Look and Feel del sistema operativo)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InventarioGUI().setVisible(true);
            }
        });
    }
}