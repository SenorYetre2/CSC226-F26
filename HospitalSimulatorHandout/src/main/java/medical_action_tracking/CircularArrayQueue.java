package medical_action_tracking;

public class CircularArrayQueue<T> {

    private T[] items;
    private int front;
    private int rear;
    private int size;

    public CircularArrayQueue(int capacity) {
        items = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(T item) {
        if (isFull()) {
            return;
        }
        if (item == null) {
            throw new IllegalArgumentException();
        }
        items[rear] = item;
        rear = (rear + 1) % items.length;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T saveItem = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size--;
        return saveItem;
    }

    public T peekFront() {
        if (isEmpty()) {
            return null;
        }
        return items[front];
    }

    public boolean isEmpty() {
        boolean testEmpty = true;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                testEmpty = false;
            }
        }
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (size == items.length) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
    
        for (int i = 0; i < size; i++) {
            int index = (front + i) % items.length;
            result.append(items[index]);

            if (i < size - 1) {
                result.append(", ");
            }
        }

        result.append("]");
        return result.toString();
    }
}