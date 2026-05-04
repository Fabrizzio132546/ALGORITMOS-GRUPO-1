package Ejercicio3;

import java.util.List;

public class MultiColaPrioridad<E> {
    private QueueLink<E>[] colas; 
    private int niveles;
    public MultiColaPrioridad(int niveles) {
        this.niveles = niveles;

        colas = new QueueLink[niveles];
        for (int i = 0; i < niveles; i++) {
            colas[i] = new QueueLink<>();
        }
    }

    public void enqueue(E x, int prioridad) {
        if (prioridad >= 0 && prioridad < niveles) {
            colas[prioridad].enqueue(x); 
        } else {
            System.out.println("El nivel de prioridad no es valido");
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
    	
        for (int i = niveles - 1; i >= 0; i--) { 
            if (!colas[i].isEmpty()) {
                return colas[i].dequeue(); 
            }
        }
        throw new ExceptionIsEmpty("las colas de prioridad estan vacias");
    }
    
    public boolean isEmpty() {
        for (int i = 0; i < niveles; i++) {
            if (!colas[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public List<E> obtenerElementosDeCola(int prioridad) {
        return colas[prioridad].getElementosComoLista();
    }
}
