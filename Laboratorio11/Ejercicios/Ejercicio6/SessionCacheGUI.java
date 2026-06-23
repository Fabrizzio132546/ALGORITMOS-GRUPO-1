package lab11.ejercicios.ejercicio6;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.UUID;

public class SessionCacheGUI extends JFrame {

    private SessionCache cache;
    private JTextArea consoleArea;
    private JTextField txtUsername;
    private JComboBox<String> cbRole;
    private JSpinner spnTtl;
    private JTextField txtTokenAction;

    public SessionCacheGUI() {
        cache = new SessionCache(11);

        setTitle("Dashboard de Monitoreo - Caché de Sesiones");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initUI();
        redirectSystemStreams();
        
        System.out.println("=== Sistema de Caché Iniciado ===");
        System.out.println("Esperando acciones...\n");
    }

    private void initUI() {
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
        panelControles.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelControles.setPreferredSize(new Dimension(300, 0));

        // 1. Panel de Login
        JPanel panelLogin = new JPanel(new GridLayout(4, 2, 5, 5));
        panelLogin.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Simular Nuevo Login", TitledBorder.LEFT, TitledBorder.TOP));

        panelLogin.add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        panelLogin.add(txtUsername);

        panelLogin.add(new JLabel("Rol:"));
        cbRole = new JComboBox<>(new String[]{"USER", "ADMIN", "GUEST"});
        panelLogin.add(cbRole);

        panelLogin.add(new JLabel("TTL (ms):"));
        spnTtl = new JSpinner(new SpinnerNumberModel(5000, 1000, 60000, 1000));
        panelLogin.add(spnTtl);

        // BOTÓN NORMAL SIN ESTILOS PARA WINDOWS
        JButton btnLogin = new JButton("Generar Token & Login");
        panelLogin.add(new JLabel("")); 
        panelLogin.add(btnLogin);

        // 2. Panel de Acciones por Token
        JPanel panelAcciones = new JPanel(new GridLayout(3, 1, 5, 5));
        panelAcciones.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Acciones por Token", TitledBorder.LEFT, TitledBorder.TOP));

        txtTokenAction = new JTextField();
        txtTokenAction.setToolTipText("Ingrese el token aquí");
        panelAcciones.add(txtTokenAction);

        JPanel panelBotonesAccion = new JPanel(new GridLayout(1, 2, 5, 0));
        JButton btnValidate = new JButton("Validar Token");
        JButton btnLogout = new JButton("Forzar Logout");
        panelBotonesAccion.add(btnValidate);
        panelBotonesAccion.add(btnLogout);
        panelAcciones.add(panelBotonesAccion);

        // 3. Panel de Mantenimiento
        JPanel panelAdmin = new JPanel(new GridLayout(2, 1, 5, 5));
        panelAdmin.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Mantenimiento del Servidor", TitledBorder.LEFT, TitledBorder.TOP));

        // BOTÓN NORMAL SIN ESTILOS PARA WINDOWS
        JButton btnClean = new JButton("Limpiar Expirados (GC)");
        panelAdmin.add(btnClean);

        panelControles.add(panelLogin);
        panelControles.add(Box.createRigidArea(new Dimension(0, 15)));
        panelControles.add(panelAcciones);
        panelControles.add(Box.createRigidArea(new Dimension(0, 15)));
        panelControles.add(panelAdmin);

        add(panelControles, BorderLayout.WEST);

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setBackground(new Color(43, 43, 43));
        consoleArea.setForeground(new Color(169, 183, 198));
        consoleArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        
        JScrollPane scrollPane = new JScrollPane(consoleArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log del Servidor (System.out)"));
        add(scrollPane, BorderLayout.CENTER);

        // EVENTOS
        btnLogin.addActionListener(e -> {
            String user = txtUsername.getText().trim();
            if (user.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un nombre de usuario.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String role = (String) cbRole.getSelectedItem();
            long ttl = ((Number) spnTtl.getValue()).longValue();
            
            String token = UUID.randomUUID().toString().substring(0, 8);
            
            System.out.println("> Solicitud de LOGIN recibida...");
            cache.login(token, user, role, ttl);
            
            txtTokenAction.setText(token);
            txtUsername.setText("");
            System.out.println("--------------------------------------------------");
        });

        btnValidate.addActionListener(e -> {
            String token = txtTokenAction.getText().trim();
            if (token.isEmpty()) return;
            System.out.println("> Solicitud de VALIDACIÓN para token: [" + token + "]");
            Session s = cache.validate(token);
            if (s != null) {
                System.out.println("ESTADO: Válido. Datos -> " + s.toString());
            }
            System.out.println("--------------------------------------------------");
        });

        btnLogout.addActionListener(e -> {
            String token = txtTokenAction.getText().trim();
            if (token.isEmpty()) return;
            System.out.println("> Solicitud de LOGOUT MANUAL para token: [" + token + "]");
            cache.logout(token);
            System.out.println("--------------------------------------------------");
        });

        btnClean.addActionListener(e -> {
            System.out.println("> Ejecutando rutina de LIMPIEZA...");
            cache.cleanExpired();
            System.out.println("--------------------------------------------------");
        });
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateConsole(String.valueOf((char) b));
            }
            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateConsole(new String(b, off, len));
            }
            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };
        System.setOut(new PrintStream(out, true));
    }

    private void updateConsole(final String text) {
        SwingUtilities.invokeLater(() -> {
            consoleArea.append(text);
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            SessionCacheGUI gui = new SessionCacheGUI();
            gui.setVisible(true);
        });
    }
}