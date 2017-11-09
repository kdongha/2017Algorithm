public class Dijkstra_algorithm {
    public static void main(String[] args) {
        final int MAX = 2147483647;
        int[][] table = {
                {0, 10, 3, MAX, MAX},
                {MAX, 0, 1, 2, MAX},
                {MAX, 4, 0, 8, 2},
                {MAX, MAX, MAX, 0, 7},
                {MAX, MAX, MAX, 9, 0}
        };
        int[] distance = {0, MAX, MAX, MAX, MAX};
        int[] set = new int[5];
        int set_size = 0;
        Min_Heap minHeap = new Min_Heap();

        for (int i = 0; i < 5; i++) {
            minHeap.insert(new Node(i, distance[i]));
        }
        while (minHeap.heap_size != 0) {
            Node extract_node = minHeap.extract_min();
            set[set_size] = extract_node.get_index();
            distance[extract_node.get_index()] = extract_node.get_distance();
            System.out.println("==================================================");
            System.out.printf("S[%d] : d[%c] = %d\n", set_size, 'A'+set[set_size], distance[set[set_size++]]);
            System.out.println("--------------------------------------------------");
            for (int i = 1; i <= minHeap.heap_size; i++) {
                Node visit_node = minHeap.min_heap[i];
                System.out.printf("Q[%d] : d[%c] = %d", i, 'A'+visit_node.get_index(), visit_node.get_distance());
                boolean is_update = false;
                if (table[extract_node.get_index()][visit_node.get_index()] != MAX) {  //오버플로우 체크
                    if (distance[visit_node.get_index()] > distance[extract_node.get_index()] + table[extract_node.get_index()][visit_node.get_index()]) {
                        is_update = true;
                        distance[visit_node.get_index()] = distance[extract_node.get_index()] + table[extract_node.get_index()][visit_node.get_index()];
                        minHeap.update(i, distance[visit_node.get_index()]);
                    }
                    if (is_update) {
                        System.out.printf(" -> d[%c] = %d", 'A'+minHeap.min_heap[i].get_index(), minHeap.min_heap[i].get_distance());
                    }
                }
                System.out.println();
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
