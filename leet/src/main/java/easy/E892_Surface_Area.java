package easy;

/**
 * @author gaiqun
 * @date 2020/3/25
 *
 * 总结
 *
 * 最开始的思路，把每个面向坐标平面投影，只要某个坐标有值，他就会贡献两个面积。
 * 计算结果是错误的。主要是如果两个柱体中间相隔一个较低的柱体，则可能会贡献四个面积。
 *
 * 换个思路思考。参考别人的解法。
 * 对于网格上每一摞正方体，如果有高度，则会贡献上下两个面，以及高度*4共四个面的面积。
 * 如果柱体与其相邻的柱体有相接，则需要两个柱体各减去一次相接的面积。
 * 因此思路是循环遍历网格每个柱体，计算2+height*4面积，然后如果相邻的柱体有相接，减去相接的面积。
 *
 * 这种计算总数，然后减去多加的部分，比较容易思考和实现。
 */
public class E892_Surface_Area {

    public static void main(String[] args) {
        E892_Surface_Area solution = new E892_Surface_Area();
        //int[][] grid = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] grid = {{2, 2, 2}, {2, 1, 2}, {2, 2, 2}};
        System.out.println(solution.surfaceArea2(grid));
    }

    // 错误解法
    public int surfaceArea(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int zArea = 0;
        int xArea = 0;
        int yArea = 0;
        int xLen = grid.length;
        int yLen = grid[0].length;
        for (int i = 0; i < xLen; i++) {
            int rowMax = 0;
            for (int j = 0; j < yLen; j++) {
                if (grid[i][j] > 0) {
                    zArea++;
                }
                rowMax = Math.max(rowMax, grid[i][j]);
            }
            xArea += rowMax;
        }
        for (int j = 0; j < yLen; j++) {
            int colMax = 0;
            for (int i = 0; i < xLen; i++) {
                colMax = Math.max(colMax, grid[i][j]);
            }
            yArea += colMax;
        }
        return 2 * (zArea + xArea + yArea);
    }

    public int surfaceArea2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int xLen = grid.length;
        int yLen = grid[0].length;
        int surface = 0;
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                int level = grid[i][j];
                if (level > 0) {
                    // 这里通过位运算代替乘除。注意位运算的优先级比加减运算要低，需要加括号
                    // 位运算用不好不如直接用乘除。
                    // 计算每个柱体共享的面积
                    surface += (level << 2) + 2;
                    // 减去行方向相接柱体多算的面积
                    surface -= i > 0 ? Math.min(level, grid[i - 1][j]) << 1 : 0;
                    // 减去列方向相接柱体多算的面积
                    surface -= j > 0 ? Math.min(level, grid[i][j - 1]) << 1 : 0;
                }
            }
        }
        return surface;
    }
}
