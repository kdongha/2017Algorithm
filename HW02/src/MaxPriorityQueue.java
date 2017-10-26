package src;

import java.io.*;

public class MaxPriorityQueue {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("data_heap.txt"));
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        Node heap[] = new Node[1000];
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            String[] line = str.split(", ");
            heap[++count] = new Node(Integer.parseInt(line[0]), line[1]);
        }
        buildMaxHeap(heap);
        String inputNum="";
        System.out.println("\n**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****\n");
        while (!inputNum.equals("6")) {
            for(int i=1;i<=count;i++) {
                System.out.println(heap[i]);
            }
            System.out.println("\n-----------------------------------------------");
            System.out.println("1. 작업 추가    2. 최댓값  3. 최대 우선순위 작업 처리");
            System.out.println("4. 원소 키값 증가         5. 작업제거        6.종료");
            System.out.println("-----------------------------------------------");
            inputNum = scanner.readLine();
            try {
                switch (Integer.parseInt(inputNum)) {
                    case 1:
                        System.out.print("신규 작업명 (20 Bytes 이내) : ");
                        String work = scanner.readLine();
                        System.out.print("우선 순위 (0 ~ 999) : ");
                        int rank = Integer.parseInt(scanner.readLine());
                        insert(heap, new Node(rank, work));
                        System.out.println("**** 작업 추가 완료 ****\n");
                        break;
                    case 2:
                        Node maxNode = max(heap);
                        if(maxNode==null){
                            System.out.println("\n큐가 비어있습니다.\n");
                        }else{
                            System.out.println("\n**** MAX : " + maxNode.key + ", " + maxNode.value + " ****\n");
                        }
                        break;
                    case 3:
                        Node extract_Node = extract_max(heap);
                        if(extract_Node==null){
                            System.out.println("\n큐가 비어있습니.\n");
                        }else{
                            System.out.println("\n**** 한 개의 작업을 완료했습니다. ****\n");
                        }
                        break;
                    case 4:
                        System.out.print("수정할 노드 x 입력 : ");
                        int modifyIndex = Integer.parseInt(scanner.readLine());
                        System.out.print("수정할 key : ");
                        int modityKey = Integer.parseInt(scanner.readLine());
                        Node increase_Node = increase_key(heap, modifyIndex, modityKey);
                        if(increase_Node==null){
                            System.out.println("\n현재 key보다 값이 작습니다. \n");
                        }else {
                            System.out.println("\n**** 한 개의 작업을 완했습니다. ****");
                        }
                        break;
                    case 5:
                        System.out.print("삭제할 노드 x 입력 : ");
                        int removeIndex = Integer.parseInt(scanner.readLine());
                        Node delete_Node = delete(heap, removeIndex);
                        if(delete_Node==null){
                            System.out.println("\n삭제 가능한 노드가 없습니다.\n");
                        }else{
                            System.out.println("\n**** 한 개의 작업을 완했습니다. ****\n");
                        }
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("\n**** (1~6) 사이에 값을 입력해 주세요! ****");
                        break;
                }
            } catch (NumberFormatException error) {
                System.out.println("\n**** 숫자(1~6)를 입력해 주세요 !! ****");
            }
        }
    }


    private static void insert(Node[] S, Node x) {
        S[++count] = x;
        buildMaxHeap(S);
    }

    private static Node max(Node[] S) {
        if (count > 0) {
            return S[1];
        } else {
            return null;
        }
    }

    private static Node extract_max(Node[] S) {
        Node extractNode = S[1];
        S[1] = S[count--];
        maxHeapify(S, 1);
        return extractNode;
    }

    private static Node increase_key(Node[] S, int x, int k) {
        if (S[x].key > k) {
            Node returnNode=S[x];
            S[x].key = k;
            buildMaxHeap(S);
            return returnNode;
        } else {
            return null;
        }
    }


    private static Node delete(Node[] S, int x) {
        Node extractNode = S[x];
        swap(S,x,count--);
        buildMaxHeap(S);
        return extractNode;
    }

     private static void maxHeapify(Node[] S, int index) {
        int left = 2 * index;
        int right = 2 * index + 1;
        int largest;
        if (left <= count && S[left].key > S[index].key) {
            largest = left;
        } else {
            largest = index;
        }
        if (right <= count && S[right].key > S[largest].key) {
            largest = right;
        }
        if (largest != index) {
            swap(S,index,largest);
            maxHeapify(S, largest);
        }
    }

     private static void buildMaxHeap(Node[] S) {
        for (int i = count; i > 0; i--) {
            maxHeapify(S, i);
        }
    }

    private static void swap(Node[] S, int a, int b) {
        Node temp = S[a];
        S[a]=S[b];
        S[b] = temp;
    }

static class Node {
    int key;
    String value;

    private Node(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + ", " + value;
    }
}
}
