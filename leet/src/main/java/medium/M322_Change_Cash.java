package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaiqun
 * @date 2020/4/12
 *
 * 总结
 *
 * 动态规划问题
 *
 * 思路1：贪心求解，回溯并剪枝。这个算法要么超时，要么求解错误。
 * 考虑这种情形：coins = [1,7,10]，amount = 14。使用贪心算法，求解会是：1+1+1+1+10共5枚。
 * 但最优解是7+7共两枚。如果不剪枝，遍历所有结果然后找最小值，可以得到答案，但是会超时。
 *
 * 综上，不能用贪心算法求解。
 *
 * 思路2：动态规划
 *
 * 自顶向下备忘录，自底向上dp表。自底向上迭代方法更快。
 *
 * 求最值问题是动态规划的范畴。问题具有最有子结构，问题间相互独立。
 * 找到状态，定义dp，找到状态转移方程，初始化边界值。
 *
 * 状态如何找到？可以从最终最值设计状态。状态是变化的部分，从题目中找到变化的变量，看可不可以作为状态。
 *
 * 本题的状态就是不同目标金额，需要的最少硬币数。
 */
public class M322_Change_Cash {

    public static void main(String[] args) {
        int[] coins = {186, 419, 83, 408};
        int amount = 6249;
        //int[] coins = {1, 2, 5};
        //int amount = 11;
        M322_Change_Cash solution = new M322_Change_Cash();
        System.out.println(solution.coinChange3(coins, amount));
    }

    public int[] cents;
    public Map<Integer, Integer> counts;
    public boolean flag;
    public int minCount = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount <= 0) {
            return 0;
        }
        Arrays.sort(coins);
        cents = coins;
        counts = new HashMap<>();
        for (int cent : cents) {
            counts.put(cent, 0);
        }
        flag = false;
        backtrace(amount);
        return flag ? minCount : -1;
    }

    public void backtrace(int remain) {
        // 这里剪枝，找到结果就返回，贪心得不到全局最优解
        if (flag) {
            return;
        }
        // 如果不剪枝，回溯求解所有值，可以得到结果，但是超时。
        for (int i = cents.length - 1; i >= 0; i--) {
            if (remain == 0) {
                flag = true;
                int count = 0;
                for (int cnt : counts.keySet()) {
                    count += counts.get(cnt);
                }
                minCount = Math.min(minCount, count);
                return;
            }
            int cent = cents[i];
            if (cent <= remain) {
                counts.put(cent, counts.get(cent) + 1);
                backtrace(remain - cent);
                counts.put(cent, counts.get(cent) - 1);
            }
        }
    }

    private Map<Integer, Integer> memo;

    private Integer MAX = Integer.MAX_VALUE;

    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount <= 0) {
            return 0;
        }
        memo = new HashMap<>();
        int count = helper(coins, amount);
        return count;
    }

    public int helper(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }
        int minCount = MAX;
        for (int coin : coins) {
            int count = helper(coins, amount - coin);
            if (count == -1) {
                continue;
            }
            minCount = Math.min(minCount, count + 1);
        }
        if (minCount != MAX) {
            memo.put(amount, minCount);
        } else {
            memo.put(amount, -1);
        }
        return memo.get(amount);
    }

    public int coinChange3(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount <= 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int minCount = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MIN_VALUE && dp[i - coin] != -1) {
                    minCount = Math.min(minCount, dp[i - coin] + 1);
                }
            }
            if (minCount != Integer.MAX_VALUE) {
                dp[i] = minCount;
            } else {
                dp[i] = -1;
            }
        }
        return dp[amount];
    }
}
