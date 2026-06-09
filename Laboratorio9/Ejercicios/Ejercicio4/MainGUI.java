package lab09.ejercicios.ejercicio4;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainGUI extends JFrame {
    private Biblioteca biblioteca;
    
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscarIsbn;
    private JTextField txtIsbn, txtTitulo, txtAutor, txtAnio;

    public MainGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo cargar el tema del sistema.");
        }

        biblioteca = new Biblioteca();
        
        setTitle("🏛️ Sistema de Gestión Bibliotecaria - FAGUSA");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- ENCABEZADO ---
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(41, 128, 185)); // Azul elegante
        pnlHeader.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        JLabel lblTituloGeneral = new JLabel("Biblioteca Digital Central");
        lblTituloGeneral.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTituloGeneral.setForeground(Color.WHITE);
        pnlHeader.add(lblTituloGeneral);
        add(pnlHeader, BorderLayout.NORTH);

        // --- SISTEMA DE PESTAÑAS ---
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        pestanas.addTab("📖 Catálogo Público", crearPanelCatalogo());
        pestanas.addTab("⚙️ Administración", crearPanelGestion());
        
        add(pestanas, BorderLayout.CENTER);

        // --- CARGA AUTOMÁTICA AL INICIAR ---
        String cargaInicial = biblioteca.cargarDesdeArchivo("libros.txt");
        if (!cargaInicial.contains("Error al cargar")) {
            actualizarTabla(); // Llena la tabla si se cargó con éxito
        } else {
            JOptionPane.showMessageDialog(this, 
                "No se encontró 'libros.txt'. La base de datos inicia vacía.", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ==========================================
    // PESTAÑA 1: CATÁLOGO Y BÚSQUEDA
    // ==========================================
    private JPanel crearPanelCatalogo() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Barra superior de búsqueda
        JPanel pnlBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBusqueda.add(new JLabel("Buscar por ISBN:"));
        txtBuscarIsbn = new JTextField(15);
        txtBuscarIsbn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JButton btnBuscar = new JButton("🔍 Buscar");
        JButton btnMostrarTodos = new JButton("📚 Mostrar Todo");
        JButton btnEstadisticas = new JButton("📊 Estadísticas del Árbol");
        
        pnlBusqueda.add(txtBuscarIsbn);
        pnlBusqueda.add(btnBuscar);
        pnlBusqueda.add(Box.createHorizontalStrut(20)); // Espaciador
        pnlBusqueda.add(btnMostrarTodos);
        pnlBusqueda.add(btnEstadisticas);

        panel.add(pnlBusqueda, BorderLayout.NORTH);

        // Tabla de resultados
        String[] columnas = {"ISBN", "Título del Libro", "Autor", "Año"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que editen la tabla haciendo doble clic
            }
        };
        
        tablaLibros = new JTable(modeloTabla);
        tablaLibros.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaLibros.setRowHeight(25);
        tablaLibros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaLibros.getTableHeader().setBackground(new Color(236, 240, 241));
        
        // Centrar columnas específicas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaLibros.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ISBN
        tablaLibros.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Año

        panel.add(new JScrollPane(tablaLibros), BorderLayout.CENTER);

        // Eventos
        btnBuscar.addActionListener(e -> buscarEnCatalogo());
        btnMostrarTodos.addActionListener(e -> actualizarTabla());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticas());

        return panel;
    }

    // ==========================================
    // PESTAÑA 2: GESTIÓN INTERNA (CRUD)
    // ==========================================
    private JPanel crearPanelGestion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel pnlFormulario = new JPanel(new GridLayout(5, 2, 10, 15));
        pnlFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Registro y Eliminación de Ejemplares", 
                0, 0, new Font("Segoe UI", Font.BOLD, 14)));

        Font formFont = new Font("Segoe UI", Font.PLAIN, 14);

        pnlFormulario.add(new JLabel("  Identificador (ISBN):"));
        txtIsbn = new JTextField(); txtIsbn.setFont(formFont);
        pnlFormulario.add(txtIsbn);

        pnlFormulario.add(new JLabel("  Título de la Obra:"));
        txtTitulo = new JTextField(); txtTitulo.setFont(formFont);
        pnlFormulario.add(txtTitulo);

        pnlFormulario.add(new JLabel("  Autor / Escritor:"));
        txtAutor = new JTextField(); txtAutor.setFont(formFont);
        pnlFormulario.add(txtAutor);

        pnlFormulario.add(new JLabel("  Año de Publicación:"));
        txtAnio = new JTextField(); txtAnio.setFont(formFont);
        pnlFormulario.add(txtAnio);

        panel.add(pnlFormulario, BorderLayout.NORTH);

        // Botones de acción
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnAgregar = new JButton("➕ Ingresar Nuevo Libro");
        JButton btnEliminar = new JButton("🗑️ Eliminar por ISBN");
        JButton btnRecargarArchivo = new JButton("🔄 Recargar libros.txt");

        btnAgregar.setBackground(new Color(46, 204, 113));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        pnlBotones.add(btnAgregar);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnRecargarArchivo);

        panel.add(pnlBotones, BorderLayout.CENTER);

        // Eventos
        btnAgregar.addActionListener(e -> agregarLibro());
        btnEliminar.addActionListener(e -> eliminarLibro());
        btnRecargarArchivo.addActionListener(e -> {
            biblioteca.cargarDesdeArchivo("libros.txt");
            actualizarTabla();
            JOptionPane.showMessageDialog(this, "Base de datos sincronizada con archivo físico.");
        });

        return panel;
    }

    // ==========================================
    // MÉTODOS DE APOYO Y LÓGICA DE EVENTOS
    // ==========================================

    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpiar tabla actual
        List<Libro> libros = biblioteca.obtenerTodosOrdenados();
        if (libros != null) {
            for (Libro l : libros) {
                modeloTabla.addRow(new Object[]{l.getIsbn(), l.getTitulo(), l.getAutor(), l.getAnio()});
            }
        }
    }

    private void buscarEnCatalogo() {
        String isbn = txtBuscarIsbn.getText().trim();
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ISBN.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String ruta = biblioteca.buscarLibroPorISBN(isbn);
        
        JTextArea areaRuta = new JTextArea(ruta);
        areaRuta.setEditable(false);
        areaRuta.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaRuta.setBackground(new Color(245, 245, 245));
        
        JOptionPane.showMessageDialog(this, new JScrollPane(areaRuta), 
                "Resultado de Búsqueda y Ruta en Árbol B", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarEstadisticas() {
        int altura = biblioteca.obtenerAltura();
        int cantidad = biblioteca.obtenerCantidadLibros();
        
        String msj = String.format("<html><body><h3>Información Estructural del Árbol B</h3>"
                + "<ul><li><b>Total de libros indexados:</b> %d</li>"
                + "<li><b>Altura actual del árbol:</b> %d niveles</li>"
                + "</ul></body></html>", cantidad, altura);
                
        JOptionPane.showMessageDialog(this, msj, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarLibro() {
        try {
            String isbn = txtIsbn.getText().trim();
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());
            
            if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (biblioteca.agregarLibro(isbn, titulo, autor, anio)) {
                actualizarTabla(); // Reflejar en la vista del catálogo
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, "Libro registrado exitosamente en el sistema.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El ISBN ya se encuentra registrado.", "Conflicto", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLibro() {
        String isbn = txtIsbn.getText().trim();
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ISBN del libro a eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar el ejemplar con ISBN " + isbn + "?", "Confirmar baja", JOptionPane.YES_NO_OPTION);
            
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (biblioteca.eliminarLibro(isbn)) {
                actualizarTabla();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, "Libro dado de baja correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún libro con ese ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarFormulario() {
        txtIsbn.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAnio.setText("");
        txtIsbn.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}