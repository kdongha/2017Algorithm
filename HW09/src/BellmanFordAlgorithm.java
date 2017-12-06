import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class BellmanFordAlgorithm {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("HW09/data10.txt"));
        final int MAX = Integer.MAX_VALUE;
        int nodeNum = scanner.nextInt();
        int startNode = scanner.nextInt();
        int endNode = scanner.nextInt();
        int edgeNum = scanner.nextInt();
        Edge[] edge = new Edge[edgeNum];
        boolean negative=false;


        for (int i = 0; i < edgeNum; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            edge[i] = new Edge(from, to, cost);
        }

        int[] distance = new int[nodeNum];
        Arrays.fill(distance, MAX);
        distance[startNode] = 0;

        for (int i = 0; i <= nodeNum; i++) {
            for (int j = 0; j < edgeNum; j++) {
                int from = edge[j].from;
                int to = edge[j].to;
                int cost = edge[j].cost;

                if (distance[from] != MAX && distance[to] > distance[from] + cost) {
                    distance[to] = distance[from] + cost;
                    if(i==nodeNum){
                        negative=true;
                    }
                }
            }
        }
        if(negative){
            System.out.println("negative");
        }else if(distance[endNode]==MAX){
            System.out.println("연결 X");
        }else{
            System.out.println(distance[endNode]);
        }
    }

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
