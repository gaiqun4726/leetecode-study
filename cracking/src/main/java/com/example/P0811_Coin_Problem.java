package com.example;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/4/23
 */
public class P0811_Coin_Problem {

    public static void main(String[] args) {
        P0811_Coin_Problem solution = new P0811_Coin_Problem();
        System.out.println(solution.waysToChange(10));
    }

    private final static int mod = 1000000007;
    private final static int[] coins = {25, 10, 5, 1};

    public int waysToChange(int n) {
        int[] f = new int[n + 1];
        Arrays.fill(f, 0);
        f[0] = 1;
        for (int c = 0; c < 4; ++c) {
            int coin = coins[c];
            for (int i = coin; i <= n; ++i) {
                f[i] = (f[i] + f[i - coin]) % mod;
            }
        }
        return f[n];
    }
}

