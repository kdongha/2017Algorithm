import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Closest_Pair_of_Points {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(new File("closest_data.txt"));
        //Scanner scanner=new Scanner(new File("closest_100.in"));
        //Scanner scanner=new Scanner(new File("closest_1000.in"));
        //Scanner scanner=new Scanner(new File("closest_10000.in"));
        int count=0;
        double[][] temparray=new double[10001][2];
        while(scanner.hasNext()){
            temparray[count][0]=scanner.nextDouble();
            temparray[count++][1]=scanner.nextDouble();
        }
        double[][] array=new double[count][2];
        System.arraycopy(temparray,0,array,0,count);

        Arrays.sort(array, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                if(o1[0]==o2[0]){
                    return Double.compare(o1[1],o2[1]);
                }
                return Double.compare(o1[0],o2[0]);
            }
        });

    }



}
