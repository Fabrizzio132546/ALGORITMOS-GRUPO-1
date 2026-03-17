import java.util.Stack;

class Usuario {
    String nombre;
    
    Usuario(String n) { 
        nombre = n; 
    }
    String permiso() {  return "estandar"; }
}

class Empleado extends Usuario {
    Empleado(String n) { 
        super(n); 
    }
    @Override
    String permiso() {  return "total"; }
}

public class Controlacceso {
    public static void main(String[] args) {
        Usuario[] lista = { new Empleado("fabrizzio"), new Usuario("gustavo") };
        
        Stack<Usuario> salidas = new Stack<>();

        System.out.println("registro de ingresos:");
        for (Usuario u : lista) {
            System.out.println("usuario: " + u.nombre + ", acceso: " + u.permiso());
            salidas.push(u); 
        }

        System.out.println("ultima persona en salir");
        if (!salidas.isEmpty()) {
            Usuario ultimo = salidas.pop();
            System.out.println("salida registrada: " + ultimo.nombre);
        }
    }
}
