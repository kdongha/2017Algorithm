import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Karatsuba_Algorithm {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("HW04/data05_karatsuba.txt"));
        long num1 = scanner.nextLong();
        long num2 = scanner.nextLong();
        if (String.valueOf(num1).length() != String.valueOf(num2).length()) {
            System.out.println("두 수의 자릿수가 다릅니다.");
        } else {
            System.out.println(karatsuba(num1, num2));
        }
    }

    private static long karatsuba(long x, long y) {
        int x_size = String.valueOf(x).length();
        int y_size = String.valueOf(y).length();
        if (x_size < 4 || y_size < 4) {
            return x * y;
        } else {
            long x1 = Long.parseLong(String.valueOf(x).substring(0, x_size / 2));
            long x2 = Long.parseLong(String.valueOf(x).substring(x_size / 2));
            long y1 = Long.parseLong(String.valueOf(y).substring(0, y_size / 2));
            long y2 = Long.parseLong(String.valueOf(y).substring(y_size / 2));
            long z0 = karatsuba(x2, y2);
            long z2 = karatsuba(x1, y1);
            long z1 = karatsuba((x2 + x1), (y2 + y1)) - z0 - z2;
            return (long) (z2 * Math.pow(10, x_size) + z1 * Math.pow(10, x_size / 2) + z0);
        }
    }
}
