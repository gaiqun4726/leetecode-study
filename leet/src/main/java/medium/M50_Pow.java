package medium;

/**
 * @author gaiqun
 * @date 2020/5/11
 *
 * 总结
 *
 * 快速幂法。对于每一个x的幂次n可能是奇数或偶数，可以表示为2k或者2k+1的形式。
 * 利用递归，求n次幂，则求k次幂与k次幂的乘积，如果是奇数就再乘一遍x。
 * 注意幂次可能是负整数。
 */
public class M50_Pow {

    public static void main(String[] args) {
        M50_Pow solution = new M50_Pow();
        // 边界值结果不对，不知道为什么
        System.out.println(solution.myPow(2.0, -2147483648));
        System.out.println(1.0/0);
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        // x等于零与其他数值没区别，这个分支也不需要
        if (x == 0 && n >= 0) {
            return x;
        }
        // 对于1.0/0的情形，double已经考虑到除零问题了，所以可以不用考虑这个分支
        if (x == 0 && n < 0) {
            return Double.POSITIVE_INFINITY;
        }
        boolean positive = n > 0;
        n = Math.abs(n);
        int half = n / 2;
        double remain = (n & 1) == 0 ? 1 : x;
        // 这里，相当于递归调了两遍，会导致TLE。把递归结果保存到y里，然后直接用y相乘，就不会超时了。
        //double result = myPow(x, half) * myPow(x, half) * remain;
        double y = myPow(x, half);
        double result = y * y * remain;
        return positive ? result : 1 / result;
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }
}
