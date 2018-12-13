package medium;

import java.util.Stack;

public class M151_Reverse_words {
    public static void main(String[] args) {
        String str = " ";
        System.out.println(new M151_Reverse_words().reverseWords(str));
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return s;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                continue;
            StringBuilder sb = new StringBuilder();
            while (i < s.length() && s.charAt(i) != ' ')
                sb.append(s.charAt(i++));
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
}
