package medium;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gaiqun
 * @date 2020/5/7
 *
 * 总结
 *
 * 可以把时间转化为分钟，然后排序。注意首尾需要比较一下。
 */
public class M539_Min_Time_Diff {

    public int findMinDifference(List<String> timePoints) {
        timePoints.sort((o1, o2) -> {
            String[] times1 = o1.split(":");
            String[] times2 = o2.split(":");
            if (times1[0].equals(times2[0])) {
                return times1[1].compareTo(times2[1]);
            }
            return times1[0].compareTo(times2[0]);
        });
        timePoints.add(addOneDay(timePoints.get(0)));
        String prev = timePoints.get(0);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < timePoints.size(); i++) {
            minDiff = Math.min(minDiff, getDiff(prev, timePoints.get(i)));
            prev = timePoints.get(i);
        }
        return minDiff;
    }

    private int getDiff(String s1, String s2) {
        String[] times1 = s1.split(":");
        String[] times2 = s2.split(":");
        int minute1 = Integer.parseInt(times1[0]) * 60 + Integer.parseInt(times1[1]);
        int minute2 = Integer.parseInt(times2[0]) * 60 + Integer.parseInt(times2[1]);
        return Math.abs(minute2 - minute1);
    }

    private String addOneDay(String s) {
        String[] times = s.split(":");
        times[0] = String.valueOf(Integer.parseInt(times[0]) + 24);
        return String.join(":", times);
    }

    public int findMinDifference2(List<String> timePoints) {
        // 24小时最多有24*60分。总分钟数超过这个数，必有重复。此时最小分钟为0
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        List<Integer> minutes = timePoints.stream().map(this::convertToInt).collect(Collectors.toList());
        Collections.sort(minutes);
        // 添加尾元素
        minutes.add(minutes.get(0) + 24 * 60);
        int prev = minutes.get(0);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < minutes.size(); i++) {
            minDiff = Math.min(minDiff, minutes.get(i) - prev);
            prev = minutes.get(i);
        }
        return minDiff;
    }

    private int convertToInt(String s) {
        String[] times = s.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }
}
