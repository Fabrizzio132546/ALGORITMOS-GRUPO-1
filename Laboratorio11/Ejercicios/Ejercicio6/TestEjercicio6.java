package lab11.ejercicios.ejercicio6;

public class TestEjercicio6 {
    public static void main(String[] args) throws InterruptedException {
        // Inicializamos el caché con un tamaño primo
        SessionCache cache = new SessionCache(11); 

        System.out.println("--- 1. INICIANDO SESIONES ---");
        // Usuario 1: Expiración larga (10 segundos)
        cache.login("abc123", "juan_perez", "ADMIN", 10000); 
        
        // Usuario 2: Expiración MUY corta (1 segundo)
        cache.login("xyz789", "ana_gomez", "USER", 1000); 
        
        // Usuario 3: Expiración media (5 segundos)
        cache.login("qwe456", "luis_rojas", "GUEST", 5000); 

        System.out.println("\n--- 2. ESPERANDO 2 SEGUNDOS ---");
        // Pausamos la ejecución del programa para forzar la expiración de 'ana_gomez'
        Thread.sleep(2000); 

        System.out.println("\n--- 3. VALIDANDO TOKENS ---");
        Session s1 = cache.validate("abc123");
        System.out.println("Resultado abc123: " + (s1 != null ? s1.getUsername() : "N/A"));
        
        // Este debe fallar porque ya pasaron 2 segundos y su límite era 1 segundo
        Session s2 = cache.validate("xyz789"); 
        System.out.println("Resultado xyz789: " + (s2 != null ? s2.getUsername() : "N/A"));

        System.out.println("\n--- 4. CERRANDO SESIÓN EXPLÍCITAMENTE ---");
        // Luis decide cerrar sesión manualmente usando su token
        cache.logout("qwe456"); 

        System.out.println("\n--- 5. LIMPIEZA DE SESIONES EXPIRADAS ---");
        // Recorre la tabla hash y elimina los nodos caducados de las ListLinked
        cache.cleanExpired(); 
    }
}