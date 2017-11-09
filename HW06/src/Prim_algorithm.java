public class Prim_algorithm {
    public static void main(String[] args) {
        final int MAX = Integer.MAX_VALUE;
        int[][] table={
                // a      b       c       d       e       f       g       h       i
           /*a*/{0,     4,      MAX,    MAX,    MAX,    MAX,    MAX,    8,      MAX},
           /*b*/{4,     0,      8,      MAX,    MAX,    MAX,    MAX,    MAX,    MAX},
           /*c*/{MAX,   8,      0,      7,      MAX,    4,      MAX,    MAX,    2},
           /*d*/{MAX,   MAX,    7,      0,      9,      14,     MAX,    MAX,    MAX},
           /*e*/{MAX,   MAX,    MAX,    9,      0,      10,     MAX,    MAX,    MAX},
           /*f*/{MAX,   MAX,    4,      14,     10,     0,      2,      MAX,    MAX},
           /*g*/{MAX,   MAX,    MAX,    MAX,    MAX,    2,      0,      1,      6},
           /*h*/{8,     MAX,    MAX,    MAX,    MAX,    MAX,    1,      0,      7},
           /*i*/{MAX,   MAX,    2,      MAX,    MAX,    MAX,    6,      7,      0}
        };

        int[] distance = {0, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX};
        int[] parent = new int[9];
        int set_size = 0;
        Min_Heap minHeap = new Min_Heap();
        parent[0] = -1;

        for (int i = 0; i < 9; i++) {
            minHeap.insert(new Node(i, distance[i]));
        }
        while (minHeap.heap_size != 0) {
            Node extract_node = minHeap.extract_min();
            System.out.printf("w<%c, %c> = %d\n", parent[extract_node.get_index()] >= 0 ? 'a' + parent[extract_node.get_index()] : ' ', 'a' + extract_node.get_index(), distance[extract_node.get_index()]);
            for (int i = 1; i <= minHeap.heap_size; i++) {
                if (minHeap.min_heap[i].get_distance() > table[extract_node.get_index()][minHeap.min_heap[i].get_index()]) {
                    minHeap.update(i, table[extract_node.get_index()][minHeap.min_heap[i].get_index()]);
                    distance[minHeap.min_heap[i].get_index()] = table[extract_node.get_index()][minHeap.min_heap[i].get_index()];
                    parent[minHeap.min_heap[i].get_index()] = extract_node.get_index();
                }
            }
            minHeap.build_min_heap();
        }
    }

    private static class Min_Heap {
        private final int MAX_SIZE = 1000;
        private Node[] min_heap = new Node[MAX_SIZE];
        private int heap_size = 0;

        private void build_min_heap() {
            for (int i = heap_size / 2; i > 0; i--) {
                heapify(i);
            }
        }

        private void heapify(int index) {
            int left = 2 * index;
            int right = left + 1;
            int smallest = index;

            if (left <= heap_size && min_heap[left].get_distance() < min_heap[smallest].get_distance()) {
                smallest = left;
            }
            if (right <= heap_size && min_heap[right].get_distance() < min_heap[smallest].get_distance()) {
                smallest = right;
            }
            if (smallest != index) {
                Node temp = min_heap[smallest];
                min_heap[smallest] = min_heap[index];
                min_heap[index] = temp;
                heapify(smallest);
            }
        }

        private Node extract_min() {
            Node extract_node = min_heap[1];
            min_heap[1] = min_heap[heap_size--];
            heapify(1);
            return extract_node;
        }

        private void insert(Node node) {
            min_heap[++heap_size] = node;
            build_min_heap();
        }

        private void update(int index, int distance) {
            min_heap[index].set_distance(distance);
        }
    }

    private static class Node {
        private int index;
        private int distance;

        private Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int get_index() {
            return this.index;
        }

        public int get_distance() {
            return this.distance;
        }

        public void set_distance(int distance) {
            this.distance = distance;
        }
    }
}
