import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {
    @Test
    void testBinarySearch() {
        BinarySearch.ExecutionRecord result = BinarySearch.binarySearch(new int[]{1, 2, 3, 4, 5, 7}, 7);
        BinarySearch.ExecutionRecord result2 = BinarySearch.binarySearch(new int[]{1, 2, 3, 4, 5, 7, 99, 100}, 11);
        System.out.println(result);
        System.out.println(result2);
        assertEquals(new BinarySearch.ExecutionRecord(5, 3), result);
    }
}
