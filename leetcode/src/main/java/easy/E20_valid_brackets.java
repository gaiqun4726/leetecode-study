package easy;

import java.util.Stack;

public class E20_valid_brackets {
    public static void main(String[] args) {
        System.out.println(new E20_valid_brackets().isValid("["));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        if (s == null || s.equals(""))
            return true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if (stack.isEmpty())  //由右括号开始的情形
                return false;
            else {
                char top = stack.peek();
                if ((top == '(' && c == ')') || (top == '[' && c == ']') || (top == '{' && c == '}'))
                    stack.pop();
                else
                    return false;
            }
        }
        return stack.isEmpty(); //只有一个左括号的情形
    }
}
