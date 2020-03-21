package easy;

/**
 * @author gaiqun
 * @date 2020/3/18
 *
 * 总结
 *
 * 最开始的思路，如果矩形的端点位于另一个矩形内则有重叠，否则没有。
 * 这个思路是不充分的。如果两个矩形以十字架的形式重叠，这个条件判断不出来。
 *
 * 参考解题思路，有两种解决方法。一种是判断矩形不在另一个矩形的上下左右，则一定重叠。另一种是把矩形投影到xy轴，投影都重叠则矩形重叠
 * 代码量少所以归类为简单题目，不过思路还是蛮好的。
 */
public class E836_Rect_Intersect {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return inside(new int[] {rec1[0], rec1[1]}, rec2)
                || inside(new int[] {rec1[0], rec1[3]}, rec2)
                || inside(new int[] {rec1[2], rec1[1]}, rec2)
                || inside(new int[] {rec1[2], rec1[3]}, rec2)
                || inside(new int[] {rec2[0], rec2[1]}, rec1)
                || inside(new int[] {rec2[0], rec2[3]}, rec1)
                || inside(new int[] {rec2[2], rec2[1]}, rec1)
                || inside(new int[] {rec2[2], rec2[3]}, rec1);
    }

    private boolean inside(int[] points, int[] rec) {
        return points[0] < rec[2] && points[0] > rec[0] && points[1] < rec[3] && points[1] > rec[1];
    }

    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        // 不在上下左右，则一定重叠
        return !(rec1[1] >= rec2[3] || rec1[3] <= rec2[1] || rec1[2] <= rec2[0] || rec1[0] >= rec2[2]);
    }

    public boolean isRectangleOverlap3(int[] rec1, int[] rec2) {
        // 投影后，x轴右端点的最小值需要大于左端点的最大值才会重叠，y轴也需要同时满足这个条件。
        return Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2]) && Math.max(rec1[1], rec2[1]) < Math.min(rec1[3],
                rec2[3]);
    }
}
