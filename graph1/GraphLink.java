package graph1;
import listlinked.ListLinked;
import listlinked.Node;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GraphLink<E> {
    private ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    public boolean searchVertex(E data) {
        return listVertex.contains(new Vertex<>(data));
    }

    public boolean searchEdge(E v, E z) {
        Vertex<E> vertexV = getVertex(v);
        Vertex<E> vertexZ = getVertex(z);

        if (vertexV == null || vertexZ == null) return false;

        return vertexV.listAdj.contains(new Edge<>(vertexZ));
    }

    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.insertLast(new Vertex<>(data));
        }
    }

    public void insertEdge(E v, E z) {
        insertEdge(v, z, -1);
    }

    public void insertEdge(E v, E z, int weight) {
        Vertex<E> vertexV = getVertex(v);
        Vertex<E> vertexZ = getVertex(z);

        if (vertexV == null || vertexZ == null) return;

        Edge<E> edge1 = new Edge<>(vertexZ, weight);
        Edge<E> edge2 = new Edge<>(vertexV, weight);

        if (!vertexV.listAdj.contains(edge1)) {
            vertexV.listAdj.insertLast(edge1);
        }

        if (!vertexZ.listAdj.contains(edge2)) {
            vertexZ.listAdj.insertLast(edge2);
        }
    }

    public void removeEdge(E v, E z) {
        Vertex<E> vertexV = getVertex(v);
        Vertex<E> vertexZ = getVertex(z);

        if (vertexV == null || vertexZ == null) return;

        vertexV.listAdj.remove(new Edge<>(vertexZ));
        vertexZ.listAdj.remove(new Edge<>(vertexV));
    }

    public void removeVertex(E data) {
        Vertex<E> vertexToRemove = getVertex(data);
        if (vertexToRemove == null) return;
        // Eliminar todas las aristas que llegan a este vértice
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            Vertex<E> v = current.getData();
            if (!v.equals(vertexToRemove)) {
                v.listAdj.remove(new Edge<>(vertexToRemove));
            }
            current = current.getNext();
        }
        // Eliminar el vértice
        listVertex.remove(vertexToRemove);
    }

    public void dfs(E startData) {
        Vertex<E> startVertex = getVertex(startData);
        if (startVertex == null) return;

        Set<E> visited = new HashSet<>();
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(Vertex<E> current, Set<E> visited) {
        if (visited.contains(current.getData())) return;

        System.out.println(current.getData());
        visited.add(current.getData());

        Node<Edge<E>> adjNode = current.listAdj.getFirst();
        while (adjNode != null) {
            Vertex<E> neighbor = adjNode.getData().refDest;
            dfsRecursive(neighbor, visited);
            adjNode = adjNode.getNext();
        }
    }

    private Vertex<E> getVertex(E data) {
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public void bfs(E startData) {
    Vertex<E> startVertex = getVertex(startData);
    if (startVertex == null) return;

    Set<Vertex<E>> visited = new HashSet<>();
    Queue<Vertex<E>> queue = new LinkedList<>();

    queue.add(startVertex);
    visited.add(startVertex);

    while (!queue.isEmpty()) {
        Vertex<E> current = queue.poll();
        System.out.println(current.getData());

        Node<Edge<E>> adj = current.listAdj.getFirst();
        while (adj != null) {
            Vertex<E> neighbor = adj.getData().refDest;
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
            adj = adj.getNext();
        }
    }
}


    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            sb.append(current.getData().toString());
            current = current.getNext();
        }
        return sb.toString();
    }
}
