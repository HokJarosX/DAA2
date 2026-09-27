# Assignment 2 — Algorithmic Analysis

## Overview

This project implements three data structures in Java: Dynamic Array, Linked List, and Min-Heap. The purpose is to analyze their complexity, correctness, and performance.

## Complexity Analysis

### Dynamic Array

| Operation | Best | Average | Worst | Space |
|---|---|---|---|---|
| add(x) | Ω(1) | Θ(1) | O(n) | O(n) |
| add(index, x) | Ω(1) | Θ(n) | O(n) | O(n) |
| remove(index) | Ω(1) | Θ(n) | O(n) | Θ(1) |
| get(index) | Θ(1) | Θ(1) | Θ(1) | Θ(1) |
| contains(x) | Ω(1) | Θ(n) | O(n) | Θ(1) |

### Linked List

| Operation | Best | Average | Worst | Space |
|---|---|---|---|---|
| add(x) | Ω(1) | Θ(n) | O(n) | Θ(1) |
| add(index, x) | Ω(1) | Θ(n) | O(n) | Θ(1) |
| remove(index) | Ω(1) | Θ(n) | O(n) | Θ(1) |
| get(index) | Ω(1) | Θ(n) | O(n) | Θ(1) |
| contains(x) | Ω(1) | Θ(n) | O(n) | Θ(1) |

### Min-Heap

| Operation | Best | Average | Worst | Space |
|---|---|---|---|---|
| insert(x) | Ω(1) | O(log n) | O(log n) | Θ(1) |
| peekMin() | Θ(1) | Θ(1) | Θ(1) | Θ(1) |
| extractMin() | Ω(1) | O(log n) | O(log n) | Θ(1) |

## Correctness

### DynamicArray.add(index, value)

**Invariant:** Elements after the current position are correctly shifted one position right.

- **Initialization:** No elements are shifted before the loop.
- **Maintenance:** Each iteration shifts one element right.
- **Termination:** All required elements are shifted, so the new value can be inserted.

### MinHeap.insert(value)

**Invariant:** The heap property is valid except possibly between the inserted element and its parent.

- **Initialization:** The element is inserted at the end.
- **Maintenance:** If the parent is larger, they are swapped.
- **Termination:** The element reaches a valid position, restoring the heap property.

## Experimental Setup

- `n`: 100, 1,000, 10,000, 100,000
- 5 runs per experiment
- Timing: `System.nanoTime()`
- Random seed: `Random(42)`
- Random Access: 10,000 operations
- Search: 1,000 operations
- Insertion/Removal: 1,000 operations
- Min-Heap: `n` insertions and extractions

## Results

### Workload 1 — Random Access

#### Dynamic Array

| n | Time (ns) | Accesses |
|---:|---:|---:|
| 100 | 418340 | 10000 |
| 1000 | 81220 | 10000 |
| 10000 | 36480 | 10000 |
| 100000 | 8160 | 10000 |

#### Linked List

| n | Time (ns) | Accesses |
|---:|---:|---:|
| 100 | 1317340 | 511508 |
| 1000 | 6225860 | 5015208 |
| 10000 | 61370580 | 50139208 |
| 100000 | 621997760 | 502499208 |

### Workload 2 — Search

#### Dynamic Array

| n | Time (ns) | Comparisons |
|---:|---:|---:|
| 100 | 450580 | 100000 |
| 1000 | 1588920 | 1000000 |
| 10000 | 4796680 | 10000000 |
| 100000 | 48579860 | 100000000 |

#### Linked List

| n | Time (ns) | Comparisons |
|---:|---:|---:|
| 100 | 394060 | 100000 |
| 1000 | 1499660 | 1000000 |
| 10000 | 11988740 | 10000000 |
| 100000 | 139514140 | 100000000 |

### Workload 3 — Insertion and Removal

#### Dynamic Array — Insert Beginning

| n | Time (ns) | Movements |
|---:|---:|---:|
| 100 | 1550900 | 599500 |
| 1000 | 140740 | 1499500 |
| 10000 | 1075640 | 10499500 |
| 100000 | 8925000 | 100499500 |

#### Linked List — Insert Beginning

| n | Time (ns) | Accesses |
|---:|---:|---:|
| 100 | 45360 | 0 |
| 1000 | 33520 | 0 |
| 10000 | 42300 | 0 |
| 100000 | 37040 | 0 |

#### Dynamic Array — Remove Beginning

| n | Time (ns) | Movements |
|---:|---:|---:|
| 100 | 83600 | 4950 |
| 1000 | 579780 | 499500 |
| 10000 | 1006640 | 9499500 |
| 100000 | 9670360 | 99499500 |

#### Linked List — Remove Beginning

| n | Time (ns) | Accesses |
|---:|---:|---:|
| 100 | 19200 | 0 |
| 1000 | 69360 | 0 |
| 10000 | 68920 | 0 |
| 100000 | 77640 | 0 |

#### Dynamic Array — Insert Middle

| n | Time (ns) | Movements |
|---:|---:|---:|
| 100 | 85700 | 549500 |
| 1000 | 109500 | 999500 |
| 10000 | 423420 | 5499500 |
| 100000 | 4594340 | 50499500 |

#### Linked List — Insert Middle

| n | Time (ns) | Accesses |
|---:|---:|---:|
| 100 | 158960 | 49000 |
| 1000 | 661060 | 499000 |
| 10000 | 6120080 | 4999000 |
| 100000 | 61645740 | 49999000 |

#### Dynamic Array — Remove Middle

| n | Time (ns) | Movements |
|---:|---:|---:|
| 100 | 12080 | 1225 |
| 1000 | 83300 | 124750 |
| 10000 | 362760 | 4499500 |
| 100000 | 4694980 | 49499500 |

#### Linked List — Remove Middle

| n | Time (ns) | Accesses |
|---:|---:|---:|
| 100 | 16180 | 3626 |
| 1000 | 519960 | 373751 |
| 10000 | 6194280 | 4999000 |
| 100000 | 61220920 | 49999000 |

### Workload 4 — Priority Processing

#### Min-Heap Insert

| n | Time (ns) | Comparisons |
|---:|---:|---:|
| 100 | 19700 | 206 |
| 1000 | 74320 | 2326 |
| 10000 | 487260 | 22753 |
| 100000 | 1881620 | 227857 |

#### Min-Heap Extract

| n | Time (ns) | Comparisons |
|---:|---:|---:|
| 100 | 60740 | 863 |
| 1000 | 134880 | 14996 |
| 10000 | 1005640 | 216531 |
| 100000 | 9155780 | 2831426 |

All Min-Heap extraction tests returned `sorted=true`.

## Discussion

The results generally match the theoretical complexity. Dynamic Array provides constant-time random access, while Linked List access grows with `n`. Both structures use linear search. Linked List is efficient for operations at the beginning, while middle operations require traversal.

Execution time can differ from theoretical predictions because of JVM optimization, hardware, caching, and constant factors.

## Design Recommendations

- Dynamic Array is suitable for frequent random access.
- Linked List is useful for insertion and removal at the beginning.
- Min-Heap is suitable for priority-based processing.

## Conclusion

The experiments show that the best data structure depends on the workload. The measured results generally agree with the theoretical complexity of the implemented operations.