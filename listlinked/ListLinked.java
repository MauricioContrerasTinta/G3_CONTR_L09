package listlinked;

public class ListLinked<T> {
    private Node<T> first;

    public ListLinked() {
        this.first = null;
    }

    public void insertLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
        } else {
            Node<T> current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public boolean contains(T data) {
        Node<T> current = first;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void remove(T data) {
        if (first == null) return;

        if (first.getData().equals(data)) {
            first = first.getNext();
            return;
        }

        Node<T> prev = first;
        Node<T> current = first.getNext();

        while (current != null) {
            if (current.getData().equals(data)) {
                prev.setNext(current.getNext());
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }

    public Node<T> search(T data) {
    Node<T> current = first;
    while (current != null) {
        if (current.getData().equals(data)) {
            return current;
        }
        current = current.getNext();
    }
    return null;
    }


    public Node<T> getFirst() {
        return first;
    }

    public boolean isEmpty() {
        return first == null;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = first;
        while (current != null) {
            sb.append(current.getData().toString()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }
}
