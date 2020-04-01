package medium;

import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/4/1
 *
 * 总结
 *
 * 这道题开始很难理解，不理解有效括号的深度怎么求，不理解如何构建有效括号，不理解怎么构建深度最小的两个有效括号。
 * 通过看题解理解了。有效括号的深度，就是用栈进行括号匹配时，栈的最大深度。
 * 构建最大深度最小的两个有效括号，即让两个有效括号的深度尽量相近，即尽量平分两个有效括号。
 * 当栈中累积左括号时可能产生最大深度，让连续的左括号平分到两个有效括号里，则可以得到深度最小的两个有效括号。
 * 划分的方法就是，根据深度的奇偶分配左括号。
 * 使用栈来模拟括号匹配过程，并同时计算括号深度，借此分配括号给两个有效括号。
 */
public class M1111_Feasible_Brackets_Depth {

    /**
     * 使用栈模拟括号匹配过程
     * @param seq
     * @return
     */
    public int[] maxDepthAfterSplit(String seq) {
        char[] chars = seq.toCharArray();
        int len = chars.length;
        int[] res = new int[len];
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '(') {
                stack.push('(');
                // 栈的深度按奇偶划分有效括号
                res[i] = stack.size() % 2;
            } else {
                // 如果出现右括号，右括号需要和左括号划到一组。
                // 弹出左括号后，如果右来一个左括号，此时新的左括号和原来的左括号深度相同。
                // 根据括号连接的定义，不会增加括号的深度，因此划到同一组是没问题的。
                res[i] = stack.size() % 2;
                stack.pop();
            }
        }
        return res;
    }

    /**
     * 由栈的模拟过程看出，我们并不需要知道栈里存的是什么，只需要知道栈的深度即可。
     * 使用一个标识栈深度的遍历depth，有左括号则增加，有右括号则减少。
     * @param seq
     * @return
     */
    public int[] maxDepthAfterSplit2(String seq) {
        char[] chars = seq.toCharArray();
        int len = chars.length;
        int[] res = new int[len];
        int depth = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] == '(') {
                depth++;
                // 栈的深度按奇偶划分有效括号
                res[i] = depth % 2;
            } else {
                // 如果出现右括号，右括号需要和左括号划到一组。
                // 弹出左括号后，如果右来一个左括号，此时新的左括号和原来的左括号深度相同。
                // 根据括号连接的定义，不会增加括号的深度，因此划到同一组是没问题的。
                res[i] = depth % 2;
                depth--;
            }
        }
        return res;
    }
}
