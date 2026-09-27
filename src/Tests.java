public class Tests {

    public static void main(String[] args) {
        testDynamicArray();
        testLinkedList();
        testMinHeap();
        System.out.println("All tests completed successfully");
    }

    private static void testDynamicArray() {
        DynamicArray array = new DynamicArray();

        // Empty structure
        if (array.size() != 0)
            System.out.println("DynamicArray empty test failed");

        // One element
        array.add(10);
        if (array.get(0) != 10)
            System.out.println("DynamicArray one element test failed");

        // Multiple elements and duplicates
        array.add(20);
        array.add(20);
        if (!array.contains(20))
            System.out.println("DynamicArray contains test failed");

        // Boundary indices
        array.add(0, 5);
        if (array.get(0) != 5)
            System.out.println("DynamicArray boundary test failed");

        // Invalid index
        try {
            array.get(-1);
            System.out.println("DynamicArray invalid index test failed");
        } catch (IndexOutOfBoundsException ignored) {}

        // Large input
        for (int i = 0; i < 100_000; i++)
            array.add(i);
    }

    private static void testLinkedList() {
        LinkedList list = new LinkedList();

        // Empty structure
        if (list.size() != 0)
            System.out.println("LinkedList empty test failed");

        // One element
        list.add(10);
        if (list.get(0) != 10)
            System.out.println("LinkedList one element test failed");

        // Multiple elements and duplicates
        list.add(20);
        list.add(20);
        if (!list.contains(20))
            System.out.println("LinkedList contains test failed");

        // Boundary indices
        list.add(0, 5);
        if (list.get(0) != 5)
            System.out.println("LinkedList boundary test failed");

        // Invalid index
        try {
            list.get(-1);
            System.out.println("LinkedList invalid index test failed");
        } catch (IndexOutOfBoundsException ignored) {}

        // Large input
        for (int i = 0; i < 100_000; i++)
            list.add(i);
    }

    private static void testMinHeap() {
        MinHeap heap = new MinHeap();

        // Empty structure
        if (!heap.isEmpty())
            System.out.println("MinHeap empty test failed");

        // One and multiple elements, duplicates
        heap.insert(20);
        heap.insert(10);
        heap.insert(10);
        heap.insert(30);

        // Heap property / minimum
        if (heap.peekMin() != 10)
            System.out.println("MinHeap property test failed");

        // Non-decreasing extraction
        int previous = heap.extractMin();

        while (!heap.isEmpty()) {
            int current = heap.extractMin();

            if (current < previous)
                System.out.println("MinHeap extraction test failed");

            previous = current;
        }

        // Large input
        for (int i = 100_000; i >= 0; i--)
            heap.insert(i);
    }

}