import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Closest_Pair_of_Points {
    public static void main(String[] args) throws Exception {
        //Scanner scanner = new Scanner(new File("closest_data.txt"));
        //Scanner scanner=new Scanner(new File("closest_100.in"));
        //Scanner scanner=new Scanner(new File("closest_1000.in"));
        Scanner scanner=new Scanner(new File("closest_10000.in"));
        int count = 0;
        double[][] array = new double[10001][2];
        while (scanner.hasNext()) {
            array[count][0] = scanner.nextDouble();
            array[count++][1] = scanner.nextDouble();
        }
        quickSort(array, 0, count - 1, 0);
        System.out.println(closet_fair(array,0,count-1));

    }

    private static double closet_fair(double[][] array, int left, int right) {
        int size = right - left + 1;
        int mid = (left + right) / 2;
        if (size <= 3) {
            return brute_force(array,0,size-1);
        } else {
            double left_min = closet_fair(array, left, mid);
            double right_min = closet_fair(array, mid + 1, right);
            double min = (left_min > right_min) ? right_min : left_min;

            double[][] temparray = new double[size][2];
            int temparray_count = 0;
            for (int i = left; i < right; i++) {
                if (Math.abs(array[mid][0] - array[i][0]) <= min) {
                    temparray[temparray_count++] = array[i];
                }
            }
            quickSort(temparray, 0, temparray_count - 1, 1);

            double temp = brute_force(temparray, 0, temparray_count - 1);
            if (min > temp) {
                min = temp;
            }
            return min;
        }
    }

    private static double brute_force(double[][] array, int left, int right) {
        double min = get_distance(array[0], array[1]);
        for (int i = 1; i < right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double temp = get_distance(array[i], array[j]);
                if (min > temp) {
                    min = temp;
                }
            }
        }
        return min;
    }

    private static int partition(double[][] A, int p, int r, int datum_num) {
        double X = A[r][datum_num];
        int i = p;
        int j = r - 1;
        while (j != i - 1) {
            if (A[i][datum_num] <= X) {
                i++;
            } else if (X <= A[j][datum_num]) {
                j--;
            } else {
                double[] temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        double[] temp = A[i];
        A[i] = A[r];
        A[r] = temp;
        return i;
    }

    private static void quickSort(double[][] A, int p, int r, int datum_num) {
        if (p < r) {
            int q = partition(A, p, r, datum_num);
            quickSort(A, p, q - 1, datum_num);
            quickSort(A, q + 1, r, datum_num);
        }
    }


    private static double get_distance(double[] a, double[] b) {
        double x_distance = Math.abs(a[0] - b[0]);
        double y_distance = Math.abs(a[1] - b[1]);
        return Math.sqrt(x_distance * x_distance + y_distance * y_distance);
    }
}
