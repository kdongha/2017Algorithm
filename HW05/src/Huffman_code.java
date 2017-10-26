import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Huffman_code {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("HW05/data06_huffman.txt"));
        HashMap<Character, Integer> frequency_table = new HashMap<>();
        String inputLine = scanner.nextLine();
        Heap heap = new Heap();
        Node[] nodes;

        for (int i = 0; i < inputLine.length(); i++) {
            if (frequency_table.containsKey(inputLine.charAt(i))) {
                frequency_table.replace(inputLine.charAt(i), frequency_table.get(inputLine.charAt(i)) + 1);
            } else {
                frequency_table.put(inputLine.charAt(i), 1);
            }
        }

        nodes = new Node[frequency_table.size() + 1];
        for (Character Char : frequency_table.keySet()) {
            heap.insert(nodes, new Node(Char, frequency_table.get(Char)));
        }

        while (heap.num > 1) {
            int n = heap.num;
            for (int i = 1; i < n; i++) {
                Node z = new Node();
                z.leftNode = heap.extract(nodes);
                z.rightNode = heap.extract(nodes);
                z.value = z.leftNode.value + z.rightNode.value;
                heap.insert(nodes, z);
            }
        }

        huffman(nodes[1], "");

    }

    static private void huffman(Node node, String str) {
        if (node.Char != null) {
            System.out.println(node.Char + ", " + str);
        } else {
            if (node.leftNode != null) {
                huffman(node.leftNode, str + "0");
            }
            if (node.rightNode != null) {
                huffman(node.rightNode, str + "1");
            }
        }
    }


    static class Heap {
        int num = 0;

        private void insert(Node[] nodes, Node x) {
            nodes[++num] = x;
            build_heap(nodes);
        }

        private void heapfy(Node[] nodes, int index) {
            int left = index * 2;
            int right = left + 1;
            int smallest = index;
            if (left <= num && nodes[left].value < nodes[index].value) {
                smallest = left;
            }
            if (right <= num && nodes[smallest].value > nodes[right].value) {
                smallest = right;
            }
            if (smallest != index) {
                Node tempNode = nodes[index];
                nodes[index] = nodes[smallest];
                nodes[smallest] = tempNode;
                heapfy(nodes, smallest);
            }
        }

        private void build_heap(Node[] nodes) {
            for (int i = num / 2; i > 0; i--) {
                heapfy(nodes, i);
            }
        }

        private Node extract(Node[] nodes) {
            Node extractNode = nodes[1];
            nodes[1] = nodes[num--];
            build_heap(nodes);
            return extractNode;
        }

    }

    static class Node {
        Character Char;
        int value;
        Node leftNode = null;
        Node rightNode = null;

        private Node() {
            Character Char = null;
            int value = 0;
        }

        private Node(Character Char, int value) {
            this.Char = Char;
            this.value = value;
        }


    }
}
