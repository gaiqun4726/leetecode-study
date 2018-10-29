package medium;

public class M31_Next_Permutation {
    /**
     * 1. 从右向左，找到第一对连续的顺序数，记录开始位置i
     * 2. 如果找不到，则意味着下一个排列是最小排列。如果找到，继续执行
     * 3. 从右侧找第一个大于i所在值的位置j，交换i,j的值。此时，i右侧的数组是逆序的
     * 4. 将i右侧的数组变为顺序的，得到下一个排列
     * 5. 特别注意处理边界值，重复值
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int len = nums.length;
        int i = len - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = len - 1;
            while (nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int len = nums.length;
        int end = len - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
