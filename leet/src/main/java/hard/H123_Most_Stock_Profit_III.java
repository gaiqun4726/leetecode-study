package hard;

/**
 * @author gaiqun
 * @date 2020/12/26
 */
public class H123_Most_Stock_Profit_III {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int days = prices.length;
        int k = 2;
        int[][][] profits = new int[days][k + 1][2];
        for (int i = 0; i < days; i++) {
            for (int j = 0; j <= k; j++) {
                if (i == 0 || j == 0) {
                    profits[i][j][0] = 0;
                    profits[i][j][1] = -prices[i];
                    continue;
                }
                profits[i][j][0] = Math.max(profits[i - 1][j][0], profits[i - 1][j][1] + prices[i]);
                profits[i][j][1] = Math.max(profits[i - 1][j][1], profits[i - 1][j - 1][0] - prices[i]);
            }
        }
        return profits[days - 1][k][0];
    }
}
