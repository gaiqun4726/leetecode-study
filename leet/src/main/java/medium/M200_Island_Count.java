package medium;

/**
 * @author gaiqun
 * @date 2020/4/20
 *
 * 总结
 *
 * dfs遍历，加访问标记。直接在原数组上访问标记
 */
public class M200_Island_Count {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(i, j, grid);
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (i >= row || i < 0 || j >= col || j < 0 || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        int[][] steps = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int k = 0; k < steps.length; k++) {
            dfs(i + steps[k][0], j + steps[k][1], grid);
        }
    }
}
