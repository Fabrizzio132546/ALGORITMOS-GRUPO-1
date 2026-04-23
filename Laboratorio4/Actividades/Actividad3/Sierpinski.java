import java.awt.*;
import javax.swing.*;
public class Sierpinski extends JPanel {
private int nivel;
// Constructor que recibe el nivel de recursión
public Sierpinski(int nivel) {
this.nivel = nivel;
}
// Método recursivo que dibuja el triángulo
public void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int nivel) {
// Caso base: dibuja el triángulo relleno
if (nivel == 0) {
int[] xPoints = {x1, x2, x3};
int[] yPoints = {y1, y2, y3};
g.fillPolygon(xPoints, yPoints, 3);
} else {
// Calcula los puntos medios de cada lado
int mx12 = (x1 + x2) / 2, my12 = (y1 + y2) / 2;
int mx23 = (x2 + x3) / 2, my23 = (y2 + y3) / 2;
int mx31 = (x3 + x1) / 2, my31 = (y3 + y1) / 2;
// Llamadas recursivas para los tres sub-triángulos
drawTriangle(g, x1, y1, mx12, my12, mx31, my31, nivel - 1); // superior
drawTriangle(g, mx12, my12, x2, y2, mx23, my23, nivel - 1); // inferior derecho
drawTriangle(g, mx31, my31, mx23, my23, x3, y3, nivel - 1); // inferior izquierdo
}
}
@Override
protected void paintComponent(Graphics g) {
super.paintComponent(g);
g.setColor(Color.BLACK);
drawTriangle(g, 100, 500, 500, 500, 300, 100, nivel);
}
// Crea y muestra una ventana para un nivel dado
public static void mostrarNivel(int nivel) {
JFrame frame = new JFrame("Tri´angulo de Sierpinski - Nivel " + nivel);
Sierpinski panel = new Sierpinski(nivel);
frame.add(panel);
frame.setSize(620, 580);
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
}
public static void main(String[] args) {
// Genera los tres niveles requeridos por el laboratorio
mostrarNivel(4);
mostrarNivel(6);
mostrarNivel(8);
}
}

