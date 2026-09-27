public class MinHeap {

    private int[] heap;
    private int size;

    public MinHeap() {
        heap = new int[10];
        size = 0;
    }

    // insert(x)
    public void insert(int value) {
        if (size == heap.length) {
            resize();
        }

        heap[size] = value;
        int index = size;
        size++;

        // Move element upward
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap[parent] <= heap[index]) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    // peekMin()
    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        return heap[0];
    }

    // extractMin()
    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];

        heap[0] = heap[size - 1];
        size--;

        int index = 0;

        // Move element downward
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }

        return min;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize() {
        int[] newHeap = new int[heap.length * 2];

        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}