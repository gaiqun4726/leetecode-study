package easy;

/**
 * @author gaiqun
 * @date 2020/4/18
 *
 * 总结
 *
 * 位运算
 */
public class E461_Hamming_Distance {

    public int hammingDistance(int x, int y) {
        // 汉明距离，异或计算
        int val = x ^ y;
        int distance = 0;
        while (val != 0) {
            // 计算1的个数，与1进行与运算
            distance += val & 1;
            // 除以2，右移一位
            val = val >> 1;
        }
        return distance;
    }
}
