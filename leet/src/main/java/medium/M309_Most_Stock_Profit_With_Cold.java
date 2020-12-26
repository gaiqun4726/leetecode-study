package medium;

/**
 * @author gaiqun
 * @date 2020/12/26
 */
public class M309_Most_Stock_Profit_With_Cold {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int days = prices.length;
        int[][] dp = new int[days][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(prices[1] - prices[0], 0);
        dp[1][1] = Math.max(-prices[0], -prices[1]);
        for (int i = 2; i < days; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // i-2天未交易才可在i天交易
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[days - 1][0];
    }
}
