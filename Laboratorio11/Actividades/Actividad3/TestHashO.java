package hash;

public class TestHashO {
    public static void main(String[] args) {

        HashO<String> hashTable = new HashO<>(7);

        System.out.println("INSERTANDO ELEMENTOS EN LA TABLA HASH ABIERTA");
        System.out.println("============================================");

        hashTable.insert(new Register<>(10, "Juan"));
        hashTable.insert(new Register<>(17, "Ana"));
        hashTable.insert(new Register<>(24, "Luis"));
        hashTable.insert(new Register<>(31, "Rosa"));
        hashTable.insert(new Register<>(5, "Pedro"));
        hashTable.insert(new Register<>(12, "Carla"));

        System.out.println("\nESTADO FINAL DE LA TABLA HASH");
        System.out.println("=============================");
        hashTable.printTable();

        System.out.println("\nVALORES QUE PRODUCEN COLISIONES");
        System.out.println("================================");
        System.out.println("10, 17, 24 y 31 producen colisión en la posición 3.");
        System.out.println("5 y 12 producen colisión en la posición 5.");

        System.out.println("\nBÚSQUEDA DE LA CLAVE 24");
        System.out.println("=======================");

        Register<String> result = hashTable.search(24);

        if (result != null) {
            System.out.println("Nombre asociado a la clave 24: " + result.getName());
            System.out.println("La clave 24 se encuentra en la posición 3, nodo 3 de la lista.");
        } else {
            System.out.println("La clave 24 no fue encontrada.");
        }

        System.out.println("\nELIMINANDO LA CLAVE 17");
        System.out.println("======================");

        hashTable.delete(17);

        System.out.println("\nTABLA DESPUÉS DE ELIMINAR LA CLAVE 17");
        System.out.println("=====================================");
        hashTable.printTable();

        System.out.println("\nDespués de eliminar la clave 17:");
        System.out.println("En la cadena de la posición 3 quedan 3 nodos.");
        System.out.println("Los nodos restantes son: 10, 24 y 31.");
    }
}