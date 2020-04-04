package easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2020/4/4
 *
 * 总结
 *
 * 多源BFS解法。跟最远海洋问题是一样的。
 * 开始我觉得写麻烦了，看了题解和我的解法是一样的。这题归为Easy就很迷
 */
public class E994_Rot_Orange {

    public static void main(String[] args) {
        E994_Rot_Orange solution = new E994_Rot_Orange();
        int[][] grid = {{2, 1, 1}};
        System.out.println(solution.orangesRotting(grid));
    }

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // 用数组保存上下左右坐标的变量
        int[][] steps = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean tag = false;
            // 遍历完一层，可以用标志位。或者像这里一样，用上一层的size遍历上一层的元素。
            for (int k = 0; k < size; k++) {
                int[] rot = queue.poll();
                int x = rot[0];
                int y = rot[1];
                for (int i = 0; i < 4; i++) {
                    int newX = x + steps[i][0];
                    int newY = y + steps[i][1];
                    if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        queue.offer(new int[] {newX, newY});
                        tag = true;
                    }
                }
            }
            // 最后只剩一个烂橘子，但他没感染其他橘子，则上一轮其实就已结束了。
            if (tag) {
                count++;
            }
        }
        // 最后检查是否有未感染的，如果有，则永远无法感染所有橘子，返回-1。
        int remain = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    remain++;
                }
            }
        }
        return remain != 0 ? -1 : count;
    }
}
