package graph1;
import listlinked.ListLinked;
import listlinked.Node;
import java.util.HashSet;
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
}
