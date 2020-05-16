package easy;

import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/5/12
 *
 * 总结
 *
 * 两个栈，一个正常使用；一个保存当前最小元素。
 * 新入栈的元素比最小栈的栈顶更小，则在最小栈中压入新元素；否则在最小栈中再压一遍栈顶元素。
 */
public class E155_Min_Stack {

    class MinStack {

        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || minStack.peek() >= x) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
