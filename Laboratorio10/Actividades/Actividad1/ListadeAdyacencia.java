import listlinked.ListLinked; // Asegúrate de importar tu propia lista enlazada

public class ListasAdyacencia {

    public static void main(String[] args) {
        construirGrafoA();
        construirGrafoB();
        construirGrafoC();
    }

    private static void construirGrafoA() {
        System.out.println("=== A. GRAFO NO DIRIGIDO (Inserción al final) ===");
        // Instanciamos nuestro mapa personalizado con capacidad para 6 vértices
        CustomMap<Integer, ListLinked<Integer>> grafoA = new CustomMap<>(6);
        for (int i = 1; i <= 6; i++) {
            grafoA.put(i, new ListLinked<>());
        }

        // Se evalúan en orden numérico y se insertan al final con addLast()
        grafoA.get(1).addLast(2); grafoA.get(1).addLast(3);
        grafoA.get(2).addLast(1); grafoA.get(2).addLast(3); grafoA.get(2).addLast(4);
        grafoA.get(3).addLast(1); grafoA.get(3).addLast(2); grafoA.get(3).addLast(4); grafoA.get(3).addLast(5);
        grafoA.get(4).addLast(2); grafoA.get(4).addLast(3); grafoA.get(4).addLast(5); grafoA.get(4).addLast(6);
        grafoA.get(5).addLast(3); grafoA.get(5).addLast(4); grafoA.get(5).addLast(6);
        grafoA.get(6).addLast(4); grafoA.get(6).addLast(5);

        imprimirGrafo(grafoA);
    }

    private static void construirGrafoB() {
        System.out.println("\n=== B. GRAFO DIRIGIDO (Inserción al inicio) ===");
        // Instanciamos nuestro mapa personalizado con capacidad para 5 vértices
        CustomMap<String, ListLinked<String>> grafoB = new CustomMap<>(5);
        String[] nodos = {"A", "B", "C", "D", "E"};
        for (String nodo : nodos) {
            grafoB.put(nodo, new ListLinked<>());
        }

        // Se evalúan en orden alfabético, pero se insertan al inicio con insertFirst()
        grafoB.get("A").insertFirst("B"); grafoB.get("A").insertFirst("C"); grafoB.get("A").insertFirst("E");
        grafoB.get("B").insertFirst("C");
        grafoB.get("C").insertFirst("A");
        grafoB.get("D").insertFirst("B");
        grafoB.get("E").insertFirst("B"); grafoB.get("E").insertFirst("C"); grafoB.get("E").insertFirst("D");

        imprimirGrafo(grafoB);
    }

    private static void construirGrafoC() {
        System.out.println("\n=== C. GRAFO PONDERADO (Inserción al final) ===");
        // Instanciamos nuestro mapa personalizado con capacidad para 5 vértices
        CustomMap<Integer, ListLinked<String>> grafoC = new CustomMap<>(5);
        for (int i = 1; i <= 5; i++) {
            grafoC.put(i, new ListLinked<>());
        }

        // Para representar el peso, usamos un String "Destino(Peso)" y addLast()
        grafoC.get(1).addLast("2(5)");
        grafoC.get(2).addLast("4(6)");
        // Nodo 3 no tiene salidas
        grafoC.get(4).addLast("2(3)"); grafoC.get(4).addLast("5(2)");
        grafoC.get(5).addLast("1(4)"); grafoC.get(5).addLast("3(1)");

        imprimirGrafo(grafoC);
    }

    // Método de impresión adaptado para iterar sobre nuestro CustomMap
    private static <K extends Comparable<K>, V extends Comparable<V>> void imprimirGrafo(CustomMap<K, ListLinked<V>> grafo) {
        for (int j = 0; j < grafo.size(); j++) {
            K clave = grafo.getKey(j);
            ListLinked<V> lista = grafo.getValue(j);
            
            System.out.print(clave + " -> ");
            if (lista.isEmpty()) {
                System.out.print("nulo");
            } else {
                for (int i = 0; i < lista.size(); i++) {
                    System.out.print(lista.get(i));
                    if (i < lista.size() - 1) {
                        System.out.print(" -> ");
                    } else {
                        System.out.print(" -> nulo");
                    }
                }
            }
            System.out.println();
        }
    }
}

// =========================================================
// ESTRUCTURA PERSONALIZADA PARA REEMPLAZAR EL HASHMAP
// =========================================================

class CustomMap<K extends Comparable<K>, V> {
    private Object[] keys;
    private Object[] values;
    private int size;

    public CustomMap(int capacity) {
        keys = new Object[capacity];
        values = new Object[capacity];
        size = 0;
    }

    // Almacena la llave y el valor (como el put de LinkedHashMap)
    public void put(K key, V value) {
        keys[size] = key;
        values[size] = value;
        size++;
    }

    // Busca y retorna el valor asociado a la llave (como el get de HashMap)
    @SuppressWarnings("unchecked")
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (((K) keys[i]).compareTo(key) == 0) {
                return (V) values[i];
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    // Métodos para poder iterar en el orden de inserción
    @SuppressWarnings("unchecked")
    public K getKey(int index) {
        return (K) keys[index];
    }

    @SuppressWarnings("unchecked")
    public V getValue(int index) {
        return (V) values[index];
    }
}
