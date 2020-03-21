package medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author gaiqun
 * @date 2020/3/21
 *
 * 总结
 *
 * 1. 使用DFS，递归遍历。水壶A/B，把状态定义为以下六种：
 * 1）A清空
 * 2）B清空
 * 3）A装满
 * 4）B装满
 * 5）A倒进B，直到A倒空或B倒满
 * 6）B倒进A，直到B倒空或A倒满
 * 为避免重复操作，用二维数组记录A-B数值是否被尝试过。这里没有用Set去存Pair，一是因为编程不支持pair，二是因为引用没法去除。
 * 从A和B都是空开始，递归去尝试以上所有可能。
 * 问题在于，一共六个分支，每个都尝试一遍，栈很容易溢出，所以不能用这种递归的方法。
 *
 * 2. 使用BFS，用一个队列存放从当前状态可以转换的状态，用一个set拼接已经访问过的状态。
 * 每次从队列拿出一个状态，并把它可能转换的六个状态加入队列。注意剪枝。
 * 提交代码，依旧是TLE。因此，如果想AC，只能用数学方法了。
 *
 * 3. 数学方法
 * 贝祖定理：对于等式 ax+by=z，x、y有解当且仅当z是a，b最大公约数的倍数。
 * 在水壶问题中，两个水壶总量的变化，只能是增减x的倍数，或y的倍数，即水壶总量只能是ax+by，a、b为整数。
 * 因此只需判断z在满足z<=x+y条件下，z是不是x、y最大公约数的倍数即可。
 *
 * Die hand问题，背下来吧。
 * 一般游戏类问题，都有数学解法。这类问题只能多做了。
 */
public class M365_Bottle_Problem {

    public static void main(String[] args) {
        M365_Bottle_Problem solution = new M365_Bottle_Problem();
        System.out.println(solution.canMeasureWater(2, 6, 5));
    }

    private static boolean canSolve = false;
    private int[][] states;
    private int capacityX, capacityY;

    public boolean canMeasureWater(int x, int y, int z) {
        states = new int[x + 1][y + 1];
        capacityX = x;
        capacityY = y;
        mesure(0, 0, z);
        return canSolve;
    }

    private void mesure(int m, int n, int z) {
        if (canSolve || states[m][n] == 1) {
            return;
        }
        states[m][n] = 1;
        if (m + n == z) {
            canSolve = true;
            return;
        }
        mesure(0, n, z);
        mesure(m, 0, z);
        mesure(capacityX, n, z);
        mesure(m, capacityY, z);
        mesure(Math.max(0, m - capacityY + n), Math.min(capacityY, m + n), z);
        mesure(Math.min(capacityX, m + n), Math.max(0, n - capacityX + m), z);
    }

    private Queue<String> queue = new LinkedList<>();
    private Set<String> set = new HashSet<>();

    public boolean canMeasureWater2(int x, int y, int z) {
        capacityX = x;
        capacityY = y;
        queue.offer(buildStr(0, 0));
        while (!queue.isEmpty()) {
            String str = queue.poll();
            x = parseX(str);
            y = parseY(str);
            if (x == z || y == z || x + y == z) {
                return true;
            }
            if (!set.contains(str)) {
                set.add(str);
                queue.offer(buildStr(0, y));
                queue.offer(buildStr(x, 0));
                queue.offer(buildStr(capacityX, y));
                queue.offer(buildStr(x, capacityY));
                queue.offer(buildStr(Math.max(0, x - capacityY + y), Math.min(capacityY, x + y)));
                queue.offer(buildStr(Math.min(capacityX, x + y), Math.max(0, y - capacityX + x)));
            }
        }
        return false;
    }

    private String buildStr(int x, int y) {
        return x + "," + y;
    }

    private int parseX(String str) {
        return Integer.parseInt(str.split(",")[0]);
    }

    private int parseY(String str) {
        return Integer.parseInt(str.split(",")[1]);
    }

    public boolean canMeasureWater3(int x, int y, int z) {
        // 注意边界条件，x、y都是0没法求最大公约数
        if (z == 0 || z == x || z == y || z == x + y) {
            return true;
        }
        // 超过题目限制，即使是最大公约数的倍数也不是解
        if (z < 0 || z > x + y) {
            return false;
        }
        return z % gcd(x, y) == 0;
    }

    private int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}
