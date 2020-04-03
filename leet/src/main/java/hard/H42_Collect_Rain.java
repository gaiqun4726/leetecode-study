package hard;

/**
 * @author gaiqun
 * @date 2020/3/22
 *
 * 总结
 *
 * 这道题我没有思路，都是参考别人的解法。
 *
 * 思路1：汇总每一列可能的需水量。
 * 对于每一列，找到其左右两侧最高的柱子高度，如果其中较小的大于当前柱子，则当前柱子可以蓄水，需水量为min(maxLeft,maxRight)-height.
 * 我的最开始的思路跟这个类似，想不出解法的原因是，想要找到两个最高柱子直接区域水的和，但是这时需要记录两个柱子之间每个柱子的蓄水量，太复杂了。
 * 经验就是，可以把问题减小到最小规模，这样便于分析问题。然后再一点点优化，不要指望一下就能想到最优解。
 * 时间复杂度O(n^2)。
 *
 * 思路2：动态规划
 * 基于思路1，每个柱子找其左右最高的柱子，可以变为对于柱子i，找柱子i-1左侧最高的柱子和i-1直接较大的值；右边同理。
 * 利用动态规划，减少每个柱子重复比较所有柱子的操作。
 * 动态规划空间换时间，时间复杂度O(n)，空间复杂度O(n)
 *
 * 思路3：双指针
 * 基于思路2，通过双指针，避免存储表示每个位置最大左右柱子的dp数组，从而降低空间复杂度。
 * 这个解法从优化空间复杂度的思路可以理解，但是如果直接理解双指针的思想，我现在理解不了。先不实现这个解法。
 * 时间复杂度O(n)，空间复杂度O(1)。
 */
public class H42_Collect_Rain {

    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int total = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int j = i - 1, k = i + 1;
            int maxLeft = 0;
            int maxRight = 0;
            while (j >= 0) {
                maxLeft = Math.max(maxLeft, height[j--]);
            }
            while (k <= height.length - 1) {
                maxRight = Math.max(maxRight, height[k++]);
            }
            int border = Math.min(maxLeft, maxRight);
            if (border > height[i]) {
                total += border - height[i];
            }
        }
        return total;
    }

    public int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int total = 0;
        int len = height.length;
        int[] dpLeft = new int[len];
        int[] dpRight = new int[len];
        dpLeft[0] = 0;
        dpRight[len - 1] = 0;
        for (int i = 1; i <= len - 1; i++) {
            dpLeft[i] = Math.max(dpLeft[i - 1], height[i - 1]);
        }
        for (int j = len - 2; j >= 0; j--) {
            dpRight[j] = Math.max(dpRight[j + 1], height[j + 1]);
        }
        for (int i = 1; i < len; i++) {
            int border = Math.min(dpLeft[i], dpRight[i]);
            if (border > height[i]) {
                total += border - height[i];
            }
        }
        return total;
    }

    public int trap3(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = 0;
        right[len - 1] = 0;
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }
        int count = 0;
        for (int i = 1; i < len - 1; i++) {
            int limit = Math.min(left[i], right[i]);
            if (limit > height[i]) {
                count += limit - height[i];
            }
        }
        return count;
    }
}
