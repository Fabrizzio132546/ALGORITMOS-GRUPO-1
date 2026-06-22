package hash;

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

    @SuppressWarnings("unchecked")
    public HashC(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("El tamaño debe ser mayor que cero.");
        }

        this.size = size;
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

            if (current.register != null &&
                !current.isAvailable &&
                current.register.getKey() == reg.getKey()) {

                current.register = reg;
                return;
            }

            if (current.register != null &&
                current.isAvailable &&
                targetPos == -1) {

                targetPos = pos;
            }

            if (current.register == null) {
                if (targetPos == -1) {
                    targetPos = pos;
                }

                table[targetPos].register = reg;
                table[targetPos].isAvailable = false;
                return;
            }
        }

        if (targetPos != -1) {
            table[targetPos].register = reg;
            table[targetPos].isAvailable = false;
        } else {
            System.out.println("Error: tabla hash llena");
        }
    }

    public Register<E> search(int key) {
        int initialPos = hash(key);

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;
            Element<E> current = table[pos];

            if (current.register == null) {
                return null;
            }

            if (current.register != null &&
                !current.isAvailable &&
                current.register.getKey() == key) {

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

            if (current.register == null) {
                return;
            }

            if (current.register != null &&
                !current.isAvailable &&
                current.register.getKey() == key) {

                current.isAvailable = true;
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
                System.out.println("DELETED antes: " + table[pos].register);
            } else {
                System.out.println(table[pos].register);
            }
        }
    }
}





