package medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2020/3/29
 *
 * 总结
 *
 * 最开始是暴力破解的思路，对每个海洋区域，查看它到所有陆地区域的距离里的最小值；然后求出这些值里的最大值。时间复杂度O(n^3)
 *
 * 这题的解法参考了别人的解法。使用BFS广度优先搜索。区别于一般的单源广度优先，这个题是多源广度优先搜索。
 * 从陆地区域向四周增长，当增长的一层有海洋区域时，则距离陆地的距离加一。当不能增长时，得到距离陆地的最短距离。
 * 时间复杂度O(n^2)。
 */
public class M1162_Manhattan_Distance {
    public static void main(String[] args) {
        M1162_Manhattan_Distance solution = new M1162_Manhattan_Distance();
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        System.out.println(solution.maxDistance(grid));
    }

    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        // 队列保存可以增长的陆地
        Queue<Node> queue = new LinkedList<>();
        // 所有陆地作为广度优先的源，加入队列
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new Node(i, j));
                }
            }
        }
        // 如果没有陆地或全是陆地，则返回-1
        if (queue.size() == 0 || queue.size() == row * col) {
            return -1;
        }
        // 第一层，距离是0，初始化为-1
        int depth = -1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            depth++;
            // 把该层的都遍历完，再遍历下一层，层数增加
            for (int i = 0; i < n; i++) {
                Node node = queue.poll();
                int x = node.row;
                int y = node.col;
                // 增长的方向是上下左右四个方向
                // 没超过边界，且为海洋时，才加入下一层的增长；否则不加入队列
                if (x - 1 >= 0 && grid[x - 1][y] == 0) {
                    // 把这块海洋加入下一层增长的队列
                    queue.offer(new Node(x - 1, y));
                    // 标记为1，避免其他区域重复增长
                    grid[x - 1][y] = 1;
                }
                if (x + 1 < row && grid[x + 1][y] == 0) {
                    queue.offer(new Node(x + 1, y));
                    grid[x + 1][y] = 1;
                }
                if (y - 1 >= 0 && grid[x][y - 1] == 0) {
                    queue.offer(new Node(x, y - 1));
                    grid[x][y - 1] = 1;
                }
                if (y + 1 < col && grid[x][y + 1] == 0) {
                    queue.offer(new Node(x, y + 1));
                    grid[x][y + 1] = 1;
                }
            }
        }
        return depth;
    }

    public static class Node {
        public int row;
        public int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
