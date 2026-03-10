import java.util.Stack;

public class EjemploPila {
    public static void main(String[] args) {
        Stack<Integer> pila = new Stack<Integer>();
        pila.push(5);
        pila.push(8);
        pila.push(12);
        System.out.println("pila: " + pila);
        int ultimo = pila.pop();
        System.out.println("quitamos : " + ultimo);
        System.out.println("como queda: " + pila);
    }
}
