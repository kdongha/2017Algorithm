import java.util.*;

public class SequenceAlignmentLinear {
    static int GAP = 1;
    static int MISMATCH = 2;
    static List<Node> path=new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1;
        String str2;
        System.out.print("String 1 : ");
        str1 = scanner.nextLine();
        System.out.print("String 2 : ");
        str2 = scanner.nextLine();
        align(str1, str2,0,0);
        Collections.sort(path, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.x>o2.x){
                    return 1;
                }else if(o1.x==o2.x && o1.y>o2.y){
                        return 1;
                }
                return -1;
            }
        });

        for(int i=0;i<path.size();i++){
            System.out.printf("(%d, %d) ",path.get(i).x,path.get(i).y);
        }
        System.out.println();
    }

    private static void align(String str1, String str2,int xindent,int yindent) {
        int n = str1.length() + 1;
        int m = str2.length() + 1;

        if (n <= 3 || m <= 3) {
            int[][] dp_table = new int[n][m];
            for (int i = 0; i < m; i++) {
                dp_table[0][i] = i * GAP;
            }
            for (int i = 0; i < n; i++) {
                dp_table[i][0] = i * GAP;
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    dp_table[i][j] = getMin(dp_table[i - 1][j - 1] + mismatch(str1.charAt(i - 1), str2.charAt(j - 1)), getMin(dp_table[i - 1][j], dp_table[i][j - 1]) + 1);
                }
            }
            n--;
            m--;
            while (n > 0 || m > 0) {
                if (n == 0) {
                    path.add(new Node(n + xindent, m + yindent));
                    m--;
                } else if (m == 0) {
                    path.add(new Node(n + xindent, m + yindent));
                    n--;
                } else if (dp_table[n][m] > dp_table[n - 1][m]) {
                    path.add(new Node(n + xindent, m + yindent));
                    n--;
                } else if (dp_table[n][m] > dp_table[n][m - 1]) {
                    path.add(new Node(n + xindent, m + yindent));
                    m--;
                } else {
                    path.add(new Node(n + xindent, m + yindent));
                    n--;
                    m--;
                }
            }
        } else {
            int[] YPrefix = AllYPrefixCosts(str1, n / 2, str2);
            int[] YSuffix = AllYSuffixCosts(str1, n / 2, str2);
            int best = Integer.MAX_VALUE;
            int bestq = 0;
            for (int q = 0; q < m; q++) {
                int cost = YPrefix[q] + YSuffix[m - q - 1];
                if (cost < best) {
                    best = cost;
                    bestq = q;
                }
            }
            align(str1.substring(0, n / 2), str2.substring(0, bestq),xindent,yindent);
            align(str1.substring(n / 2), str2.substring(bestq),xindent+n/2,yindent+bestq);
        }
    }

    private static int[] AllYPrefixCosts(String str1, int mid, String str2) {
        String x = str1.substring(0, mid);
        String y = str2;
        int[] costs = new int[y.length() + 1];
        for (int i = 0; i <= y.length(); i++) {
            costs[i] = i * GAP;
        }
        for (int i = 1; i <= x.length(); i++) {
            int preCost = costs[0];
            costs[0] = i * GAP;
            for (int j = 1; j <= y.length(); j++) {
                int cost = getMin(costs[j - 1] + GAP, getMin(preCost + mismatch(x.charAt(i - 1), y.charAt(j - 1)), costs[j] + GAP));
                preCost = costs[j];
                costs[j] = cost;
            }
        }
        return costs;
    }

    private static int[] AllYSuffixCosts(String str1, int mid, String str2) {
        String x = reverseString(str1.substring(mid));
        String y = reverseString(str2);
        int[] costs = new int[y.length() + 1];
        for (int i = 0; i <= y.length(); i++) {
            costs[i] = i * GAP;
        }
        for (int i = 1; i <= x.length(); i++) {
            int preCost = costs[0];
            costs[0] = i * GAP;
            for (int j = 1; j <= y.length(); j++) {
                int cost = getMin(costs[j - 1] + GAP, getMin(preCost + mismatch(x.charAt(i - 1), y.charAt(j - 1)), costs[j] + GAP));
                preCost = costs[j];
                costs[j] = cost;
            }
        }
        return costs;
    }

    private static int mismatch(char char1, char char2) {
        return char1 == char2 ? 0 : MISMATCH;
    }

    private static int getMin(int x, int y) {
        return x < y ? x : y;
    }

    private static String reverseString(String str) {
        return new StringBuffer(str).reverse().toString();
    }


    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
