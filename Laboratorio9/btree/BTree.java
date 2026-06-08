import java.util.ArrayList;

class BNode<E> {
    private static int idCounter = 1;
    
    protected int idNode; 
    protected ArrayList<E> keys;            
    protected ArrayList<BNode<E>> childs;   
    protected int count;                    

    public BNode(int n) {
        this.idNode = idCounter++;
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;
        
        for (int i = 0; i < n; i++) { 
            this.keys.add(null);
            this.childs.add(null);
        }
    }

    public boolean nodeFull(int maxKeys) {
        return this.count >= maxKeys;
    }

    public boolean nodeEmpty(int minKeys) {
        return this.count < minKeys;
    }

    public boolean searchNode(E cl, int[] pos) {
        pos[0] = 0;
        while (pos[0] < this.count && ((Comparable<E>) cl).compareTo(this.keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        if (pos[0] < this.count && cl.equals(this.keys.get(pos[0]))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < this.count; i++) {
            sb.append(this.keys.get(i));
            if (i < this.count - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}


class BTree<E extends Comparable<E>> {
    private BNode<E> root;  
    private int orden;      
    private boolean up;     
    private BNode<E> nDes;   

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean search(E cl) {
        return search(this.root, cl);
    }

    private boolean search(BNode<E> current, E cl) {
        if (current == null) {
            System.out.println("Clave " + cl + " no encontrada en el árbol."); 
            return false;
        }
        int[] pos = new int[1];
        boolean encontrado = current.searchNode(cl, pos);
        if (encontrado) {
            System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + pos[0] + "."); 
            return true;
        } else {
            return search(current.childs.get(pos[0]), cl);
        }
    }

    public void insert(E cl) {
        up = false; 
        E mediana;
        BNode<E> pnew;
        
        mediana = push(this.root, cl); 

        if (up) { 
            pnew = new BNode<E>(this.orden);
            pnew.count = 1; 
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root); 
            pnew.childs.set(1, nDes); 
            this.root = pnew; 
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1]; 
        E mediana;
        
        if (current == null) {
            up = true; 
            nDes = null;
            return cl; 
        } else {
            boolean fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado\n"); 
                up = false; 
                return null; 
            }
            mediana = push(current.childs.get(pos[0]), cl); 
            
            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]); 
                } else {
                    up = false; 
                    putNode(current, mediana, nDes, pos[0]); 
                }
            }
            return mediana;
        }
    }


    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) { 
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1)); 
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd); 
        current.count++; 
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes; 
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1; 
        nDes = new BNode<E>(this.orden); 
        for (i = posMdna; i < this.orden - 1; i++) { 
            nDes.keys.set(i - posMdna, current.keys.get(i)); 
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1)); 
        }
        nDes.count = (this.orden - 1) - posMdna; 
        current.count = posMdna; 
        if (k <= this.orden / 2) {
            putNode(current, cl, rd, k); 
        } else {
            putNode(nDes, cl, rd, k - posMdna); 
        }
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count)); 
        current.count--; 
        
        return median; 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-25s %-12s %-15s\n", "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos"));
        if (isEmpty()) {
            sb.append("BTree is empty...");
        } else {
            sb.append(writeTree(this.root));
        }
        return sb.toString();
    }

    private String writeTree(BNode<E> current) {
        return writeTree(current, "--");
    }

    private String writeTree(BNode<E> current, String parentId) {
        if (current == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        String idStr = String.valueOf(current.idNode);
        String keysStr = current.toString(); 
        String parentStr = (parentId == null || parentId.equals("--")) ? "--" : "[" + parentId + "]";
        String childrenStr = "--";
        if (current.childs.get(0) != null) {
            StringBuilder cb = new StringBuilder("[");
            boolean firstChild = true;
            
            for (int i = 0; i <= current.count; i++) {
                BNode<E> child = current.childs.get(i);
                if (child != null) {
                    if (!firstChild) {
                        cb.append(", ");
                    }
                    cb.append(child.idNode);
                    firstChild = false;
                }
            }
            cb.append("]");
            childrenStr = cb.toString();
        }
        sb.append(String.format("%-10s %-25s %-12s %-15s\n", idStr, keysStr, parentStr, childrenStr));
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                sb.append(writeTree(current.childs.get(i), idStr));
            }
        }
        return sb.toString();
    }
    
    
    public void searchRange(E min, E max) {
        if (min == null || max == null) {
            System.out.println("Error: Los límites del rango no pueden ser nulos.");
            return;
        }
        if (min.compareTo(max) > 0) {
            System.out.println("Rango inválido: el valor mínimo es mayor que el valor máximo.");
            return;
        }
        if (isEmpty()) {
            System.out.println("El árbol B está vacío.");
            return;
        }
        System.out.print("Claves en el rango [" + min + ", " + max + "]: ");
        boolean[] found = new boolean[1]; 
        searchRange(this.root, min, max, found);

        if (!found[0]) {
            System.out.print("No existen claves en ese rango.");
        }
        System.out.println();
    }
    private void searchRange(BNode<E> current, E min, E max, boolean[] found) {
        if (current == null) {
            return;
        }
        int i;
        for (i = 0; i < current.count; i++) {
            E currentKey = current.keys.get(i);
            if (currentKey.compareTo(min) >= 0) {
                searchRange(current.childs.get(i), min, max, found);
            }
            if (currentKey.compareTo(min) >= 0 && currentKey.compareTo(max) <= 0) {
                System.out.print(currentKey + " ");
                found[0] = true;
            }

            if (currentKey.compareTo(max) > 0) {
                return; 
            }
        }
        searchRange(current.childs.get(i), min, max, found);
    }
}
