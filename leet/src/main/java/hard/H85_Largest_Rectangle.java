package hard;

/**
 * @author gaiqun
 * @date 2020/3/29
 *
 * 总结
 *
 * 暴力求解。计算出每一个坐标，以这个坐标为终止的行内连续1的个数，存入二维数组dp。时间复杂度O(n^2)。
 * 接着就转化为求直方图中最大矩形面积的问题提，即Medium84题
 * 然后对于每一个点，计算以这个点为右下角的矩形的最大面积。
 * 计算方法是使用上面的dp，计算这个点上面的行能够成的最大矩形面积，时间复杂度O(n^3)。
 * 总的复杂度O(n^3)。
 *
 * 在dp问题中，经常需要存储之前的状态供后面使用。对于二维面积问题，可以以右下角为一个dp统计的切入点解决问题。
 */
public class H85_Largest_Rectangle {

    public static void main(String[] args) {
        H85_Largest_Rectangle solution = new H85_Largest_Rectangle();
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}};
        System.out.println(solution.maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
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
                    maxArea = Math.max(maxArea, minHeight * (i - k + 1));
                    k--;
                }
                maxRect = Math.max(maxArea, maxRect);
            }
        }
        return maxRect;
    }
}
