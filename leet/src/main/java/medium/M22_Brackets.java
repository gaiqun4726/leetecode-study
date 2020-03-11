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
}
