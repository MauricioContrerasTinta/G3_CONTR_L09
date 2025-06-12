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
}
