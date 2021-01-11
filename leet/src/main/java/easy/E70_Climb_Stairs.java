package easy;

public class E70_Climb_Stairs {
    /**
     * 这道题要从第一次爬楼梯的选择思考。如果从最后一次爬楼梯思考，会陷入死胡同
     * 爬n级楼梯，等于第一次走一级然后走n-1级，和第一次走两级然后走n-2级的和
     * f(n) = f(n-1) + f(n-2)
     */

    /**
     * 递归解法，超时
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n <= 2)
            return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 记忆化搜索算法，只需两个变量保存f(n-1)和f(n-2)
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int one_step = 1;
        int two_step = 2;
        int n_step = 0;
        for (int i = 3; i <= n; i++) {
            n_step = one_step + two_step;
            one_step = two_step;
            two_step = n_step;
        }
        return n_step;
    }

    int[] memo;

    public int climbStairs3(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        memo[2] = 2;
        return helper(n);
    }

    private int helper(int n) {
        if (memo[n] != 0) {
            return memo[n];
        }
        String s = String.valueOf(1);
        return (helper(n - 1) + helper(n - 2)) % 1000000008;
    }
}
