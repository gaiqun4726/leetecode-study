package medium;

/**
 * @author gaiqun
 * @date 2020/4/3
 *
 * 总结
 *
 * 使用二分查找。
 * 本题每个数字都不同。如果有相同数字，那么在发现有相同数字时，只能遍历剩下的元素找到最小元素。
 */
public class M153_Min_In_Rotated_Sorted_Array {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        M153_Min_In_Rotated_Sorted_Array solution = new M153_Min_In_Rotated_Sorted_Array();
        System.out.println(solution.findMin(nums));
    }

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high) / 2;
        while (low < high) {
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                break;
            } else if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = (low + high) / 2;
        }
        return nums[mid];
    }
}
