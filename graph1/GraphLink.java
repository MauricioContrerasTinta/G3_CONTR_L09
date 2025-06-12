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
}
