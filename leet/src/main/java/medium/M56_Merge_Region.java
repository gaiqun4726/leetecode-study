package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/3/23
 *
 * 总结
 *
 * 以下做法应该是最优解了，时间复杂度O(nlogn)。
 */
public class M56_Merge_Region {

    public int[][] merge(int[][] intervals) {
        // 如果不合法，传入什么返回什么
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return intervals;
        }
        // List里可以以数组为元素
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // 能合并则合并
            if (prev[1] >= intervals[i][0]) {
                // 左边一定是前边的元素小，右边的不一定是后边的元素大
                prev[1] = Math.max(prev[1], intervals[i][1]);
            } else {
                // 不能合并则输出之前合并的集合
                list.add(prev);
                prev = intervals[i];
            }
        }
        // 边界条件，最后一次合并需要在循环退出后加入
        list.add(prev);
        // list直接转array有问题，循环搞一下。
        int[][] array = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}