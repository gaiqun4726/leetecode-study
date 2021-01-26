package kuaishou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaiqun
 * @date 2021/1/25
 * 本题不能对数组排序。或者构建对象再排序。
 * 使用哈希表，空间换时间
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        // write code here
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= numbers.length; i++) {
            int key = numbers[i - 1];
            List<Integer> list = map.getOrDefault(key, new ArrayList<>());
            list.add(i);
            map.put(key, list);
        }
        int left = 0, right = 0;
        for (int i : map.keySet()) {
            List<Integer> list = map.get(i);
            int another = target - i;
            // 数组可能存在重复元素
            if (i == another) {
                if (list.size() > 1) {
                    left = list.get(0);
                    right = list.get(1);
                    break;
                }
            } else if (map.containsKey(another)) {
                left = map.get(i).get(0);
                right = map.get(another).get(0);
                break;
            }
        }
        int[] res = new int[2];
        res[0] = Math.min(left, right);
        res[1] = Math.max(left, right);
        return res;
    }
}
