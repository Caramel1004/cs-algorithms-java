public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 44, 5, 38, 47, 15, 36, 26};
        mergeSort(arr, 0, arr.length - 1);
    }


    // 병합 정렬
    private static void mergeSort(int[] arr, int firstIndex, int lastIndex) {
        // 마지막 배열 크기 1일인 경우 if문 패스
        if (firstIndex < lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;

            // 제일 작은 단위까지 분할되도록 재귀 호출
            mergeSort(arr, firstIndex, middleIndex);
            mergeSort(arr, middleIndex + 1, lastIndex);

            int[] mergedArr = new int[lastIndex - firstIndex + 1];
            int leftPointer = firstIndex; // 중앙 인덱스 왼쪽 구간의 시작 포인터
            int rightPointer = middleIndex + 1; // 중앙 인덱스 오른쪽 구간의 시작 포인터


            while (leftPointer <= middleIndex && rightPointer <= lastIndex) {

            }

            while (leftPointer <= middleIndex) {

            }

            while (rightPointer <= lastIndex) {

            }
        }
    }
}
