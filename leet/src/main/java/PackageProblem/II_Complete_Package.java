package PackageProblem;

/**
 * @author gaiqun
 * @date 2020/5/5
 *
 * 总结
 *
 * 完全背包问题，状态dp[i][j]与01背包一样，表示前i个物品放入体积j的背包的最大价值。
 * 状态转移方程 dp[i][j]=Max{dp[i-1][j-k*c[i]]+k*w[i]|k*c[i]<=j}
 * 由于每种物品可选无限次，因此每种物品可选的个数应当不超过当前背包最大容量。
 *
 * 降为1维。首先上面的状态转移方程可以等价的转化为dp[i][j]=Max{dp[i-1][j],dp[i][j-c[i]]+w[i]}。
 * 有优化条件：如果c[i]>=c[j]&&w[i]<=w[j]，则可以无条件不考虑第j件物品。
 * 因为dp[i][j-c[i]]这一项，必然是在dp[i][j]之前已经生成过的项;
 * 在生成dp[i][j-c[i]]的时候，求的是Max{dp[i-1][j-c[i]],dp[i][j-2*c[i]]+2*w[i]}。
 * 而这里的dp[i-1][j-c[i]]必然是小于dp[i-1][j]的（因为背包可用体积更小）。
 * 综上，dp[i][j] = Max{dp[i-1][j],dp[i][j-c[i]]+w[i]}与dp[i][j]=Max{dp[i-1][j-k*c[i]]+k*w[i]|k*c[i]<=j}
 * 是等价的。
 *
 * 因此，将01背包的一维方法稍微改动，即体积的遍历方式改为从小到大，这样就可以用到第i层已经计算过的数值了。
 *
 * 虽然降为1维，理解问题上还是要从二维理解。
 * 从最终的展现行驶时来看，完全背包的框架是
 * for i 1...N:
 *   for j c[i]...V:
 *     dp[j] = Max(dp[j], dp[j-c[i]] + w[i])
 * return dp[V];
 */
public class II_Complete_Package {

    static class Main {

        public static void main(String[] args) {
            int N = 4, V = 5;
            int[][] items = {{0, 0}, {1, 2}, {2, 4}, {3, 4}, {4, 5}};
            System.out.println(new Main().calcMaxValue2(items, N, V));
        }

        public int calcMaxValue(int[][] items, int N, int V) {
            int[] dp = new int[V + 1];
            for (int i = 0; i < N; i++) {
                int[] item = items[i];
                for (int j = item[0]; j <= V; j++) {
                    // 这里表示的是dp[i][j]=Math.max(dp[i-1][j],dp[i][j-item[0]]+item[1]);
                    dp[j] = Math.max(dp[j], dp[j - item[0]] + item[1]);
                }
            }
            return dp[V];
        }

        public int calcMaxValue2(int[][] items, int N, int V) {
            int[][] dp = new int[N + 1][V + 1];
            int max = 0;
            for (int i = 1; i <= N; i++) {
                int[] item = items[i];
                for (int j = item[0]; j <= V; j++) {
                    for (int k = 0; k * item[0] <= j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * item[0]] + k * item[1]);
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
            return max;
        }
    }
}
