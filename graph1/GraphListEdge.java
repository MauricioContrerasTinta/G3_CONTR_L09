package graph1;

import java.util.*;

public class GraphListEdge<V,E> {
    ArrayList<VertexObj<V,E>> secVertex;
    ArrayList<EdgeObj<V,E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            secVertex.add(new VertexObj<>(v, secVertex.size()));
        }
    }

    public void insertEdge(V v, V z) {
        VertexObj<V,E> vert1 = getVertex(v);
        VertexObj<V,E> vert2 = getVertex(z);
        if (vert1 == null || vert2 == null) return;

        EdgeObj<V,E> edge = new EdgeObj<>(vert1, vert2, null, secEdge.size());
        if (!secEdge.contains(edge)) {
            secEdge.add(edge);
        }
    }

    public boolean searchVertex(V v) {
        return getVertex(v) != null;
    }

    public boolean searchEdge(V v, V z) {
        VertexObj<V,E> vert1 = getVertex(v);
        VertexObj<V,E> vert2 = getVertex(z);
        if (vert1 == null || vert2 == null) return false;
        return secEdge.contains(new EdgeObj<>(vert1, vert2, null, 0));
    }

    public void bfs(V start) {
        VertexObj<V,E> startVertex = getVertex(start);
        if (startVertex == null) return;

        Set<VertexObj<V,E>> visited = new HashSet<>();
        Queue<VertexObj<V,E>> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            VertexObj<V,E> current = queue.poll();
            System.out.println(current);

            for (EdgeObj<V,E> edge : secEdge) {
                VertexObj<V,E> neighbor = null;
                if (edge.getEndVertex1().equals(current) && !visited.contains(edge.getEndVertex2())) {
                    neighbor = edge.getEndVertex2();
                } else if (edge.getEndVertex2().equals(current) && !visited.contains(edge.getEndVertex1())) {
                    neighbor = edge.getEndVertex1();
                }
                if (neighbor != null) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    private VertexObj<V,E> getVertex(V v) {
        for (VertexObj<V,E> vert : secVertex) {
            if (vert.getInfo().equals(v)) {
                return vert;
            }
        }
        return null;
    }
}

