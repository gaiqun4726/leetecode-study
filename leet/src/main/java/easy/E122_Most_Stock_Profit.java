package easy;

public class E122_Most_Stock_Profit {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new E122_Most_Stock_Profit().maxProfit(prices));
    }

    /**
     * 找到所有相邻的极小值和极大值，计算它们的差。所有极值的差的和就是最大利润
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = prices[0];
        int maxPrice = prices[0];
        int profit = 0;
        int i = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            minPrice = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            maxPrice = prices[i];
            profit += maxPrice - minPrice;
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int profit = 0;
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return profit;
        }
        int len = prices.length;
        int cur = 1;
        while (cur < len) {
            while (cur < len && prices[cur] <= prices[cur - 1]) {
                cur++;
            }
            int buy = prices[cur - 1];
            while (cur < len && prices[cur] >= prices[cur - 1]) {
                cur++;
            }
            int sell = prices[cur - 1];
            profit += sell - buy;
        }
        return profit;
    }
}
