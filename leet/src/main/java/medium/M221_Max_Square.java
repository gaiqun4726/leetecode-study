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
 *
 * 其实上面的想法很接近可以解答了。本地参考Hard85题，稍微改动下就能求解。
 */
public class M221_Max_Square {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        // 记录每个点为结尾，一行内连续1的个数。
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            int count = 0;
            for (int j = 0; j < col; j++) {
                // 注意比较的是char型的'1'
                count = matrix[i][j] == '1' ? count + 1 : 0;
                dp[i][j] = count;
            }
        }
        int maxRect = 0;
        // 对每个点，计算以这个点为右下角可以构成的最大矩形面积
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int minHeight = dp[i][j];
                int k = i;
                int maxArea = 0;
                // 找当前点上面的每一行
                while (k >= 0 && dp[k][j] > 0) {
                    minHeight = Math.min(minHeight, dp[k][j]);
                    int width = i - k + 1;
                    // 这里是与求最大矩形面积的区别，计算最大正方形面积
                    if (minHeight >= width) {
                        maxArea = Math.max(maxArea, width * width);
                    }
                    k--;
                }
                maxRect = Math.max(maxArea, maxRect);
            }
        }
        return maxRect;
    }
}
