package com.example;

/**
 * @author gaiqun
 * @date 2020/5/24
 */
public class Q29_Spin_Print {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[] result = new Q29_Spin_Print().spiralOrder(matrix);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] result = new int[row * col];
        int index = 0;
        // 用左上、右下坐标作为标记
        int tr = 0, tc = 0, br = row - 1, bc = col - 1;
        // 只有小于边界才循环打印
        while (tc < bc && tr < br) {
            // 每次循环开始时，当前打印坐标需要往里走一层
            int cr = tr, cc = tc;
            while (cc < bc) {
                result[index++] = matrix[cr][cc++];
            }
            while (cr < br) {
                result[index++] = matrix[cr++][cc];
            }
            while (cc > tc) {
                result[index++] = matrix[cr][cc--];
            }
            while (cr > tr) {
                result[index++] = matrix[cr--][cc];
            }
            tc++;
            tr++;
            bc--;
            br--;
        }
        // 如果重叠，证明是奇数行，会有一行没有打印
        if (tr == br) {
            // 这里列要取小于等于
            while (tc <= bc) {
                result[index++] = matrix[tr][tc++];
            }
        }
        if (tc == bc) {
            while (tr <= br) {
                result[index++] = matrix[tr++][tc];
            }
        }
        return result;
    }
}
