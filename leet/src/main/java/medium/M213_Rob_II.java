package medium;

/**
 * @author gaiqun
 * @date 2020/12/8
 *
 * 打家劫舍2。房屋连成环。
 */
public class M213_Rob_II {

    public static void main(String[] args) {
//        int[] nums = {2, 3, 2};
//        int[] nums = {1, 2, 3, 1};
        int[] nums = {1, 2, 1, 1};
        M213_Rob_II solution = new M213_Rob_II();
        System.out.println(solution.rob2(nums));
    }

    /**
     * 复用打家劫舍1的动态规划思路。分别考虑必须光临第一家和必须不光临第一家两种情形，分别做一次动态规划。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 光临第一家，则最后一家必须不光临。
        int result = 0;
        // 使用滚动数组优化空间
        int v1 = nums[0];
        int v2 = nums[0];
        // 只需看dp[n-1]的结果
        for (int i = 2; i < len - 1; i++) {
            int tmp = Math.max(nums[i] + v1, v2);
            v1 = v2;
            v2 = tmp;
        }
        result = Math.max(result, v2);
        // 必须不光临第一家
        v1 = 0;
        v2 = nums[1];
        // 要看dp[n]的结果
        for (int i = 2; i < len; i++) {
            int tmp = Math.max(nums[i] + v1, v2);
            v1 = v2;
            v2 = tmp;
        }
        result = Math.max(result, v2);
        return result;
    }

    /**
     * 自顶向下解法
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        // 两次动态规划，不能混用备忘录
        int[] memo1 = new int[len];
        int[] memo2 = new int[len];
        for (int i = 0; i < len; i++) {
            memo1[i] = -1;
            memo2[i] = -1;
        }
        return Math.max(dp(nums, memo1, 0, len - 2), dp(nums, memo2, 1, len - 1));
    }

    public int dp(int[] nums, int[] memo, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        memo[start] = Math.max(dp(nums, memo, start + 1, end), nums[start] + dp(nums, memo, start + 2, end));
        return memo[start];
    }
}
