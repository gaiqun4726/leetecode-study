package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/4/8
 *
 * 总结
 *
 * 这道计算器的题，只有 '+' '-' '*' '/' ' '几种运算符，和非负整数
 *
 * 思路1：中缀转后缀表达式，然后求逆波兰式的值。
 * 本题的难点在于如何从字符串中提取数字和运算符。
 * 可否通过正则式从字符串中提取数字和运算符？
 *
 * 思路2：第一轮计算乘除，第二轮计算加减。但是对于包含括号的表达式，这种方法就不能用了
 */
public class P1626_Calculator {

    public static void main(String[] args) {
        P1626_Calculator solution = new P1626_Calculator();
        String expression = " 3 + 5 /2 ";
        System.out.println(solution.calculate(expression));
    }

    public Map<String, Integer> map = new HashMap<>();

    public void init() {
        map.put("+", 0);
        map.put("-", 0);
        map.put("*", 1);
        map.put("/", 1);
    }

    public int calculate(String s) {
        init();
        int start = 0, end = 0;
        List<String> elements = new ArrayList<>();
        Stack<String> operators = new Stack<>();
        // 利用正则式优化下字符串的空格。如果不用Matcher类，字符串支持正则式的方法只有matches, replace, split三种
        s = s.replaceAll("\\s+", "");
        int len = s.length();
        while (start < len && end < len) {
            end = start;
            if (!Character.isDigit(s.charAt(end))) {
                end++;
            } else {
                // 连续的数字构成整数。连续数字直接没有空格了
                while (end < len && Character.isDigit(s.charAt(end))) {
                    end++;
                }
            }
            String element = s.substring(start, end);
            start = end;
            // 中缀转后缀的规则：数字直接打印，运算符优先级高则入栈，否则站内元素出栈
            if (map.containsKey(element)) {
                while (!operators.isEmpty() && map.get(operators.peek()) >= map.get(element)) {
                    elements.add(operators.pop());
                }
                operators.push(element);
            } else {
                elements.add(element);
            }
        }
        while (!operators.isEmpty()) {
            elements.add(operators.pop());
        }
        return compute(elements);
    }

    public int compute(List<String> elements) {
        // 求逆波兰式。从左到右，遇到运算符就计算
        Stack<Integer> stack = new Stack<>();
        for (String s : elements) {
            int a, b;
            switch (s) {
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
                    stack.push(Integer.parseInt(s));
                    break;
            }
        }
        return stack.peek();
    }
}
