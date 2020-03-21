package easy;

public class E121_Best_Stock_Profit {
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
        if (prices == null || prices.length == 0)
            return 0;
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] <= minPrice)
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }
}
