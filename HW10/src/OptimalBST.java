import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class OptimalBST {
    public static void main(String[] args) throws IOException {
        double[] p = new double[101];
        double[] q = new double[101];
        double[][] e = new double[101][101];
        double[][] w = new double[101][101];
        int[][] root = new int[101][101];
        int n=0;

        Scanner scanner=new Scanner(new File("HW10/data11.txt"));
        while(scanner.hasNextLine()){
            String[] str = scanner.nextLine().split("\t");
            p[n] = str[0].equals("-1") ? 0 : Double.parseDouble(str[0]);
            q[n] = Double.parseDouble(str[1]);
            n++;
        }
        for(int i=1; i<=n; i++){
            e[i][i-1] = q[i-1];
            w[i][i-1] = q[i-1];
        }

        for(int l=1; l<n; l++){
            for(int i=1; i<n-l+1; i++){
                int j = i + l -1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j-1] + p[j] + q[j];
                for(int r = i; r <= j; r++){
                    double t = e[i][r-1] + e[r+1][j] + w[i][j];
                    if(t < e[i][j]){
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
        System.out.println(e[1][n-1]);
    }
}