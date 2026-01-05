import java.util.Arrays;

public class BinarySearch {
    static class ExecutionRecord {
        private int index;
        private int exCnt;

        public ExecutionRecord(int index, int exCnt) {
            this.index = index;
            this.exCnt = exCnt;
        }

    }
    /**
     * O(log(n))
     *
     */
    static ExecutionRecord binarySearch(int[] nums, int target) {
        Arrays.binarySearch(nums, target);
        int lPtr = 0;
        int rPtr = nums.length - 1;
        int targetIdx = -1;
        int exCnt = 0;
        while (lPtr <= rPtr) {
            exCnt++;
            int midPtr = (lPtr + rPtr) / 2;
            if(nums[midPtr] < target) {
                lPtr = midPtr + 1;
            } else if(nums[midPtr] > target) {
                rPtr = midPtr - 1;
            } else if(nums[midPtr] == target) {
                targetIdx = midPtr;
                break;
            }
        }
        return new ExecutionRecord(targetIdx, exCnt) ;
    }
}
