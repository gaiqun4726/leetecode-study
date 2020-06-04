package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaiqun
 * @date 2020/4/9
 *
 * 总结
 *
 * 回溯法求解。回溯法中递归函数里需要遍历解的所有可能，但并不是说必须用循环。对于不可用循环的，列举所有情形是一样的
 *
 * 思路是，如果剩余的右括号多于剩余的左括号，则可以继续向部分解里添加左、右括号。否则不合法，减掉分支。
 *
 * 剪枝的思想就是，在回溯中，部分情况下直接return，不再走后面的递归方法。
 */
public class M22_Gen_Parenthesis {

    //public static void main(String[] args) {
    //    M22_Gen_Parenthesis solution = new M22_Gen_Parenthesis();
    //    System.out.println(solution.generateParenthesis(4));
    //}

    public List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtrace("", n, n);
        return result;
    }

    public void backtrace(String sub, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(sub);
            return;
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            backtrace(sub + "(", left - 1, right);
        }
        backtrace(sub + ")", left, right - 1);
    }

    public static void main(String[] args) {
        M22_Gen_Parenthesis solution = new M22_Gen_Parenthesis();
        List<String> result = solution.generateParenthesis2(3);
        for (String s : result) {
            System.out.println(s);
        }
    }

    List<String> result2 = new ArrayList<>();

    public List<String> generateParenthesis2(int n) {
        StringBuilder sb = new StringBuilder();
        backtrace2(sb, n, n);
        return result2;
    }

    private void backtrace2(StringBuilder sb, int left, int right) {
        if (left == 0 && right == 0) {
            result2.add(sb.toString());
            return;
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            sb.append("(");
            backtrace2(sb, left - 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right > 0) {
            sb.append(")");
            backtrace2(sb, left, right - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
