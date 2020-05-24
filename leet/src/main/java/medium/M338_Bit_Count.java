package medium;

/**
 * @author gaiqun
 * @date 2020/5/3
 *
 * 总结
 *
 * 可以用dp求解，关键就是找数字之间1的位数的关系。
 * 对于奇数来说，它的1的位数比之前的偶数位数多1。
 * 对于偶数来说，它与右移一位，即除以2的数字的1的位数一样多。
 */
public class M338_Bit_Count {

    public static void main(String[] args) {
        M338_Bit_Count solution = new M338_Bit_Count();
        System.out.println(solution.countBits(2));
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int count = 0;
            int val = i;
            while (val != 0) {
                if ((val & 1) == 1) {
                    count++;
                }
                val = val >> 1;
            }
            result[i] = count;
        }
        return result;
    }

    public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;
        for (int i = 1; i <= num; i++) {
            // 与运算优先级较低，要用括号
            // 判断奇偶，不一定用取余。与1进行与运算也可以。
            result[i] = (i & 1) == 0 ? result[i / 2] : result[i - 1] + 1;
        }
        return result;
    }
}
