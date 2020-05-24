package com.example;

/**
 * @author gaiqun
 * @date 2020/4/8
 *
 * 深度优先遍历DFS。
 */
public class Q13_Robot_Move {

    public static void main(String[] args) {
        Q13_Robot_Move solution = new Q13_Robot_Move();
        System.out.println(solution.movingCount(3, 1, 0));
    }

    public int M;
    public int N;
    public int K;
    public int count = 0;

    // 因为从网格原点开始移动，横纵坐标都为正，所以只会往右或上移动
    public int[][] steps = {{0, 1}, {1, 0}};
    // 需要访问标记，否则没法终止
    public int[][] flags;

    public int movingCount(int m, int n, int k) {
        M = m;
        N = n;
        K = k;
        flags = new int[M][N];
        backtrace(0, 0);
        return count;
    }

    /**
     * 回溯求解
     * @param x
     * @param y
     */
    public void backtrace(int x, int y) {
        for (int i = 0; i < steps.length; i++) {
            // 访问过则返回；校验不通过则返回
            if (!check(x, y) || flags[x][y] == 1) {
                return;
            }
            count++;
            flags[x][y] = 1;
            backtrace(x + steps[0][0], y + steps[0][1]);
            backtrace(x + steps[1][0], y + steps[1][1]);
        }
    }

    private boolean check(int x, int y) {
        if (x > M - 1 || x < 0 || y > N - 1 || y < 0) {
            return false;
        }
        int cnt = 0;
        while (x > 0) {
            cnt += x % 10;
            x /= 10;
        }
        while (y > 0) {
            cnt += y % 10;
            y /= 10;
        }
        return cnt <= K;
    }
}
