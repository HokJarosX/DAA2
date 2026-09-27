import java.util.Random;

public class Benchmark {

    static int[] sizes = {100, 1000, 10000, 100000};

    public static void main(String[] args) {
        workload1();
        workload2();
        workload3();
        workload4();
    }


    // WORKLOAD 1 - RANDOM ACCESS
    static void workload1() {
        System.out.println("WORKLOAD 1");

        for (int n : sizes) {
            DynamicArray array = createArray(n);
            LinkedList list = createList(n);

            Random random = new Random(42);
            int[] indices = new int[10000];

            for (int i = 0; i < 10000; i++) {
                indices[i] = random.nextInt(n);
            }

            array.resetMetrics();
            list.resetMetrics();

            long arrayTime = 0;
            long listTime = 0;

            for (int run = 0; run < 5; run++) {

                long start = System.nanoTime();

                for (int index : indices)
                    array.get(index);

                arrayTime += System.nanoTime() - start;


                start = System.nanoTime();

                for (int index : indices)
                    list.get(index);

                listTime += System.nanoTime() - start;
            }

            System.out.println(
                    "n=" + n +
                            " Array: " + arrayTime / 5 +
                            " ns, accesses=" + array.getAccesses() / 5
            );

            System.out.println(
                    "n=" + n +
                            " List: " + listTime / 5 +
                            " ns, accesses=" + list.getAccesses() / 5
            );
        }
    }


    // WORKLOAD 2 - SEARCH
    static void workload2() {
        System.out.println("\nWORKLOAD 2");

        for (int n : sizes) {
            DynamicArray array = createArray(n);
            LinkedList list = createList(n);

            Random random = new Random(42);
            int[] searchValues = new int[1000];

            for (int i = 0; i < 1000; i++) {
                searchValues[i] = random.nextInt();
            }

            array.resetMetrics();
            list.resetMetrics();

            long arrayTime = 0;
            long listTime = 0;

            for (int run = 0; run < 5; run++) {

                long start = System.nanoTime();

                for (int value : searchValues)
                    array.contains(value);

                arrayTime += System.nanoTime() - start;


                start = System.nanoTime();

                for (int value : searchValues)
                    list.contains(value);

                listTime += System.nanoTime() - start;
            }

            System.out.println(
                    "n=" + n +
                            " Array: " + arrayTime / 5 +
                            " ns, comparisons=" + array.getComparisons() / 5
            );

            System.out.println(
                    "n=" + n +
                            " List: " + listTime / 5 +
                            " ns, comparisons=" + list.getComparisons() / 5
            );
        }
    }


    // WORKLOAD 3 - INSERTION AND REMOVAL
    static void workload3() {
        System.out.println("\nWORKLOAD 3");

        for (int n : sizes) {
            System.out.println("n=" + n);

            testInsert(n, 0, "beginning");
            testRemove(n, 0, "beginning");

            testInsert(n, n / 2, "middle");
            testRemove(n, n / 2, "middle");
        }
    }


    static void testInsert(int n, int index, String position) {

        long arrayTime = 0;
        long listTime = 0;

        long arrayMovements = 0;
        long listAccesses = 0;

        for (int run = 0; run < 5; run++) {

            DynamicArray array = createArray(n);
            LinkedList list = createList(n);

            array.resetMetrics();
            list.resetMetrics();

            long start = System.nanoTime();

            for (int i = 0; i < 1000; i++)
                array.add(index, i);

            arrayTime += System.nanoTime() - start;
            arrayMovements += array.getMovements();


            start = System.nanoTime();

            for (int i = 0; i < 1000; i++)
                list.add(index, i);

            listTime += System.nanoTime() - start;
            listAccesses += list.getAccesses();
        }

        System.out.println(
                "Array insert " + position +
                        ": " + arrayTime / 5 +
                        " ns, movements=" + arrayMovements / 5
        );

        System.out.println(
                "List insert " + position +
                        ": " + listTime / 5 +
                        " ns, accesses=" + listAccesses / 5
        );
    }


    static void testRemove(int n, int index, String position) {

        // n=100 cannot perform 1000 removals
        int operations = Math.min(1000, n);

        long arrayTime = 0;
        long listTime = 0;

        long arrayMovements = 0;
        long listAccesses = 0;

        for (int run = 0; run < 5; run++) {

            DynamicArray array = createArray(n);
            LinkedList list = createList(n);

            array.resetMetrics();
            list.resetMetrics();

            long start = System.nanoTime();

            for (int i = 0; i < operations; i++) {
                int currentIndex = Math.min(index, array.size() - 1);
                array.remove(currentIndex);
            }

            arrayTime += System.nanoTime() - start;
            arrayMovements += array.getMovements();


            start = System.nanoTime();

            for (int i = 0; i < operations; i++) {
                int currentIndex = Math.min(index, list.size() - 1);
                list.remove(currentIndex);
            }

            listTime += System.nanoTime() - start;
            listAccesses += list.getAccesses();
        }

        System.out.println(
                "Array remove " + position +
                        ": " + arrayTime / 5 +
                        " ns, movements=" + arrayMovements / 5
        );

        System.out.println(
                "List remove " + position +
                        ": " + listTime / 5 +
                        " ns, accesses=" + listAccesses / 5
        );
    }


    // WORKLOAD 4 - MIN HEAP
    static void workload4() {
        System.out.println("\nWORKLOAD 4");

        for (int n : sizes) {

            Random random = new Random(42);
            int[] values = new int[n];

            for (int i = 0; i < n; i++)
                values[i] = random.nextInt();

            long insertTime = 0;
            long extractTime = 0;

            long insertComparisons = 0;
            long extractComparisons = 0;

            boolean sorted = true;

            for (int run = 0; run < 5; run++) {

                MinHeap heap = new MinHeap();

                heap.resetMetrics();

                long start = System.nanoTime();

                for (int value : values)
                    heap.insert(value);

                insertTime += System.nanoTime() - start;
                insertComparisons += heap.getComparisons();


                heap.resetMetrics();

                start = System.nanoTime();

                int previous = heap.extractMin();

                while (!heap.isEmpty()) {
                    int current = heap.extractMin();

                    if (current < previous)
                        sorted = false;

                    previous = current;
                }

                extractTime += System.nanoTime() - start;
                extractComparisons += heap.getComparisons();
            }

            System.out.println(
                    "n=" + n +
                            " Insert: " + insertTime / 5 +
                            " ns, comparisons=" + insertComparisons / 5
            );

            System.out.println(
                    "n=" + n +
                            " Extract: " + extractTime / 5 +
                            " ns, comparisons=" + extractComparisons / 5 +
                            ", sorted=" + sorted
            );
        }
    }


    // CREATE STRUCTURES

    static DynamicArray createArray(int n) {
        DynamicArray array = new DynamicArray();

        for (int i = 0; i < n; i++)
            array.add(i);

        return array;
    }


    static LinkedList createList(int n) {
        LinkedList list = new LinkedList();

        for (int i = 0; i < n; i++)
            list.add(i);

        return list;
    }
}