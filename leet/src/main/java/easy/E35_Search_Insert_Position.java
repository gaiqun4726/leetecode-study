package easy;

/**
 * @author gaiqun
 * @date 2020/4/12
 *
 * 总结
 *
 * 模板代码。二分查找，找不到返回应插入的位置。唯一需要特别注意的，是while循环的条件。必须是low<=high
 */
public class E35_Search_Insert_Position {

    public static void main(String[] args) {
        E35_Search_Insert_Position solution = new E35_Search_Insert_Position();
        int[] nums = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(nums, 7));
    }

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int low = 0, high = len - 1;
        // 这里必须是low<=high
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 退出，则low>high。此时的low就是需插入的位置。记住
        return low;
    }
}
