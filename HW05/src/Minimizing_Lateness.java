import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Minimizing_Lateness {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("HW05/data06_lateness.txt"));
        int n = scanner.nextInt();
        int[][] table = new int[n][2];
        for (int i = 0; i < n; i++) {
            table[i][0] = scanner.nextInt();
            table[i][1] = scanner.nextInt();
        }

        Arrays.sort(table, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]);
                } else {
                    return Integer.compare(o1[1], o2[1]);
                }
            }
        });
        int time = 0;
        int lateness = 0;
        for (int i = 0; i < n; i++) {
            time += table[i][0];
            lateness += Math.max(0, time - table[i][1]);
        }

        System.out.println(lateness);
    }
}
