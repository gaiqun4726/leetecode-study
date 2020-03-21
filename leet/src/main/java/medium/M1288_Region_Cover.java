package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/3/12
 *
 * 总结
 *
 * 暴力求解的方法，是两重循环比较两两区间，时间复杂度为O(n^2)
 *
 * 想想能不能通过排序，把时间复杂度降低为O(nlogn)
 *
 * 按左端点升序排列，右端点降序排列。这样如果右端点不能够大于之前右端点的最大值，则一定会被覆盖。
 *
 * 注意考虑左端点重叠的情形，这时必须用右端点作为第二排序条件降序排列，否则上面的条件难以满足。
 *
 * 在遍历的时候，经常需要找到一个表征之前遍历过元素的特征值，后续不再需要和前面的元素再次比较，只要和
 * 特征值比较就可以了。这个值经常是最大最小值，在最大回撤问题里也有类似的特征值。
 */
public class M1288_Region_Cover {

    public int removeCoveredIntervals(int[][] intervals) {
        if (intervals.length == 0 || intervals[0].length != 2) {
            return 0;
        }
        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            intervalList.add(new Interval(interval[0], interval[1]));
        }
        // 按左端点升序，右端点降序排列
        intervalList.sort((o1, o2) -> {
            // 返回左减右的结果，是升序排列
            int res1 = o1.left - o2.left;
            if (res1 != 0) {
                return res1;
            }
            // 返回右减左的结果，是降序排列
            return o2.right - o1.right;
        });
        // 使用右端点最大值表征曾经遍历过的右端点的特征
        int maxRight = Integer.MIN_VALUE;
        int count = intervalList.size();
        for (Interval interval : intervalList) {
            // 大于之前的最大右端点则不可能被覆盖；否则，由于左端点比之前的任一个左端点大，右端点又小，一点会被覆盖。
            if (interval.right > maxRight) {
                maxRight = interval.right;
            } else {
                count--;
            }
        }
        return count;
    }

    static class Interval {

        private int left;
        private int right;

        Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
