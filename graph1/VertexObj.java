package graph1;

public class VertexObj<V,E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    public V getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VertexObj) {
            VertexObj<V,E> other = (VertexObj<V,E>) obj;
            return this.info.equals(other.info);
        }
        return false;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
