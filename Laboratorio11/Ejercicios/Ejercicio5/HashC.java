package lab11.ejercicios.ejercicio5;
import hash.*;

package lab11.ejercicios.ejercicio5;
import hash.*;

public class HashC<E> {

    private static class Element<E> {
        Register<E> register;
        int mark; // 0 = EMPTY, 1 = OCCUPIED, -1 = DELETED

        public Element() {
            this.register = null;
            this.mark = 0; 
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

    private int linearProbing(int initialPos, int key) {
        int targetPos = -1;

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;
            Element<E> current = table[pos];

            // Si el elemento ya existe, devolvemos su posición
            if (current.mark == 1 && current.register.getKey() == key) {
                return pos;
            }

            // Guardamos la primera posición eliminada que encontremos
            if (current.mark == -1 && targetPos == -1) {
                targetPos = pos;
            }

            // Si encontramos un espacio vacío
            if (current.mark == 0) {
                if (targetPos != -1) {
                    return targetPos; // Preferimos usar la celda borrada si existe
                }
                return pos;
            }
        }

        return targetPos;
    }

    public void insert(Register<E> reg) {
        if (reg == null) {
            return;
        }

        int initialPos = hash(reg.getKey());
        int pos = linearProbing(initialPos, reg.getKey());

        if (pos == -1) {
            System.out.println("Error: tabla hash llena");
            return;
        }

        // Detectar si estamos insertando un nuevo elemento o actualizando uno existente
        boolean isNewElement = (table[pos].mark != 1 || table[pos].register.getKey() != reg.getKey());

        table[pos].register = reg;
        table[pos].mark = 1;

        // Solo aumentamos el contador y verificamos el rehash si es un elemento nuevo
        if (isNewElement) {
            numElements++;
            checkRehash();
        }
    }

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

        // Re-insertar elementos válidos basándonos en la marca (mark == 1)
        for (Element<E> current : oldTable) {
            if (current.mark == 1) {
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

            if (current.mark == 0) {
                return null;
            }

            if (current.mark == 1 && current.register.getKey() == key) {
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

            if (current.mark == 0) {
                return;
            }

            if (current.mark == 1 && current.register.getKey() == key) {
                current.mark = -1; // Marcamos como eliminado
                numElements--;     // Reducimos el número de elementos
                return;
            }
        }
    }

    public void printTable() {
        for (int pos = 0; pos < size; pos++) {
            System.out.print(pos + ": ");

            if (table[pos].mark == 0) {
                System.out.println("EMPTY");
            } else if (table[pos].mark == -1) {
                System.out.println("DELETED -> antes: " + table[pos].register);
            } else {
                System.out.println(table[pos].register);
            }
        }
    }
}
