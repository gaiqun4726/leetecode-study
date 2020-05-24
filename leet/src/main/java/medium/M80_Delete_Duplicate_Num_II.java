package medium;

/**
 * @author gaiqun
 * @date 2020/5/3
 *
 * 总结
 *
 * 删除排序数组的重复元素，原地修改，数组中剩余位置元素不用管。
 *
 * 双指针，一个指向应放的位置，一个指向当前遍历的位置。如果当前位置的元素与应放位置之前两个元素不同，则放入当前元素。
 * 时间复杂度O(N)。
 */
public class M80_Delete_Duplicate_Num_II {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 2, 3, 3};
        M80_Delete_Duplicate_Num_II solution = new M80_Delete_Duplicate_Num_II();
        System.out.println(solution.removeDuplicates(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }
        int cur = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[cur - 2] != nums[i]) {
                nums[cur++] = nums[i];
            }
        }
        return cur;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = 0, len = nums.length, count = 0, prev = nums[0];
        while (end < len) {
            if (prev != nums[end]) {
                count = 0;
            }
            prev = nums[end];
            if (count < 2) {
                nums[start++] = nums[end++];
                count++;
            } else {
                int current = nums[end];
                while (end < len && nums[end] == current) {
                    end++;
                }
            }
        }
        return start;
    }
}
