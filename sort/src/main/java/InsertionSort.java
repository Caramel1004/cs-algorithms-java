import java.util.Arrays;

public class InsertionSort {
    // 오름 차순
    // O(n^2)
    static void insertionSort(int[] arr) {
        int exCnt = 0;
        for(int i = 1; i < arr.length; i++){
            int temp = arr[i];
            int targetIdx = i;
            while(targetIdx > 0 && arr[targetIdx - 1] > temp){
                exCnt++;
                arr[targetIdx] = arr[targetIdx - 1];
                targetIdx--;
            }
            arr[targetIdx] = temp;
        }
        System.out.println("삽입 정렬 실행 횟수: " + exCnt);
    }

    // 내림 차순
    // O(n^2)
    static void insertionSortDesc(int[] arr) {
        int exCnt = 0;
        for(int i = 1; i < arr.length; i++){
            int temp = arr[i];
            int targetIdx = i;
            while(targetIdx > 0 && arr[targetIdx - 1] < temp){
                exCnt++;
                arr[targetIdx] = arr[targetIdx - 1];
                targetIdx = targetIdx - 1;
            }
            arr[targetIdx] = temp;
        }
        System.out.println("삽입 정렬 실행 횟수: " + exCnt);
    }
}
