package medium;

import java.util.Stack;

/**
 * @author gaiqun
 * @date 2023/9/9
 */
public class M443_Compress_Chars {

    public static void main(String[] args) {
        M443_Compress_Chars solution = new M443_Compress_Chars();
//        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
//        char[] chars = {'a'};
        char[] chars = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        System.out.println(solution.compress(chars));
    }

    public int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }
        int cnt = 1;
        int pos = 1;
        int cur = 1;
        char c = chars[0];
        while (cur < chars.length) {
            while (cur < chars.length && chars[cur] == c) {
                cur++;
                cnt++;
            }
            if (cur == chars.length) {
                break;
            }
            c = chars[cur++];

            if (cnt != 1) {
                Stack<Character> stack = new Stack<>();
                while (cnt != 0) {
                    stack.push((char) (cnt % 10 + '0'));
                    cnt /= 10;
                }
                while (!stack.isEmpty()) {
                    chars[pos++] = stack.pop();
                }
            }
            cnt = 1;
            chars[pos++] = c;
        }
        if (cnt != 1) {
            Stack<Character> stack = new Stack<>();
            while (cnt != 0) {
                stack.push((char) (cnt % 10 + '0'));
                cnt /= 10;
            }
            while (!stack.isEmpty()) {
                chars[pos++] = stack.pop();
            }
        }

        return pos;
    }
}
