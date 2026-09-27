public class LinkedList {

    private Node head;
    private int size;

    ///
    private long accesses;
    private long comparisons;
    ///

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public void add(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null) {
                ///
                accesses++;
                ///

                current = current.next;
            }

            current.next = newNode;
        }

        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(value);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                ///
                accesses++;
                ///

                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        int removedValue;

        if (index == 0) {
            removedValue = head.value;
            head = head.next;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                ///
                accesses++;
                ///

                current = current.next;
            }

            removedValue = current.next.value;
            current.next = current.next.next;
        }

        size--;
        return removedValue;
    }

    public int get(int index) {
        checkIndex(index);

        Node current = head;

        ///
        accesses++;
        ///

        for (int i = 0; i < index; i++) {
            current = current.next;

            ///
            accesses++;
            ///
        }

        return current.value;
    }

    public boolean contains(int value) {
        Node current = head;

        while (current != null) {

            ///
            accesses++;
            comparisons++;
            ///

            if (current.value == value) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int size() {
        return size;
    }

    ///
    public long getAccesses() {
        return accesses;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void resetMetrics() {
        accesses = 0;
        comparisons = 0;
    }
    ///
}