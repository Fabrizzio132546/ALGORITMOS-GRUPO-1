package Ejercicio3;

class MultiColaPrioridad<E> {
	// arreglo que almacena las distintas colas
    private QueueLink<E>[] colas; 
    private int niveles;
    
    public MultiColaPrioridad(int niveles) {
        this.niveles = niveles;
        // arreglo con un tamaño en especifico
        colas = new QueueLink[niveles];
        // inicializamos cada posicion del arreglo conun anueva instancia de tipo queue link
        for (int i = 0; i < niveles; i++) {
            colas[i] = new QueueLink<>();
        }
    }

    public void enqueue(E x, int prioridad) {
    	// la prioridad debe estar dentro del rango
        if (prioridad >= 0 && prioridad < niveles) {
        	// el elemento se añade al final de la cola especifica
            colas[prioridad].enqueue(x); 
        } else {
            System.out.println("El nivel de prioridad no es valido");
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
    	// se recorre desde el nivel mas alto, hacia el mas bajo
        for (int i = niveles - 1; i >= 0; i--) { 
        	
        	//si la cola actual tiene elementos se extrae el primero disponible
            if (!colas[i].isEmpty()) {
                return colas[i].dequeue(); 
            }
        }
        throw new ExceptionIsEmpty("las colas de prioridad estan vacias");
    }
    
    public boolean isEmpty() {
    	
    	// se revisa cada una de las colas
        for (int i = 0; i < niveles; i++) {
            if (!colas[i].isEmpty()) {
            	// si almenos una cola tiene algo, no esta vacio
                return false;
            }
        }
        return true;
    }

    public java.util.List<E> obtenerElementosDeCola(int prioridad) {
    	// retorna los notos de la cola convertidos en una lista 
        return colas[prioridad].getElementosComoLista();
    }
}
