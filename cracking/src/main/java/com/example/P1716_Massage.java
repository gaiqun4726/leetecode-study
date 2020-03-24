package com.example;

/**
 * @author gaiqun
 * @date 2020/3/24
 *
 * 总结
 *
 * 动态规划，注意边界问题。多设计一些测试用例，并自测边界
 */
public class P1716_Massage {

    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // 如果只有一个元素，直接返回这个元素
        if (len == 1) {
            return nums[0];
        }
        // 如果元素个数是2，需要下标0、1比较
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int maxTime = 0;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = nums[1];
        // 这里需要找不相邻的元素，所以下标最早从2开始
        for (int i = 2; i < len; i++) {
            int temp = 0;
            for (int j = i - 2; j >= 0; j--) {
                temp = Math.max(temp, dp[j] + nums[i]);
            }
            dp[i] = temp;
            // 这里在迭代里计算最大值，但是循环从2开始，所以最大值的比较没有比较0、1下标的元素。
            maxTime = Math.max(maxTime, dp[i]);
        }
        // 如果元素个数是3个，需要和下标是1的元素比较一下。
        return Math.max(nums[1], maxTime);
    }
}
