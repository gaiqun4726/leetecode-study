package hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/5/31
 *
 * 总结
 *
 * 回溯法，遍历决策树。
 */
public class H51_N_Queens {

    int N;
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        N = n;
        LinkedList<Integer> path = new LinkedList<>();
        backtrace(path, n);
        return result;
    }

    private void backtrace(LinkedList<Integer> path, int choices) {
        if (path.size() == N) {
            result.add(build(path));
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!valid(path, i)) {
                continue;
            }
            path.addLast(i);
            backtrace(path, choices - 1);
            path.removeLast();
        }
    }

    private boolean valid(List<Integer> path, int pos) {
        for (int p : path) {
            if (p == pos) {
                return false;
            }
        }
        int size = path.size();
        int left = pos, right = pos;
        for (int i = size - 1; i >= 0; i--) {
            left--;
            right++;
            if (left == path.get(i) || right == path.get(i)) {
                return false;
            }
        }
        return true;
    }

    List<String> build(List<Integer> solution) {
        List<String> res = new ArrayList<>();
        for (int s : solution) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < N; i++) {
                if (i == s) {
                    tmp.append("Q");
                } else {
                    tmp.append(".");
                }
            }
            res.add(tmp.toString());
        }
        return res;
    }
}
