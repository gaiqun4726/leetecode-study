package medium;

public class M343_Integer_Break {
    public static void main(String[] args) {
        System.out.println(new M343_Integer_Break().integerBreak(10));
    }

    int[] dp = new int[58];
    int max = 0;

    public int integerBreak(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            max = 0;
            for (int j = 1; j < (i / 2 + 1); j++) {
                int value = dp[j] * dp[i - j];
                max = max > value ? max : value;
            }
            dp[i] = max;
        }
        return dp[n];
    }
}
