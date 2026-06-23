package hash;

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

    private int linearProbing(int initialPos, int key) {
        int targetPos = -1;

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;
            Element<E> current = table[pos];

            if (current.mark == 1 &&
                current.register.getKey() == key) {

                return pos;
            }

            if (current.mark == -1 &&
                targetPos == -1) {

                targetPos = pos;
            }

            if (current.mark == 0) {
                if (targetPos != -1) {
                    return targetPos;
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

        table[pos].register = reg;
        table[pos].mark = 1;
    }

    public Register<E> search(int key) {
        int initialPos = hash(key);

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;
            Element<E> current = table[pos];

            if (current.mark == 0) {
                return null;
            }

            if (current.mark == 1 &&
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

            if (current.mark == 0) {
                return;
            }

            if (current.mark == 1 &&
                current.register.getKey() == key) {

                current.mark = -1;
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
                System.out.println("DELETED antes: " + table[pos].register);
            } else {
                System.out.println(table[pos].register);
            }
        }
    }
}


