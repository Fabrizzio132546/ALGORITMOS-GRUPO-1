package lab11.ejercicios.ejercicio5;
import hash.*;

public class HashC<E> {

    private static class Element<E> {
        Register<E> register;
        boolean isAvailable;

        public Element() {
            this.register = null;
            this.isAvailable = true;
        }
    }

    private Element<E>[] table;
    private int size;
    private int numElements;

    @SuppressWarnings("unchecked")
    public HashC(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("El tamaño debe ser mayor que cero.");
        }

        this.size = size;
        this.numElements = 0;
        this.table = (Element<E>[]) new Element[size];

        for (int pos = 0; pos < size; pos++) {
            table[pos] = new Element<>();
        }
    }

    private int hash(int key) {
        return Math.floorMod(key, size);
    }

    public void insert(Register<E> reg) {
        if (reg == null) {
            return;
        }

        int initialPos = hash(reg.getKey());
        int targetPos = -1;

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;
            Element<E> current = table[pos];

            if (current.register != null && !current.isAvailable && current.register.getKey() == reg.getKey()) {
                current.register = reg;
                return;
            }

            if (current.register != null && current.isAvailable && targetPos == -1) {
                targetPos = pos;
            }

            if (current.register == null) {
                if (targetPos == -1) {
                    targetPos = pos;
                }
                table[targetPos].register = reg;
                table[targetPos].isAvailable = false;
                numElements++;
                checkRehash(); // Verificar si superamos el factor de carga
                return;
            }
        }

        if (targetPos != -1) {
            table[targetPos].register = reg;
            table[targetPos].isAvailable = false;
            numElements++;
            checkRehash();
        } else {
            System.out.println("Error: tabla hash llena");
        }
    }

    // --- LÓGICA DE REHASHING DINÁMICO ---
    private void checkRehash() {
        double alpha = (double) numElements / size;
        System.out.printf("Factor de carga actual (alfa): %.2f%n", alpha);
        
        if (alpha > 0.75) {
            int nextSize = nextPrime(this.size); // Buscar el siguiente primo dinámicamente
            rehash(nextSize); 
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    private int nextPrime(int n) {
        int prime = n + 1;
        while (!isPrime(prime)) {
            prime++;
        }
        return prime;
    }

    @SuppressWarnings("unchecked")
    private void rehash(int newSize) {
        System.out.println("\n--- INICIANDO REHASHING ---");
        System.out.println("Redimensionando tabla de " + size + " a " + newSize);
        
        Element<E>[] oldTable = table;
        this.size = newSize;
        this.table = (Element<E>[]) new Element[newSize];
        this.numElements = 0;

        for (int i = 0; i < size; i++) {
            table[i] = new Element<>();
        }

        // Re-insertar elementos válidos
        for (Element<E> current : oldTable) {
            if (current.register != null && !current.isAvailable) {
                insert(current.register); 
            }
        }
        System.out.println("--- REHASHING COMPLETADO ---\n");
    }
    // ------------------------------------

    public Register<E> search(int key) {
        int initialPos = hash(key);

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;
            Element<E> current = table[pos];

            if (current.register == null) return null;

            if (current.register != null && !current.isAvailable && current.register.getKey() == key) {
                return current.register;
            }
        }
        return null;
    }

    public void delete(int key) {
        int initialPos = hash(key);

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;
            Element<E> current = table[pos];

            if (current.register == null) return;

            if (current.register != null && !current.isAvailable && current.register.getKey() == key) {
                current.isAvailable = true;
                numElements--; 
                return;
            }
        }
    }

    public void printTable() {
        for (int pos = 0; pos < size; pos++) {
            System.out.print(pos + ": ");

            if (table[pos].register == null) {
                System.out.println("EMPTY");
            } else if (table[pos].isAvailable) {
                System.out.println("DELETED -> " + table[pos].register);
            } else {
                System.out.println(table[pos].register);
            }
        }
    }
}