import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 1, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 0}
        };
        int source = 1;
        BFS(graph, source);
    }

    static void BFS(int[][] G, int s) {
        Node[] nodes = new Node[G.length];
        for (int i = 0; i < G.length; i++) {
            if (i != s) {
                nodes[i] = new Node(Color.WHITE, Integer.MAX_VALUE);
            } else {
                nodes[i] = new Node(Color.GRAY, 0);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < G[u].length; v++) {
                if (G[u][v] == 1) {
                    if (nodes[v].getColor().equals(Color.WHITE)) {
                        nodes[v].setColor(Color.GRAY);
                        nodes[v].setDistance(nodes[u].getDistance()+1);
                        nodes[v].setParentNode(nodes[u]);
                        queue.add(v);
                    }
                }
            }
            nodes[u].setColor(Color.BLACK);
        }
        for(int i=0;i<nodes.length;i++){
            System.out.printf("%d : %d\n",i,nodes[i].getDistance());
        }
    }

    static class Node {
        private Color color;
        private int distance;
        private Node parentNode;

        public Node(Color color, int distance) {
            this.color = color;
            this.distance = distance;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }
    }
}
