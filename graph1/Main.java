package graph1;

public class Main {
    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>();
        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        // Insertar aristas
        graph.insertEdge("A", "B", 2);
        graph.insertEdge("A", "C", 3);
        graph.insertEdge("B", "D", 4);
        graph.insertEdge("C", "D");
    }
}
