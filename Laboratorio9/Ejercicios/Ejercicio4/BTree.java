package lab09.ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.List;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;  
    private int orden;      
    private boolean up;     
    private BNode<E> nDes;   

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    // 1. Mostrar recorrido de búsqueda
    public String searchWithPath(E cl) {
        StringBuilder path = new StringBuilder("Ruta de búsqueda:\n");
        boolean[] found = new boolean[1];
        BNode<E> resultNode = searchWithPathRec(this.root, cl, path, found);
        if (!found[0]) {
            path.append("\n❌ La clave no existe en el árbol.");
        }
        return path.toString();
    }

    private BNode<E> searchWithPathRec(BNode<E> current, E cl, StringBuilder path, boolean[] found) {
        if (current == null) return null;
        
        path.append(" -> Visitando Nodo ID ").append(current.idNode)
            .append(" con ").append(current.count).append(" claves.\n");

        int[] pos = new int[1];
        boolean nodeFound = current.searchNode(cl, pos);
        
        if (nodeFound) {
            found[0] = true;
            path.append("\n✅ ¡Clave encontrada en el Nodo ID ").append(current.idNode)
                .append(" en la posición ").append(pos[0]).append("!\n");
            return current;
        } else {
            return searchWithPathRec(current.childs.get(pos[0]), cl, path, found);
        }
    }

    // 2. Mostrar ordenados
    public List<E> getInOrder() {
        List<E> list = new ArrayList<>();
        getInOrderRec(this.root, list);
        return list;
    }

    private void getInOrderRec(BNode<E> current, List<E> list) {
        if (current == null) return;
        int i;
        for (i = 0; i < current.count; i++) {
            getInOrderRec(current.childs.get(i), list);
            list.add(current.keys.get(i));
        }
        getInOrderRec(current.childs.get(i), list);
    }

    //Obtener Altura
    public int getHeight() {
        if (isEmpty()) return 0;
        int height = 1;
        BNode<E> current = root;
        while (current.childs.get(0) != null) {
            height++;
            current = current.childs.get(0);
        }
        return height;
    }

    //Obtener Cantidad Total de Elementos
    public int getSize() {
        return getSizeRec(this.root);
    }

    private int getSizeRec(BNode<E> current) {
        if (current == null) return 0;
        int count = current.count;
        for (int i = 0; i <= current.count; i++) {
            count += getSizeRec(current.childs.get(i));
        }
        return count;
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

    public boolean insert(E cl) {
        up = false; 
        E mediana;
        BNode<E> pnew;
        
        boolean[] duplicated = new boolean[]{false};
        mediana = push(this.root, cl, duplicated); 

        if (duplicated[0]) return false;

        if (up) { 
            pnew = new BNode<E>(this.orden);
            pnew.count = 1; 
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root); 
            pnew.childs.set(1, nDes); 
            this.root = pnew; 
        }
        return true;
    }

    private E push(BNode<E> current, E cl, boolean[] duplicated) {
        int pos[] = new int[1]; 
        E mediana;
        
        if (current == null) {
            up = true; 
            nDes = null;
            return cl; 
        } else {
            boolean fl = current.searchNode(cl, pos);
            if (fl) {
                duplicated[0] = true;
                up = false; 
                return null; 
            }
            mediana = push(current.childs.get(pos[0]), cl, duplicated); 
            
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
    public boolean remove(E cl) {
        if (isEmpty()) return false;
        
        int prevSize = getSize();
        removeRec(this.root, cl);

        if (this.root != null && this.root.count == 0) {
            if (this.root.childs.get(0) == null) {
                this.root = null; 
            } else {
                this.root = this.root.childs.get(0); 
            }
        }
        return prevSize != getSize(); // Retorna true si se eliminó algo
    }

    private void removeRec(BNode<E> node, E cl) {
        if (node == null) return;
        int[] pos = new int[1];
        boolean found = node.searchNode(cl, pos);
        int ind = pos[0]; 

        if (found) {
            if (node.childs.get(0) == null) removeFromLeaf(node, ind);
            else removeFromNonLeaf(node, ind);
        } else {
            if (node.childs.get(0) == null) return; 

            boolean isLastChild = (ind == node.count);
            removeRec(node.childs.get(ind), cl);

            int minKeys = (this.orden - 1) / 2;
            if (node.childs.get(ind) != null && node.childs.get(ind).count < minKeys) {
                fill(node, ind);
            }
        }
    }

    private void removeFromLeaf(BNode<E> node, int ind) { 
        for (int i = ind; i < node.count - 1; ++i) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private void removeFromNonLeaf(BNode<E> node, int ind) { 
        E cl = node.keys.get(ind);
        int minKeys = (this.orden - 1) / 2;

        if (node.childs.get(ind).count >= minKeys + 1) {
            E pred = getPredecessor(node, ind);
            node.keys.set(ind, pred);
            removeRec(node.childs.get(ind), pred);
        } else if (node.childs.get(ind + 1).count >= minKeys + 1) {
            E succ = getSuccessor(node, ind);
            node.keys.set(ind, succ);
            removeRec(node.childs.get(ind + 1), succ);
        } else {
            merge(node, ind);
            removeRec(node.childs.get(ind), cl);
        }
    }

    private E getPredecessor(BNode<E> node, int ind) { 
        BNode<E> current = node.childs.get(ind);
        while (current.childs.get(0) != null) current = current.childs.get(current.count);
        return current.keys.get(current.count - 1);
    }

    private E getSuccessor(BNode<E> node, int ind) { 
        BNode<E> current = node.childs.get(ind + 1);
        while (current.childs.get(0) != null) current = current.childs.get(0);
        return current.keys.get(0);
    }

    private void fill(BNode<E> node, int ind) { 
        int minKeys = (this.orden - 1) / 2;
        if (ind != 0 && node.childs.get(ind - 1).count >= minKeys + 1) borrowFromPrev(node, ind);
        else if (ind != node.count && node.childs.get(ind + 1).count >= minKeys + 1) borrowFromNext(node, ind);
        else {
            if (ind != node.count) merge(node, ind);
            else merge(node, ind - 1);
        }
    }

    private void borrowFromPrev(BNode<E> node, int ind) { 
        BNode<E> child = node.childs.get(ind);
        BNode<E> hermano = node.childs.get(ind - 1); 

        for (int i = child.count - 1; i >= 0; --i) child.keys.set(i + 1, child.keys.get(i));
        if (child.childs.get(0) != null) {
            for (int i = child.count; i >= 0; --i) child.childs.set(i + 1, child.childs.get(i));
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

    private void borrowFromNext(BNode<E> node, int ind) { 
        BNode<E> child = node.childs.get(ind);
        BNode<E> hermano = node.childs.get(ind + 1); 

        child.keys.set(child.count, node.keys.get(ind));
        if (child.childs.get(0) != null) child.childs.set(child.count + 1, hermano.childs.get(0));
        node.keys.set(ind, hermano.keys.get(0));

        for (int i = 1; i < hermano.count; ++i) hermano.keys.set(i - 1, hermano.keys.get(i));
        if (hermano.childs.get(0) != null) {
            for (int i = 1; i <= hermano.count; ++i) hermano.childs.set(i - 1, hermano.childs.get(i));
        }

        hermano.keys.set(hermano.count - 1, null);
        hermano.childs.set(hermano.count, null);
        child.count += 1;
        hermano.count -= 1;
    }

    private void merge(BNode<E> node, int ind) { 
        BNode<E> child = node.childs.get(ind);
        BNode<E> hermano = node.childs.get(ind + 1); 

        child.keys.set(child.count, node.keys.get(ind));
        for (int i = 0; i < hermano.count; ++i) child.keys.set(child.count + 1 + i, hermano.keys.get(i));
        if (child.childs.get(0) != null) {
            for (int i = 0; i <= hermano.count; ++i) child.childs.set(child.count + 1 + i, hermano.childs.get(i));
        }

        for (int i = ind + 1; i < node.count; ++i) node.keys.set(i - 1, node.keys.get(i));
        for (int i = ind + 2; i <= node.count; ++i) node.childs.set(i - 1, node.childs.get(i));

        node.keys.set(node.count - 1, null);
        node.childs.set(node.count, null);
        child.count += hermano.count + 1;
        node.count -= 1;
    }
}