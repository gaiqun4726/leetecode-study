package easy;

public class E121_Best_Stock_Profit {
    public static void main(String[] args) {
        E121_Best_Stock_Profit solution = new E121_Best_Stock_Profit();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfit3(prices));
    }

    /**
     * 找到最小值，以及之后的最大值，它们的差就是最大利润
     * minPrice保存最小值，maxProfit保存当前值和最小值之差的最大值
     * 每次找到比minPrice小的值则更新极小值；每次找到比minPrice大的值，则计算它和minPrice的差，并和maxPrice比较
     * 一次遍历即可求出全局最大利润
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] <= minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length == 0) {
            return maxProfit;
        }
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // 初始值，第一天就买入
        int hold = -prices[0];
        int not_hold = 0;
        int days = prices.length;
        for (int i = 1; i < days; i++) {
            // 由于只能买一次，因此要么之前持有，要么之前不持有本次持有
            int new_hold = Math.max(hold, -prices[i]);
            int new_not_hold = Math.max(not_hold, hold + prices[i]);
            hold = new_hold;
            not_hold = new_not_hold;
        }
        return not_hold;
    }
}
