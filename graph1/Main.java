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

        // Mostrar grafo
        System.out.println("Grafo:\n" + graph);

        System.out.println("Existe vertice C? " + graph.searchVertex("C")); //true
        System.out.println("Existe arista A-B? " + graph.searchEdge("A", "B")); //true
        System.out.println("Existe arista B-C? " + graph.searchEdge("B", "C")); //false

        System.out.println("\nDFS desde A:");
        graph.dfs("A");

        System.out.println("\nEliminar arista A-C");
        graph.removeEdge("A", "C");
        System.out.println("Grafo:\n" + graph);

        System.out.println("Eliminando vertice D");
        graph.removeVertex("D");
        System.out.println("Grafo:\n" + graph);

        System.out.println(graph.representacionFormal());
        System.out.println(graph.representacionListaAdyacencia());
        System.out.println(graph.representacionMatrizAdyacencia());
    }
}
