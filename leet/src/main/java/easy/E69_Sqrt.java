package easy;

/**
 * @author gaiqun
 * @date 2020/2/19
 */
public class E69_Sqrt {

    public static void main(String[] args) {
        E69_Sqrt solution = new E69_Sqrt();
        System.out.println(solution.mySqrt(111));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.sqrt(Integer.MAX_VALUE));
        System.out.println(Math.pow(2, 15));
    }

    /**
     * 二分法求平方根。使用二分法时要注意的一点就是，需要确认输入数据的范围
     * 因为每次都是求平方，如果平方赋值给整形，则有可能越界
     * 如果输入范围是整形（32位），则可以通过赋值给long（64位）来解决问题
     * 否则就是求出整形平方根的最大值，过滤掉大于它的数字，不过比较麻烦
     * 二分法关键记住数据范围就可以了。
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        long low = 0;
        long high = x;
        long mid = x / 2;
        long res = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            long pow = mid * mid;
            if (pow <= x && (mid + 1) * (mid + 1) > x) {
                res = mid;
                break;
            } else if (pow > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) res;
    }

    /**
     * 牛顿法。通过快速迭代，不断逼近目标值。
     * 设输入为C，则目标值构成的方程为`y=x^2-C`，方程的零值即为平方根。
     * 对方程求导得到`y'=2x`。取初始值x=x_，带入原方程得到(x_,x_^2-C)，带入导数方程得到该点的斜率为2x_。
     * 利用斜截式得到切线方程`y=2x_(x-x_)+x_^2-C`，简化为`y=2x_x-x_^2-C`。
     * 取y等于0，得到`x=1/2*(x_+C/x_)`
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        // 如果初始值是最大整数，下面初始值加1会越界。这里减1不影响最终结果
        if (x == Integer.MAX_VALUE) {
            x = x - 1;
        }
        double prev = x;
        double cur = (x + 1) / 2;
        while (prev - cur > 1e-6) {
            prev = cur;
            cur = (prev + x / prev) / 2;
        }
        return (int) cur;
    }
}
