package medium;

public class M33_Search_Rotated_Sorted_Array {
    public static void main(String[] args) {
        int[] nums = {4};
        System.out.println(new M33_Search_Rotated_Sorted_Array().search(nums, 4));
    }

    /**
     * lg(n)的时间复杂度，想到用二分。但是二分的条件不是mid和target的分支，而是mid和end（或start）的分支。
     * 如果使用mid和target分支，则做不出来。
     * 注意，本题是变形的二分。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < nums[end]) { // 用mid和end进行二分
                if (target > nums[mid] && target <= nums[end]) // 在右边的完全升序数组中查找
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                if (target < nums[mid] && target >= nums[start]) // 在左边的完全升序数组中查找
                    end = mid - 1;
                else
                    start = mid + 1;
            }
        }
        return -1;
    }
}
