package lab05.ejercicio8;

import java.util.ArrayList;
import java.util.Random;

public class ColaReproduccion extends ListaDoble<Cancion> {
    
    private NodeDoble<Cancion> tempo; 
    
    public ColaReproduccion() {
        super();
        this.tempo = null;
    }
    
    @Override
    public void agregar(Cancion cancion) {
        super.agregar(cancion);
        
        // Si acabamos de agregar la primera canción, el reproductor debe apuntar a ella
        if (tempo == null) {
            tempo = first; 
        }
    }
    
    public Cancion reproducirSiguiente() {
        if (tempo != null && tempo.getNext() != null) {
            tempo = tempo.getNext();
            return tempo.getDato();
        }
        System.out.println("No hay una canción siguiente en la cola :(");
        return null;
    }
    
    public Cancion reproducirAnterior() {
        if (tempo != null && tempo.getPrev() != null) {
            tempo = tempo.getPrev();
            return tempo.getDato();
        }
        System.out.println("No hay una canción anterior en la cola :( ");
        return null;    
    }
    
    public void mezclar() {
        if (size <= 1) return;
        
        Random generador = new Random();
        ArrayList<NodeDoble<Cancion>> listaTemp = new ArrayList<>();
        NodeDoble<Cancion> actual = first;
        
        while(actual != null) {
            listaTemp.add(actual);
            actual = actual.getNext();
        }
        
        for(int i = listaTemp.size() - 1; i > 0; i--) {
            int j = generador.nextInt(i + 1);
            NodeDoble<Cancion> temp = listaTemp.get(i);
            listaTemp.set(i, listaTemp.get(j));
            listaTemp.set(j, temp);
        }
        
        first = listaTemp.get(0);
        first.setPrev(null);
        actual = first;
        
        for(int i = 1; i < listaTemp.size(); i++) {
            NodeDoble<Cancion> siguiente = listaTemp.get(i);
            actual.setNext(siguiente);
            siguiente.setPrev(actual);
            actual = siguiente;
        }
        
        actual.setNext(null);
        
        // ¡IMPORTANTE! Como desordenamos la lista, debemos actualizar el puntero 'last' heredado
        last = actual; 
        tempo = first; 
    }
    
    public void mostrarCola() {
        if (estaVacia()) { // Usamos el método heredado
            System.out.println("La cola está vacía");
            return;
        }
            
        int contador = 1;
        NodeDoble<Cancion> actual = first;
        System.out.println("\n=== COLA DE REPRODUCCIÓN (" + size + " canciones) ===");
        
        while(actual != null) {
            if (actual == tempo) {
                System.out.print(" ▶ [SONANDO] ");
            } else {
                System.out.print("   "); 
            }
            
            System.out.println(contador + ". " + actual.getDato().toString());
            actual = actual.getNext();
            contador++;
        }
        System.out.println("==============================================\n");
    }
    
    public int duracionTotal() {
        int duracionT = 0;
        NodeDoble<Cancion> actual = first;
        
        while(actual != null) {
            duracionT += actual.getDato().getDuracion();
            actual = actual.getNext();
        }
        return duracionT;
    }
}
