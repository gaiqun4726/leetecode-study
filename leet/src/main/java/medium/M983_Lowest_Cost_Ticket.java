package medium;

/**
 * @author gaiqun
 * @date 2020/5/6
 *
 * 总结
 *
 * 动态规划求解
 *
 * 思路1：自顶向下备忘录解法。dp[i]表示第i天走完剩下行程的最少票价。
 * dp[i]=Min{dp[i+c[j]]+w[j]|j表示三种通行证的选择}，求dp[1]。
 * 第37个用例超时，因此需要优化。
 *
 * 检查了下，是因为备忘录没有赋值，备忘录没起作用所以超时。备忘录赋值后双100%。
 */
public class M983_Lowest_Cost_Ticket {

    public static void main(String[] args) {
        //int[] days = {1, 4, 6, 7, 8, 20};
        //int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] days =
                {3, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20, 21, 23, 25, 26, 27, 29, 30, 33, 34, 35, 36, 38, 39, 40,
                        42, 45, 46, 47, 48, 49, 51, 53, 54, 56, 57, 58, 59, 60, 61, 63, 64, 67, 68, 69, 70, 72, 74, 77,
                        78, 79, 80, 81, 82, 83, 84, 85, 88, 91, 92, 93, 96};
        //int[] costs = {2, 7, 15};
        int[] costs = {3, 17, 57};
        System.out.println(new M983_Lowest_Cost_Ticket().mincostTickets(days, costs));
    }

    int[] memo = new int[366];
    int[] days, costs;
    int[] ranges = {1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        return dp(0, 0);
    }

    public int dp(int range, int index) {
        // 如果这一天被range覆盖，即被上一次通行证的选择覆盖，则找下一天
        while (index < days.length && range > days[index]) {
            index++;
        }
        // 如果越界，则剩余天不需重新买票
        if (index >= days.length) {
            return 0;
        }
        int day = this.days[index];
        // 直接从备忘录取值
        if (memo[day] != 0) {
            return memo[day];
        }
        int minCost = Integer.MAX_VALUE;
        // 遍历每一种票据选择，自顶向下递归
        for (int i = 0; i < 3; i++) {
            minCost = Math.min(minCost, costs[i] + dp(day + ranges[i], index + 1));
        }
        // 别忘了给备忘录赋值。
        memo[day] = minCost;
        return minCost;
    }
}
