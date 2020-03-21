package medium;

/**
 * @author gaiqun
 * @date 2020/3/14
 *
 * 总结LIS最长递增子序列问题是经典dp问题，背也要背下来。
 *
 * dp[i]表示以i结尾的最长递增子序列的长度。求最长递增子序列长度则是求dp[i]中的最大值。
 *
 * 初始化dp[0]，然后迭代计算所有dp[i]。时间复杂度O(n^2)，空间复杂度O(n)。
 *
 * 有时间复杂度为O(nlogn)的解法，目前看不懂。等以后能力有提升再看。
 */
public class M300_Longest_Increase_Subsequence {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        // 第一重循环，计算每个位置的dp，同时计算dp的最大值
        for (int i = 0; i < dp.length; i++) {
            int maxVal = 0;
            // 第二重循环，查找当前位置之前所有位置，满足能够成递增子序列里的最大dp，dp+1就是当前的dp
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxVal = Math.max(dp[j], maxVal);
                }
            }
            dp[i] = maxVal + 1;
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
}
