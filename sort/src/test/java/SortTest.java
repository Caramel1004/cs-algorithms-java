import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortTest {
    @Test
    void bubbleSortTest() {
        System.out.println("-------------------------------");
        int[] arr = new int[]{23, 21, 32, 4, 5, 17};
        BubbleSort.bubbleSortDesc(arr);
        System.out.println(Arrays.toString(arr) + "\n");

        BubbleSort.bubbleSortAsc(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void selectionSortTest() {
        System.out.println("-------------------------------");
        int[] arr = new int[]{23, 21, 32, 4, 5, 17};
        SelectionSort.selectionSortAsc(arr);
        System.out.println(Arrays.toString(arr) + "\n");

        SelectionSort.selectionSortDesc(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void insertSortTest() {
        System.out.println("-------------------------------");
        int[] arr = new int[]{23, 21, 32, 4, 5, 17};
        InsertionSort.insertionSort(arr);
        System.out.println(Arrays.toString(arr) + "\n");

        InsertionSort.insertionSortDesc(arr);
        System.out.println(Arrays.toString(arr));
    }
}
