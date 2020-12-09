package easy;

import java.util.Stack;

public class E20_valid_brackets {

    public static void main(String[] args) {
        //String s = "";
        //String s = "{([]())}";
        //String s = "{(][())}";
        String s = "{(][())";
        System.out.println(new E20_valid_brackets().isValid2(s));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        if (s == null || s.equals("")) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (stack.isEmpty())  //由右括号开始的情形
            {
                return false;
            } else {
                char top = stack.peek();
                if ((top == '(' && c == ')') || (top == '[' && c == ']') || (top == '{' && c == '}')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty(); //只有一个左括号的情形
    }

    public boolean isValid2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            Character c = s.charAt(i++);
            switch (c) {
                case '{':
                case '[':
                case '(':
                    stack.push(c);
                    break;
                case '}':
                    if (stack.peek() != '{') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                case ']':
                    if (stack.peek() != '[') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                case ')':
                    if (stack.peek() != '(') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}
