package lab11.ejercicios.ejercicio6;

import listlinked.ListLinked;
import listlinked.Node;

public class SessionCache {
    private ListLinked<Session>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public SessionCache(int size) {
        this.size = size;
        this.table = (ListLinked<Session>[]) new ListLinked[size];
        
        // Inicializamos cada índice con tu propia ListLinked vacía
        for (int i = 0; i < size; i++) {
            table[i] = new ListLinked<>();
        }
    }

    // Función hash para Strings
    private int hash(String token) {
        return Math.abs(token.hashCode()) % size;
    }

    public void login(String token, String username, String role, long ttlMs) {
        int pos = hash(token);
        long expiresAt = System.currentTimeMillis() + ttlMs;
        
        Session newSession = new Session(token, username, role, expiresAt);
        table[pos].addLast(newSession); // Método de tu ListLinked
        System.out.println("Login exitoso: " + username + " -> " + token);
    }

    public Session validate(String token) {
        int pos = hash(token);
        
        // Uso de tu clase Node para iterar tu ListLinked
        Node<Session> actual = table[pos].getFirst();
        
        while (actual != null) {
            Session session = actual.getData();
            if (session.getToken().equals(token)) {
                if (System.currentTimeMillis() > session.getExpiresAt()) {
                    System.out.println("Validación fallida: La sesión con token '" + token + "' ha expirado.");
                    return null;
                }
                return session;
            }
            actual = actual.getNext();
        }
        
        System.out.println("Validación fallida: Token inexistente.");
        return null;
    }

    public void logout(String token) {
        int pos = hash(token);
        // Creamos una sesión ficticia solo con el token para usar tu método removeNode
        // Esto funciona porque sobreescribimos compareTo en Session para comparar por token
        Session dummySession = new Session(token); 
        
        boolean removed = table[pos].removeNode(dummySession); // Método de tu ListLinked
        
        if (removed) {
            System.out.println("Logout exitoso para el token: " + token);
        } else {
            System.out.println("Logout fallido: Token no encontrado.");
        }
    }

    public void cleanExpired() {
        long currentTime = System.currentTimeMillis();
        int activeSessions = 0;
        
        for (int i = 0; i < size; i++) {
            Node<Session> actual = table[i].getFirst();
            
            while (actual != null) {
                Session session = actual.getData();
                Node<Session> next = actual.getNext(); // Guardamos el siguiente antes de posibles borrados
                
                if (currentTime > session.getExpiresAt()) {
                    table[i].removeNode(session); // Eliminamos usando tu método
                } else {
                    activeSessions++;
                }
                actual = next; // Avanzamos
            }
        }
        
        System.out.println("Mantenimiento: Limpieza completada. Sesiones activas restantes: " + activeSessions);
    }
}