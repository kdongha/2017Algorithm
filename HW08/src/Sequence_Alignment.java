import java.util.Scanner;

public class Sequence_Alignment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gap = 1;
        System.out.print("string1 : ");
        String str1 = scanner.nextLine();
        System.out.print("string2 : ");
        String str2 = scanner.nextLine();
        int[][] dp_table = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            dp_table[i][0] = i * gap;
        }
        for (int i = 0; i <= str2.length(); i++) {
            dp_table[0][i] = i * gap;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                dp_table[i][j] = min(dp_table[i - 1][j - 1] + mismatches(str1.charAt(i - 1), str2.charAt(j - 1)), min(dp_table[i - 1][j], dp_table[i][j - 1]) + 1);
            }
        }
        System.out.println("MIN COST : " + dp_table[str1.length()][str2.length()]);
        for (int i = -1; i < str1.length() + 1; i++) {
            if (i <= 0) {
                System.out.print("   ");
            } else {
                System.out.printf("%-3s", str1.charAt(i - 1));
            }
            for (int j = 0; j < str2.length() + 1; j++) {
                if (i < 0) {
                    if (j == 0) {
                        System.out.print("   ");
                    } else {
                        System.out.printf("%-3s", str2.charAt(j - 1));
                    }
                } else {
                    System.out.printf("%-3d", dp_table[i][j]);
                }
            }
            System.out.println();
        }

        System.out.print("\n경로(역순) : ");
        int i=str1.length();
        int j=str2.length();
        while(i>0 || j>0){
            if(i==0){
                System.out.print("← ");
                j--;
            }else if(j==0){
                System.out.print("↑ ");
                i--;
            }else if(dp_table[i][j]>dp_table[i-1][j]){
                System.out.print("↑ ");
                i--;
            }else if(dp_table[i][j]>dp_table[i][j-1]){
                System.out.print("← ");
                j--;
            }else{
                System.out.print("↖ ");
                i--;
                j--;
            }
        }
        System.out.println();
    }

    static private int min(int a, int b) {
        return a > b ? b : a;
    }

    static private int mismatches(char a, char b) {
        if (a == b) {
            return 0;
        }
        return 2;
    }
}
