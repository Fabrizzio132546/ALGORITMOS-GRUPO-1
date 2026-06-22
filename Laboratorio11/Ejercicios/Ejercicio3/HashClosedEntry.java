package hash;

public class HashClosedEntry {

    private enum State {
        EMPTY,
        OCCUPIED,
        DELETED
    }

    private static class Entry {
        int key;
        State state;

        public Entry() {
            this.state = State.EMPTY;
        }
    }

    private Entry[] table;
    private int size;

    public HashClosedEntry(int size) {
        this.size = size;
        this.table = new Entry[size];

        for (int pos = 0; pos < size; pos++) {
            table[pos] = new Entry();
        }
    }

    private int hash(int key) {
        return Math.floorMod(key, size);
    }

    public void insert(int key) {
        int initialPos = hash(key);
        int targetPos = -1;

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;

            if (table[pos].state == State.OCCUPIED &&
                table[pos].key == key) {

                return;
            }

            if (table[pos].state == State.DELETED &&
                targetPos == -1) {

                targetPos = pos;
            }

            if (table[pos].state == State.EMPTY) {
                if (targetPos == -1) {
                    targetPos = pos;
                }

                table[targetPos].key = key;
                table[targetPos].state = State.OCCUPIED;
                return;
            }
        }

        if (targetPos != -1) {
            table[targetPos].key = key;
            table[targetPos].state = State.OCCUPIED;
        } else {
            System.out.println("La tabla esta llena. No se pudo insertar " + key);
        }
    }

    public boolean search(int key) {
        int initialPos = hash(key);

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;

            System.out.println("Revisando posicion " + pos + ": " + getStateText(pos));

            if (table[pos].state == State.EMPTY) {
                return false;
            }

            if (table[pos].state == State.OCCUPIED &&
                table[pos].key == key) {

                return true;
            }
        }

        return false;
    }

    public void delete(int key) {
        int initialPos = hash(key);

        for (int i = 0; i < size; i++) {
            int pos = (initialPos + i) % size;

            if (table[pos].state == State.EMPTY) {
                return;
            }

            if (table[pos].state == State.OCCUPIED &&
                table[pos].key == key) {

                table[pos].state = State.DELETED;
                return;
            }
        }
    }

    private String getStateText(int pos) {
        if (table[pos].state == State.EMPTY) {
            return "EMPTY";
        }

        if (table[pos].state == State.DELETED) {
            return "DELETED";
        }

        return "OCCUPIED " + table[pos].key;
    }

    public void printTable() {
        for (int pos = 0; pos < size; pos++) {
            System.out.println(pos + ": " + getStateText(pos));
        }
    }
}













