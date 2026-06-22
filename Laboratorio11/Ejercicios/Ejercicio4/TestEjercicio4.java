package hash;

public class TestEjercicio4 {
    public static void main(String[] args) {

        HashClosedEntry table = new HashClosedEntry(7);

        System.out.println("INSERTANDO CLAVES");
        System.out.println("=================");

        table.insert(5);
        table.insert(12);
        table.insert(19);
        table.insert(26);

        System.out.println("\nTABLA DESPUES DE INSERTAR");
        table.printTable();

        System.out.println("\nELIMINANDO LOGICAMENTE LA CLAVE 12");
        table.delete(12);

        System.out.println("\nTABLA DESPUES DE ELIMINAR 12");
        table.printTable();

        System.out.println("\nBUSCANDO LA CLAVE 19");
        boolean found = table.search(19);

        if (found) {
            System.out.println("La clave 19 fue encontrada.");
        } else {
            System.out.println("La clave 19 no fue encontrada.");
        }

        System.out.println("\nREINSERTANDO LA CLAVE 33");
        table.insert(33);

        System.out.println("\nTABLA DESPUES DE REINSERTAR 33");
        table.printTable();

        System.out.println("\nEXPLICACION");
        System.out.println("La celda DELETED no debe detener el sondeo porque puede haber elementos");
        System.out.println("insertados despues de esa posicion debido a colisiones.");
        System.out.println("La eliminacion logica marca la celda como DELETED.");
        System.out.println("La eliminacion fisica dejaria la celda como EMPTY, lo cual podria cortar la busqueda.");
    }
}





















