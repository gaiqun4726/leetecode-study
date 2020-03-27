package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/3/27
 *
 * 总结
 *
 * 依旧是回溯法遍历所有可能解。leetcode在判断回溯问题时有bug，总是出现奇葩的结果。
 */
public class M120_Triangle_Min_Path {

    public static void main(String[] args) {
        M120_Triangle_Min_Path solution = new M120_Triangle_Min_Path();
        List<List<Integer>>  list = new ArrayList<>();
        list.add(Arrays.asList(1));
        list.add(Arrays.asList(2,3));
        list.add(Arrays.asList(6,5,7));
        list.add(Arrays.asList(4,1,8,3));
        System.out.println(solution.minimumTotal(list));
    }

    private static LinkedList<Integer> path = new LinkedList<>();
    private static Integer minPath = Integer.MAX_VALUE;

    public int minimumTotal(List<List<Integer>> triangle) {
        backtrace(triangle, 0, 0);
        return minPath;
    }

    private void backtrace(List<List<Integer>> triangle, int level, int index) {
        int levels = triangle.size();
        if (level > levels - 1 || index < 0 || index > triangle.get(level).size() - 1) {
            return;
        }
        for (int i = 0; i <= 1; i++) {
            path.addLast(triangle.get(level).get(index));
            if (level == levels - 1) {
                minPath = Math.min(minPath, path.stream().mapToInt(Integer::intValue).sum());
            } else if (index + i >= 0 && index + i <= triangle.get(level).size() - 1) {
                backtrace(triangle, level + 1, index + i);
            }
            path.removeLast();
        }
    }
}
