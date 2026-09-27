public class LinkedList {

    private Node head;
    private int size;

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    // add(x)
    public void add(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
    }

    // add(index, x)
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
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    // remove(index)
    public int remove(int index) {
        checkIndex(index);

        int removedValue;

        if (index == 0) {
            removedValue = head.value;
            head = head.next;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            removedValue = current.next.value;
            current.next = current.next.next;
        }

        size--;
        return removedValue;
    }

    // get(index)
    public int get(int index) {
        checkIndex(index);

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    // contains(x)
    public boolean contains(int value) {
        Node current = head;

        while (current != null) {
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
}