import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Counting_Inversions {
    static int counting = 0;

    public static void main(String[] args) throws IOException {
        final int MAX = 10000;
        int[] array = new int[MAX];
        Scanner scanner = new Scanner(new File("HW04/data05_inversion_01.txt"));    //22
        //Scanner scanner = new Scanner(new File("HW04/data05_inversion_02.txt"));              //0
        //Scanner scanner = new Scanner(new File("HW04/data05_inversion_03.txt"));              //66
        //Scanner scanner = new Scanner(new File("HW04/data05_inversion_04.txt"));              //29
        int count = 0;

        while (scanner.hasNext()) {
            array[count++] = scanner.nextInt();
        }

        sort_and_count(array, 0, count - 1);
        System.out.println(counting);
    }

    private static void sort_and_count(int[] array, int left, int right) {
        if (left != right) {
            int mid = (left + right) / 2;
            sort_and_count(array, left, mid);
            sort_and_count(array, mid + 1, right);
            merge_and_count(array, left, mid, right);
        }
    }

    private static void merge_and_count(int[] array, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int[] temp_array = new int[right - left + 1];
        int temp_array_count = 0;
        while (l <= mid || r <= right) {
            if (l > mid) {
                while (r <= right) {
                    temp_array[temp_array_count++] = array[r++];
                }
            } else if (r > right) {
                while (l <= mid) {
                    temp_array[temp_array_count++] = array[l++];
                }
            } else {
                if (array[l] > array[r]) {
                    counting += mid - l + 1;
                    temp_array[temp_array_count++] = array[r++];
                } else {
                    temp_array[temp_array_count++] = array[l++];
                }
            }
        }
        System.arraycopy(temp_array, 0, array, left, right - left + 1);
    }
}
