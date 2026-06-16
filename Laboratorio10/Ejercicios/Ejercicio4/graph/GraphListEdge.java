package graph;

import java.util.Arrays;
import listlinked.ListLinked;
import listlinked.Node;
import queuelink.QueueLink;

// clase base para los grafos trabajados con lista de vertices y lista de aristas
// esta clase es abstracta porque aqui va la logica general del grafo
// las clases hijas indicaran si el grafo sera dirigido o no dirigido
public abstract class GraphListEdge<E extends Comparable<E>>
        implements Graph<E, EdgeList<E>> {

    // aqui se guardan todos los vertices del grafo
    // cada elemento es un objeto Vertex
    protected ListLinked<Vertex<E>> vertices;

    // aqui se guardan todas las aristas del grafo
    // cada arista guarda origen destino y peso
    protected ListLinked<EdgeList<E>> edges;

    // constructor del grafo
    // se inicializan las dos listas principales
    public GraphListEdge() {
        vertices = new ListLinked<>();
        edges = new ListLinked<>();
    }

    // este metodo lo implementan las clases hijas
    // sirve para saber si el grafo trata sus aristas con direccion
    protected abstract boolean isDirected();

    // este metodo crea otro grafo del mismo tipo
    // se usa cuando se necesita construir el complemento
    protected abstract GraphListEdge<E> createSameType();

    // inserta un nuevo vertice en el grafo
    // no se inserta si el dato es nulo
    // tampoco se inserta si ya existe
    @Override
    public void insertVertex(E data) {
        if (data == null || searchVertex(data)) {
            return;
        }

        vertices.addLast(new Vertex<>(data));
    }

    // inserta una arista sin indicar peso
    // cuando no se manda peso se usa 1 por defecto
    @Override
    public void insertEdge(E origin, E destination) {
        insertEdge(origin, destination, 1);
    }

    // inserta una arista entre dos vertices existentes
    // tambien permite guardar el peso de la arista
    public void insertEdge(E origin, E destination, int weight) {
        if (origin == null || destination == null) {
            return;
        }

        // se evita unir un vertice consigo mismo
        // asi el grafo se mantiene como grafo simple
        if (origin.compareTo(destination) == 0) {
            return;
        }

        // se buscan los objetos Vertex correspondientes
        Vertex<E> vOrigin = findVertex(origin);
        Vertex<E> vDestination = findVertex(destination);

        // si alguno de los vertices no existe no se puede crear la arista
        if (vOrigin == null || vDestination == null) {
            return;
        }

        // se evita repetir la misma arista
        // en no dirigido tambien se considera repetida si esta al reves
        if (searchEdge(origin, destination)) {
            return;
        }

        // se agrega la arista a la lista general de aristas
        edges.addLast(new EdgeList<>(vOrigin, vDestination, weight));
    }

    // elimina un vertice del grafo
    // tambien elimina todas las aristas donde ese vertice aparece
    @Override
    public boolean removeVertex(E data) {
        Vertex<E> vertex = findVertex(data);

        // si el vertice no existe no hay nada que eliminar
        if (vertex == null) {
            return false;
        }

        boolean removed;

        // se recorre la lista de aristas varias veces
        // esto permite eliminar todas las aristas incidentes sin saltarse ninguna
        do {
            removed = false;
            Node<EdgeList<E>> actual = edges.getFirst();

            while (actual != null && !removed) {
                EdgeList<E> edge = actual.getData();

                // una arista es incidente si el vertice aparece como origen o destino
                boolean incident =
                        edge.getOrigin().getData().compareTo(data) == 0 ||
                        edge.getDestination().getData().compareTo(data) == 0;

                if (incident) {
                    edges.removeNode(edge);
                    removed = true;
                } else {
                    actual = actual.getNext();
                }
            }

        } while (removed);

        // cuando ya no quedan aristas relacionadas se elimina el vertice
        return vertices.removeNode(vertex);
    }

    // elimina la arista que une dos vertices
    @Override
    public boolean removeEdge(E origin, E destination) {
        Vertex<E> vOrigin = findVertex(origin);
        Vertex<E> vDestination = findVertex(destination);

        // si alguno de los vertices no existe no se puede eliminar la arista
        if (vOrigin == null || vDestination == null) {
            return false;
        }

        // primero se intenta eliminar la arista en el sentido enviado
        boolean removed = edges.removeNode(new EdgeList<>(vOrigin, vDestination));

        // si es no dirigido tambien se intenta eliminar en el sentido contrario
        // esto sirve porque en un grafo no dirigido A B es igual que B A
        if (!removed && !isDirected()) {
            removed = edges.removeNode(new EdgeList<>(vDestination, vOrigin));
        }

        return removed;
    }

    // busca si un vertice existe dentro del grafo
    @Override
    public boolean searchVertex(E data) {
        return findVertex(data) != null;
    }

    // busca si existe una arista entre dos vertices
    @Override
    public boolean searchEdge(E origin, E destination) {
        if (origin == null || destination == null) {
            return false;
        }

        Node<EdgeList<E>> actual = edges.getFirst();

        // se recorre toda la lista de aristas
        while (actual != null) {
            EdgeList<E> edge = actual.getData();

            // compara la arista en el mismo sentido
            boolean direct =
                    edge.getOrigin().getData().compareTo(origin) == 0 &&
                    edge.getDestination().getData().compareTo(destination) == 0;

            // en grafo no dirigido tambien se compara en sentido inverso
            boolean inverse =
                    !isDirected() &&
                    edge.getOrigin().getData().compareTo(destination) == 0 &&
                    edge.getDestination().getData().compareTo(origin) == 0;

            if (direct || inverse) {
                return true;
            }

            actual = actual.getNext();
        }

        return false;
    }

    // devuelve los vertices que estan conectados con el vertice recibido
    @Override
    public ListLinked<E> adjacentVertices(E data) {
        ListLinked<E> adjacent = new ListLinked<>();

        Node<EdgeList<E>> actual = edges.getFirst();

        // se revisan todas las aristas para encontrar vecinos
        while (actual != null) {
            EdgeList<E> edge = actual.getData();

            E origin = edge.getOrigin().getData();
            E destination = edge.getDestination().getData();

            // si el vertice es origen entonces su vecino es el destino
            if (origin.compareTo(data) == 0) {
                adjacent.addLast(destination);

            // si el grafo no es dirigido tambien se toma el caso contrario
            } else if (!isDirected() && destination.compareTo(data) == 0) {
                adjacent.addLast(origin);
            }

            actual = actual.getNext();
        }

        return adjacent;
    }

    // busca un vertice en la lista de vertices
    // devuelve el objeto Vertex completo
    protected Vertex<E> findVertex(E data) {
        if (data == null) {
            return null;
        }

        Node<Vertex<E>> actual = vertices.getFirst();

        while (actual != null) {
            if (actual.getData().getData().compareTo(data) == 0) {
                return actual.getData();
            }

            actual = actual.getNext();
        }

        return null;
    }

    // devuelve cuantos vertices tiene el grafo
    public int vertexCount() {
        return vertices.size();
    }

    // devuelve cuantas aristas tiene el grafo
    public int edgeCount() {
        return edges.size();
    }

    // verifica si el grafo es conexo
    // en no dirigido basta con llegar a todos desde un vertice
    // en dirigido se revisa ida y vuelta para asegurar conexion fuerte
    public boolean isConexo() {
        if (vertices.isEmpty()) {
            return true;
        }

        // se toma el primer vertice como punto de inicio
        E start = vertices.getFirst().getData().getData();

        // primer recorrido siguiendo las aristas normales
        int normal = reachableCount(start, false);

        // si no se alcanza a todos entonces no es conexo
        if (normal != vertexCount()) {
            return false;
        }

        // si es dirigido tambien se hace un recorrido inverso
        // esto confirma que tambien se puede regresar desde los demas vertices
        if (isDirected()) {
            int reverse = reachableCount(start, true);
            return reverse == vertexCount();
        }

        return true;
    }

    // cuenta cuantos vertices se pueden alcanzar desde un vertice inicial
    // el parametro reverse indica si se recorren las aristas al reves
    private int reachableCount(E start, boolean reverse) {
        ListLinked<E> visited = new ListLinked<>();
        QueueLink<E> queue = new QueueLink<>();

        // se marca el vertice inicial como visitado
        visited.addLast(start);
        queue.enqueue(start);

        int count = 0;

        // recorrido en anchura usando cola
        while (!queue.isEmpty()) {
            E current = queue.dequeue();
            count++;

            Node<EdgeList<E>> actual = edges.getFirst();

            // se revisan todas las aristas para buscar vecinos del actual
            while (actual != null) {
                EdgeList<E> edge = actual.getData();

                E origin = edge.getOrigin().getData();
                E destination = edge.getDestination().getData();

                E neighbor = null;

                if (isDirected()) {
                    // recorrido normal en grafo dirigido
                    if (!reverse && origin.compareTo(current) == 0) {
                        neighbor = destination;

                    // recorrido inverso en grafo dirigido
                    } else if (reverse && destination.compareTo(current) == 0) {
                        neighbor = origin;
                    }

                } else {
                    // en grafo no dirigido se puede avanzar en ambos sentidos
                    if (origin.compareTo(current) == 0) {
                        neighbor = destination;
                    } else if (destination.compareTo(current) == 0) {
                        neighbor = origin;
                    }
                }

                // si se encontro un vecino nuevo se agrega a la cola
                if (neighbor != null && !visited.search(neighbor)) {
                    visited.addLast(neighbor);
                    queue.enqueue(neighbor);
                }

                actual = actual.getNext();
            }
        }

        return count;
    }

    //  para usarlo de forma mas clara en las pruebas
    public boolean isIsomorfo(GraphListEdge<E> other) {
        return isIsomorphic(other);
    }

    // verifica si dos grafos tienen la misma estructura
    // los nombres de los vertices no importan
    // importa como estan conectados
    public boolean isIsomorphic(GraphListEdge<E> other) {
        if (other == null) {
            return false;
        }

        // no se compara un grafo dirigido con uno no dirigido
        if (this.isDirected() != other.isDirected()) {
            return false;
        }

        // para ser isomorfos deben tener la misma cantidad de vertices
        if (this.vertexCount() != other.vertexCount()) {
            return false;
        }

        // tambien deben tener la misma cantidad de aristas
        if (this.edgeCount() != other.edgeCount()) {
            return false;
        }

        // se crean matrices para comparar conexiones
        boolean[][] a = this.adjacencyMatrix();
        boolean[][] b = other.adjacencyMatrix();

        int n = vertexCount();

        // mapping guarda que vertice de este grafo corresponde al otro grafo
        int[] mapping = new int[n];

        // used marca que vertices del otro grafo ya fueron usados
        boolean[] used = new boolean[n];

        // al inicio no hay correspondencias asignadas
        Arrays.fill(mapping, -1);

        return backtrackIsomorphism(0, mapping, used, a, b);
    }

    // prueba combinaciones de vertices hasta encontrar una correspondencia valida
    private boolean backtrackIsomorphism(
            int position,
            int[] mapping,
            boolean[] used,
            boolean[][] a,
            boolean[][] b) {

        int n = a.length;

        // si ya se asignaron todos los vertices la forma coincide
        if (position == n) {
            return true;
        }

        // se prueba cada vertice del segundo grafo como posible pareja
        for (int candidate = 0; candidate < n; candidate++) {
            if (!used[candidate]
                    && sameDegree(position, candidate, a, b)
                    && compatible(position, candidate, mapping, a, b)) {

                // se guarda la correspondencia temporal
                mapping[position] = candidate;
                used[candidate] = true;

                // se continua con el siguiente vertice
                if (backtrackIsomorphism(position + 1, mapping, used, a, b)) {
                    return true;
                }

                // si no funciono se deshace la eleccion
                mapping[position] = -1;
                used[candidate] = false;
            }
        }

        return false;
    }

    // compara los grados de dos vertices
    // esto ayuda a descartar combinaciones que no pueden funcionar
    private boolean sameDegree(int vertexA, int vertexB, boolean[][] a, boolean[][] b) {
        int outA = 0;
        int outB = 0;
        int inA = 0;
        int inB = 0;

        // se cuentan conexiones de salida y entrada
        for (int i = 0; i < a.length; i++) {
            if (a[vertexA][i]) {
                outA++;
            }

            if (b[vertexB][i]) {
                outB++;
            }

            if (a[i][vertexA]) {
                inA++;
            }

            if (b[i][vertexB]) {
                inB++;
            }
        }

        // en grafo dirigido se comparan entradas y salidas
        if (isDirected()) {
            return outA == outB && inA == inB;
        }

        // en no dirigido la matriz es simetrica y el grado sale de out
        return outA == outB;
    }

    // revisa si la nueva pareja de vertices respeta las conexiones ya asignadas
    private boolean compatible(
            int position,
            int candidate,
            int[] mapping,
            boolean[][] a,
            boolean[][] b) {

        // solo se compara contra vertices que ya tienen pareja asignada
        for (int i = 0; i < position; i++) {
            int mapped = mapping[i];

            if (mapped != -1) {
                // verifica conexion desde position hacia i
                if (a[position][i] != b[candidate][mapped]) {
                    return false;
                }

                // verifica conexion desde i hacia position
                if (a[i][position] != b[mapped][candidate]) {
                    return false;
                }
            }
        }

        return true;
    }

    // construye la matriz de adyacencia
    // true significa que existe una arista entre dos posiciones
    private boolean[][] adjacencyMatrix() {
        int n = vertexCount();
        boolean[][] matrix = new boolean[n][n];

        Node<EdgeList<E>> actual = edges.getFirst();

        // se recorren todas las aristas guardadas
        while (actual != null) {
            EdgeList<E> edge = actual.getData();

            int i = indexOfVertex(edge.getOrigin().getData());
            int j = indexOfVertex(edge.getDestination().getData());

            if (i != -1 && j != -1) {
                // se marca la conexion normal
                matrix[i][j] = true;

                // en no dirigido tambien se marca la conexion inversa
                if (!isDirected()) {
                    matrix[j][i] = true;
                }
            }

            actual = actual.getNext();
        }

        return matrix;
    }

    // obtiene la posicion de un vertice dentro de la lista
    // se usa para poder ubicarlo dentro de la matriz
    private int indexOfVertex(E data) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getData().compareTo(data) == 0) {
                return i;
            }
        }

        return -1;
    }

    // construye el complemento del grafo
    // el complemento tiene las aristas que el grafo original no tiene
    public GraphListEdge<E> getComplement() {
        GraphListEdge<E> complement = createSameType();

        // se copian primero todos los vertices
        // el complemento conserva los mismos vertices del grafo original
        for (int i = 0; i < vertices.size(); i++) {
            complement.insertVertex(vertices.get(i).getData());
        }

        // se revisan todos los pares posibles de vertices
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (i == j) {
                    continue;
                }

                // en no dirigido no hace falta revisar pares repetidos
                // por eso solo se trabaja una mitad de la matriz
                if (!isDirected() && j <= i) {
                    continue;
                }

                E origin = vertices.get(i).getData();
                E destination = vertices.get(j).getData();

                // si la arista no existe en el original se agrega al complemento
                if (!searchEdge(origin, destination)) {
                    complement.insertEdge(origin, destination);
                }
            }
        }

        return complement;
    }

    // nombre en español para usarlo en la prueba
    public boolean isAutoComplementario() {
        return isAutoComplementary();
    }

    // verifica si el grafo es autocomplementario
    // para eso compara el grafo original con su complemento
    public boolean isAutoComplementary() {
        int n = vertexCount();

        int totalPossibleEdges;

        // cantidad maxima de aristas en grafo dirigido simple
        if (isDirected()) {
            totalPossibleEdges = n * (n - 1);

        // cantidad maxima de aristas en grafo no dirigido simple
        } else {
            totalPossibleEdges = n * (n - 1) / 2;
        }

        // si no tiene la mitad de las aristas posibles no puede ser autocomplementario
        if (edgeCount() * 2 != totalPossibleEdges) {
            return false;
        }

        GraphListEdge<E> complement = getComplement();

        // se verifica si el complemento tiene la misma forma que el grafo original
        return this.isIsomorphic(complement);
    }

    // devuelve una representacion en texto del grafo
    @Override
    public String toString() {
        String result = "";

        result += "Vertices: ";

        Node<Vertex<E>> vertexNode = vertices.getFirst();

        // se muestran todos los vertices
        while (vertexNode != null) {
            result += vertexNode.getData() + " ";
            vertexNode = vertexNode.getNext();
        }

        result += "\nAristas:\n";

        Node<EdgeList<E>> edgeNode = edges.getFirst();

        // se muestran todas las aristas con su peso
        while (edgeNode != null) {
            EdgeList<E> edge = edgeNode.getData();

            // se usa una flecha distinta segun el tipo de grafo
            String connector = isDirected() ? " -> " : " -- ";

            result += edge.getOrigin() + connector
                    + edge.getDestination()
                    + " (" + edge.getWeight() + ")\n";

            edgeNode = edgeNode.getNext();
        }

        return result;
    }
}
