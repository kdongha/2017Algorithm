import java.util.Scanner;

public class Knapsack_problem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, weight;
        n = scanner.nextInt();
        weight = scanner.nextInt();
        int[][] value_table = new int[n][2];
        for (int i = 0; i < n; i++) {
            value_table[i][0] = scanner.nextInt();
            value_table[i][1] = scanner.nextInt();
        }
        int[][] dp_table = new int[n + 1][weight + 1];
        create_dp_table(n, weight, value_table, dp_table);
        print_dp_table(n, weight, value_table, dp_table);
    }

    //dp_table을 채우는 함수
    static private void create_dp_table(int n, int weight, int[][] value_table, int[][] dp_table) {
        for (int i = 0; i <= weight; i++) {
            dp_table[0][i] = 0;
            System.out.printf("%10d", dp_table[0][i]);
        }
        System.out.println();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= weight; j++) {
                if (value_table[i - 1][1] > j) {
                    dp_table[i][j] = dp_table[i - 1][j];
                } else {
                    dp_table[i][j] = dp_table[i - 1][j] > dp_table[i - 1][j - value_table[i - 1][1]] + value_table[i - 1][0] ? dp_table[i - 1][j] : dp_table[i - 1][j - value_table[i - 1][1]] + value_table[i - 1][0];
                }
                System.out.printf("%10d", dp_table[i][j]);
            }
            System.out.println();
        }
    }

    //item value 출력함수
    static private void print_dp_table(int n, int weight, int[][] value_table, int[][] dp_table) {
        System.out.println("max : " + dp_table[n][weight]);
        System.out.print("item :");
        int i = n;
        int j = weight;
        while (dp_table[i][j] != 0) {
            if (dp_table[i][j] == dp_table[i - 1][j]) {
                i -= 1;
            } else {
                System.out.print(" " + i);
                j -= value_table[i - 1][1];
            }
        }
    }

}
