package medium;

/**
 * @author gaiqun
 * @date 2020/3/15
 *
 * 总结
 *
 * 深度优先遍历，采用递归求解。从每个种子点开始，向上下左右四个方向递归增长。不能增长则回溯。
 *
 * 为了避免重复计算，使用一个二维数组标记已经访问过的结点，进行剪枝。同时注意不要越界。
 *
 * 时间复杂度？
 *
 * 官方解答也是使用深度优先遍历。区别在于不需要定义一个标记数组。只需要把访问过的位置置为0，下次不访问就可以了。
 *
 * 另一个解法是使用栈，其实跟深度优先是一样的。
 */
public class M695_Bigest_Island_Square {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        // 标记访问过元素的二维数组
        int[][] array = new int[grid.length][grid[0].length];
        int maxArea = 0;
        // 从每一个种子去生长
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxArea = Math.max(maxArea, computeSquare(i, j, grid, array));
            }
        }
        // 返回最大岛屿面积。
        return maxArea;
    }

    /**
     * 递归计算岛屿大小
     * @param x
     * @param y
     * @param grid
     * @param array
     * @return
     */
    int computeSquare(int x, int y, int[][] grid, int[][] array) {
        // 不能越过数组边界，不访问0元素，不访问已经访问过的元素。
        if (x < 0 || y < 0 || x > grid.length - 1 || y > grid[0].length - 1 || grid[x][y] == 0 || array[x][y] == 1) {
            return 0;
        }
        // 标记已经访问过当前结点。
        array[x][y] = 1;
        // 递归访问四个方向的结点。
        int up = computeSquare(x, y - 1, grid, array);
        int down = computeSquare(x, y + 1, grid, array);
        int left = computeSquare(x - 1, y, grid, array);
        int right = computeSquare(x + 1, y, grid, array);
        // 汇总岛屿面积。
        return 1 + up + down + left + right;
    }

    public int maxAreaOfIsland2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        // 从每一个种子去生长
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxArea = Math.max(maxArea, computeSquare2(i, j, grid));
            }
        }
        // 返回最大岛屿面积。
        return maxArea;
    }

    /**
     * 递归计算岛屿大小
     * @param x
     * @param y
     * @param grid
     * @return
     */
    int computeSquare2(int x, int y, int[][] grid) {
        // 不能越过数组边界，不访问0元素，不访问已经访问过的元素。
        if (x < 0 || y < 0 || x > grid.length - 1 || y > grid[0].length - 1 || grid[x][y] == 0) {
            return 0;
        }
        // 标记已经访问过当前结点。
        grid[x][y] = 0;
        // 递归访问四个方向的结点。
        int up = computeSquare2(x, y - 1, grid);
        int down = computeSquare2(x, y + 1, grid);
        int left = computeSquare2(x - 1, y, grid);
        int right = computeSquare2(x + 1, y, grid);
        // 汇总岛屿面积。
        return 1 + up + down + left + right;
    }
}
