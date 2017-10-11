import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Loop_Invariant {
    public static void main(String[] args) throws Exception {
        int[] arr=new int[10001];
        int count=0;
        int search_number;
        FileReader fileReader=new FileReader(new File("invariant_data.txt"));
        Scanner scanner=new Scanner(fileReader);
        while(scanner.hasNext()){
            arr[count++]=scanner.nextInt();
        }
        scanner=new Scanner(System.in);
        System.out.print("index를 찾을 수를 입력해 주세요 : ");
        search_number=scanner.nextInt();
        System.out.println();

        //Precondition
        int start=0;
        int end=count;
        int mid=(start+end)/2;

        while(arr[mid]!=search_number){
            if(arr[mid]>search_number){
                end=mid-1;
            }else{
                start=mid+1;
            }
            mid=(start+end)/2;
        }
        System.out.printf("%d의 index는 %d 입니다.\n",search_number,mid);
    }
}
