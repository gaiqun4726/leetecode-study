package hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/4/9
 *
 * 总结
 *
 * 本题支持的运算符是 + - （ ） " "
 *
 * 计算器题目的套路：
 * 1. 写计算逆波兰式的函数evalRpn；
 * 2. 把给定字符串格式化，用正则式+replaceAll去掉空格
 * 3. 把给定字符串由中缀式转为逆波兰式
 * 4. 用evalRpn函数求解表达式
 */
public class H224_Basic_Calculatorr {

    public static void main(String[] args) {
        H224_Basic_Calculatorr solution = new H224_Basic_Calculatorr();
        String expression = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(solution.calculate3(expression));
    }

    public int calculate(String s) {
        // 栈存储运算符
        Stack<String> stack = new Stack<>();
        // 队列存储逆波兰式
        List<String> tokens = new ArrayList<>();
        // 格式化去掉空格
        s = s.replaceAll("\\s+", "");
        int start = 0, end = 0;
        int len = s.length();
        while (start < len) {
            // 获取数字
            if (Character.isDigit(s.charAt(end))) {
                while (end < len && Character.isDigit(s.charAt(end))) {
                    end++;
                }
                tokens.add(s.substring(start, end));
            } else {
                // 获取操作符
                end++;
                String operator = s.substring(start, end);
                switch (operator) {
                    case ")":
                        // 遇到右括号则弹出栈中的所有操作符，左括号也弹出但不存储
                        while (!"(".equals(stack.peek())) {
                            tokens.add(stack.pop());
                        }
                        stack.pop();
                        break;
                    case "(":
                        // 遇到左括号直接压栈
                        stack.push(operator);
                        break;
                    default:
                        // 遇到运算符，应该按照优先级决定是否入栈或弹出
                        // 本题只有+-运算，所以如果栈顶是运算符则弹出
                        if (!stack.isEmpty() && !"(".equals(stack.peek())) {
                            tokens.add(stack.pop());
                        }
                        // 弹出栈里元素后，当前操作符需要继续入栈
                        stack.push(operator);
                        break;
                }
            }
            start = end;
        }
        // 注意最后需要把所有栈里的操作符都弹出
        while (!stack.isEmpty()) {
            tokens.add(stack.pop());
        }
        return evalRpn(tokens);
    }

    /**
     * 从左到右遍历逆波兰式，遇到数字就压栈；遇到运算符则计算栈顶两个数字，然后再压栈
     * 重复上述过程，最后栈顶元素就是表达式的值
     *
     * @param tokens
     * @return
     */
    public int evalRpn(List<String> tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            int a, b;
            switch (token) {
                case "+":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a + b);
                    break;
                case "-":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a - b);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }

    public int calculate2(String s) {
        return calc(convert(s));
    }

    private List<String> convert(String s) {
        Stack<String> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            while (right < s.length() && s.charAt(right) == ' ') {
                right++;
            }
            if (right >= s.length()) {
                break;
            }
            left = right;
            if (s.charAt(right) == '(') {
                stack.push(s.substring(right, right + 1));
                right++;
            } else if (s.charAt(right) == '+' || s.charAt(right) == '-') {
                if (!stack.isEmpty()) {
                    result.add(stack.pop());
                }
                stack.push(s.substring(right, right + 1));
                right++;
            } else if (s.charAt(right) == ')') {
                while (!stack.peek().equals("(")) {
                    result.add(stack.pop());
                    stack.pop();
                    right++;
                }
            } else if (Character.isDigit(s.charAt(right))) {
                while (right < s.length() && Character.isDigit(s.charAt(right))) {
                    right++;
                }
                result.add(s.substring(left, right));
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private int calc(List<String> ops) {
        int index = 1;
        int a = Integer.parseInt(ops.get(0));
        while (index < ops.size()) {
            int b = Integer.parseInt(ops.get(index++));
            String op = ops.get(index++);
            if (op.equals("+")) {
                a += b;
            } else {
                a -= b;
            }
        }
        return a;
    }

    public int calculate3(String s) {
        return compute(convertToTokens(s));
    }

    private List<String> convertToTokens(String s) {
        Stack<Character> ops = new Stack<>();
        List<String> tokens = new ArrayList<>();
        s = s.trim();
        while (s.length() != 0) {
            int pos = 0;
            char c = s.charAt(pos);
            switch (c) {
                case '+':
                case '-':
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        tokens.add(String.valueOf(ops.pop()));
                    }
                    ops.push(c);
                    s = s.substring(1).trim();
                    break;
                case '(':
                    ops.push(c);
                    s = s.substring(1).trim();
                    break;
                case ')':
                    while (ops.peek() != '(') {
                        tokens.add(String.valueOf(ops.pop()));
                    }
                    ops.pop();
                    s = s.substring(1).trim();
                    break;
                default:
                    int val = 0;
                    while (pos < s.length()) {
                        c = s.charAt(pos++);
                        if (Character.isDigit(c)) {
                            val = 10 * val + c - '0';
                        } else {
                            break;
                        }
                    }
                    tokens.add(String.valueOf(val));
                    s = s.substring(pos-1).trim();
                    break;
            }
        }
        while(!ops.isEmpty()) {
            tokens.add(String.valueOf(ops.pop()));
        }
        return tokens;
    }

    private int compute(List<String> tokens) {
        Stack<String> nums = new Stack<>();
        for (String token : tokens) {
            int left, right;
            switch (token) {
                case "+":
                    right = Integer.parseInt(nums.pop());
                    left = Integer.parseInt(nums.pop());
                    nums.push(String.valueOf(left + right));
                    break;
                case "-":
                    right = Integer.parseInt(nums.pop());
                    left = Integer.parseInt(nums.pop());
                    nums.push(String.valueOf(left - right));
                    break;
                default:
                    nums.push(token);
                    break;
            }
        }
        return Integer.parseInt(nums.peek());
    }
}
