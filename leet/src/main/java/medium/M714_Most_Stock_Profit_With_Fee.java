package medium;

/**
 * @author gaiqun
 * @date 2020/12/26
 */
public class M714_Most_Stock_Profit_With_Fee {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // base case
        int dp0 = 0;
        int dp1 = -prices[0];
        int days = prices.length;
        for (int i = 1; i < days; i++) {
            // 使用临时变量，避免滚动数组被提前赋值
            int new_dp0 = Math.max(dp0, dp1 + prices[i] - fee);
            int new_dp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = new_dp0;
            dp1 = new_dp1;
        }
        return dp0;
    }
}
