package easy;

public class E53_Max_Subsequence_Sum {

    /**
     * 从数组开始连续序列的和第一次变为负数的点为分界点
     * 目标序列不可能含有这个和为负数的子串。因此从这里开始重新计算子序列的和
     * 注意本题要求子序列至少有一个值。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int maxSum = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (curSum < 0) {
                curSum = nums[i];
            } else {
                curSum += nums[i];
            }
            maxSum = maxSum > curSum ? maxSum : curSum;
        }
        return maxSum;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0, end = 0, len = nums.length;
        int maxSum = Integer.MIN_VALUE;
        while (end < len) {
            sum += nums[end];
            maxSum = Math.max(maxSum, sum);
            end++;
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }
}
