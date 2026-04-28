package practicapura.listas;
import java.util.ArrayList;
import java.util.Random;

public class ColaReproduccion<T extends Cancion> {
	private NodeDoble<T> first; // Inicio de la cola
	private NodeDoble<T> tempo; // Canción actual
	
	public ColaReproduccion() {
		this.first = null;
		this.tempo = first;
	}
	
	public void agregarCancion(T cancion) {
		NodeDoble<T> nuevoNodo = new NodeDoble<>(cancion);
		
		// Si la cola está vacía, es la primera canción
		if (first == null) {
			first = nuevoNodo;
			tempo = first;
		}
		// Si ya hay canciones, la agregamos al final
		else {
			NodeDoble<T> actual = first;
			while (actual != null) {
				if (actual.getNext() == null) {
					// Creamos el doble enlace
					actual.setNext(nuevoNodo);
					nuevoNodo.setPrev(actual);
					break; 
				}
			    actual = actual.getNext();
			}
		}
	}
	
	public T reproducirSiguiente() {
		// Verifica si existe una canción siguiente
		if (tempo != null && tempo.getNext() != null) {
			tempo = tempo.getNext(); // Avanza
			return tempo.getDato();
		}
		System.out.println("No hay una canción siguiente en la cola :(");
		return null;
	}
	
	public T reproducirAnterior() {
		// Verifica si existe una canción anterior
		if (tempo != null && tempo.getPrev() != null) {
			tempo = tempo.getPrev(); // Retrocede
			return tempo.getDato();
		}
		System.out.println("No hay una canción anterior en la cola :( ");
		return null;	
	}
	
	public void mezclar() {
		// Se ocupa más de 1 canción para mezclar
		if (first == null || first.getNext() == null)
			return;
        
        Random generador = new Random();
        ArrayList<NodeDoble<T>> listaTemp = new ArrayList<>();
        NodeDoble<T> actual = first;
        
        // 1. Pasamos los nodos a una lista temporal
        while(actual != null) {
            listaTemp.add(actual);
            actual = actual.getNext();
        }
        
        // 2. Los desordenamos al azar
        for(int i = listaTemp.size() - 1; i > 0; i--) {
            int j = generador.nextInt(i + 1);
            NodeDoble<T> temp = listaTemp.get(i);
            listaTemp.set(i, listaTemp.get(j));
            listaTemp.set(j, temp);
        }
        
        // 3. Reconstruimos los enlaces de la lista
        first = listaTemp.get(0);
        first.setPrev(null);
        actual = first;
        
        for(int i = 1; i < listaTemp.size(); i++) {
            NodeDoble<T> siguiente = listaTemp.get(i);
            actual.setNext(siguiente);
            siguiente.setPrev(actual);
            actual = siguiente;
        }
        
        // Cerramos la lista y reiniciamos
        actual.setNext(null);
        tempo = first; 
	}
	
	public void mostrarCola() {
		if (first == null) {
			System.out.println("La cola está vacía");
			return;
		}
			
		int contador = 1;
		NodeDoble<T> actual = first;
		System.out.println("\n=== COLA DE REPRODUCCIÓN ===");
		
        while(actual != null) {
        	// Marca visualmente la canción que está sonando
            if (actual == tempo) {
                System.out.print(" ▶ [SONANDO] ");
            } else {
                System.out.print("   "); 
            }
            
            System.out.println(contador + ". " + actual.getDato().toString());
            actual = actual.getNext();
            contador++;
        }
        System.out.println("============================\n");
	}
	
	public int duracionTotal() {
		int duracionT = 0;
		NodeDoble<T> actual = first;
		
		// Suma el tiempo de todas las canciones
		while(actual != null) {
			duracionT += actual.getDato().getDuracion();
			actual = actual.getNext();
		}
		return duracionT;
	}
}