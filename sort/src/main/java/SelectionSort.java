public class SelectionSort {
    static void selectionSortAsc(int[] arr) {
        int exCnt = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    swap(arr, i, j);
                }
                exCnt++;
            }
        }
        System.out.println("선택 정렬 실행 횟수: " + exCnt);
    }

    static void selectionSortDesc(int[] arr) {
        int exCnt = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] < arr[j]){
                    swap(arr, i, j);
                }
                exCnt++;
            }
        }
        System.out.println("선택 정렬 실행 횟수: " + exCnt);
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
