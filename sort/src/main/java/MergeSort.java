public class MergeSort {
        private static int[] temp;
    public static void main(String[] args) {
        int[] arr = {3, 44, 5, 38, 47, 15, 36, 26};
        temp =  new int[arr.length];

        mergeSort(arr, 0, arr.length - 1);

        temp = null;
    }


    // 병합 정렬
    private static void mergeSort(int[] arr, int firstIndex, int lastIndex) {
        if (firstIndex < lastIndex) {
            int pl = firstIndex;
            int pr = lastIndex;
            int pmid = (pl + pr) / 2;

            // 제일 작은 단위까지 분할되도록 재귀 호출
            mergeSort(arr, pl, pmid);
            mergeSort(arr, pmid, pr);

            // arr[pl] ~ arr[pmid]: 배열 앞부분
            // 배열 앞부분을 temp[pl] ~ temp[pmid]까지 저장
            for(int i = firstIndex; i <= pmid; i++){
                temp[pl++] = arr[i];
            }

            // arr[pmid + 1] ~ arr[pr]과 temp[pl] ~ temp[pmid] 비교


            // 배열 temp에 남은 요소를 배열 arr 요소로 저장
        }
    }
}
