package Actividad01;

public class Principal {

    public static void main(String[] args) {

        // Bolsa de chocolatinas
        Bolsa<Chocolatina> bolsaCho = new Bolsa<>(3);

        Chocolatina c1 = new Chocolatina("Milka");
        Chocolatina c2 = new Chocolatina("Ferrero");
        Chocolatina c3 = new Chocolatina("Nestlé");

        bolsaCho.add(c1);
        bolsaCho.add(c2);
        bolsaCho.add(c3);

        System.out.println("Chocolatinas:");
        for (Chocolatina c : bolsaCho) {
            System.out.println(c);
        }

        // Bolsa de golosinas
        Bolsa<Golosina> bolsaGolo = new Bolsa<>(3);

        bolsaGolo.add(new Golosina("Gomita", 10));
        bolsaGolo.add(new Golosina("Caramelo", 5));
        bolsaGolo.add(new Golosina("Chicle", 3));

        System.out.println("\nGolosinas:");
        for (Golosina g : bolsaGolo) {
            System.out.println(g);
        }

        // Prueba del metodo generico
        Integer nums1[] = {1, 2, 3, 4, 5};
        Integer nums2[] = {1, 2, 3, 4, 5};
        Integer nums3[] = {1, 2, 7, 4, 5};

        System.out.println("\nComparación de arrays:");
        System.out.println("nums1 == nums2: " +
                DemoMetodoGenerico.igualArrays(nums1, nums2)); 
        System.out.println("nums1 == nums3: " +
                DemoMetodoGenerico.igualArrays(nums1, nums3)); 

    }
}
