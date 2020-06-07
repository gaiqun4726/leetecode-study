package medium;

import java.util.ArrayList;
import java.util.List;

public class M22_Brackets {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        traceBack(list, "", 0, 0, n);
        return list;
    }

    private void traceBack(List<String> list, String cur, int open, int close, int max) {
        if (cur.length() == 2 * max) {
            list.add(cur);
            return;
        }
        if (open < max) {
            traceBack(list, cur + "(", open + 1, close, max);
        }
        if (open > close) {
            traceBack(list, cur + ")", open, close + 1, max);
        }
    }

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis2(int n) {
        if (n <= 0) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        backtrace(sb, n, n);
        return result;
    }

    private void backtrace(StringBuilder sb, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(sb.toString());
            return;
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            sb.append("(");
            backtrace(sb, left - 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right > 0) {
            sb.append(")");
            backtrace(sb, left, right - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
