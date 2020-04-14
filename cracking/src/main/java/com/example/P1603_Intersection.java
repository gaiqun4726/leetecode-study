package com.example;

/**
 * @author gaiqun
 * @date 2020/4/12
 *
 * 总结
 *
 * 解题思路是通过求解方程组，求解两条直线，然后求交点。判断交点在不在直线上。
 * 难点在于要分情况讨论，还不能头脑混乱。
 * 直接抄了别人的题解。
 */
public class P1603_Intersection {

    public static void main(String[] args) {
        P1603_Intersection solution = new P1603_Intersection();
        int[] start1 = {0, 0};
        int[] end1 = {1, -1};
        int[] start2 = {0, 0};
        int[] end2 = {-1, 1};

        //int[] start1 = {0, 3};
        //int[] end1 = {0, 6};
        //int[] start2 = {0, 1};
        //int[] end2 = {0, 5};
        double[] intersection = solution.intersection(start1, end1, start2, end2);
        if (intersection.length > 0) {
            System.out.println(intersection[0] + " " + intersection[1]);
        }
    }

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {

        // 为了避免给后面使用造成不便，修改了入参声明

        Point[] points = checkAndConvertIntoPoint(start1, end1, start2, end2);
        Point newStart1 = points[0];
        Point newEnd1 = points[1];
        Point newStart2 = points[2];
        Point newEnd2 = points[3];

        // 封装成直线类，以便计算斜率和截距
        Line line1 = new Line(newStart1, newEnd1);
        Line line2 = new Line(newStart2, newEnd2);

        // 误差精度
        double epslion = 1e-6f;
        // 两条直线的交点（如果存在的话）
        Point intersection;

        if (line1.k == Integer.MAX_VALUE || line2.k == Integer.MAX_VALUE) {
            // 情况 1：（特殊情况）两条直线有一条斜率为正无穷
            if (line1.k == Integer.MAX_VALUE && line2.k == Integer.MAX_VALUE) {
                // 这里 b 不是截距的意思，而是表示 x = a 这条线段
                if (Math.abs(line1.b - line2.b) <= epslion && isBetween(newStart1, newStart2, newEnd1)) {
                    return new double[] {newStart2.x, newStart2.y};
                } else {
                    return new double[0];
                }
            }

            if (line1.k == Integer.MAX_VALUE) {
                intersection = new Point(line1.b, line1.b * line2.k + line2.b);
            } else {
                intersection = new Point(line2.b, line2.b * line1.k + line1.b);
            }
        } else if (Math.abs(line1.k - line2.k) <= epslion) {
            // 情况 2：（特殊情况）斜率相等的情况下，如果在 y 轴上的截距相等，就表示两条直线重合
            if (Math.abs(line1.b - line2.b) <= epslion && isBetween(newStart1, newStart2, newEnd1)) {
                return new double[] {newStart2.x, newStart2.y};
            }
            return new double[0];
        } else {
            // 情况 3：（一般情况）使用公式计算交点的坐标
            double x = (line2.b - line1.b) / (line1.k - line2.k);
            double y = x * line1.k + line1.b;

            intersection = new Point(x, y);
        }

        // 检测所在直线的交点是否在两条线段的横纵坐标范围之内
        if (isBetween(newStart1, intersection, newEnd1) && isBetween(newStart2, intersection, newEnd2)) {
            return new double[] {intersection.x, intersection.y};
        }
        return new double[0];
    }

    /**
     * 总是保证线段端点是从左向右发射；总是保证第一个线段的左端点比第二个线段的左端点靠左。
     * 便于后面计算。
     * @param start1_
     * @param end1_
     * @param start2_
     * @param end2_
     * @return
     */
    private Point[] checkAndConvertIntoPoint(int[] start1_, int[] end1_, int[] start2_, int[] end2_) {
        // 封装成 Point 类，将 int 转换成 double 类型，以便于计算
        Point start1 = new Point(start1_[0], start1_[1]);
        Point end1 = new Point(end1_[0], end1_[1]);
        Point start2 = new Point(start2_[0], start2_[1]);
        Point end2 = new Point(end2_[0], end2_[1]);

        // 参数校验：保证横坐标符合约定
        // 对于单条线段而言，起点坐标总是横坐标较小的那一个。横坐标相同则取纵坐标较小的
        if (start1.x > end1.x || (start1.x == end1.x && start1.y > end1.y)) {
            swap(start1, end1);
        }
        if (start2.x > end2.x || (start2.x == end2.x && start2.y > end2.y)) {
            swap(start2, end2);
        }

        // 对于两条线段而言，线段 1 的横坐标小于等于线段 2 的横坐标。横坐标相同取纵坐标较小的
        if (start1.x > start2.x || (start1.x == start2.x && start1.y > start2.y)) {
            // 两条线段交换
            swap(start1, start2);
            swap(end1, end2);
        }
        return new Point[] {start1, end1, start2, end2};
    }

    /**
     * 判断数值是否在给定的区间内
     * @param start
     * @param middle
     * @param end
     * @return
     */
    private boolean isBetween(double start, double middle, double end) {
        if (start > end) {
            // 逆序
            return end <= middle && middle <= start;
        } else {
            // 顺序
            return start <= middle && middle <= end;
        }
    }

    /**
     * 判断点是否在给定点的范围内。
     * @param start
     * @param middle
     * @param end
     * @return
     */
    private boolean isBetween(Point start, Point middle, Point end) {
        return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y);
    }

    /**
     * 交换两个点坐标的数值
     *
     * @param point1
     * @param point2
     */
    private void swap(Point point1, Point point2) {
        double tempX = point1.x;
        double tempY = point1.y;

        point1.x = point2.x;
        point1.y = point2.y;

        point2.x = tempX;
        point2.y = tempY;
    }

    private class Line {

        /**
         * 斜率
         */
        private double k;

        /**
         * 在 y 轴上的截距
         */
        private double b;

        public Line(Point start, Point end) {
            double deltaY = end.y - start.y;
            double deltaX = end.x - start.x;

            // 注意：当 deltaX = 0 的时候，设置斜率为正无穷
            if (deltaX == 0) {
                k = Integer.MAX_VALUE;
                // 此时截距为直线在 x 轴上的截距，这里是特殊的做法
                b = end.x;
            } else {
                k = deltaY / deltaX;
                b = end.y - k * end.x;
            }
        }
    }

    /**
     * 将输入封装成 Point，以便把 int 类型转换成 double 类型，便于计算
     */
    private class Point {

        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
