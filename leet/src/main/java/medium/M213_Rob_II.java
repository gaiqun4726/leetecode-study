package medium;

/**
 * @author gaiqun
 * @date 2020/12/8
 *
 * 打家劫舍2。房屋连成环。
 */
public class M213_Rob_II {

    public static void main(String[] args) {
        //int[] nums = {2,3,2};
        int[] nums = {1, 2, 3, 1};
        M213_Rob_II solution = new M213_Rob_II();
        System.out.println(solution.rob(nums));
    }

    /**
     * 复用打家劫舍1的动态规划思路。分别考虑必须光临第一家和必须不光临第一家两种情形，分别做一次动态规划。
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
}
