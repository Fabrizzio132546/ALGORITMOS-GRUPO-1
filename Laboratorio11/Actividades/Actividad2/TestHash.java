package hash;

public class TestHash {
    public static void main(String[] args) {

        HashC<String> hashTable = new HashC<>(11);

        hashTable.insert(new Register<>(34, "Ana"));
        hashTable.insert(new Register<>(3, "Bruno"));
        hashTable.insert(new Register<>(7, "Carlos"));
        hashTable.insert(new Register<>(30, "Diana"));
        hashTable.insert(new Register<>(11, "Elena"));
        hashTable.insert(new Register<>(8, "Fernando"));
        hashTable.insert(new Register<>(7, "Gabriela"));
        hashTable.insert(new Register<>(23, "Helena"));
        hashTable.insert(new Register<>(41, "Ivan"));
        hashTable.insert(new Register<>(16, "Julia"));
        hashTable.insert(new Register<>(34, "Karla"));

        System.out.println("\nTABLA ANTES DE ELIMINAR");
        hashTable.printTable();

        System.out.println("\nBUSCANDO LA CLAVE 23");
        Register<String> result = hashTable.search(23);

        if (result != null) {
            System.out.println("Registro encontrado: " + result);
        } else {
            System.out.println("No se encontró la clave 23.");
        }

        System.out.println("\nELIMINANDO LA CLAVE 30");
        hashTable.delete(30);

        System.out.println("\nTABLA DESPUÉS DE ELIMINAR");
        hashTable.printTable();
    }
}