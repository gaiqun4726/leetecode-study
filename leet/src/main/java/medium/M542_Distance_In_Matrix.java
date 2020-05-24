package medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2020/4/15
 *
 * 总结
 *
 * 跟最近海洋问题是一样的问题，多源广度优先遍历。
 */
public class M542_Distance_In_Matrix {

    public static void main(String[] args) {
        M542_Distance_In_Matrix solution = new M542_Distance_In_Matrix();
        int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] res = solution.updateMatrix(matrix);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        // 需要一个visited数组。因为数组里只有0和1，对于数值1，没办法判断是访问还是没访问过。
        int[][] visited = new int[row][col];
        int[][] step = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                    visited[i][j] = 1;
                }
            }
        }
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] cord = queue.poll();
                for (int i = 0; i < step.length; i++) {
                    int x = cord[0] + step[i][0];
                    int y = cord[1] + step[i][1];
                    if (x < row && x >= 0 && y < col && y >= 0 && visited[x][y] == 0) {
                        matrix[x][y] = level;
                        queue.offer(new int[] {x, y});
                        visited[x][y] = 1;
                    }
                }
                size--;
            }
            level++;
        }
        return matrix;
    }
}
