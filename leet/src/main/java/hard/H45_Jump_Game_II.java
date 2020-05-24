package hard;

/**
 * @author gaiqun
 * @date 2020/5/4
 *
 * 总结
 *
 * 思路1：动态规划。dp[i]表示到达位置i的最少跳跃次数。虽然使用了dp数组，时间复杂度还是O(n^2)。
 * 92个测试用例，第91个TLE。
 *
 * 思路2：贪心算法。策略是，选择可达范围内，下一步可达最远的那一步。
 * 比如第一步，最远可到达2，那么下一步的选择应当是0到2中，下一步最远的i。即i+nums[i]最大的i。
 * 确定了一步的选择以后，这一步最远的边界end就确定了。遍历直到end的每个位置，更新下一步最远可达的边界boarder。
 * 到达end以后，这一步的选择就确定了，就是使boarder最大的i。但是我们不需要记录i，只需要把steps增加就行了。
 * 然后更新end为boarder，继续找下一步。
 * 一次遍历即可求得最少步数，时间复杂度O(N)。
 */
public class H45_Jump_Game_II {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            int steps = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= i - j && dp[j] != Integer.MAX_VALUE) {
                    steps = Math.min(steps, dp[j] + 1);
                }
            }
            dp[i] = steps;
        }
        return dp[len - 1];
    }

    public int jump2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        // end、boarder初始化为0，第一步必须跳
        int end = 0;
        int boarder = 0;
        int steps = 0;
        // 最后一个位置不要遍历，否则会多跳一次
        for (int i = 0; i < nums.length - 1; i++) {
            boarder = Math.max(boarder, nums[i] + i);
            // 到达当前边界以后，步数增加，更新边界
            if (i == end) {
                steps++;
                end = boarder;
            }
        }
        return steps;
    }

    public int jump3(int[] nums) {
        int last = nums.length - 1;
        // 下面的循环不满足只有一个元素的情形，所以单独列出来
        if (last == 0) {
            return 0;
        }
        int minSteps = 0;
        // i、j表示当前步骤可以选择的边界
        int i = 0, j = nums[0];
        // j小于last，表示还没达到目标位置。由于题目说必然可达最后位置，因此退出循环可达目标
        while (j < last) {
            int temp = 0;
            // 找到当前边界内可选择的跳的最远的位置，把i、j更新
            for (int k = i; k <= j; k++) {
                if (nums[k] + k > temp) {
                    temp = nums[k] + k;
                    i = k;
                }
            }
            j = temp;
            minSteps++;
        }
        // 退出循环，表示这一步可达目标位置，但是还没跳，所以结果加一。
        return minSteps + 1;
    }
}
