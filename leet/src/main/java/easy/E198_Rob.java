package easy;

/**
 * @author gaiqun
 * @date 2020/12/8
 */
public class E198_Rob {

    public static void main(String[] args) {
        //int[] nums = {2,7,9,3,1};
        int[] nums = {2, 1, 1, 2};
        E198_Rob solution = new E198_Rob();
        System.out.println(solution.rob(nums));
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
}
