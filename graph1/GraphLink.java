package graph1;
import listlinked.ListLinked;
import listlinked.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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

    public ArrayList<E> bfsPath(E fromData, E toData) {
        Vertex<E> start = getVertex(fromData);
        Vertex<E> end = getVertex(toData);
        if (start == null || end == null) return null;

        Map<Vertex<E>, Vertex<E>> parent = new HashMap<>();
        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (current.equals(end)) break;

            Node<Edge<E>> adj = current.listAdj.getFirst();
            while (adj != null) {
                Vertex<E> neighbor = adj.getData().refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parent.put(neighbor, current);
                }
                adj = adj.getNext();
            }
        }

        ArrayList<E> path = new ArrayList<>();
        if (!parent.containsKey(end)) return path;

        Vertex<E> step = end;
        while (step != null) {
            path.add(0, step.getData());
            step = parent.get(step);
        }

        return path;
    }

    public void insertEdgeWeight(E v, E z, int weight) {
        insertEdge(v, z, weight); // reutiliza el método existente
    }

    public ArrayList<E> shortPath(E fromData, E toData) {
        Vertex<E> start = getVertex(fromData);
        Vertex<E> end = getVertex(toData);
        if (start == null || end == null) return null;
        Map<Vertex<E>, Integer> dist = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        Set<Vertex<E>> visited = new HashSet<>();
        PriorityQueue<Vertex<E>> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        // Inicialización
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            Vertex<E> v = current.getData();
            dist.put(v, v.equals(start) ? 0 : Integer.MAX_VALUE);
            prev.put(v, null);
            current = current.getNext();
        }
        pq.add(start);
        while (!pq.isEmpty()) {
            Vertex<E> u = pq.poll();
            if (visited.contains(u)) continue;
            visited.add(u);

            Node<Edge<E>> adj = u.listAdj.getFirst();
            while (adj != null) {
                Vertex<E> neighbor = adj.getData().refDest;
                int weight = adj.getData().weight;
                if (dist.get(u) + weight < dist.get(neighbor)) {
                    dist.put(neighbor, dist.get(u) + weight);
                    prev.put(neighbor, u);
                    pq.add(neighbor);
                }
                adj = adj.getNext();
            }
        }
        // Construir el camino
        ArrayList<E> path = new ArrayList<>();
        Vertex<E> step = end;
        if (dist.get(end) == Integer.MAX_VALUE) return path; // no hay camino

        while (step != null) {
            path.add(0, step.getData());
            step = prev.get(step);
        }
        return path;
    }

    public boolean isConexo() {
        if (listVertex.isEmpty()) return true;

        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        Vertex<E> start = listVertex.getFirst().getData();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();

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
        // Recorremos todos los vértices y verificamos si fueron visitados
        Node<Vertex<E>> node = listVertex.getFirst();
        while (node != null) {
            if (!visited.contains(node.getData())) return false;
            node = node.getNext();
        }
        return true;
    }

    public Stack<E> dijkstra(E fromData, E toData) {
        ArrayList<E> path = shortPath(fromData, toData);
        Stack<E> stack = new Stack<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            stack.push(path.get(i));
        }
        return stack;
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
