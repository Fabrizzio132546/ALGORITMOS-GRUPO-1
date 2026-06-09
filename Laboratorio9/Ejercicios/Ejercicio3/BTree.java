package lab09.ejercicios.ejercicio3;

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
        if (pos[0] < count && ((Comparable<E>) cl).compareTo(this.keys.get(pos[0])) == 0) {
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
        sb.append(String.format(
            "%-10s %-25s %-12s %-15s\n",
            "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos"
        ));
        if (isEmpty()) {
            sb.append("BTree is empty...");
        } else {
            sb.append(writeTree(this.root, null));
        }
        return sb.toString();
    }

    private String writeTree(BNode<E> current, String parentId) {
        if (current == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String idStr = String.valueOf(current.idNode);
        String keysStr = current.toString();
        String parentStr = (parentId == null) ? "--" : "[" + parentId + "]";
        String childrenStr = "--";
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
        if (!firstChild) {
            cb.append("]");
            childrenStr = cb.toString();
        }
        sb.append(String.format("%-10s %-25s %-12s %-15s\n",idStr,keysStr,parentStr,childrenStr));
        for (int i = 0; i <= current.count; i++) {
            BNode<E> child = current.childs.get(i);

            if (child != null) {
                sb.append(writeTree(child, idStr));
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
    public void remove(E cl) {
        if (isEmpty()) {
            System.out.println("El árbol B está vacío.");
            return;
        }

        remove(this.root, cl);

        // Si la raíz se queda vacía después de la eliminación
        if (this.root.count == 0) {
            if (this.root.childs.get(0) == null) {
                this.root = null; // El árbol queda completamente vacío
            } else {
                this.root = this.root.childs.get(0); // El primer hijo pasa a ser la nueva raíz
            }
        }
    }

    // Método recursivo interno
    private void remove(BNode<E> node, E cl) {
        int[] pos = new int[1];
        boolean found = node.searchNode(cl, pos);
        int ind = pos[0]; 

        if (found) {
            if (node.childs.get(0) == null) {
                removeFromLeaf(node, ind);
            } else {
                removeFromNonLeaf(node, ind);
            }
        } else {
            if (node.childs.get(0) == null) {
                System.out.println("La clave " + cl + " no existe en el árbol.");
                return;
            }

            boolean isLastChild = (ind == node.count);
            remove(node.childs.get(ind), cl);

            // Si el hijo donde descendimos quedó con menos del mínimo, lo rellenamos
            int minKeys = (this.orden - 1) / 2;
            if (node.childs.get(ind).count < minKeys) {
                fill(node, ind);
            }
        }
    }

    // Eliminar de una hoja
    private void removeFromLeaf(BNode<E> node, int ind) { 
        for (int i = ind; i < node.count - 1; ++i) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    // Eliminar de un nodo interno
    private void removeFromNonLeaf(BNode<E> node, int ind) { 
        E cl = node.keys.get(ind);
        int minKeys = (this.orden - 1) / 2;

        if (node.childs.get(ind).count >= minKeys + 1) {
            E pred = getPredecessor(node, ind);
            node.keys.set(ind, pred);
            remove(node.childs.get(ind), pred);
        } else if (node.childs.get(ind + 1).count >= minKeys + 1) {
            E succ = getSuccessor(node, ind);
            node.keys.set(ind, succ);
            remove(node.childs.get(ind + 1), succ);
        } else {
            merge(node, ind);
            remove(node.childs.get(ind), cl);
        }
    }

    // Obtener predecesor
    private E getPredecessor(BNode<E> node, int ind) { 
        BNode<E> current = node.childs.get(ind);
        while (current.childs.get(0) != null) {
            current = current.childs.get(current.count);
        }
        return current.keys.get(current.count - 1);
    }

    // Obtener sucesor
    private E getSuccessor(BNode<E> node, int ind) { 
        BNode<E> current = node.childs.get(ind + 1);
        while (current.childs.get(0) != null) {
            current = current.childs.get(0);
        }
        return current.keys.get(0);
    }

    // Solucionar el subdesbordamiento (redistribución o fusión)
    private void fill(BNode<E> node, int ind) { 
        int minKeys = (this.orden - 1) / 2;

        if (ind != 0 && node.childs.get(ind - 1).count >= minKeys + 1) {
            borrowFromPrev(node, ind);
        } else if (ind != node.count && node.childs.get(ind + 1).count >= minKeys + 1) {
            borrowFromNext(node, ind);
        } else {
            if (ind != node.count) {
                merge(node, ind);
            } else {
                merge(node, ind - 1);
            }
        }
    }

    // Redistribución: pedir prestado al hermano izquierdo
    private void borrowFromPrev(BNode<E> node, int ind) { 
        BNode<E> child = node.childs.get(ind);
        BNode<E> hermano = node.childs.get(ind - 1); 

        for (int i = child.count - 1; i >= 0; --i) {
            child.keys.set(i + 1, child.keys.get(i));
        }
        if (child.childs.get(0) != null) {
            for (int i = child.count; i >= 0; --i) {
                child.childs.set(i + 1, child.childs.get(i));
            }
        }

        child.keys.set(0, node.keys.get(ind - 1));

        if (child.childs.get(0) != null) {
            child.childs.set(0, hermano.childs.get(hermano.count));
            hermano.childs.set(hermano.count, null);
        }

        node.keys.set(ind - 1, hermano.keys.get(hermano.count - 1));
        hermano.keys.set(hermano.count - 1, null);

        child.count += 1;
        hermano.count -= 1;
    }

    // Redistribución: pedir prestado al hermano derecho
    private void borrowFromNext(BNode<E> node, int ind) { 
        BNode<E> child = node.childs.get(ind);
        BNode<E> hermano = node.childs.get(ind + 1); 

        child.keys.set(child.count, node.keys.get(ind));

        if (child.childs.get(0) != null) {
            child.childs.set(child.count + 1, hermano.childs.get(0));
        }

        node.keys.set(ind, hermano.keys.get(0));

        for (int i = 1; i < hermano.count; ++i) {
            hermano.keys.set(i - 1, hermano.keys.get(i));
        }
        if (hermano.childs.get(0) != null) {
            for (int i = 1; i <= hermano.count; ++i) {
                hermano.childs.set(i - 1, hermano.childs.get(i));
            }
        }

        hermano.keys.set(hermano.count - 1, null);
        hermano.childs.set(hermano.count, null);

        child.count += 1;
        hermano.count -= 1;
    }

    // Fusión (Merge) de dos nodos hermanos
    private void merge(BNode<E> node, int ind) { 
        BNode<E> child = node.childs.get(ind);
        BNode<E> hermano = node.childs.get(ind + 1); 

        // Bajar la clave del padre al hijo
        child.keys.set(child.count, node.keys.get(ind));

        // Transferir las claves del hermano
        for (int i = 0; i < hermano.count; ++i) {
            child.keys.set(child.count + 1 + i, hermano.keys.get(i));
        }

        // Transferir los hijos del hermano
        if (child.childs.get(0) != null) {
            for (int i = 0; i <= hermano.count; ++i) {
                child.childs.set(child.count + 1 + i, hermano.childs.get(i));
            }
        }

        // Desplazar claves del padre un paso atrás para llenar el hueco
        for (int i = ind + 1; i < node.count; ++i) {
            node.keys.set(i - 1, node.keys.get(i));
        }

        // Desplazar hijos del padre un paso atrás
        for (int i = ind + 2; i <= node.count; ++i) {
            node.childs.set(i - 1, node.childs.get(i));
        }

        // Limpiar referencias sueltas en el padre
        node.keys.set(node.count - 1, null);
        node.childs.set(node.count, null);

        child.count += hermano.count + 1;
        node.count -= 1;
    }
}