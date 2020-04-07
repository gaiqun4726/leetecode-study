package com.example;

/**
 * @author gaiqun
 * @date 2020/4/7
 *
 * 总结
 *
 * 旋转矩阵问题，都是利用翻转的方法解决。沿对角线，沿中轴线
 */
public class P0107_Rotate_Matrix {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int N = matrix.length;
        int half = N / 2;
        int temp = 0;
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < N; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[N - i - 1][j];
                matrix[N - i - 1][j] = temp;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
