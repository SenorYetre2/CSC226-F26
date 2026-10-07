package medical_action_tracking;

public class LinkedQueue<T> {
    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
        // TODO: Initialize an empty queue.
    }

    public void enqueue(T item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(item);
        if (isEmpty()) { 
            front = newNode;
            rear = newNode;
            size = 1;
        } else { 
            rear.next = newNode;
            rear = newNode;
            size++;
        }
        // TODO: Add a node at the rear. Update both references when the queue is empty.
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        } else
            if(front.next == null) {
                Node rememberTheFinal = front;
                front = null;
                rear = null;
                size--;
                return rememberTheFinal.data;
            } else { 
                Node rememberTheFront = front;
                Node soonToBeFront = front.next;
                front.next = null;
                front = soonToBeFront; 
                size--;
                return rememberTheFront.data;
        }

    }

    public T peekFront() {
        if (isEmpty()) { 
            return null;
        } else {
            return front.data;
        }
    }

    public boolean isEmpty() {
        if (front != null) { 
            return false;
        } else { 
            return true;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node current = front;

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

    private void appendNodesRecursively(Node current, StringBuilder result) {
        if (current == null) {
            return;
        } else {
            result.append(current.data);
            if (current.next != null) {
                result.append(", ");
            }
            appendNodesRecursively(current.next, result);
        }
        // TODO: Add the current node's data, then recursively visit current.next.
        // TODO: Stop at the null-node base case.
    }





    //EXTRA CREDIT CODE RIGHT HERE!!!!!
    public int recursiveCount() {
        return recursiveCount(front);
    }

    private int recursiveCount(Node fart) {
        if (fart == null) {
            return 0;
        } else {
            return 1 + recursiveCount(fart.next);
        }
    }
}
