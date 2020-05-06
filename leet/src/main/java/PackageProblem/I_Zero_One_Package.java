package PackageProblem;

/**
 * @author gaiqun
 * @date 2020/5/5
 *
 * 总结
 *
 * 01背包问题，状态为dp[i][j]，表示的含义是前i个物品，放入体积为j的背包，可以获得的最大价值。
 * 状态转移方程为dp[i][j]=Max{dp[i-1][j],dp[i-1][j-c(i)]+w(i)}。
 * 最优解为Max{dp[n][0...V]}。
 *
 * 将01背包的状态降为1维，即只用体积表示状态。因为dp[i]的状态只与dp[i-1]有关，因此可以省去第一维，用滚动数组的方式使用i-1的结果。
 * 对于不需装满背包的题目，初始化的时候，如果只有一维，则每个体积都初始化为0，即0是一种解。
 * 如果需要恰好装满背包，则只有体积为0时初始化为0，其他情况初始化为负无穷。
 *
 * 降为1维以后，依旧是两重循环，外围的循环是物品的选择i，内层的循环是体积的选择j。
 * 注意内层循环需要从大到小循环，这样循环数组才能用到i-1层生成的数值。
 *
 * 虽然降为1维，理解问题上还是要从二维理解。
 * 从最终的展现行驶时来看，01背包的框架是
 * for i 1...N:
 *   for j V...c[i]:
 *     dp[j] = Max(dp[j], dp[j-c[i]] + w[i])
 * return dp[V];
 */
public class I_Zero_One_Package {

    static class Main {

        public static void main(String[] args) {
            int N = 4, V = 5;
            int[][] items = {{0, 0}, {1, 2}, {2, 4}, {3, 4}, {4, 5}};
            System.out.println(new Main().calcMaxValue(items, N, V));
        }

        /**
         * 一维状态法
         * @param items
         * @param N
         * @param V
         * @return
         */
        public int calcMaxValue(int[][] items, int N, int V) {
            int[] dp = new int[V + 1];
            for (int i = 0; i < N; i++) {
                int[] item = items[i];
                // 滚动数组，需要从大到小遍历，这样才能用到i-1的状态。
                for (int j = V; j >= item[0]; j--) {
                    // 这里相当于dp[i][j]=Math.max(dp[i-1][j]+dp[i-1][j-item[0]]+item[1]);
                    dp[j] = Math.max(dp[j], dp[j - item[0]] + item[1]);
                }
            }
            //
            return dp[V];
        }

        /**
         * 二维状态方法。为了避免i-1小于0的判断，i从1开始取。
         * @param items
         * @param N
         * @param V
         * @return
         */
        public int calcMaxValue2(int[][] items, int N, int V) {
            int[][] dp = new int[N + 1][V + 1];
            int max = 0;
            for (int i = 1; i <= N; i++) {
                int[] item = items[i];
                // j从大于等于c[i]开始取，避免内部的条件判断
                for (int j = item[0]; j <= V; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item[0]] + item[1]);
                    max = Math.max(dp[i][j], max);
                }
            }
            return max;
        }
    }
}
