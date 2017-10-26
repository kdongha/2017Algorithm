package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        int[] data = new int[10000000];
        int cnt = 0;
        int key;
        long startTime, endTime;
        Scanner scanner = new Scanner(new File("input.txt"));
        FileWriter fileWriter=new FileWriter(new File("201202141_quick.txt"));
        while (scanner.hasNext()) {
            data[cnt++] = scanner.nextInt();
        }

        startTime = System.nanoTime();

        quickSort(data,0,cnt-1);

        endTime = System.nanoTime();
        System.out.println(endTime - startTime);
        fileWriter.write(""+data[0]);
        for (int i = 1; i < cnt; i++) {
            fileWriter.write(" "+data[i]);
        }
        fileWriter.flush();
        fileWriter.close();

    }
    static int partition(int[] A,int p,int r){
        int X=A[r];
        int i=p;
        int j=r-1;
        while(j!=i-1){
            if(A[i]<=X){
                i++;
            }else if(X<=A[j]) {
                j--;
            }else{
                int temp=A[i];
                A[i]=A[j];
                A[j]=temp;
            }
        }
        int temp=A[i];
        A[i]=A[r];
        A[r]=temp;
        return i;
    }
    static void quickSort(int[] A,int p,int r){
        if(p<r){
            int q=partition(A,p,r);
            quickSort(A,p,q-1);
            quickSort(A,q+1,r);
        }
    }
}
