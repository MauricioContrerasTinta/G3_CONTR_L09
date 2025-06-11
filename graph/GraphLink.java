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



    public String toString(){
        return this.listVertex.toString();
    }
}