package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/6/2
 *
 * 总结
 *
 * 求组合，利用回溯法。为避免得到重复结果，每次决策从大于最后一个元素的元素集合里选择下一个要添加的元素。
 */
public class M77_Combination {

    List<List<Integer>> result = new ArrayList<>();
    int N, K;

    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || k > n) {
            result.add(new ArrayList<>());
            return result;
        }
        N = n;
        K = k;
        LinkedList<Integer> path = new LinkedList<>();
        backtrace(path);
        return result;
    }

    private void backtrace(LinkedList<Integer> path) {
        if (path.size() == K) {
            result.add(new ArrayList<>(path));
            return;
        }
        int last = path.isEmpty() ? 0 : path.getLast();
        for (int i = last + 1; i <= N; i++) {
            path.addLast(i);
            backtrace(path);
            path.removeLast();
        }
    }
}
