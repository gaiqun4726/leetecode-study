package easy;

/**
 * @author gaiqun
 * @date 2020/5/3
 *
 * 总结
 *
 * 求无符号整数中1的个数。数字n和n-1进行与运算，会把n最后一个1变为0。
 * 把n与n-1进行与运算，直到n变为0。
 */
public class E191_Haming_Weight {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= n - 1;
        }
        return count;
    }
}
