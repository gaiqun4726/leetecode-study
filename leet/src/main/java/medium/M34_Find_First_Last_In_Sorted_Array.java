package medium;

/**
 * @author gaiqun
 * @date 2020/5/5
 *
 * 总结
 *
 * 查找目标值在排序数组中的收尾位置。利用二分查找，分别查找左右边界。
 * 方法是两次二分查找，第一次大于等于target则向左查找；第二次小于等于target则向右查找。
 * 注意第一次的mid向下取整，第二次的mid向上取整。原理是啥暂且不清楚。
 */
public class M34_Find_First_Last_In_Sorted_Array {

    public static void main(String[] args) {
        int[] nums = {5, 7, 8, 8, 8, 10};
        M34_Find_First_Last_In_Sorted_Array solution = new M34_Find_First_Last_In_Sorted_Array();
        int[] result = solution.searchRange(nums, 8);
        System.out.println(result[0] + "," + result[1]);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        int start = 0, end = nums.length - 1;
        while (start < end) {
            // 向下取整
            int mid = (start + end) >> 1;
            if (nums[mid] >= target) {
                // 需要包含mid
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] != target) {
            return result;
        }
        result[0] = start;
        start = 0;
        end = nums.length - 1;
        while (start < end) {
            // 向上取整
            int mid = (start + end + 1) >> 1;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        result[1] = start;
        return result;
    }

    /**
     * labuladong算法小抄里关于二分查找的解法。
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] result = new int[] {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 取值范围是左闭右闭
        int left = 0, right = nums.length - 1;
        // 因此可以取到左右相等
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 找到target值时，右边界继续左移
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 终止时left就是左边界。但是左边界可能越界，也可能不存在target，因此要判断一下。
        result[0] = (left == nums.length || nums[left] != target) ? -1 : left;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 左边界向右移
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // right是右边界。right可能越界或不存在，要判断一下。
        result[1] = (right < 0 || nums[right] != target) ? -1 : right;
        return result;
    }
}
