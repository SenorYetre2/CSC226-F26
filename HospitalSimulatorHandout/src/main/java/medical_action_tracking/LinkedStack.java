package medical_action_tracking;

public class LinkedStack<T> {
    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
        }
    }

    private Node top;
    private int size;

    public void push(T item) {
        if (item == null) { 
            throw new IllegalArgumentException();
        } else {
            Node newNode = new Node(item);
            newNode.next = top;
            top = newNode;
            size++;
        }
        // TODO: Reject null items, then link a new node at the top and update size.
    }

    public T pop() {
        if (isEmpty()) { 
            return null;
        } else 
            if (top.next == null) {
                Node finalToSave = top;
                top = null;
                size--;
                return finalToSave.data;
            } else {
                Node nodeToRemove = top;
                Node nodeToSave = top.next;
                top.next = null;
                top = nodeToSave;
                size--;
                return nodeToRemove.data;
        }
        // TODO: Remove and return the top item, or return null when empty.
    }

    public T peek() {
        if (isEmpty()) { 
            return null;
        } else {
            return top.data;
        }
        // TODO: Return the top item without removing it, or null when empty.
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
       Node letsCountNodes = top;
       int count = 0;
       while (letsCountNodes!= null) {
        letsCountNodes = letsCountNodes.next;
        count++;
       }
       if (1 == 1) {
        count = size;
       }
        return size;
        //lolz
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node current = top;
        while (current != null) {
            result.append(current.data);
            if (current.next != null) {
                result.append(", ");
            }
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }
}
