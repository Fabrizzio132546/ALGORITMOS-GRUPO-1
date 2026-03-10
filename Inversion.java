import java.util.Stack;

public class InvertirPalabra {
    public static void main(String[] args) {
        String palabra = "JAVA";
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < palabra.length(); i++) {
            pila.push(palabra.charAt(i));
        }

        System.out.print("Palabra invertida: ");
        while (!pila.isEmpty()) {
            System.out.print(pila.pop());
        }
    }
}
