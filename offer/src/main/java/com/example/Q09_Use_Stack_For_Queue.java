package com.example;

import java.util.Stack;

/**
 * @author gaiqun
 * @date 2020/4/14
 */
public class Q09_Use_Stack_For_Queue {

    static class CQueue {

        private Stack<Integer> inStack;
        private Stack<Integer> outStack;

        public CQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        public void appendTail(int value) {
            while (!outStack.isEmpty()) {
                inStack.push(outStack.pop());
            }
            inStack.push(value);
        }

        public int deleteHead() {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
            return outStack.isEmpty() ? -1 : outStack.pop();
        }
    }
}
