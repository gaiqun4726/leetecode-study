package com.example;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author gaiqun
 * @date 2020/5/28
 */
public class WaitNotifyBasic {

    private static final int FULL = 10;
    private static Queue<Integer> queue = new PriorityQueue<>(FULL);

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }

    static class Consumer extends Thread {

        @Override
        public void run() {
            while(true) {
                synchronized (queue) {
                    if(queue.isEmpty()) {
                        try {
                            sleep(1000);
                            queue.wait();
                        } catch (InterruptedException e) {
                            queue.notify();
                        }
                    }
                    System.out.println("consume: " + queue.poll());
                    queue.notify();
                }
            }
        }
    }

    static class Producer extends Thread {

        @Override
        public void run() {
            int count = 0;
            while(true) {
                synchronized (queue) {
                    if(queue.size()==FULL) {
                        try {
                            sleep(1000);
                            queue.wait();
                        } catch (InterruptedException e) {
                            queue.notify();
                        }
                    }
                    queue.offer(count);
                    System.out.println("produce: " + count++);
                    queue.notify();
                }
            }
        }
    }
}
