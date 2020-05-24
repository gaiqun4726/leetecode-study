package medium;

import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/4/10
 *
 * 逆波兰式求解。
 *
 * 思路1：参考博客里求逆波兰式的解法，使用栈，从右向左把字符压栈。遇到连续的数字，则求表达式值，并把求得的值入栈。然后重复上述操作。
 * 可以求解问题，但是需要弹出栈里的三个元素才能计算，代码不够优雅。
 *
 * 思路2：使用栈，从左向右遍历。如果遇到数字则压栈；如果遇到运算符，则栈顶两个数字出栈计算，然后压栈。最后栈里剩下的就是表达式值。
 * 只要是合法的逆波兰式（后缀表达式），这种方法就能求值，而且代码简洁。
 * 一般逆波兰式是由中缀表达式转换过来的，可以保证逆波兰式是合法的。所以记住方法2。
 */
public class M150_Calc_Reversed_Potland_Notion {

    public static void main(String[] args) {
        M150_Calc_Reversed_Potland_Notion solution = new M150_Calc_Reversed_Potland_Notion();
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(solution.evalRPN(tokens));
    }

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int len = tokens.length;
        for (int i = len - 1; i >= 0; i--) {
            stack.push(tokens[i]);
            while (stack.size() >= 3) {
                String a = stack.pop();
                String b = stack.pop();
                if (isNumber(a) && isNumber(b)) {
                    int val = calc(Integer.parseInt(a), Integer.parseInt(b), stack.pop().charAt(0));
                    stack.push(String.valueOf(val));
                } else {
                    stack.push(b);
                    stack.push(a);
                    break;
                }
            }
        }
        return Integer.parseInt(stack.peek());
    }

    public int calc(int a, int b, char operator) {
        if (operator == '+') {
            return a + b;
        } else if (operator == '-') {
            return a - b;
        } else if (operator == '*') {
            return a * b;
        } else {
            return a / b;
        }
    }

    public boolean isNumber(String token) {
        boolean isNumber = false;
        try {
            Integer.parseInt(token);
            isNumber = true;
        } catch (Exception ex) {

        }
        return isNumber;
    }

    /**
     * 推荐解法，使用栈，从左到右遍历。逆波兰式里只有四则运算符，没有括号
     * @param tokens
     * @return
     */
    public int evalRPN2(String[] tokens) {
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
                case "*":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a * b);
                    break;
                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a / b);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.peek();
    }
}
