package medium;

/**
 * 总结
 *
 * 这道题需要背下来。每次做的时候都很费劲。
 *
 * 思路1：二分查找，每次根据条件判断是向前半段还是后半段查找。难点在于分支的判断。
 *
 * 思路2：参考查找旋转数组分割点的问题。找到分割点以后，前后半段都分别是有序的数组，在前后半段进行二分查找。
 * 总的时间复杂度是O(logN)。
 * 这种解法看似思路清晰，实际分支条件也极容易遗漏。还是用方法1吧。
 */
public class M33_Search_Rotated_Sorted_Array {

    public static void main(String[] args) {
        int[] nums = {3, 1};
        System.out.println(new M33_Search_Rotated_Sorted_Array().search2(nums, 1));
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
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 这里按照mid在断点的左、右分情形讨论，可以使问题更清晰
            else if (nums[mid] < nums[end]) { // 用mid和end进行二分
                if (target > nums[mid] && target <= nums[end]) // 在右边的完全升序数组中查找
                {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if (target < nums[mid] && target >= nums[start]) // 在左边的完全升序数组中查找
                {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        // 找到分隔点sep
        int sep = end;
        if (nums[start] > nums[end]) {
            while (start <= end) {
                int mid = (start + end) / 2;
                // 注意边界
                if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
                    sep = mid;
                    break;
                }
                if (nums[mid] > nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        if (target == nums[sep]) {
            return sep;
        }
        // 前后半段分别查找target元素
        start = 0;
        end = sep;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        start = sep;
        end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
