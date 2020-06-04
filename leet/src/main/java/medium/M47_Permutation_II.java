package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author gaiqun
 * @date 2020/5/31
 *
 * 总结
 *
 * 求有重复数字的全排列，要去重。
 *
 * 如果是求出全排列再去重，比较困难，需要重写HashSet的hashCode方法。
 * 直接在回溯时去重。把每个数字出现的次数存到map里，从可选集合中选择时，从map里选并把出现次数减少。
 */
public class M47_Permutation_II {

    List<List<Integer>> result = new ArrayList<>();
    int N;

    public List<List<Integer>> permuteUnique(int[] nums) {
        N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        LinkedList<Integer> path = new LinkedList<>();
        backtrace(path, map);
        return result;
    }

    private void backtrace(LinkedList<Integer> path, Map<Integer, Integer> choices) {
        if (path.size() == N) {
            result.add(new ArrayList<>(path));
        }
        for (int key : choices.keySet()) {
            if (choices.get(key) == 0) {
                continue;
            }
            path.addLast(key);
            Map<Integer, Integer> newChoices = new HashMap<>(choices);
            newChoices.put(key, newChoices.get(key) - 1);
            backtrace(path, newChoices);
            path.removeLast();
        }
    }
}
