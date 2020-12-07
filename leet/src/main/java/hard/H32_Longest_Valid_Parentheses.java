package hard;

import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/5/23
 *
 * 求最长有效括号。
 * 这题貌似没法用动态规划求解，因为没有相同子结构
 *
 * 解法1：暴力求解。对于每一个位置，计算以该位置为起始的最长有效字符串的长度。
 * 判断一个字符串是否是有效括号，可以用栈来匹配。也可以记录左括号和右括号的个数，遍历完成后左右括号相同则有效。
 * 本题使用记录个数的方法。时间复杂度为O(n^2)，空间复杂度O(1)。
 */
public class H32_Longest_Valid_Parentheses {

    public int longestValidParentheses(String s) {
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            len = Math.max(len, validLength(s, i));
        }
        return len;
    }

    private int validLength(String s, int index) {
        int leftCount = 0;
        int rightCount = 0;
        int maxLen = 0;
        do {
            char c = s.charAt(index);
            if (c == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            index++;
            // 边遍历，边计算最长有效括号长度。
            if (leftCount == rightCount) {
                maxLen = Math.max(maxLen, 2 * leftCount);
            }
        } while (leftCount >= rightCount && index < s.length());
        return maxLen;
    }

    public int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty() || stack.peek() == -1) {
                    stack.push(-1);
                } else {
                    int len = i - stack.pop() + 1;
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen;
    }
}
