package hard;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/3/22
 *
 * 总结
 *
 * 利用动态规划求解。按照信封大小排序后，dp存储这个信封最多可以套的信封个数。
 * 转移方程为dp[i] = max(dp[j]+1)，其中dp[j]为可以放进该信封的较小信封。
 * 排序时间复杂度O(nlogn)，遍历时间复杂度O(n^2)，总的时间复杂度O(n^2)，空间复杂度O(n)。
 */
public class H354_Russian_Envelope {

    public static void main(String[] args) {
        H354_Russian_Envelope solution = new H354_Russian_Envelope();
        int[][] envelopes = {{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}};
        System.out.println(solution.maxEnvelopes(envelopes));
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        // 先按宽排列，宽相同按长排列
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int len = envelopes.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int maxEnvelops = 1;
        // 遍历每个信封
        for (int i = 1; i < len; i++) {
            int max = 1;
            // 小于当前宽的所有信封
            for (int j = 0; j < i; j++) {
                // 能放进当前信封才计算。能放进的条件是小于，没有等于。
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            // 遍历时直接统计最大值，不用结束后在dp里找最大值。
            maxEnvelops = Math.max(maxEnvelops, dp[i]);
        }
        return maxEnvelops;
    }
}
