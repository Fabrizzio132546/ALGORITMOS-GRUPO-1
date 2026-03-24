package Actividad01;

public class TestGen {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE CAJONERÍA (GOLOSINAS) ===");
        Cajoneria<Golosina> miCajoneria = new Cajoneria<>(10);
        
        Golosina g1 = new Golosina("Gomita", 10);
        miCajoneria.add(new Caja<>("Rojo", g1));
        miCajoneria.add(new Caja<>("Azul", new Golosina("Caramelo", 5)));
        miCajoneria.add(new Caja<>("Verde", g1)); // Repetida
        miCajoneria.add(new Caja<>("Amarillo", new Golosina("Chicle", 3)));
        miCajoneria.add(new Caja<>("Blanco", new Golosina("Paleta", 15)));

        System.out.println(miCajoneria);
        System.out.println("Búsqueda de 'Gomita (10g)': " + miCajoneria.search(g1));
        System.out.println("Conteo de 'Gomita (10g)': " + miCajoneria.contar(g1));

        System.out.println("\nEliminando 'Caramelo (5g)'...");
        miCajoneria.delete(new Golosina("Caramelo", 5));

        System.out.println("\n=== PRUEBA DE CAJONERÍA (CHOCOLATINAS) ===");
        Cajoneria<Chocolatina> miCajonCho = new Cajoneria<>(5);
        Chocolatina ch1 = new Chocolatina("Milka");
        miCajonCho.add(new Caja<>("Lila", ch1));
        miCajonCho.add(new Caja<>("Dorado", ch1));
        
        System.out.println(miCajonCho);
        System.out.println("Ocurrencias de Milka: " + miCajonCho.contar(ch1));
    }
}

    @Override
    public Iterator<Caja<T>> iterator() { return lista.iterator(); }
}
