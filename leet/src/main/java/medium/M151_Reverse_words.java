package medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 */
public class M151_Reverse_words {

    public static void main(String[] args) {
        String s = "  hello world!  ";
        M151_Reverse_words solution = new M151_Reverse_words();
        System.out.println(solution.reverseWords2(s));
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while (i < s.length() && s.charAt(i) != ' ') {
                sb.append(s.charAt(i++));
            }
            stack.push(sb.toString());
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
            res.append(" ");
        }
        String str = res.toString();
        return str.isEmpty() ? "" : str.substring(0, str.length() - 1);
    }

    public String reverseWords2(String s) {
        if (s.isEmpty()) {
            return s;
        }
        Stack<String> stack = new Stack<>();
        int start = 0;
        int end = 0;
        int len = s.length();
        while (start < len && end < len) {
            while (start < len && s.charAt(start) == ' ') {
                start++;
            }
            end = start;
            while (end < len && s.charAt(end) != ' ') {
                end++;
            }
            if (start < len) {
                stack.push(s.substring(start, end));
            }
            start = end;
        }
        List<String> words = new ArrayList<>();
        while (!stack.isEmpty()) {
            words.add(stack.pop());
        }
        return String.join(" ", words);
    }
}
