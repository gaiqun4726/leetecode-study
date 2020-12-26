package hard;

/**
 * @author gaiqun
 * @date 2020/12/26
 */
public class H188_Most_Stock_Profit_IV {
    public static void main(String[] args) {
        H188_Most_Stock_Profit_IV solution = new H188_Most_Stock_Profit_IV();
        int[] prices = {3, 2, 6, 5, 0, 3};
        System.out.println(solution.maxProfit(2, prices));
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int days = prices.length;
        // 状态：第i天，做了j次买卖后，最大收益
        // k可以取到，因此数组第二维长度为k+1。第三维表示选择
        int[][][] profits = new int[days][k + 1][2];

        for (int i = 0; i < days; i++) {
            for (int j = 0; j <= k; j++) {
                // base case，第0天收益
                if (i == 0) {
                    profits[i][j][0] = 0;
                    profits[i][j][1] = -prices[i];
                    continue;
                }
                // base case，没有可买卖次数的收益
                if (j == 0) {
                    profits[i][j][0] = 0;
                    profits[i][j][1] = 0;
                    continue;
                }
                profits[i][j][0] = Math.max(profits[i - 1][j][0], profits[i - 1][j][1] + prices[i]);
                profits[i][j][1] = Math.max(profits[i - 1][j][1], profits[i - 1][j - 1][0] - prices[i]);
            }
        }
        return profits[days - 1][k][0];
    }

}
