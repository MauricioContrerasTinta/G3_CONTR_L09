package graph;
import java.util.HashSet;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink(){
        listVertex = new ListLinked<Vertex<E>>();
    }

    public void insertVertex(E data) {
        Vertex<E> newVertex = new Vertex<>(data);
        if (listVertex.search(newVertex) == null) {
            listVertex.insertLast(newVertex);
        }

    }

    public void insertEdge(E verOri, E verDes){
        Vertex<E> vOri = listVertex.search(new Vertex<E>(verOri));
        Vertex<E> vDes = listVertex.search(new Vertex<E>(verDes));
        if (vOri != null && vDes != null) {
            if (vOri.listAdj.search(new Edge<E>(vDes)) == null)
                vOri.listAdj.insertLast(new Edge<E>(vDes));
            if (vDes.listAdj.search(new Edge<E>(vOri)) == null)
                vDes.listAdj.insertLast(new Edge<E>(vOri)); // porque es grafo no dirigido
        }
    }

    public boolean searchVertex(E data) {
        return listVertex.search(new Vertex<E>(data)) != null;
    }

    public boolean searchEdge(E verOri, E verDes) {
        Vertex<E> vOri = listVertex.search(new Vertex<E>(verOri));
        Vertex<E> vDes = listVertex.search(new Vertex<E>(verDes));
        if (vOri != null && vDes != null) {
            return vOri.listAdj.search(new Edge<E>(vDes)) != null;
        }
        return false;
    }

    public void removeVertex(E data) {
        Vertex<E> vRemove = listVertex.search(new Vertex<>(data));
        if (vRemove == null) return;
        ListLinked.Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            current.data.listAdj.remove(new Edge<>(vRemove));
            current = current.next;
        }
        listVertex.remove(vRemove);
    }

    public void removeEdge(E verOri, E verDes) {
        Vertex<E> vOri = listVertex.search(new Vertex<>(verOri));
        Vertex<E> vDes = listVertex.search(new Vertex<>(verDes));
        if (vOri != null && vDes != null) {
            vOri.listAdj.remove(new Edge<>(vDes));
            vDes.listAdj.remove(new Edge<>(vOri)); // por ser no dirigido
        }
    }

    public void dfs(E data) {
        HashSet<E> visited = new HashSet<>();
        Vertex<E> start = listVertex.search(new Vertex<>(data));
        if (start != null)
            dfsRecursive(start, visited);
    }

    private void dfsRecursive(Vertex<E> vertex, HashSet<E> visited) {
        if (visited.contains(vertex.getData()))
            return;

        System.out.println(vertex.getData());
        visited.add(vertex.getData());

        ListLinked.Node<Edge<E>> current = vertex.listAdj.getHead();
        while (current != null) {
            Vertex<E> neighbor = current.data.getRefDest();
            if (!visited.contains(neighbor.getData())) {
                dfsRecursive(neighbor, visited);
            }
            current = current.next;
        }
    }


    public String toString(){
        return this.listVertex.toString();
    }
}