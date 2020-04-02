package easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2020/4/2
 *
 * 总结
 *
 * 用两个队列实现。可以使用一个变量专门存储top，这样peek操作可以更快
 */
public class E225_Use_Queue_For_Stack {

    static class MyStack {

        private Queue<Integer> q1;
        private Queue<Integer> q2;
        private boolean tag;

        /** Initialize your data structure here. */
        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
            tag = true;
        }

        /** Push element x onto stack. */
        public void push(int x) {
            if (tag) {
                q1.offer(x);
            } else {
                q2.offer(x);
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int val = -1;
            if (tag) {
                while (q1.size() > 1) {
                    q2.offer(q1.poll());
                }
                val = q1.poll();
            } else {
                while (q2.size() > 1) {
                    q1.offer(q2.poll());
                }
                val = q2.poll();
            }
            tag = !tag;
            return val;
        }

        /** Get the top element. */
        public int top() {
            int val = -1;
            if (tag) {
                while (q1.size() > 1) {
                    q2.offer(q1.poll());
                }
                val = q1.peek();
            } else {
                while (q2.size() > 1) {
                    q1.offer(q2.poll());
                }
                val = q2.peek();
            }
            return val;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }
}
