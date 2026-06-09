package lab09.ejercicios.ejercicio1;

import java.util.ArrayList;

// --- CLASE BNODE ---
class BNode<E extends Comparable<E>> {
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

    public boolean searchNode(E cl, int[] pos) {
        pos[0] = 0;
        while (pos[0] < this.count && cl.compareTo(this.keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        if (pos[0] < this.count && cl.compareTo(this.keys.get(pos[0])) == 0) {
            return true;
        }
        return false;
    }
}

// --- CLASE BTREE ---
class BTree<E extends Comparable<E>> {
    protected BNode<E> root;  
    private int orden;      

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean search(E cl) {
        return search(this.root, cl);
    }

    private boolean search(BNode<E> current, E cl) {
        if (current == null) {
            return false;
        }
        
        int[] pos = new int[1];
        boolean encontrado = current.searchNode(cl, pos);
        
        if (encontrado) {
            System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + pos[0]); 
            return true;
        } else {
            return search(current.childs.get(pos[0]), cl);
        }
    }
}
