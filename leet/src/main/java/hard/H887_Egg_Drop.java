package hard;

import java.util.Arrays;

/**
 * @author gaiqun
 * @date 2020/4/11
 *
 * 总结
 *
 * 初始思路：找规律，每次用一个蛋把剩余的部分二分。推导出公式 移动次数=(n/2^k)+k-1。
 * 结果是错误的，因此不能这么求。
 *
 * 解法：动态规划+二分查找
 */
public class H887_Egg_Drop {

    public static void main(String[] args) {
        H887_Egg_Drop solution = new H887_Egg_Drop();
        System.out.println(solution.superEggDrop(1, 2));
    }

    public int superEggDrop(int K, int N) {
        if (K == 1) {
            return N;
        }
        if (K >= log2(N)) {
            return (int) Math.ceil(log2(N));
        } else {
            return (int) Math.ceil(N / Math.pow(2, K)) + K - 1;
        }
    }

    public double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    //public int superEggDrop2(int K, int N) {
    //    //Arrays.fill();
    //}

}
