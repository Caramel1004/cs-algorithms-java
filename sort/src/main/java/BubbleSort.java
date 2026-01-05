import java.util.Arrays;

public class BubbleSort {
    /**
     *
     * O(n^2)
     */
    static void bubbleSortDesc(int[] arr) {
        int exCnt = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
                exCnt++;
            }
        }
        System.out.println("버블 정렬 실행 횟수: " + exCnt);
    }

    static void bubbleSortAsc(int[] arr) {
        int exCnt = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
            exCnt++;
        }
        System.out.println("버블 정렬 실행 횟수: " + exCnt);
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
