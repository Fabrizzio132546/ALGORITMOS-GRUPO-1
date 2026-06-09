package lab09.ejercicios.ejercicio4;

import java.util.ArrayList;

public class BNode<E> {
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