package easy;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/12/8
 */
public class E198_Rob {

    public static void main(String[] args) {
        //int[] nums = {2,7,9,3,1};
//        int[] nums = {1, 2, 3, 1};
        int[] nums = {2, 1, 1, 2};
        E198_Rob solution = new E198_Rob();
        System.out.println(solution.rob2(nums));
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        // dp[k]的含义是，对于前k间屋子，能够盗取的最大金额。包括盗取及不盗取第k间屋子的情形。
        int dp[] = new int[n];
        dp[0] = nums[0];
        // dp[1]的初始化
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            // dp[k]只与dp[k-1]和dp[k-2]有关，可以用滚动数组优化。
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    //自顶向下递归+备忘录法。废弃，有bug，只能通过部分case
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        memo = new int[N];
        Arrays.fill(memo, -1);
        return Math.max(dp(nums, N - 1, true), dp(nums, N - 1, false));
    }

    public int[] memo;

    public int dp(int[] nums, int num, boolean rob) {
        if (num == 0) {
            if (rob) {
                return nums[0];
            } else {
                return 0;
            }
        }
        if (memo[num] != -1) {
            return memo[num];
        }
        if (rob) {
            memo[num] = dp(nums, num - 1, false) + nums[num];
        } else {
            memo[num] = Math.max(dp(nums, num - 1, true), dp(nums, num - 1, false));
        }
        return memo[num];
    }

    //自底向上，dp数组迭代法。状态有两个：房间数和打劫状态。因此dp数组设置了二维
    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[][] dp = new int[N][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[N - 1][0], dp[N - 1][1]);
    }
}
