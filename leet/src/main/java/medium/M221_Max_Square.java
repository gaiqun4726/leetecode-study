package medium;

/**
 * @author gaiqun
 * @date 2020/3/24
 *
 * 总结
 *
 * 第一个想法是暴力求解。每一行找到连续的1，然后向下判断能不能构成一个1的正方形。然后继续找这一行的连续的1，直到这行结束。
 * 对每一行重复上述过程。
 * 每个正方形都要遍历一遍下面的行和列，总的时间复杂度O(n^3)。最关键的是，太繁琐了，我写了一半写不下去了。
 * 条件判断需要很细心，循环太多，很容易出错。不能这么弄。
 */
public class M221_Max_Square {
    public int maximalSquare(char[][] matrix) {
        int maxSquare = 0;
        if(matrix==null||matrix.length==0||matrix[0]==null||matrix[0].length==0) {
            return maxSquare;
        }
        int row = matrix.length;
        int column = matrix[0].length;

        return 0;
    }
}
