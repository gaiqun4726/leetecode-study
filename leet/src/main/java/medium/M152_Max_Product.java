package medium;

/**
 * @author gaiqun
 * @date 2020/5/18
 *
 * 总结
 *
 * 乘积最大子数组，动态规划。
 *
 * 由于数字可能存在正负，原来最小的负数与下一个负数相乘，可以得到较大的正数。
 * 使用dp[i][2]存储以i结尾的数字，乘积的最小dp[i][0]与最大值dp[i][1]。
 * 对每一个dp[i][2]来说，其状态转移方程为dp[i][1]=Max{i,i*dp[i][0],i*dp[i][1]}, dp[i][0]=Min{i,i*dp[i][0],i*dp[i][1]}.
 * 即保存以i结尾乘积的最小与最大值。不管i的正负，取i，i与最小值乘积、i与最大值乘积的最大值作为最大值。
 * 全局的最大值即为目标结果。
 */
public class M152_Max_Product {

    public static void main(String[] args) {
        int[] nums = {-2, 0, -1, -2, 1, 5, -3};
        M152_Max_Product solution = new M152_Max_Product();
        System.out.println(solution.maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0] = new int[] {nums[0], nums[0]};
        int maxProduct = nums[0];
        for (int i = 1; i < n; i++) {
            int[] minAndMax = new int[2];
            minAndMax[0] = Math.min(nums[i], Math.min(nums[i] * dp[i - 1][0], nums[i] * dp[i - 1][1]));
            minAndMax[1] = Math.max(nums[i], Math.max(nums[i] * dp[i - 1][0], nums[i] * dp[i - 1][1]));
            dp[i] = minAndMax;
            maxProduct = Math.max(maxProduct, minAndMax[1]);
        }
        return maxProduct;
    }
}
