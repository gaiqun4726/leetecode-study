package medium;

/**
 * @author gaiqun
 * @date 2020/4/30
 *
 * 总结
 *
 * 题目要求时间复杂度小于O(n^2)，空间复杂度O(1)，不能修改原数组
 *
 * 共n+1个数字，数字范围从1到n，只有一个数字重复。
 * 如果把数组中的数字看做索引，那么数组中必然存在环，并且环的入口点就是我们要找的重复数字。
 * 这道题就跟环形链表找入口点M142题一样了。
 * 因为0所在的数字必然不是0，而重复的数字必然占据多个位置。以0所在数字作为链表的头结点。
 * 时间复杂度O(n)，空间复杂度O(1)。
 *
 * 思路2：利用二分查找。在1到n的有序数组中，取mid；在原数组中统计小于mid数字的个数。
 * 如果小于mid的个数大于mid，则意味着小于mid的数字中存在重复数字。
 * 重复上述过程，直到start=end=mid。
 * 二分法并不需要真正开辟1到n的有序数组，只需要几个标志位即可，空间复杂度O(1)。
 * 时间复杂度O(NlogN)。
 *
 * 二分查找，通过设置不同的分支条件，可以解决不同的问题，不仅限于有序数组中查找目标值。
 */
public class M287_Find_Duplicate_Number {

    public static void main(String[] args) {
        M287_Find_Duplicate_Number solution = new M287_Find_Duplicate_Number();
        int[] nums = {1, 3, 4, 2, 2};
        //int[] nums = {3, 1, 3, 4, 2};
        //int[] nums = {1, 4, 4, 2, 4};
        System.out.println(solution.findDuplicate2(nums));
    }

    /**
     * 注意快慢指针里的关键点
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // 进入循环之前，先让满指针走1步，快指针走两步。这样循环就可以以二者相等作为跳出条件。
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        // 第二次相遇，快慢指针每次走一步
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        // slow=fast时，数字就是重复的数字
        return slow;
    }

    public int findDuplicate2(int[] nums) {
        int len = nums.length;
        int start = 1, end = len - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            // 这里，如果小于mid的个数大于mid，则意味着小于mid的数字存在重复数字
            if (count > mid) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
